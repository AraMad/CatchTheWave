package Graphics.View;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import Graphics.Controllers.DrawSceneThread;
import Settings.EngineSettins;
import Graphics.Models.Scene;

/**
 * Created by Arina on 10.01.2017.
 */

public class OwnSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    private Scene scene;
    DrawSceneThread drawSceneThread;

    private int red;
    private int green;
    private int blue;

    public OwnSurfaceView(Context context, Scene scene) {
        super(context);
        this.scene = scene;
        this.getHolder().addCallback(this);

        red = 0;
        green = 0;
        blue = 0;
    }

    public void setRed(int red) {
        this.red = red;
    }

    public void setGreen(int green) {
        this.green = green;
    }

    public void setBlue(int blue) {
        this.blue = blue;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawSceneThread = new DrawSceneThread(getHolder(), scene);
        drawSceneThread.setCollorsForBackgroundRGB(red, green, blue);
        drawSceneThread.setIsRun(true);
        drawSceneThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        EngineSettins.generateSettings(width, height);
        scene.setSceneWightAndHeight(width, height);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        drawSceneThread.setIsRun(false);
        while (retry) {
            try {
                drawSceneThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
