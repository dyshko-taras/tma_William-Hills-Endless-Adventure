package com.tma.game.wh.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class GameSD {
    private static final String PREFERENCES_NAME = "game_settings_william_hill";
    private static final String KEY_MUSIC_ON = "music_on";
    private static final String KEY_SFX_ON = "sfx_on";
    private static final String KEY_SPIN = "spin";


    private static Preferences preferences;

    public static void init() {
        preferences = Gdx.app.getPreferences(PREFERENCES_NAME);
    }


    //music
    public static boolean getMusicOn() {
        return preferences.getBoolean(KEY_MUSIC_ON, true);
    }

    public static void setMusicOn(boolean music) {
        preferences.putBoolean(KEY_MUSIC_ON, music);
        preferences.flush();
    }

    //sfx
    public static boolean getSFXOn() {
        return preferences.getBoolean(KEY_SFX_ON, true);
    }

    public static void setSFXOn(boolean sfx) {
        preferences.putBoolean(KEY_SFX_ON, sfx);
        preferences.flush();
    }

    //spin
    public static int getSpin() {
        return preferences.getInteger(KEY_SPIN, 0);
    }

    public static void setSpin(int spin) {
        preferences.putInteger(KEY_SPIN, spin);
        preferences.flush();
    }
}

