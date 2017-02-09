package com.example.arina.game;

import android.util.Log;

import Graphics.Models.Layer;
import Graphics.Models.Scene;
import Graphics.Models.Sprite;
import Settings.Constants;

/**
 * Created by Arina on 21.01.2017.
 */

public class GameLogic {

    private float border;
    private int step;
    private float control_level;

    private float start_point_Y;

    int num_of_layer;

    public GameLogic(float border, int step, float control_level, float start_point_Y) {
        this.border = border;
        this.step = step;
        this.control_level = control_level+200;
        this.start_point_Y = start_point_Y;
        num_of_layer = 0;
    }

    public boolean is_win(Sprite players_object){

        if (players_object.getCoordinate_Y() <= border){
            return true;
        }
        return false;
    }

    public void move(Sprite players_object, float current_control_level){
        if (current_control_level-200 > control_level){
            players_object.moveByStraightLine(players_object.getCoordinate_X(), players_object.getCoordinate_Y()-step, Constants.MIDLLE_SPEED);
            num_of_layer++;
            if (num_of_layer == 3){num_of_layer =0;}
        } else if (players_object.getCoordinate_Y() - step <= start_point_Y){
            players_object.moveByStraightLine(players_object.getCoordinate_X(), start_point_Y, Constants.MIDLLE_SPEED);
            num_of_layer = 0;
        } else if (players_object.getCoordinate_Y() != start_point_Y){
            players_object.moveByStraightLine(players_object.getCoordinate_X(), players_object.getCoordinate_Y()+step, Constants.MIDLLE_SPEED);
            num_of_layer--;
        }
    }

    public boolean is_player_died(Sprite players_object, Sprites sprites){

        for (int i = 0; i < 3; i++){
            switch (num_of_layer){
                case 0:{
                    if (players_object.isCollision(sprites.portals_layer1[i], 20)){
                        return true;
                    }
                    break;
                }
                case 1:{
                    if (players_object.isCollision(sprites.portals_layer2[i], 30)){
                        return true;
                    }
                    break;
                }
                case 2:{
                    if (players_object.isCollision(sprites.portals_layer3[i], 50)){
                        return true;
                    }
                    break;
                }
            }
        }
        return false;
    }

}
