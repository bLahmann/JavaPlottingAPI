package PlottingAPI;

import java.awt.*;

public class LineProperties {

    public enum Style { Dashed, Solid }

    private Style style = Style.Solid;
    private double lineWidth = 2.0;
    private Color color = Color.BLACK;

    public static LineProperties blackLine(double lineWidth){
        return new LineProperties(Style.Solid, lineWidth, Color.BLACK);
    }

    public static LineProperties redLine(double lineWidth){
        return new LineProperties(Style.Solid, lineWidth, Color.RED);
    }

    public LineProperties(Style style, double lineWidth, Color color) {
        this.style = style;
        this.lineWidth = lineWidth;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public BasicStroke getStroke(){
        switch (style){
            case Solid:
                return new BasicStroke((float) lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL);
            case Dashed:
                return new BasicStroke((float) lineWidth, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                        10.0f, new float[] {10.0f}, 0.0f);
            default:
                return null;
        }
    }
}
