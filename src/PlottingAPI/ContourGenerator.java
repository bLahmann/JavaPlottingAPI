package PlottingAPI;

import jdk.nashorn.internal.runtime.arrays.ArrayIndex;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class ContourGenerator {

    private final static double ACCEPTING_VALUE_FRACTION = 0.01;
    private final static double ACCEPTING_DISTANCE_FRACTION = 0.01;
    private final static double maxAngle = 25.0;

    private static double ACCEPTING_DISTANCE = 0.0;

    private double[] xValues, yValues;
    private double[][] zValues;

    public ContourGenerator(double[] x, double[] y, double[][] z) {
        this.xValues = x;
        this.yValues = y;
        this.zValues = z;

        Point2D leftBottom = new Point2D.Double(
                Math.min(x[0], x[x.length-1]),
                Math.min(y[0], y[y.length-1]));
        Point2D rightTop = new Point2D.Double(
                Math.max(x[0], x[x.length-1]),
                Math.max(y[0], y[y.length-1]));

        ACCEPTING_DISTANCE = ACCEPTING_DISTANCE_FRACTION * leftBottom.distance(rightTop);
    }

    public XYSeriesCollection getContourSeriesCollection(String label, double contour){

        // Naively calculate the intersections
        ArrayList<IndexPoint> intersectionPoints = getIntersectionPoints(contour);

        // Sort these points
        ArrayList<ArrayList<IndexPoint>> sortedPoints = sortPoints(intersectionPoints);

        // Convert them to an XYSeries
        XYSeriesCollection collection = new XYSeriesCollection();
        for (int i = 0; i < sortedPoints.size(); i++) {
            String name = String.format("%s - %d", label, i);
            ArrayList<IndexPoint> contourLine = sortedPoints.get(i);

            XYSeries series = new XYSeries(name, false);
            for (IndexPoint point : contourLine) {
                Point2D physicalPoint = getPhysicalPoint(point);
                series.add(physicalPoint.getX(), physicalPoint.getY());
            }
            collection.addSeries(series);
        }

        // Return
        return collection;
    }

    public ArrayList<IndexPoint> getIntersectionPoints(double contour){

        ArrayList<IndexPoint> points = new ArrayList<>();
        for (int i = 0; i < yValues.length; i++) {
            for (int j = 0; j < xValues.length; j++) {
                double fraction = Math.abs((zValues[i][j] - contour)/contour);
                if (fraction < ACCEPTING_VALUE_FRACTION) {
                    points.add(new IndexPoint(j, i));
                }

            }
        }
        return points;

    }


    private ArrayList<ArrayList<IndexPoint>> sortPoints(ArrayList<IndexPoint> points){

        // Initialize
        ArrayList<ArrayList<IndexPoint>> sortedPoints = new ArrayList<>();
        ArrayList<IndexPoint> currentContourLine = new ArrayList<>();

        // Add the first point
        IndexPoint currentPoint = points.get(0);
        currentContourLine.add(currentPoint);
        points.remove(currentPoint);

        while (!points.isEmpty()){

            // Find the nearest point
            IndexPoint nearestPoint = getNearestPoint(points, currentPoint);
            points.remove(nearestPoint);


            double distance = currentPoint.distance(nearestPoint);

            // If the nearest point is actually nearby, add it to the current line
            if (distance < ACCEPTING_DISTANCE) {
                currentContourLine.add(nearestPoint);
                currentPoint = nearestPoint;
            }

            // Otherwise, start a new line
            else{

                // First, check to see if we should connect the last and first point of this line
                IndexPoint firstPoint  = currentContourLine.get(0);
                if (firstPoint.distance(currentPoint) < ACCEPTING_DISTANCE){
                    currentContourLine.add(firstPoint);
                }

                sortedPoints.add(currentContourLine);
                currentContourLine = new ArrayList<>();
                currentContourLine.add(nearestPoint);
                currentPoint = nearestPoint;
            }



        }

        sortedPoints.add(currentContourLine);
        return sortedPoints;
    }

    private IndexPoint getNearestPoint(ArrayList<IndexPoint> points, IndexPoint referencePoint){
        IndexPoint nearestPoint = points.get(0);
        double minDistance = referencePoint.distance(nearestPoint);

        for (IndexPoint point : points){
            double distance = referencePoint.distance(point);
            if (distance < minDistance){
                minDistance = distance;
                nearestPoint = point;
            }
        }

        return nearestPoint;
    }

    private Point2D getPhysicalPoint(IndexPoint indexPoint){
        return new Point2D.Double(xValues[indexPoint.xIndex], yValues[indexPoint.yIndex]);
    }

    private Vector2D getGradientTangent(IndexPoint point){

        // Renaming for space
        int xi = point.xIndex;
        int yi = point.yIndex;

        // Distances over which  we're averaging the derivative
        double dx = xValues[xi + 1] - xValues[xi - 1];
        double dy = yValues[yi + 1] - yValues[yi - 1];

        // Partial with respect to x
        double dfdx = (zValues[yi][xi+1] - zValues[yi][xi-1])/dx;
        if (dfdx == 0)  return new Vector2D(1.0, 0.0);

        // Partial with respect to y
        double dfdy = (zValues[yi+1][xi] - zValues[yi-1][xi])/dy;
        if (dfdy == 0)  return new Vector2D(0.0, 1.0);

        // Vector tangent to the gradient
        Vector2D vector = new Vector2D(-1.0, dfdx / dfdy);
        return vector.normalize();
    }


    private Vector2D getVector(Point2D p1, Point2D p2){
        return new Vector2D(p2.getX() - p1.getX(), p2.getY() - p1.getY());
    }

    private class IndexPoint {
        int xIndex;
        int yIndex;

        public IndexPoint(int xIndex, int yIndex) {
            this.xIndex = xIndex;
            this.yIndex = yIndex;
        }

        public double distance(IndexPoint point){
            Point2D thisPoint = getPhysicalPoint(this);
            return thisPoint.distance(getPhysicalPoint(point));
        }
    }
}
