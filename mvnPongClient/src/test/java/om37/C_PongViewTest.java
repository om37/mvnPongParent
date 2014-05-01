package om37;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import common.GameObject;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: Odie
 * Date: 30/04/14
 * Time: 21:59
 * To change this template use File | Settings | File Templates.
 */
public class C_PongViewTest
{
    /*
    Uses stubbing and spying to test and ensure that calcNewPositions was called exactly once
    by the call to C_PongView.update().

    mockModel was created to atomise the class under test and remove depency.

    Mockito also allows us to specify the return from a call to a mocked object
     */
    @Test
    public void testUpdateCallsToModel()
    {
        C_PongView viewUnderTest = Mockito.spy(new C_PongView());
        C_PongModel mockModel = Mockito.mock(C_PongModel.class);
        Mockito.when(mockModel.getBall()).thenReturn(new GameObject(10,10,50,50));

        viewUnderTest.update(mockModel,null);

        Mockito.verify(viewUnderTest, Mockito.times(1)).calcNewPositions();
    }
}
