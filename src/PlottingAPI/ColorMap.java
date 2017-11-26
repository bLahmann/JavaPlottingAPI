package PlottingAPI;

import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.renderer.LookupPaintScale;
import org.jfree.chart.title.PaintScaleLegend;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RectangleInsets;

import java.awt.*;

public class ColorMap {

    Color[] colors;

    public static ColorMap jet  = jet();
    public static ColorMap gray = gray();
    public static ColorMap hot  = hot();

    protected LookupPaintScale getPaintScale(double min, double max){

        LookupPaintScale scale = new LookupPaintScale(min, max, Color.BLACK);
        for (int i = 0; i < colors.length; i++){
            double value = min + i * (max - min) / (colors.length - 1);
            scale.add(value, colors[i]);
        }
        return scale;

    }

    protected PaintScaleLegend getPaintLegend(double min, double max){
        PaintScaleLegend legend = new PaintScaleLegend(getPaintScale(min, max), new NumberAxis("Test"));
        legend.setAxisOffset(0.0);
        legend.setAxisLocation(AxisLocation.BOTTOM_OR_RIGHT);
        legend.setPosition(RectangleEdge.RIGHT);
        legend.setID("Test");
        legend.setMargin(new RectangleInsets(5, 0, 50, 0));
        return legend;
    }

    private static ColorMap jet(){
        ColorMap colorMap = new ColorMap();
        colorMap.colors = new Color[]{
            new Color(0.0000f, 0.0000f, 0.5625f),
            new Color(0.0000f, 0.0000f, 0.6250f),
            new Color(0.0000f, 0.0000f, 0.6875f),
            new Color(0.0000f, 0.0000f, 0.7500f),
            new Color(0.0000f, 0.0000f, 0.8125f),
            new Color(0.0000f, 0.0000f, 0.8750f),
            new Color(0.0000f, 0.0000f, 0.9375f),
            new Color(0.0000f, 0.0000f, 1.0000f),
            new Color(0.0000f, 0.0625f, 1.0000f),
            new Color(0.0000f, 0.1250f, 1.0000f),
            new Color(0.0000f, 0.1875f, 1.0000f),
            new Color(0.0000f, 0.2500f, 1.0000f),
            new Color(0.0000f, 0.3125f, 1.0000f),
            new Color(0.0000f, 0.3750f, 1.0000f),
            new Color(0.0000f, 0.4375f, 1.0000f),
            new Color(0.0000f, 0.5000f, 1.0000f),
            new Color(0.0000f, 0.5625f, 1.0000f),
            new Color(0.0000f, 0.6250f, 1.0000f),
            new Color(0.0000f, 0.6875f, 1.0000f),
            new Color(0.0000f, 0.7500f, 1.0000f),
            new Color(0.0000f, 0.8125f, 1.0000f),
            new Color(0.0000f, 0.8750f, 1.0000f),
            new Color(0.0000f, 0.9375f, 1.0000f),
            new Color(0.0000f, 1.0000f, 1.0000f),
            new Color(0.0625f, 1.0000f, 0.9375f),
            new Color(0.1250f, 1.0000f, 0.8750f),
            new Color(0.1875f, 1.0000f, 0.8125f),
            new Color(0.2500f, 1.0000f, 0.7500f),
            new Color(0.3125f, 1.0000f, 0.6875f),
            new Color(0.3750f, 1.0000f, 0.6250f),
            new Color(0.4375f, 1.0000f, 0.5625f),
            new Color(0.5000f, 1.0000f, 0.5000f),
            new Color(0.5625f, 1.0000f, 0.4375f),
            new Color(0.6250f, 1.0000f, 0.3750f),
            new Color(0.6875f, 1.0000f, 0.3125f),
            new Color(0.7500f, 1.0000f, 0.2500f),
            new Color(0.8125f, 1.0000f, 0.1875f),
            new Color(0.8750f, 1.0000f, 0.1250f),
            new Color(0.9375f, 1.0000f, 0.0625f),
            new Color(1.0000f, 1.0000f, 0.0000f),
            new Color(1.0000f, 0.9375f, 0.0000f),
            new Color(1.0000f, 0.8750f, 0.0000f),
            new Color(1.0000f, 0.8125f, 0.0000f),
            new Color(1.0000f, 0.7500f, 0.0000f),
            new Color(1.0000f, 0.6875f, 0.0000f),
            new Color(1.0000f, 0.6250f, 0.0000f),
            new Color(1.0000f, 0.5625f, 0.0000f),
            new Color(1.0000f, 0.5000f, 0.0000f),
            new Color(1.0000f, 0.4375f, 0.0000f),
            new Color(1.0000f, 0.3750f, 0.0000f),
            new Color(1.0000f, 0.3125f, 0.0000f),
            new Color(1.0000f, 0.2500f, 0.0000f),
            new Color(1.0000f, 0.1875f, 0.0000f),
            new Color(1.0000f, 0.1250f, 0.0000f),
            new Color(1.0000f, 0.0625f, 0.0000f),
            new Color(1.0000f, 0.0000f, 0.0000f),
            new Color(0.9375f, 0.0000f, 0.0000f),
            new Color(0.8750f, 0.0000f, 0.0000f),
            new Color(0.8125f, 0.0000f, 0.0000f),
            new Color(0.7500f, 0.0000f, 0.0000f),
            new Color(0.6875f, 0.0000f, 0.0000f),
            new Color(0.6250f, 0.0000f, 0.0000f),
            new Color(0.5625f, 0.0000f, 0.0000f),
            new Color(0.5000f, 0.0000f, 0.0000f),
        };

        return colorMap;
    }

    private static ColorMap gray(){
        ColorMap colorMap = new ColorMap();
        colorMap.colors = new Color[]{
            new Color(0.0000f, 0.0000f, 0.0000f),
            new Color(0.0159f, 0.0159f, 0.0159f),
            new Color(0.0317f, 0.0317f, 0.0317f),
            new Color(0.0476f, 0.0476f, 0.0476f),
            new Color(0.0635f, 0.0635f, 0.0635f),
            new Color(0.0794f, 0.0794f, 0.0794f),
            new Color(0.0952f, 0.0952f, 0.0952f),
            new Color(0.1111f, 0.1111f, 0.1111f),
            new Color(0.1270f, 0.1270f, 0.1270f),
            new Color(0.1429f, 0.1429f, 0.1429f),
            new Color(0.1587f, 0.1587f, 0.1587f),
            new Color(0.1746f, 0.1746f, 0.1746f),
            new Color(0.1905f, 0.1905f, 0.1905f),
            new Color(0.2063f, 0.2063f, 0.2063f),
            new Color(0.2222f, 0.2222f, 0.2222f),
            new Color(0.2381f, 0.2381f, 0.2381f),
            new Color(0.2540f, 0.2540f, 0.2540f),
            new Color(0.2698f, 0.2698f, 0.2698f),
            new Color(0.2857f, 0.2857f, 0.2857f),
            new Color(0.3016f, 0.3016f, 0.3016f),
            new Color(0.3175f, 0.3175f, 0.3175f),
            new Color(0.3333f, 0.3333f, 0.3333f),
            new Color(0.3492f, 0.3492f, 0.3492f),
            new Color(0.3651f, 0.3651f, 0.3651f),
            new Color(0.3810f, 0.3810f, 0.3810f),
            new Color(0.3968f, 0.3968f, 0.3968f),
            new Color(0.4127f, 0.4127f, 0.4127f),
            new Color(0.4286f, 0.4286f, 0.4286f),
            new Color(0.4444f, 0.4444f, 0.4444f),
            new Color(0.4603f, 0.4603f, 0.4603f),
            new Color(0.4762f, 0.4762f, 0.4762f),
            new Color(0.4921f, 0.4921f, 0.4921f),
            new Color(0.5079f, 0.5079f, 0.5079f),
            new Color(0.5238f, 0.5238f, 0.5238f),
            new Color(0.5397f, 0.5397f, 0.5397f),
            new Color(0.5556f, 0.5556f, 0.5556f),
            new Color(0.5714f, 0.5714f, 0.5714f),
            new Color(0.5873f, 0.5873f, 0.5873f),
            new Color(0.6032f, 0.6032f, 0.6032f),
            new Color(0.6190f, 0.6190f, 0.6190f),
            new Color(0.6349f, 0.6349f, 0.6349f),
            new Color(0.6508f, 0.6508f, 0.6508f),
            new Color(0.6667f, 0.6667f, 0.6667f),
            new Color(0.6825f, 0.6825f, 0.6825f),
            new Color(0.6984f, 0.6984f, 0.6984f),
            new Color(0.7143f, 0.7143f, 0.7143f),
            new Color(0.7302f, 0.7302f, 0.7302f),
            new Color(0.7460f, 0.7460f, 0.7460f),
            new Color(0.7619f, 0.7619f, 0.7619f),
            new Color(0.7778f, 0.7778f, 0.7778f),
            new Color(0.7937f, 0.7937f, 0.7937f),
            new Color(0.8095f, 0.8095f, 0.8095f),
            new Color(0.8254f, 0.8254f, 0.8254f),
            new Color(0.8413f, 0.8413f, 0.8413f),
            new Color(0.8571f, 0.8571f, 0.8571f),
            new Color(0.8730f, 0.8730f, 0.8730f),
            new Color(0.8889f, 0.8889f, 0.8889f),
            new Color(0.9048f, 0.9048f, 0.9048f),
            new Color(0.9206f, 0.9206f, 0.9206f),
            new Color(0.9365f, 0.9365f, 0.9365f),
            new Color(0.9524f, 0.9524f, 0.9524f),
            new Color(0.9683f, 0.9683f, 0.9683f),
            new Color(0.9841f, 0.9841f, 0.9841f),
            new Color(1.0000f, 1.0000f, 1.0000f),
        };

        return colorMap;
    }

    private static ColorMap hot(){
        ColorMap colorMap = new ColorMap();
        colorMap.colors = new Color[]{
            new Color(0.0417f, 0.0000f, 0.0000f),
            new Color(0.0833f, 0.0000f, 0.0000f),
            new Color(0.1250f, 0.0000f, 0.0000f),
            new Color(0.1667f, 0.0000f, 0.0000f),
            new Color(0.2083f, 0.0000f, 0.0000f),
            new Color(0.2500f, 0.0000f, 0.0000f),
            new Color(0.2917f, 0.0000f, 0.0000f),
            new Color(0.3333f, 0.0000f, 0.0000f),
            new Color(0.3750f, 0.0000f, 0.0000f),
            new Color(0.4167f, 0.0000f, 0.0000f),
            new Color(0.4583f, 0.0000f, 0.0000f),
            new Color(0.5000f, 0.0000f, 0.0000f),
            new Color(0.5417f, 0.0000f, 0.0000f),
            new Color(0.5833f, 0.0000f, 0.0000f),
            new Color(0.6250f, 0.0000f, 0.0000f),
            new Color(0.6667f, 0.0000f, 0.0000f),
            new Color(0.7083f, 0.0000f, 0.0000f),
            new Color(0.7500f, 0.0000f, 0.0000f),
            new Color(0.7917f, 0.0000f, 0.0000f),
            new Color(0.8333f, 0.0000f, 0.0000f),
            new Color(0.8750f, 0.0000f, 0.0000f),
            new Color(0.9167f, 0.0000f, 0.0000f),
            new Color(0.9583f, 0.0000f, 0.0000f),
            new Color(1.0000f, 0.0000f, 0.0000f),
            new Color(1.0000f, 0.0417f, 0.0000f),
            new Color(1.0000f, 0.0833f, 0.0000f),
            new Color(1.0000f, 0.1250f, 0.0000f),
            new Color(1.0000f, 0.1667f, 0.0000f),
            new Color(1.0000f, 0.2083f, 0.0000f),
            new Color(1.0000f, 0.2500f, 0.0000f),
            new Color(1.0000f, 0.2917f, 0.0000f),
            new Color(1.0000f, 0.3333f, 0.0000f),
            new Color(1.0000f, 0.3750f, 0.0000f),
            new Color(1.0000f, 0.4167f, 0.0000f),
            new Color(1.0000f, 0.4583f, 0.0000f),
            new Color(1.0000f, 0.5000f, 0.0000f),
            new Color(1.0000f, 0.5417f, 0.0000f),
            new Color(1.0000f, 0.5833f, 0.0000f),
            new Color(1.0000f, 0.6250f, 0.0000f),
            new Color(1.0000f, 0.6667f, 0.0000f),
            new Color(1.0000f, 0.7083f, 0.0000f),
            new Color(1.0000f, 0.7500f, 0.0000f),
            new Color(1.0000f, 0.7917f, 0.0000f),
            new Color(1.0000f, 0.8333f, 0.0000f),
            new Color(1.0000f, 0.8750f, 0.0000f),
            new Color(1.0000f, 0.9167f, 0.0000f),
            new Color(1.0000f, 0.9583f, 0.0000f),
            new Color(1.0000f, 1.0000f, 0.0000f),
            new Color(1.0000f, 1.0000f, 0.0625f),
            new Color(1.0000f, 1.0000f, 0.1250f),
            new Color(1.0000f, 1.0000f, 0.1875f),
            new Color(1.0000f, 1.0000f, 0.2500f),
            new Color(1.0000f, 1.0000f, 0.3125f),
            new Color(1.0000f, 1.0000f, 0.3750f),
            new Color(1.0000f, 1.0000f, 0.4375f),
            new Color(1.0000f, 1.0000f, 0.5000f),
            new Color(1.0000f, 1.0000f, 0.5625f),
            new Color(1.0000f, 1.0000f, 0.6250f),
            new Color(1.0000f, 1.0000f, 0.6875f),
            new Color(1.0000f, 1.0000f, 0.7500f),
            new Color(1.0000f, 1.0000f, 0.8125f),
            new Color(1.0000f, 1.0000f, 0.8750f),
            new Color(1.0000f, 1.0000f, 0.9375f),
            new Color(1.0000f, 1.0000f, 1.0000f),
        };

        return colorMap;
    }

}
