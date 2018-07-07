package PlottingAPI;

import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ContourGenerator {

    private double[] xValues, yValues;
    private double[][] zValues;

    public ContourGenerator(double[] x, double[] y, double[][] z) {
        this.xValues = x;
        this.yValues = y;
        this.zValues = z;
    }

    public XYSeriesCollection getContourSeriesCollection(String label, double contourValue){

        // Create a bit array (1 for greater than, 0 for less than)
        int[][] bitArray = new int[zValues.length][zValues[0].length];
        for (int i = 0; i < bitArray.length; i++){
            for (int j = 0; j < bitArray[i].length; j++){
                bitArray[i][j] = (zValues[i][j] > contourValue) ? 1 : 0;
            }
        }


        // Create an index array for the nodes between points (values between 0-15)
        int[][] nodeIndexes = new int[zValues.length - 1][zValues[0].length - 1];
        for (int i = 0; i < nodeIndexes.length; i++){
            for (int j = 0; j < nodeIndexes[i].length; j++){

                nodeIndexes[i][j] = bitArray[i][j];
                nodeIndexes[i][j] = (nodeIndexes[i][j] << 1) | bitArray[i+0][j+1];
                nodeIndexes[i][j] = (nodeIndexes[i][j] << 1) | bitArray[i+1][j+1];
                nodeIndexes[i][j] = (nodeIndexes[i][j] << 1) | bitArray[i+1][j+0];

            }
        }


        // Create an XYSeries for each node
        XYSeriesCollection collection = new XYSeriesCollection();
        for (int i = 0; i < nodeIndexes.length; i++) {
            for (int j = 0; j < nodeIndexes[i].length; j++){

                double xL = xValues[j], xR = xValues[j+1];
                double yB = yValues[i], yT = yValues[i+1];

                double zBL = zValues[i]  [j], zBR = zValues[i]  [j+1];
                double zTL = zValues[i+1][j], zTR = zValues[i+1][j+1];

                XYSeries series;
                switch (nodeIndexes[i][j]){

                    case 0: case 15:        // Do nothing
                        break;

                    case 1: case 14:        // Top left line

                        series = new XYSeries(String.format("%s_%d", label, collection.getSeriesCount()), false);
                        series.add(xL + (xR-xL) * (zTL - contourValue) / (zTL - zTR), yT);       // Top point
                        series.add(xL, yB + (yT-yB) * (zBL - contourValue) / (zBL - zTL));       // Left point

                        collection.addSeries(series);
                        break;

                    case 2: case 13:        // Top right line

                        series = new XYSeries(String.format("%s_%d", label, collection.getSeriesCount()), false);
                        series.add(xL + (xR-xL) * (zTL - contourValue) / (zTL - zTR), yT);       // Top point
                        series.add(xR, yB + (yT-yB) * (zBR - contourValue) / (zBR - zTR));       // Right point

                        collection.addSeries(series);
                        break;

                    case 3: case 12:        // Horizontal line

                        series = new XYSeries(String.format("%s_%d", label, collection.getSeriesCount()), false);
                        series.add(xL, yB + (yT-yB) * (zBL - contourValue) / (zBL - zTL));       // Left point
                        series.add(xR, yB + (yT-yB) * (zBR - contourValue) / (zBR - zTR));       // Right point

                        collection.addSeries(series);
                        break;

                    case 4: case 11:        // Bottom right line

                        series = new XYSeries(String.format("%s_%d", label, collection.getSeriesCount()), false);
                        series.add(xL + (xR-xL) * (zBL - contourValue) / (zBL - zBR), yB);       // Bottom point
                        series.add(xR, yB + (yT-yB) * (zBR - contourValue) / (zBR - zTR));       // Right point

                        collection.addSeries(series);
                        break;

                    case 5: case 10:        // Saddle point

                        System.err.println("Unhandled saddle point!");
                        break;

                    case 6: case 9:         // Vertical line

                        series = new XYSeries(String.format("%s_%d", label, collection.getSeriesCount()), false);
                        series.add(xL + (xR-xL) * (zTL - contourValue) / (zTL - zTR), yT);       // Top point
                        series.add(xL + (xR-xL) * (zBL - contourValue) / (zBL - zBR), yB);       // Bottom point

                        collection.addSeries(series);
                        break;

                    case 7: case 8:         // Bottom left line

                        series = new XYSeries(String.format("%s_%d", label, collection.getSeriesCount()), false);
                        series.add(xL + (xR-xL) * (zBL - contourValue) / (zBL - zBR), yB);       // Bottom point
                        series.add(xL, yB + (yT-yB) * (zBL - contourValue) / (zBL - zTL));       // Left point

                        collection.addSeries(series);
                        break;

                }
            }
        }

        return collection;
    }
}
