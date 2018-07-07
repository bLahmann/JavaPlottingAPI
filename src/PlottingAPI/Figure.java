package PlottingAPI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYBlockRenderer;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.RectangleInsets;

import javax.swing.*;
import java.awt.*;

public class Figure extends JFrame {

    private final Defaults defaults = new Defaults();   // Defaults for this plot
    private JFreeChart chart;                           // The chart of this plot

    // TODO: May want to change this to separate integers for different plot types (i.e. scatter vs plot)
    private int uniqueDatasets = 0;



    // ************
    // Constructors
    // ************

    public Figure(){
        this(null, null, null);
    }

    public Figure(String title){
        this(title, null, null);
    }

    public Figure(String title, String xLabel, String yLabel) {

        // Create the actual frame
        super(title);

        // Create the chart
        chart = ChartFactory.createXYLineChart(title, xLabel, yLabel, null,
                PlotOrientation.VERTICAL,false,true,false);
        chart.setPadding(new RectangleInsets(0, 0, 0, 20));


        // Set the defaults
        chart.getXYPlot().setAxisOffset(RectangleInsets.ZERO_INSETS);
        chart.getXYPlot().setBackgroundPaint(defaults.backgroundColor);

        chart.getXYPlot().setRangeGridlineStroke(defaults.getGridLineProperties().getStroke());
        chart.getXYPlot().setRangeGridlinePaint(defaults.getGridLineProperties().getColor());
        chart.getXYPlot().setRangePannable(true);

        chart.getXYPlot().setDomainGridlineStroke(defaults.getGridLineProperties().getStroke());
        chart.getXYPlot().setDomainGridlinePaint(defaults.getGridLineProperties().getColor());
        chart.getXYPlot().setDomainPannable(true);


        // Create the panel
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize( new java.awt.Dimension(defaults.windowWidth ,defaults.windowHeight) );
        setContentPane(chartPanel);



        // Show user
        setVisible(true);
    }



    // *****************
    // Line plot methods
    // *****************

    public void plot(double[] xData, double[] yData){
        plot(xData, yData, defaults.getLineProperties());
    }

    public void plot(double[] xData, double[] yData, LineProperties lineProperties){

        // Keep track of the number of unique datasets plotted
        uniqueDatasets++;

        // Draw the data
        drawLine(xData, yData, lineProperties);

    }



    // ******************
    // Stair plot methods
    // ******************

    public void stairs(double[] xData, double[] yData){
        stairs(xData, yData, defaults.getLineProperties());
    }

    public void stairs(double[] xData, double[] yData, LineProperties lineProperties){

        // Keep track of the number of unique datasets plotted
        uniqueDatasets++;

        // Draw the data
        drawStairs(xData, yData, lineProperties);

    }



    // ********************
    // Scatter plot methods
    // ********************

    public void scatter(double[] xData, double[] yData){
        scatter(xData, yData, defaults.getShapeProperties());
    }

    public void scatter(double[] xData, double[] yData, ShapeProperties shapeProperties){

        // Keep track of the number of unique datasets plotted
        uniqueDatasets++;

        // Draw the data
        drawScatter(xData, yData, shapeProperties);

    }



    // *******************
    // X error bar methods
    // *******************

    public void xErrorbars(double[] xData, double[] yData, double[] xError){
        xErrorbars(xData, yData, xError, xError, defaults.getErrorLineProperties());
    }

    public void xErrorbars(double[] xData, double[] yData, double[] lowerXError, double[] upperXError){
        xErrorbars(xData, yData, lowerXError, upperXError, defaults.getErrorLineProperties());
    }

    public void xErrorbars(double[] xData, double[] yData, double[] xError, LineProperties errorLineProperties){
        xErrorbars(xData, yData, xError, xError, errorLineProperties);
    }

    public void xErrorbars(double[] xData, double[] yData, double[] lowerXError, double[] upperXError, LineProperties errorLineProperties){
        drawErrorBars(xData, yData,
                lowerXError, upperXError,
                null, null,
                errorLineProperties);
    }



    // *******************
    // Y error bar methods
    // *******************

    public void yErrorbars(double[] xData, double[] yData, double[] yError) {
        yErrorbars(xData, yData, yError, yError, defaults.getErrorLineProperties());
    }

    public void yErrorbars(double[] xData, double[] yData, double[] lowerYError, double[] upperYError){
        yErrorbars(xData, yData, lowerYError, upperYError, defaults.getErrorLineProperties());
    }

    public void yErrorbars(double[] xData, double[] yData, double[] yError, LineProperties errorLineProperties){
        yErrorbars(xData, yData, yError, yError, errorLineProperties);
    }

    public void yErrorbars(double[] xData, double[] yData, double[] lowerYError, double[] upperYError, LineProperties errorLineProperties){
        drawErrorBars(xData, yData,
                null, null,
                lowerYError, upperYError,
                errorLineProperties);
    }



    // ********************
    // Surface plot methods
    // ********************

    public void surface(double[] xData, double[] yData, double[][] zData){
        surface(xData, yData, zData, defaults.colorMap);
    }

    public void surface(double[] xData, double[] yData, double[][] zData, ColorMap colorMap){

        // Calculate the block heights we'll use
        double blockWidth  = Math.abs(xData[1] - xData[0]);
        double blockHeight = Math.abs(yData[1] - yData[0]);

        // Calculate the min and max values
        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;

        // We need to combine all of the data into a single array
        int size = xData.length * yData.length;
        double[][] data = new double[3][size];
        for (int i = 0; i < size; i++) {
            int xIndex = i % xData.length;
            int yIndex = Math.floorDiv(i, xData.length);

            data[0][i] = xData[xIndex];
            data[1][i] = yData[yIndex];
            data[2][i] = zData[yIndex][xIndex];

            minValue = Math.min(minValue, zData[yIndex][xIndex]);
            maxValue = Math.max(maxValue, zData[yIndex][xIndex]);
        }

        // Create the dataset
        DefaultXYZDataset dataset = new DefaultXYZDataset();
        dataset.addSeries(String.valueOf(getDatasetCount()), data);

        // Create the renderer
        XYBlockRenderer renderer = new XYBlockRenderer();
        renderer.setPaintScale(colorMap.getPaintScale(minValue, maxValue));
        renderer.setBlockWidth(blockWidth);
        renderer.setBlockHeight(blockHeight);

        chart.addSubtitle(colorMap.getPaintLegend(minValue, maxValue));


        // Draw the data
        drawData(dataset, renderer);
    }

    public void contour(double[] xData, double[] yData, double[][] zData, double[] contours){

        ContourGenerator contourGenerator = new ContourGenerator(xData, yData, zData);
        for (int i = 0; i < contours.length; i++) {

            XYSeriesCollection collection =
                    contourGenerator.getContourSeriesCollection(
                            String.valueOf(getDatasetCount()), contours[i]);

            // Contour line renderer
            XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer();
            lineRenderer.setBaseShapesFilled(false);
            lineRenderer.setDrawOutlines(false);
            for (int j = 0; j < collection.getSeriesCount(); j++){
                lineRenderer.setSeriesPaint(j, defaults.colors[i]);
                lineRenderer.setSeriesStroke(j, new BasicStroke(2.0f));
            }

            drawData(collection, lineRenderer);
        }
    }



    // ******************************
    // Figure property setter methods
    // ******************************

    public void setTitle(String title, float size){
        chart.setTitle(title);
    }

    public void setXLabel(String label, double size){
        setAxisLabel(chart.getXYPlot().getDomainAxis(), label, (float) size);
    }

    public void setYLabel(String label, double size){
        setAxisLabel(chart.getXYPlot().getRangeAxis(), label, (float) size);
    }

    private void setAxisLabel(ValueAxis axis, String label, float size){
        axis.setLabelFont(axis.getLabelFont().deriveFont(size));
        axis.setLabel(label);
    }

    public void setXLimits(double min, double max){
        setAxisLimits(chart.getXYPlot().getDomainAxis(), min, max);
    }

    public void setYLimits(double min, double max){
        setAxisLimits(chart.getXYPlot().getRangeAxis(), min, max);
    }

    private void setAxisLimits(ValueAxis axis, double min, double max){
        axis.setRange(min, max);
    }



    // ***************************
    // Private convenience methods
    // ***************************

    private void drawLine(double[] xData, double[] yData, LineProperties properties) {
        // Build the data series
        XYSeries series = new XYSeries(String.valueOf(getDatasetCount()));
        for (int i = 0; i < xData.length; i++){
            series.add(xData[i], yData[i]);
        }

        // Call the generalized draw method
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        drawLine(collection, properties);
    }

    private void drawStairs(double[] xData, double[] yData, LineProperties properties) {
        XYSeries series = new XYSeries(String.valueOf(getDatasetCount()));
        double dx;

        // First 2 points
        dx = 0.5*(xData[1] - xData[0]);
        series.add(xData[0] - dx, 0.0);
        series.add(xData[0] - dx, yData[0]);

        for (int i = 1; i < (yData.length - 1); i++){
            // Points left of xData[i]
            dx = 0.5*(xData[i] - xData[i-1]);
            series.add(xData[i] - dx, yData[i-1]);
            series.add(xData[i] - dx, yData[i]);

            // Points right of xData[i]
            dx = 0.5*(xData[i+1] - xData[i]);
            series.add(xData[i] + dx, yData[i]);
            series.add(xData[i] + dx, yData[i+1]);
        }

        // Last 2 points
        dx = 0.5*(xData[yData.length-1] - xData[yData.length-2]);
        series.add(xData[yData.length-1] + dx, yData[yData.length-1]);
        series.add(xData[yData.length-1] + dx, 0.0);

        // Call the generalized draw method
        XYSeriesCollection collection = new XYSeriesCollection();
        collection.addSeries(series);
        drawLine(collection, properties);
    }

    private void drawLine(XYDataset data, LineProperties properties) {
        // Set the line color and width
        XYLineAndShapeRenderer lineRenderer = new XYLineAndShapeRenderer();
        lineRenderer.setSeriesPaint(0, properties.getColor());
        lineRenderer.setSeriesStroke(0, properties.getStroke());
        lineRenderer.setBaseShapesFilled(false);
        lineRenderer.setDrawOutlines(false);

        drawData(data,lineRenderer);
    }

    private void drawScatter(double[] xData, double[] yData, ShapeProperties properties){
        // Build the data series
        XYSeriesCollection collection = new XYSeriesCollection();
        XYSeries series = new XYSeries(String.valueOf(getDatasetCount()));
        for (int i = 0; i < xData.length; i++){
            series.add(xData[i], yData[i]);
        }
        collection.addSeries(series);

        // Set the line color and width
        XYLineAndShapeRenderer shapeRenderer = new XYLineAndShapeRenderer();
        shapeRenderer.setSeriesOutlinePaint(0, properties.getOutlineColor());
        shapeRenderer.setSeriesOutlineStroke(0, properties.getOutlineStroke());
        shapeRenderer.setSeriesPaint(0, properties.getFillColor());
        shapeRenderer.setSeriesShape(0, properties.getShape());
        shapeRenderer.setBaseShapesFilled(true);
        shapeRenderer.setDrawOutlines(true);
        shapeRenderer.setUseOutlinePaint(true);
        shapeRenderer.setSeriesLinesVisible(0, false);

        // Draw the data
        drawData(collection, shapeRenderer);
    }

    private void drawErrorBars(double[] xData, double[] yData,
                               double[] lowerXError, double[] upperXError,
                               double[] lowerYError, double[] upperYError,
                               LineProperties properties) {

        XYIntervalSeriesCollection collection = new XYIntervalSeriesCollection();
        XYErrorRenderer errorRenderer = new XYErrorRenderer();

        if (lowerXError == null){
            errorRenderer.setDrawXError(false);
            lowerXError = new double[xData.length];
            upperXError = new double[xData.length];
        }

        if (lowerYError == null){
            errorRenderer.setDrawYError(false);
            lowerYError = new double[yData.length];
            upperYError = new double[yData.length];
        }

        // Build the error series
        XYIntervalSeries series = new XYIntervalSeries(String.valueOf(getDatasetCount()));
        for (int i = 0; i < xData.length; i++){
            series.add(
                    xData[i], xData[i] - lowerXError[i], xData[i] + upperXError[i],
                    yData[i], yData[i] - lowerYError[i], yData[i] + upperYError[i]
            );
        }
        collection.addSeries(series);

        // Set the line color and width
        errorRenderer.setBaseShapesFilled(false);
        errorRenderer.setDrawOutlines(false);
        errorRenderer.setErrorPaint(properties.getColor());
        errorRenderer.setErrorStroke(properties.getStroke());
        errorRenderer.setCapLength(defaults.errorCapLength);

        drawData(collection, errorRenderer);
    }

    private void drawData(XYDataset data, XYItemRenderer renderer){
        int index = getDatasetCount();
        chart.getXYPlot().setDataset(index, data);
        chart.getXYPlot().setRenderer(index, renderer);
        pack();
    }

    private int getDatasetCount(){
        if (chart.getXYPlot().getDataset(0) == null) return 0;
        return chart.getXYPlot().getDatasetCount();
    }




    // *******************************
    // Struct for holding our defaults
    // *******************************

    private class Defaults {
        private final int windowWidth  = 500;
        private final int windowHeight = 350;

        private final Color backgroundColor = Color.WHITE;

        private final float gridLineWidth       = 0.5f;
        private final float lineWidth           = 2.0f;
        private final float scatterOutlineWidth = 1.0f;
        private final float errorLineWidth      = 1.0f;
        private final float errorCapLength      = 5.0f;

        private final float scatterSize         = 5.0f;

        private final LineProperties.Style gridLineStyle  = LineProperties.Style.Solid;
        private final LineProperties.Style lineStyle      = LineProperties.Style.Solid;
        private final LineProperties.Style errorLineStyle = LineProperties.Style.Solid;

        private final ColorMap colorMap = ColorMap.jet;

        private final Color gridLineColor       = Color.LIGHT_GRAY;
        private final Color scatterOutlineColor = Color.BLACK;

        private final Color[] colors = {
                Color.RED,
                Color.BLUE,
                Color.GREEN,
                Color.BLACK,
        };

        private LineProperties getGridLineProperties(){
            return new LineProperties(gridLineStyle, gridLineWidth, gridLineColor);
        }

        private LineProperties getLineProperties(){
            return new LineProperties(lineStyle, lineWidth, colors[uniqueDatasets]);
        }

        private ShapeProperties getShapeProperties(){
            return new ShapeProperties(scatterOutlineWidth, scatterSize, colors[uniqueDatasets], scatterOutlineColor);
        }

        private LineProperties getErrorLineProperties(){
            return new LineProperties(errorLineStyle, errorLineWidth, colors[uniqueDatasets]);
        }
    }
}