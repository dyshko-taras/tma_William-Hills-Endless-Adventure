package com.tma.game.wh.tools;

import com.badlogic.gdx.utils.Timer;

public class GameState {

    public static final int MENU = 0;
    public static final int GAME = 1;
    public static final int PAUSE = 2;
    public static final int GAME_OVER = 2;

    private static int currentState;

    public static void setState(int newState) {
        currentState = newState;
    }

    public static int getState() {
        return currentState;
    }

    private static int numPrint = 0;

    public static void printState() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                if (currentState == 0) System.out.println(String.format( "%d MENU", numPrint));
                else if (currentState == 1) System.out.println(String.format( "%d GAME", numPrint));
                else if (currentState == 2) System.out.println(String.format( "%d PAUSE", numPrint));
                else if (currentState == 3) System.out.println(String.format( "%d GAME_OVER", numPrint));
                numPrint++;
            }
        }, 0, 0.5f);
    }
}

