package PlottingAPI;

import org.jfree.util.ShapeUtilities;

import java.awt.*;

public class ShapeProperties {

    private double outlineWidth = 2.0;
    private double size         = 10.0;

    private Color fillColor   = Color.RED;
    private Color outlineColor = Color.BLACK;


    public static ShapeProperties blackSquare(double size, double lineWidth){
        return new ShapeProperties(lineWidth, size, Color.BLACK, Color.BLACK);
    }

    public static ShapeProperties redSquare(double size, double lineWidth){
        return new ShapeProperties(lineWidth, size, Color.RED, Color.BLACK);
    }


    public ShapeProperties() {
    }

    public ShapeProperties(double outlineWidth, double size, Color fillColor, Color outlineColor) {
        this.outlineWidth = outlineWidth;
        this.size = size;
        this.fillColor = fillColor;
        this.outlineColor = outlineColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getOutlineColor() {
        return outlineColor;
    }

    public Shape getShape(){
        Shape shape = ShapeUtilities.createDiamond((float) size);
        shape = ShapeUtilities.rotateShape(shape, Math.toRadians(45.0), 0.0f, 0.0f);
        return shape;
    }

    public BasicStroke getOutlineStroke(){
        return new BasicStroke((float) outlineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
    }
}
