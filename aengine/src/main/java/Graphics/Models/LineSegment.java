package Graphics.Models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Arina on 09.01.2017.
 */

public class LineSegment extends BasicPrimitive{

    private Point beginning_point;
    private Point end_point;

    public LineSegment(float beginning_x, float beginning_y, float end_x, float end_y) {
        beginning_point = new Point(beginning_x, beginning_y);
        end_point = new Point(end_x, end_y);
        type_of_primitive = TYPE_LINE_SEGMENT;
    }

    public LineSegment(Point beginning_point, Point end_point) {
        this.beginning_point = beginning_point;
        this.end_point = end_point;
        type_of_primitive = TYPE_LINE_SEGMENT;
    }

    public Point getBeginning_point() {
        return beginning_point;
    }

    public void setBeginning_point(Point beginning_point) {
        this.beginning_point = beginning_point;
    }

    public Point getEnd_point() {
        return end_point;
    }

    public void setEnd_point(Point end_point) {
        this.end_point = end_point;
    }

    @Override
    public void updateParametersOfPrimitive() {
        beginning_point.updateParametersOfPrimitive();
        end_point.updateParametersOfPrimitive();
    }

    @Override
    public boolean isPointGetIntoPrimitive(float x_of_point, float y_of_point) {
        return false;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawLine(beginning_point.getCoordinate_X(), beginning_point.getCoordinate_Y(),
                end_point.getCoordinate_X(), end_point.getCoordinate_Y(),
                paint);
    }
}
