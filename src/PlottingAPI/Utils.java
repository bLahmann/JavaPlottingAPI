package PlottingAPI;

import org.apache.commons.math3.analysis.function.Gaussian;

public class Utils {

    public static double[] linspace(double a, double b, int N){
        double min = Math.min(a, b);
        double max = Math.max(a, b);

        double[] values = new double[N];
        for (int i = 0; i < values.length; i++){
            values[i] = min + i*(max - min)/(N-1);
        }

        return values;

    }

    public static double normpdf(double area, double mu, double sigma, double x){
        return normpdf(area, mu, sigma, new double[] {x})[0];
    }

    public static double[] normpdf(double area, double mu, double sigma, double[] x){
        double norm = area / sigma / Math.sqrt(2*Math.PI);
        Gaussian gaussian = new Gaussian(norm, mu, sigma);


        double[] values = new double[x.length];
        for (int i = 0; i < values.length; i++){
            values[i] = gaussian.value(x[i]);
        }

        return values;
    }


    public static double[] arrayPow(double[] array, double power){
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++){
            result[i] = Math.pow(array[i], power);
        }
        return result;
    }

    public static double[] arrayMultiply(double[] array, double term){
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++){
            result[i] = array[i] * term;
        }
        return result;
    }

    public static double[] arrayMultiply(double[] arrayOne, double[] arrayTwo){
        double[] result = new double[arrayOne.length];
        for (int i = 0; i < arrayOne.length; i++){
            result[i] = arrayOne[i] * arrayTwo[i];
        }
        return result;
    }

    public static double[] arrayAdd(double[] arrayOne, double[] arrayTwo){
        double[] result = new double[arrayOne.length];
        for (int i = 0; i < arrayOne.length; i++){
            result[i] = arrayOne[i] + arrayTwo[i];
        }
        return result;
    }

    public static double[] arrayAdd(double[] array, double term){
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++){
            result[i] = array[i] + term;
        }
        return result;
    }



}
