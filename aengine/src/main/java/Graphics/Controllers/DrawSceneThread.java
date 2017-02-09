package Graphics.Controllers;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.SurfaceHolder;

import Graphics.Models.Layer;
import Graphics.Models.Scene;
import Settings.EngineSettins;

/**
 * Created by Arina on 19.01.2017.
 */

public class DrawSceneThread extends Thread {

    private boolean is_run = false;
    private SurfaceHolder surfaceHolder;

    Scene scene;

    private static int red_RGB = 255;
    private static int blue_RGB = 219;
    private static int green_RGB = 203;

    public DrawSceneThread(SurfaceHolder surfaceHolder, Scene scene){
        this.surfaceHolder = surfaceHolder;
        this.scene = scene;
    }

    public void setIsRun(boolean is_run) {
        this.is_run = is_run;
    }

    public void setCollorsForBackgroundRGB(int red, int green, int blue){
        red_RGB = red;
        green_RGB = green;
        blue_RGB = blue;
    }

    @Override
    public void run() {
        Canvas canvas;

        while (is_run){

            canvas = null;

            try {

                canvas = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {

                    //canvas.drawRGB(red_RGB, green_RGB, blue_RGB);

                    Paint gradPaint = new Paint();
                    gradPaint.setShader(new LinearGradient(0,0,0,1000,Color.rgb(83, 190, 100),Color.rgb(48, 79, 254), Shader.TileMode.CLAMP));
                    canvas.drawPaint(gradPaint);

                    for (int i = 0; i < scene.getCountOfLayersOnScene(); i++) {
                        Layer layer = scene.getLayerByIndex(i);
                        if (layer != null && layer.isVisible()) {
                            for (int j = 0; j < layer.getNumberOfObjectsOnLayer(); j++) {
                                layer.getObjectByIndex(j).draw(canvas, layer.getPaint());
                            }
                        }
                    }

                    scene.updateAllLayers();
                    EngineSettins.newFrame();
                }
            }
            finally {

                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}
