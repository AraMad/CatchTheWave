package Graphics.Models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Arina on 09.01.2017.
 * source with tutorial:
 * http://davidmd.ru/2011/08/09/%d0%bf%d0%b8%d1%88%d0%b5%d0%bc-%d0%b4%d0%b2%d0%b8%d0%b6%d0%be%d0%ba-%d0%b8%d0%b3%d1%80%d1%8b-%d0%bf%d0%be%d0%b4-android-tutorial-%d1%87%d0%b0%d1%81%d1%82%d1%8c-1/
 */

abstract public class BasicPrimitive {

    public static final int TYPE_POINT = 1;
    public static final int TYPE_LINE_SEGMENT = 2;
    //public static final int TYPE_POLYLINE = 3;
    //public static final int TYPE_RECT = 4;
    //public static final int TYPE_CIRCLE = 5;
    public static final int TYPE_SPRITE = 6;

    public int type_of_primitive;

    public abstract void updateParametersOfPrimitive();
    public abstract boolean isPointGetIntoPrimitive(float x_of_point, float y_of_point);
    public abstract void draw(Canvas canvas, Paint paint);
}
