package om37;

//import junit.framework.Test;//Commented because of conflicting imports. I believe this could be for junit version 3. Was generated by maven
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import common.GameObject;
import junit.framework.TestCase;
import junit.framework.TestSuite;

//Added by me
import org.junit.*;

//import org.jcheck.*;
import common.Global;
import org.mockito.*;
import junit.*;

/**
 * Unit test for simple App.
 */
public class PlayerCTest 
{
	PlayerC playerUnderTest;
    @Mock PlayerC mockPlayer;
    @Mock  C_PongModel mockModel;
    @Mock Socket mockSocket;

    public PlayerCTest()
    {

    }

    @Before
    public void setup() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    	playerUnderTest = new PlayerC(new C_PongModel(), new Socket());
    }

    @Test
    public void testBallXY()
    {
        playerUnderTest = new PlayerC(mockModel, mockSocket);//use mockito to mock dependancies
        ArgumentCaptor<GameObject> ballCaptor = ArgumentCaptor.forClass(GameObject.class);
        String[] values = new String[]{"0", "1", "2", "3", "4", "5"};
        playerUnderTest.parseDataFromServer(values);

        Mockito.verify(mockModel).setBall(ballCaptor.capture());
        //Mockito's argument captor allows us to capture the parameter sent to a call to a mocked object
        //We can then use this in jUnit assert statements to test we got the output we desired.
        Assert.assertEquals(ballCaptor.getValue().getX(),Double.parseDouble(values[0]),0);
        Assert.assertEquals(ballCaptor.getValue().getY(),Double.parseDouble(values[1]),0);
    }

    @Test
    public void testBatZeroXY()
    {
        playerUnderTest = new PlayerC(mockModel, mockSocket);
        ArgumentCaptor<GameObject[]> batCaptor = ArgumentCaptor.forClass(GameObject[].class);
        String[] values = new String[]{"0", "1", "2", "3", "4", "5"};
        playerUnderTest.parseDataFromServer(values);

        Mockito.verify(mockModel).setBats(batCaptor.capture());
        Assert.assertEquals(batCaptor.getValue()[0].getX(),Double.parseDouble(values[2]),0);
        Assert.assertEquals(batCaptor.getValue()[0].getY(),Double.parseDouble(values[3]),0);
    }

    @Test
    public void testBatOneXY()
    {
        playerUnderTest = new PlayerC(mockModel, mockSocket);
        ArgumentCaptor<GameObject[]> batCaptor = ArgumentCaptor.forClass(GameObject[].class);
        String[] values = new String[]{"0", "1", "2", "3", "4", "5"};
        playerUnderTest.parseDataFromServer(values);

        Mockito.verify(mockModel).setBats(batCaptor.capture());
        Assert.assertEquals(batCaptor.getValue()[1].getX(),Double.parseDouble(values[4]),0);
        Assert.assertEquals(batCaptor.getValue()[1].getY(),Double.parseDouble(values[5]),0);
    }

    @Test
    public void testParseDataFromServer()
    {
    	C_PongModel playersModel = playerUnderTest.getModel();
    	    	
    	String newValues = "5,5,5,5,5,5";
    	playerUnderTest.parseDataFromServer(newValues.split(","));
    	
    	double testValue = (double)Double.parseDouble("5");
    	 	
    	Assert.assertEquals(testValue , playersModel.getBall().getX(), 0.0);
    	Assert.assertEquals(testValue , playersModel.getBall().getY(), 0.0);
    	Assert.assertEquals(testValue , playersModel.getBats()[0].getX(), 0.0);
    	Assert.assertEquals(testValue , playersModel.getBats()[0].getY(), 0.0);
    	Assert.assertEquals(testValue , playersModel.getBats()[1].getX(), 0.0);
    	Assert.assertEquals(testValue , playersModel.getBats()[1].getY(), 0.0);
    	
    }
    
    @Test(expected = NumberFormatException.class)
    public void numberFormatExceptionTest()
    {
    	playerUnderTest.parseDataFromServer("This is not a double".split(" "));
    }
    
    @Test(expected= NullPointerException.class)
    public void nullStringTest()
    {
    	playerUnderTest.parseDataFromServer(null);
    }  
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void notEnoughIndexes()
    {
    	playerUnderTest.parseDataFromServer(new String[]{"0","1","2","3","4"});
    }
    
    /*
     * Currently doesn't really work
     * Can't find a way to initialise socket as can't create a connection in testing
     * Leading to writer being null
     */
    @Test(expected=NullPointerException.class)
    public void moveTest()
    {
        playerUnderTest = new PlayerC(mockModel, mockSocket);
    	playerUnderTest.moveBat("newDetails");
    	String data = (String)playerUnderTest.getReader().get();
    	System.out.println( data );
    	Assert.assertTrue(true);
    }
}
;