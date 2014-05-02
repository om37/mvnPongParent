package om37;

/**
 * Created with IntelliJ IDEA.
 * User: Odie
 * Date: 02/05/14
 * Time: 15:31
 * To change this template use File | Settings | File Templates.
 */

import org.jcheck.*;
import org.jcheck.generator.Gen;

import java.util.Random;

public class doubleArrGen implements Gen<double[]>
{
    public double[] arbitrary(Random rnd, long size)
    {
        double[] arr = new double[6];
        for(int i = 0; i < arr.length; i++)
            arr[i] = rnd.nextDouble();

        return arr;
    }
}
