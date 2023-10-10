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
import com.tma.game.wh.tools.GameState;

public class MainMenuScreen extends ScreenAdapter {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;


    //Table
    private Image playButton;
    private Image settingButton;
    private Label spinLabel;


    public MainMenuScreen(Main main) {
        this.main = main;
        GameState.setState(GameState.MENU);
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin.json"));

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("back_menu"));

        Label label = new Label("WILLIAM HILL`S\n"
                + "ENDLESS ADVENTURE", skin);
        label.setAlignment(Align.top);
        table1.add(label).padTop(99.0f).align(Align.top).colspan(2);

        table1.row();
        playButton = new Image(skin, "play");
        table1.add(playButton).padTop(186.0f).expandX().align(Align.top).colspan(2);

        table1.row();
        spinLabel = new Label("SPIN x ", skin);
        table1.add(spinLabel).padLeft(30.0f).padBottom(39.0f).expandY().align(Align.bottomLeft);

        settingButton = new Image(skin, "setting");
        table1.add(settingButton).padRight(25.0f).padBottom(30.0f).align(Align.bottomRight);
        table.add(table1).minWidth(360.0f).minHeight(800.0f).maxWidth(360.0f).maxHeight(800.0f);
        stage.addActor(table);

        setClickListeners();
        spinLabel.setText("SPIN x " + GameSD.getSpin());
    }

    private void setClickListeners() {
        playButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new GameScreen(main));
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new SettingsScreen(main));
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

