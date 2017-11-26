package PlottingAPI;

import org.apache.commons.math3.analysis.function.Gaussian;

public class ArrayUtils {

    // *************************
    // Common array constructors
    // *************************

    public static double[] linspace(double a, double b, int N){
        double min = Math.min(a, b);
        double max = Math.max(a, b);

        double[] values = new double[N];
        for (int i = 0; i < values.length; i++){
            values[i] = min + i*(max - min)/(N-1);
        }

        return values;

    }

    public static double[] logspace(double a, double b, int N){
        double min = Math.min(a, b);
        double max = Math.max(a, b);

        double[] values = new double[N];
        for (int i = 0; i < values.length; i++){
            double power = min + i*(max - min)/(N-1);
            values[i] = Math.pow(10.0, power);
        }

        return values;

    }

    public static double[] gaussian(double area, double mu, double sigma, double[] x){
        double norm = area / sigma / Math.sqrt(2*Math.PI);
        Gaussian gaussian = new Gaussian(norm, mu, sigma);


        double[] values = new double[x.length];
        for (int i = 0; i < values.length; i++){
            values[i] = gaussian.value(x[i]);
        }

        return values;
    }

    public static double[] rand(double a, double b, int N){
        double min = Math.min(a, b);
        double max = Math.max(a, b);

        double[] values = new double[N];
        for (int i = 0; i < values.length; i++){
            values[i] = min + (max - min)*Math.random();
        }

        return values;
    }

    public static double[] rand(int N){
        double[] values = new double[N];
        for (int i = 0; i < N; i++){
            values[i] = Math.random();
        }
        return values;
    }



    // ****************
    // Basic operations
    // ****************

    public static double[] pow(double[] array, double power){
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++){
            result[i] = Math.pow(array[i], power);
        }
        return result;
    }

    public static double[] multiply(double[] array, double term){
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++){
            result[i] = array[i] * term;
        }
        return result;
    }

    public static double[] multiply(double[] arrayOne, double[] arrayTwo){
        double[] result = new double[arrayOne.length];
        for (int i = 0; i < arrayOne.length; i++){
            result[i] = arrayOne[i] * arrayTwo[i];
        }
        return result;
    }

    public static double[][] matrixMultiply(double[] arrayOne, double[] arrayTwo){
        double[][] results = new double[arrayOne.length][arrayTwo.length];
        for (int i = 0; i < results.length; i++){
            for (int j = 0; j < results[i].length; j++){
                results[i][j] = arrayOne[i] * arrayTwo[j];
            }
        }
        return results;
    }

    public static double[] add(double[] array, double term){
        double[] result = new double[array.length];
        for (int i = 0; i < array.length; i++){
            result[i] = array[i] + term;
        }
        return result;
    }

    public static double[] add(double[] arrayOne, double[] arrayTwo){
        double[] result = new double[arrayOne.length];
        for (int i = 0; i < arrayOne.length; i++){
            result[i] = arrayOne[i] + arrayTwo[i];
        }
        return result;
    }


}
