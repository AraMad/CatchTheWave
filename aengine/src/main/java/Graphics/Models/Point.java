package Graphics.Models;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by Arina on 09.01.2017.
 */

public class Point extends BasicPrimitive {

    protected float coordinate_X;
    protected float coordinate_Y;
    private float acceleration_X;
    private float acceleration_Y;
    private float speed_X;
    private float speed_Y;

    public Point(float x, float y){
        coordinate_X = x;
        coordinate_Y = y;
        type_of_primitive = TYPE_POINT;
    }

    public float getCoordinate_X() {
        return coordinate_X;
    }

    public float getCoordinate_Y() {
        return coordinate_Y;
    }

    public float getAcceleration_X() {
        return acceleration_X;
    }

    public float getAcceleration_Y() {
        return acceleration_Y;
    }

    public float getSpeed_X() {
        return speed_X;
    }

    public float getSpeed_Y() {
        return speed_Y;
    }

    public void setCoordinate_X(float coordinate_X) {
        this.coordinate_X = coordinate_X;
    }

    public void setCoordinate_Y(float coordinate_Y) {
        this.coordinate_Y = coordinate_Y;
    }

    public void setXYCoordinates(float coordinate_X, float coordinate_Y) {
        this.coordinate_X = coordinate_X;
        this.coordinate_Y = coordinate_Y;
    }

    public void setAcceleration_X(float acceleration_X) {
        this.acceleration_X = acceleration_X;
    }

    public void setAcceleration_Y(float acceleration_Y) {
        this.acceleration_Y = acceleration_Y;
    }

    public void setSpeed_X(float speed_X) {
        this.speed_X = speed_X;
    }

    public void setSpeed_Y(float speed_Y) {
        this.speed_Y = speed_Y;
    }

    @Override
    public void updateParametersOfPrimitive() {
        coordinate_X = coordinate_X + speed_X;
        coordinate_Y = coordinate_Y + speed_Y;
        speed_X = acceleration_X*speed_X;
        speed_Y = acceleration_Y*speed_Y;
    }

    @Override
    public boolean isPointGetIntoPrimitive(float x_of_point, float y_of_point) {
        if (coordinate_X == x_of_point && coordinate_Y == y_of_point){
            return true;
        }
        return false;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawPoint(coordinate_X, coordinate_Y, paint);
    }
}
