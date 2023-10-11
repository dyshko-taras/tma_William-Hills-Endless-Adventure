package com.tma.game.wh.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tma.game.wh.Main;
import com.tma.game.wh.tools.GameSD;

public class SettingsScreen extends ScreenAdapter {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;


    //Table
    private Image returnButton;
    private Image musicOn;
    private Image musicOff;
    private Image sfxOn;
    private Image sfxOff;


    //Actors


    //Game
    private int score = 0;
    private float timer = 60;


    //Levels


    public SettingsScreen(Main main) {
        this.main = main;
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin.json"));

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("back_setting"));

        returnButton = new Image(skin, "return");
        table1.add(returnButton).padLeft(25.0f).padTop(27.0f).expandX().align(Align.topLeft).colspan(3);

        table1.row();
        Label label = new Label("SETTING", skin);
        table1.add(label).padTop(32.0f).expandX().align(Align.top).colspan(3);

        table1.row();
        label = new Label("Music", skin);
        table1.add(label).padLeft(42.0f).padTop(155.0f).align(Align.topLeft);

        musicOn = new Image(skin, "music_on");
        table1.add(musicOn).padRight(8.0f).padTop(135.0f).expandX().align(Align.topRight);

        musicOff = new Image(skin, "music_off");
        table1.add(musicOff).padRight(41.0f).padTop(135.0f).align(Align.topRight);

        table1.row();
        label = new Label("SFX", skin);
        table1.add(label).padLeft(42.0f).padTop(105.0f).expandY().align(Align.topLeft);

        sfxOn = new Image(skin, "sfx_on");
        table1.add(sfxOn).padRight(8.0f).padTop(85.0f).expandX().align(Align.topRight);

        sfxOff = new Image(skin, "sfx_off");
        table1.add(sfxOff).padRight(41.0f).padTop(85.0f).align(Align.topRight);
        table.add(table1).minWidth(360.0f).minHeight(800.0f).maxWidth(360.0f).maxHeight(800.0f);
        stage.addActor(table);

        setClickListeners();
    }

    private void setClickListeners() {
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MainMenuScreen(main));
            }
        });

        musicOn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSD.setMusicOn(true);
                main.playMusic(GameSD.getMusicOn());
            }
        });

        musicOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSD.setMusicOn(false);
                main.playMusic(GameSD.getMusicOn());
            }
        });

        sfxOn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSD.setSFXOn(true);
            }
        });

        sfxOff.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameSD.setSFXOn(false);
            }
        });
    }


    public void render(float delta) {
        renderCamera();
    }

    public void resize(int width, int height) {
        resizeCamera(width, height);
    }

    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    /////Camera
    private void showCameraAndStage() {
        viewport = new StretchViewport(SCREEN_WIDTH, SCREEN_HEIGHT);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);
    }

    private void renderCamera() {
        ScreenUtils.clear(Color.BLACK);

        viewport.apply();
        stage.act();
        stage.draw();
    }

    private void resizeCamera(int width, int height) {
        viewport.update(width, height, true);
    }
    ////////

}

