package om37;

//import junit.framework.Test;//Commented because of conflicting imports. I believe this could be for junit version 3. Was generated by maven

import common.GameObject;
import org.jcheck.annotations.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.net.Socket;
import org.jcheck.*;

/**
 * Unit test for simple App.
 */

@RunWith(org.jcheck.runners.JCheckRunner.class)
public class C_PongModelTest
{
    int count = 0;
    @Test
    @org.jcheck.annotations.Configuration(tests = 100)
    public void testPingCalc(long in, long out)
    {
        //count++;
        //System.out.println("Test " + count + ": " + in+" "+out);
        C_PongModel modelUnderTest = new C_PongModel();
        modelUnderTest.setTimeIn(in);
        modelUnderTest.setTimeOut(out);
        long expected = out-in;
        long result = modelUnderTest.getTimeDifference();

        Assert.assertEquals(expected,result);
    }

    @Test
    @Generator(klass = double[].class, generator =doubleArrGen.class)
    public void testParseDataFromServer(double[] values)
    {
        count++;
        System.out.println(count);

        PlayerC playerUnderTest = new PlayerC(new C_PongModel(), Mockito.mock(Socket.class));
        C_PongModel playersModel = playerUnderTest.getModel();

        String[] strVals = new String[values.length];
        for(int i = 0; i < values.length;i++)
            strVals[i] = String.valueOf(values[i]);

        playerUnderTest.parseDataFromServer(strVals);

        Assert.assertEquals(values[0] , playersModel.getBall().getX(), 0.0);
        Assert.assertEquals(values[1] , playersModel.getBall().getY(), 0.0);
        Assert.assertEquals(values[2] , playersModel.getBats()[0].getX(), 0.0);
        Assert.assertEquals(values[3] , playersModel.getBats()[0].getY(), 0.0);
        Assert.assertEquals(values[4] , playersModel.getBats()[1].getX(), 0.0);
        Assert.assertEquals(values[5] , playersModel.getBats()[1].getY(), 0.0);
    }
}