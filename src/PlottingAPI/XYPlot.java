package PlottingAPI;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYErrorRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.*;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;

import java.awt.*;

public class XYPlot extends ApplicationFrame {

    private final Defaults defaults = new Defaults();   // Defaults for this plot
    private JFreeChart chart;                           // The chart of this plot

    private int uniqueDatasets = 0;



    // ************
    // Constructors
    // ************

    public XYPlot(){
        this(null, null, null);
    }

    public XYPlot(String title){
        this(title, null, null);
    }

    public XYPlot(String title, String xLabel, String yLabel) {

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



    // **********************
    // User plot line methods
    // **********************

    public void plotLine(double[] xData, double[] yData){
        plotLine(xData, yData, defaults.getLineProperties());
    }

    public void plotLine(double[] xData, double[] yData, LineProperties lineProperties){
        plotLine(xData, yData, null, null, lineProperties, null);
    }


    public void plotLine(double[] xData, double[] yData, double[] yError){
        plotLine(xData, yData, yError, defaults.getLineProperties(), defaults.getErrorLineProperties());
    }

    public void plotLine(double[] xData, double[] yData, double[] yError, LineProperties lineProperties, LineProperties errorLineProperties){
        plotLine(xData, yData, new double[xData.length], yError, lineProperties, errorLineProperties);
    }


    public void plotLine(double[] xData, double[] yData, double[] xError, double[] yError){
        plotLine(xData, yData, xError, yError, defaults.getLineProperties(), defaults.getErrorLineProperties());
    }

    public void plotLine(double[] xData, double[] yData, double[] xError, double[] yError, LineProperties lineProperties, LineProperties errorLineProperties){

        // Keep track of the number of unique datasets plotted
        uniqueDatasets++;

        // Draw the data
        drawLine(xData, yData, lineProperties);

        // If we have them, draw error bars
        if (errorLineProperties != null) {
            drawErrorBars(xData, yData, xError, yError, errorLineProperties);
        }
    }



    // ************************
    // User plot stairs methods
    // ************************

    public void plotStairs(double[] xData, double[] yData){
        plotStairs(xData, yData, defaults.getLineProperties());
    }

    public void plotStairs(double[] xData, double[] yData, LineProperties lineProperties){
        plotStairs(xData, yData, null, null, lineProperties, null);
    }


    public void plotStairs(double[] xData, double[] yData, double[] yError){
        plotStairs(xData, yData, yError, defaults.getLineProperties(), defaults.getErrorLineProperties());
    }

    public void plotStairs(double[] xData, double[] yData, double[] yError, LineProperties lineProperties, LineProperties errorLineProperties){
        plotStairs(xData, yData, new double[xData.length], yError, lineProperties, errorLineProperties);
    }


    public void plotStairs(double[] xData, double[] yData, double[] xError, double[] yError){
        plotStairs(xData, yData, xError, yError, defaults.getLineProperties(), defaults.getErrorLineProperties());
    }

    public void plotStairs(double[] xData, double[] yData, double[] xError, double[] yError, LineProperties lineProperties, LineProperties errorLineProperties){

        // Keep track of the number of unique datasets plotted
        uniqueDatasets++;

        // Draw the data
        drawStairs(xData, yData, lineProperties);

        // If we have them, draw error bars
        if (errorLineProperties != null) {
            drawErrorBars(xData, yData, xError, yError, errorLineProperties);
        }
    }



    // *************************
    // User scatter plot methods
    // *************************

    public void scatter(double[] xData, double[] yData){
        scatter(xData, yData, defaults.getShapeProperties());
    }

    public void scatter(double[] xData, double[] yData, ShapeProperties shapeProperties){
        scatter(xData, yData, null, null, shapeProperties, null);
    }


    public void scatter(double[] xData, double[] yData, double[] yError){
        scatter(xData, yData, yError, defaults.getShapeProperties(), defaults.getErrorLineProperties());
    }

    public void scatter(double[] xData, double[] yData, double[] yError, ShapeProperties shapeProperties, LineProperties errorLineProperties){
        scatter(xData, yData, new double[xData.length], yError, shapeProperties, errorLineProperties);
    }


    public void scatter(double[] xData, double[] yData, double[] xError, double[] yError){
        scatter(xData, yData, xError, yError, defaults.getShapeProperties(), defaults.getErrorLineProperties());
    }

    public void scatter(double[] xData, double[] yData, double[] xError, double[] yError, ShapeProperties shapeProperties, LineProperties errorLineProperties){

        // Keep track of the number of unique datasets plotted
        uniqueDatasets++;

        // Draw the data
        drawScatter(xData, yData, shapeProperties);

        // If we have them, draw error bars
        if (errorLineProperties != null) {
            drawErrorBars(xData, yData, xError, yError, errorLineProperties);
        }
    }




    // ************
    // User setters
    // ************

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

    private void drawErrorBars(double[] xData, double[] yData, double[] xError, double[] yError, LineProperties properties) {
        // Build the error series
        XYIntervalSeriesCollection collection = new XYIntervalSeriesCollection();
        XYIntervalSeries series = new XYIntervalSeries(String.valueOf(getDatasetCount()));
        for (int i = 0; i < xData.length; i++){
            series.add(
                    xData[i], xData[i] - xError[i], xData[i] + xError[i],
                    yData[i], yData[i] - yError[i], yData[i] + yError[i]
            );
        }
        collection.addSeries(series);

        // Set the line color and width
        XYErrorRenderer errorRenderer = new XYErrorRenderer();
        errorRenderer.setBaseShapesFilled(false);
        errorRenderer.setDrawOutlines(false);
        errorRenderer.setErrorPaint(properties.getColor());
        errorRenderer.setErrorStroke(properties.getStroke());

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

        private final float scatterSize         = 5.0f;

        private final LineProperties.Style gridLineStyle  = LineProperties.Style.Solid;
        private final LineProperties.Style lineStyle      = LineProperties.Style.Solid;
        private final LineProperties.Style errorLineStyle = LineProperties.Style.Solid;

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