package com.example.arina.game;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

import Graphics.Models.Scene;
import Graphics.View.OwnSurfaceView;
import Settings.EngineSettins;

/**
 * Created by Arina on 19.01.2017.
 */

public class GameScreen extends Activity implements GameThread.CallbackThread {

    OwnSurfaceView ownSurfaceView;
    Scene scene;
    Sprites sprites;
    GameThread gameThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        EngineSettins.generateSettings(displayMetrics.widthPixels, displayMetrics.heightPixels);
        EngineSettins.setTargetFrameRate(25);

        sprites = new Sprites(this);

        scene = new Scene(Scene.ORIENTATION_VER, 450 , 450 ,4);

        for (int i = 0; i < 3; i++){
            scene.setCurrentLayer(i);
            for (int j = 0; j < 3; j++){
                if (i == 0){
                    scene.addObjectOnCurrentLayer(sprites.portals_layer1[j]);
                } else if(i == 1){
                    scene.addObjectOnCurrentLayer(sprites.portals_layer2[j]);
                } else {
                    scene.addObjectOnCurrentLayer(sprites.portals_layer3[j]);
                }
            }
        }

        scene.setCurrentLayer(3);

        for(int i = 0; i < 6; i++){
            scene.addObjectOnCurrentLayer(sprites.magnets[i]);
        }

        sprites.players_object.setXYCoordinates(310, 1170);
        scene.addObjectOnCurrentLayer(sprites.players_object);

        ownSurfaceView = new OwnSurfaceView(this, scene);

        ownSurfaceView.setRed(240);
        ownSurfaceView.setBlue(128);
        ownSurfaceView.setGreen(128);

        this.setContentView(ownSurfaceView);

        gameThread = new GameThread(this, scene, sprites, getIntent().getExtras().getInt("type_of_control"));
        gameThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        ownSurfaceView.surfaceDestroyed(ownSurfaceView.getHolder());
        boolean retry = true;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }


    @Override
    public void threadFinish(int result_of_game) {
        if (result_of_game == 0){
            scene.addObjectOnCurrentLayer(sprites.user_fail);
        } else {
            scene.addObjectOnCurrentLayer(sprites.user_win);
        }
    }
}
