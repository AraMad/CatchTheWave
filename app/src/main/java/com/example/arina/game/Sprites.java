package com.example.arina.game;

import android.content.Context;

import Graphics.Models.Sprite;
import Settings.Constants;

/**
 * Created by Arina on 21.01.2017.
 */

public class Sprites {

    public Sprite players_object;
    public Sprite[] magnets;
    public Sprite[] portals_layer1;
    public Sprite[] portals_layer2;
    public Sprite[] portals_layer3;
    public Sprite background;

    public Sprite user_win;
    public Sprite user_fail;

    private final int HEIGHT_OF_FIRST_LINE = 1000;
    private final int HEIGHT_OF_SECOND_LINE = 650;
    private final int HEIGHT_OF_THRID_LINE = 300;

    private final int scale = 80;

    public Sprites(Context context) {

        user_fail = new Sprite(context.getResources(), R.drawable.fail);
        user_fail.setXYCoordinates(150, 500);
        user_win = new Sprite(context.getResources(), R.drawable.win);
        user_win.setXYCoordinates(150, 500);

        background = new Sprite(context.getResources(), R.drawable.game_screen_background);

        players_object = new Sprite(context.getResources(), R.drawable.fish);
        players_object.resize(100, 100);
        players_object.setXYCoordinates(310, 1170);

        magnets = new Sprite[6];
        for(int i = 0; i < 6; i++){
            if(i % 2 == 0){
                magnets[i] = new Sprite(context.getResources(), R.drawable.mag);
            } else {
                magnets[i] = new Sprite(context.getResources(), R.drawable.mag2);
            }
            magnets[i].resize(scale, scale);
            magnets[i].setXYCoordinates(i*150, 0);
        }

        portals_layer1 = new Sprite[3];
        for (int i = 0; i < 3; i++){
            portals_layer1[i] = new Sprite(context.getResources(), R.drawable.obstacle1);
            portals_layer1[i].resize(scale, scale);
            portals_layer1[i].setXYCoordinates(310, 1200);
        }
        portals_layer1[0].setXYCoordinates(500, HEIGHT_OF_FIRST_LINE);
        portals_layer1[0].moveByStraightLine(0, HEIGHT_OF_FIRST_LINE, Constants.SLOVE_SPEED);
        portals_layer1[1].setXYCoordinates(150, HEIGHT_OF_FIRST_LINE);
        portals_layer1[1].moveByStraightLine(600, HEIGHT_OF_FIRST_LINE, Constants.SLOVE_SPEED);
        portals_layer1[2].setXYCoordinates(310, HEIGHT_OF_FIRST_LINE);
        portals_layer1[2].moveByStraightLine(0, HEIGHT_OF_FIRST_LINE, Constants.SLOVE_SPEED);

        portals_layer2 = new Sprite[3];
        for (int i = 0; i < 3; i++){
            portals_layer2[i] = new Sprite(context.getResources(), R.drawable.obstacle2);
            portals_layer2[i].resize(scale, scale);
        }
        portals_layer2[0].setXYCoordinates(550, HEIGHT_OF_SECOND_LINE);
        portals_layer2[0].moveByStraightLine(0, HEIGHT_OF_SECOND_LINE, Constants.MIDLLE_SPEED);
        portals_layer2[1].setXYCoordinates(50, HEIGHT_OF_SECOND_LINE);
        portals_layer2[1].moveByStraightLine(600, HEIGHT_OF_SECOND_LINE, Constants.MIDLLE_SPEED);
        portals_layer2[2].setXYCoordinates(200, HEIGHT_OF_SECOND_LINE);
        portals_layer2[2].moveByStraightLine(0, HEIGHT_OF_SECOND_LINE, Constants.MIDLLE_SPEED);

        portals_layer3 = new  Sprite[3];
        for (int i = 0; i < 3; i++){
            portals_layer3[i] = new Sprite(context.getResources(), R.drawable.obstacle3);
            portals_layer3[i].resize(scale, scale);
        }
        portals_layer3[0].setXYCoordinates(430, HEIGHT_OF_THRID_LINE);
        portals_layer3[0].moveByStraightLine(0, HEIGHT_OF_THRID_LINE, Constants.FAST_SPEED);
        portals_layer3[1].setXYCoordinates(50, HEIGHT_OF_THRID_LINE);
        portals_layer3[1].moveByStraightLine(600, HEIGHT_OF_THRID_LINE, Constants.FAST_SPEED);
        portals_layer3[2].setXYCoordinates(310, HEIGHT_OF_THRID_LINE);
        portals_layer3[2].moveByStraightLine(0, HEIGHT_OF_THRID_LINE, Constants.FAST_SPEED);
    }
}
