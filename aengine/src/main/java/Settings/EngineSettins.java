package Settings;

import android.view.Display;

import java.util.Timer;

/**
 * Created by Arina on 09.01.2017.
 */

public class EngineSettins {

    private static Timer timer;
    //private static mFrameCounter tmp = new mFrameCounter();

    public static boolean is_auto_scale_needed = false;
    public static boolean is_full_screen_used = false;

    // width and height of the screen for which we have prepared a sprites
    public static int default_width_in_pixels = 480;
    public static int default_height_in_pixels = 800;

    public static int current_width;
    public static int current_height;

    public static float scale_factor_X = 1;
    public static float scale_factor_Y = 1;

    public static int target_frame_rate = 25;
    public static int real_frame_rate = 0;
    private static int frame_interval = 1000/target_frame_rate;
    public static int frame_counter = 0;

    public static void generateSettings(Display display)
    {
        current_width = display.getWidth();
        current_height = display.getHeight();
        scale_factor_X = current_width/(float) default_width_in_pixels;
        scale_factor_Y = current_height /(float) default_height_in_pixels;
        if (scale_factor_X != 1 || scale_factor_Y != 1)
        {
            is_auto_scale_needed = true;
        }
    }

    public static void generateSettings(int width, int height)
    {
        current_width = width;
        current_height = height;
        scale_factor_X = current_width/(float) default_width_in_pixels;
        scale_factor_Y = current_height /(float) default_height_in_pixels;
        if (scale_factor_X != 1 || scale_factor_Y != 1)
        {
            is_auto_scale_needed = true;
        }
    }

    public static void setTargetFrameRate(int frame)
    {
        target_frame_rate = frame;
        frame_interval = 1000/target_frame_rate;
        timer = new Timer();

        //timer.scheduleAtFixedRate( , 0, 1000);
    }

    public static void setDefaultWidthHeight(int screen_width_in_pixels, int scree_height_in_pixels)
    {
        default_width_in_pixels = screen_width_in_pixels;
        default_height_in_pixels = scree_height_in_pixels;
    }

    public static int getFrameRate()
    {
        return real_frame_rate;
    }

    public static void newFrame()
    {
       frame_counter++;
    }

    public static int getFrameInterval() {
        return frame_interval;
    }
}
