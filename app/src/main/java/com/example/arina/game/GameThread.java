package com.example.arina.game;


import android.content.Context;
import android.util.Log;

import Graphics.Models.Scene;
import Settings.Constants;
import UsersInput.LightHandler;
import UsersInput.MagneticFieldHandler;

/**
 * Created by Arina on 20.01.2017.
 */

public class GameThread extends Thread{

    private GameLogic gameLogic;
    Scene scene;
    Sprites sprites;

    int type_of_control;
    MagneticFieldHandler magneticFieldHandler;
    LightHandler lightHandler;

    private float start_X_coord = 0;
    private float finish_X_coord = 600;

    interface CallbackThread{
        void threadFinish(int result_of_game);
    }

    CallbackThread callbackThread;


    public GameThread(Context context, Scene scene, Sprites sprites, int type) {
        this.scene = scene;
        this.sprites = sprites;

        type_of_control = type;
        if (type == 0){
            magneticFieldHandler = new MagneticFieldHandler(context);
        } else {
            lightHandler = new LightHandler(context);
        }

        callbackThread  = (CallbackThread) context;
    }

    @Override
    public void run() {
        int result = 1;
        if (type_of_control == 0){
            gameLogic = new GameLogic(200, 10, magneticFieldHandler.getMagneticField(), 1190);
            while (!gameLogic.is_win(sprites.players_object)){
                movePoints();
                gameLogic.move(sprites.players_object,magneticFieldHandler.getMagneticField());
                if (gameLogic.is_player_died(sprites.players_object, sprites)){
                    result = 0;
                    break;
                }
            }
            callbackThread.threadFinish(result);
        } else {
            gameLogic = new GameLogic(200, 10, lightHandler.getIllumination(), 1190);
            while (!gameLogic.is_win(sprites.players_object)){
                movePoints();
                gameLogic.move(sprites.players_object,lightHandler.getIllumination());
                if (gameLogic.is_player_died(sprites.players_object, sprites)){
                    result = 0;
                    break;
                }
            }
            callbackThread.threadFinish(result);
        }
    }

    private void movePoints(){
        for (int i = 0; i < 3; i++){
            if (sprites.portals_layer1[i].getCoordinate_X() == start_X_coord){
                sprites.portals_layer1[i].moveByStraightLine(finish_X_coord, sprites.portals_layer1[i].getCoordinate_Y(), Constants.SLOVE_SPEED);
            } else if(sprites.portals_layer1[i].getCoordinate_X() == finish_X_coord){
                sprites.portals_layer1[i].moveByStraightLine(start_X_coord, sprites.portals_layer1[i].getCoordinate_Y(), Constants.SLOVE_SPEED);
            }

            if (sprites.portals_layer2[i].getCoordinate_X() == start_X_coord){
                sprites.portals_layer2[i].moveByStraightLine(finish_X_coord, sprites.portals_layer2[i].getCoordinate_Y(), Constants.MIDLLE_SPEED);
            } else if(sprites.portals_layer2[i].getCoordinate_X() == finish_X_coord){
                sprites.portals_layer2[i].moveByStraightLine(start_X_coord, sprites.portals_layer2[i].getCoordinate_Y(), Constants.MIDLLE_SPEED);
            }

            if (sprites.portals_layer3[i].getCoordinate_X() == start_X_coord){
                sprites.portals_layer3[i].moveByStraightLine(finish_X_coord, sprites.portals_layer3[i].getCoordinate_Y(), Constants.FAST_SPEED);
            } else if(sprites.portals_layer3[i].getCoordinate_X() == finish_X_coord){
                sprites.portals_layer3[i].moveByStraightLine(start_X_coord, sprites.portals_layer3[i].getCoordinate_Y(), Constants.FAST_SPEED);
            }
        }

    }

}
