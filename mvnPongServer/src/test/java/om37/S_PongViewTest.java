package om37;

import common.GameObject;
import common.NetObjectWriter;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Assert;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

import java.net.Socket;

/**
 * Unit test for simple App.
 */
public class S_PongViewTest
{
    public S_PongViewTest()
    {
    }

    @org.junit.Test
    public void updateTest()
    {
        NetObjectWriter mockWriterOne = Mockito.mock(NetObjectWriter.class);//mock writers to avoid dependencies
        NetObjectWriter mockWriterTwo = Mockito.mock(NetObjectWriter.class);
        S_PongModel mockModel = Mockito.mock(S_PongModel.class);//mock model for call to update()

        S_PongView viewUnderTest = Mockito.spy(new S_PongView(mockWriterOne,mockWriterTwo));//spy on our view

        Mockito.when(mockModel.getBall()).thenReturn(new GameObject(2,2,2,2));//setup return for getBall
        Mockito.when(mockModel.getBats()).                                    //And call to getBats
                thenReturn(new GameObject[]{
                    new GameObject(10, 10, 5, 5),
                    new GameObject(20, 20, 10, 10)}
        );

        ArgumentCaptor<Object> playerOneMessage = ArgumentCaptor.forClass(Object.class);
        ArgumentCaptor<Object> playerTwoMessage = ArgumentCaptor.forClass(Object.class);

        viewUnderTest.update(mockModel,null);//Run method

        Mockito.verify(mockWriterOne).put(playerOneMessage.capture());//Capture args to mock
        Mockito.verify(mockWriterTwo).put(playerTwoMessage.capture());

        Assert.assertEquals("2.0,2.0,10.0,10.0", playerOneMessage.getValue().toString());//And validate
        Assert.assertEquals("2.0,2.0,20.0,20.0", playerTwoMessage.getValue().toString());
    }

    public void anotherTest()
    {
        NetObjectWriter mockWriterOne = Mockito.mock(NetObjectWriter.class);//mock writers to avoid dependencies
        NetObjectWriter mockWriterTwo = Mockito.mock(NetObjectWriter.class);
        S_PongModel mockModel = Mockito.mock(S_PongModel.class);//mock model for call to update()

        S_PongView viewUnderTest = Mockito.spy(new S_PongView(mockWriterOne,mockWriterTwo));//spy on our view

        viewUnderTest.setWriters(mockWriterOne,mockWriterTwo);
        Assert.assertTrue(true);
    }

}
