package om37;

import java.util.Observable;
import common.GameObject;
import static common.Global.*;

/**
 * Model of the game of pong (Client)
 */
public class C_PongModel extends Observable
{
  private GameObject ball   = new GameObject( W/2, H/2, BALL_SIZE, BALL_SIZE );
  private GameObject bats[] = new GameObject[2];
  private PlayerC player;

  public C_PongModel()
  {
    bats[0] = new GameObject(  60, H/2, BAT_WIDTH, BAT_HEIGHT);
    bats[1] = new GameObject(W-60, H/2, BAT_WIDTH, BAT_HEIGHT);
  }
  
  public void addPlayer(PlayerC newPlayer)
  {
	  player = newPlayer;
  }
  
  /**
   * Return the Game object representing the ball
   * @return the ball
   */
  public GameObject getBall()
  {
    return ball;
  }
  
  public void moveBat(String direction)
  {
	  player.moveBat(direction);
  }
  
  /**
   * Set a new Ball object
   * @param aBall - Ball to be set
   */
  public void setBall( GameObject aBall )
  {
    ball = aBall;
  }

  /**
   * Return the Game object representing the Bats for player
   * @return Array of two bats
   */
  public GameObject[] getBats()
  {
    return bats;
  }

  /**
   * Set the Bats used
   * @param theBats - Players Bat
   */
  public void setBats( GameObject[] theBats )
  {
    bats = theBats;
  }
  
  /**
   * Cause update of view of game
   */
  public void modelChanged()
  {
    setChanged(); notifyObservers();
  }
  
}
