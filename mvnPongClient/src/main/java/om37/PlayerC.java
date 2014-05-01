package om37;

import common.*;
import static common.Global.*;

import java.io.IOException;
import java.net.Socket;
/**
 * Individual player run as a separate thread to allow
 * updates immediately the bat is moved
 */
class PlayerC extends Thread
{
	private NetObjectReader reader;
	private NetObjectWriter writer;

	private C_PongModel model;
	/**
	 * Constructor
	 * @param model - model of the game
	 * @param s - Socket used to communicate with server
	 */
	public PlayerC( C_PongModel aModel, Socket s  )
	{
		model=aModel;
		// The player needs to know this to be able to work	  
		try 
		{
			reader = new NetObjectReader(s);
			writer = new NetObjectWriter(s);
		}
		catch (Exception e) 
		{
			DEBUG.trace("PlayerC constructor failed" );
		}
	}

	public NetObjectReader getReader()
	{
		return reader;
	}
	
	public C_PongModel getModel()
	{
		return model;
	}
	/**
	 * Get and update the model with the latest bat movement
	 * sent by the server
	 */
	public void run()                             // Execution
	{
		DEBUG.trace("Started player thread....");

		// Listen to network to get the latest state of the
		//  game from the server
		// Update model with this information, Redisplay model
		while(true)
		{

			String data = (String) reader.get();
			DEBUG.trace( "Client PLayer read %s", data);
			String[] newCoords = data.split(",");

			parseDataFromServer(newCoords);

			//Call to update observers
			model.modelChanged();
		}
	}

	public void parseDataFromServer(String[] newCoords) {
		//Decode string...
		/*
		index:	dataItem
			0:		ballX
			1:		ballY
			2:		bat0X
			3:		bat0Y
			4:		bat1X
			5:		bat1Y
		 */

		double newBallX=Double.parseDouble(newCoords[0]);
		double newBallY=Double.parseDouble(newCoords[1]);

		double batOneX=Double.parseDouble(newCoords[2]);
		double batOneY=Double.parseDouble(newCoords[3]);

		double batTwoX=Double.parseDouble(newCoords[4]);
		double batTwoY=Double.parseDouble(newCoords[5]);

		//Create dummy ball object with new coords 
		//GameObject newBall = model.getBall();
		GameObject newBall   = new GameObject( W/2, H/2, BALL_SIZE, BALL_SIZE );
		newBall.setX(newBallX);
		newBall.setY(newBallY);

		//Same for bats....
		//GameObject[] newBats = model.getBats();
		GameObject newBats[] = new GameObject[2];
		newBats[0] = new GameObject(  60, H/2, BAT_WIDTH, BAT_HEIGHT);
		newBats[1] = new GameObject(W-60, H/2, BAT_WIDTH, BAT_HEIGHT);

		newBats[0].setX(batOneX);
		newBats[0].setY(batOneY);

		newBats[1].setX(batTwoX);
		newBats[1].setY(batTwoY);

		//Send to model:
		model.setBall(newBall);
		model.setBat(0, newBats[0]);
        model.setBat(1, newBats[1]);
        model.setBats(newBats);
	}

	public void moveBat(String details)
	{
		writer.put(details);
	}

}
