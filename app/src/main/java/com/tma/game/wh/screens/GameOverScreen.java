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

public class GameOverScreen extends ScreenAdapter {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;


    //Table
    private Image returnButton;
    private Image settingButton;
    private Label scoreLabel;
    private Image restartButton;

    //Game
    private int score = 0;

    public GameOverScreen(Main main, int score) {
        this.main = main;
        this.score = score;
        GameState.setState(GameState.GAME_OVER);
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin.json"));

        Table table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();
        table1.setBackground(skin.getDrawable("back_menu"));

        returnButton = new Image(skin, "return");
        table1.add(returnButton).padLeft(25.0f).padTop(27.0f).expandX().align(Align.topLeft);

        settingButton = new Image(skin, "setting");
        table1.add(settingButton).padRight(27.0f).padTop(27.0f).expandX().align(Align.topRight);

        table1.row();
        scoreLabel = new Label("YOUR SCORE: ", skin);
        table1.add(scoreLabel).padTop(27.0f).expandX().align(Align.top).colspan(2);

        table1.row();
        Label label = new Label("GAME OVER", skin);
        table1.add(label).padTop(115.0f).expandX().align(Align.top).colspan(2);

        table1.row();
        restartButton = new Image(skin, "restart");
        table1.add(restartButton).padTop(80.0f).expand().align(Align.top).colspan(2);
        table.add(table1).minWidth(360.0f).minHeight(800.0f).maxWidth(360.0f).maxHeight(800.0f);
        stage.addActor(table);

        setClickListeners();
        scoreLabel.setText("YOUR SCORE: " + score);
        if (score > 10) {
            GameSD.setSpin(GameSD.getSpin() + 1);
        }
    }

    private void setClickListeners() {
        returnButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new MainMenuScreen(main));
            }
        });

        restartButton.addListener(new ClickListener() {
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

