package com.tma.game.wh;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tma.game.wh.screens.MainMenuScreen;
import com.tma.game.wh.tools.GameSD;


public class Main extends Game {

	public static float SCREEN_WIDTH = 360; //360
	public static float SCREEN_HEIGHT = 800; //800

	public SpriteBatch batch;
	public Music music;

	public AndroidApplication androidApplication;

	public Main(AndroidApplication androidApplication) {
		this.androidApplication = androidApplication;
	}

	public Main() {
	}


	public void create() {
		initAssets();
		setSettings();
		this.setScreen(new MainMenuScreen(this));
	}

	public void render() {
		super.render();
	}

	public void resize(int width, int height) {
		super.resize(width, height);
	}

	public void dispose() {
		batch.dispose();
	}

	public void setSettings() {
		GameSD.init();
		playMusic(GameSD.getMusicOn());
	}

	public void initAssets() {
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
	}

	public void playMusic(boolean isMusicOn) {
		if (isMusicOn) {
			music.setLooping(true);
			music.setVolume(0.2f);
			music.play();
		} else {
			music.stop();
		}
	}
}

