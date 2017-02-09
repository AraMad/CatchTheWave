package UsersInput;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Arina on 12.01.2017.
 */

public class ScreenTouchHandler implements View.OnTouchListener {

    private float coordinate_of_touch_X;
    private float coordinate_of_touch_Y;
    private String state_of_touch;

    public ScreenTouchHandler() {
        coordinate_of_touch_X = -1;
        coordinate_of_touch_Y = -1;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        coordinate_of_touch_X = event.getX();
        coordinate_of_touch_Y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                state_of_touch = "DOWN";
                break;
            case MotionEvent.ACTION_MOVE:
                state_of_touch = "MOVE";
                break;
            case MotionEvent.ACTION_UP:
                state_of_touch = "UP";
                break;
            case MotionEvent.ACTION_CANCEL:
                state_of_touch = "CANCEL";
                break;
        }
        Log.i("game touch: ", state_of_touch);
        return true;
    }

    public float getCoordinateOfTouch_X() {
        return coordinate_of_touch_X;
    }

    public float getCoordinateOfTouch_Y() {
        return coordinate_of_touch_Y;
    }

    public String getStateOfTouch() {
        return state_of_touch;
    }
}
