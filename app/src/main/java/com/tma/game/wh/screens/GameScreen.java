package com.tma.game.wh.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.tma.game.wh.Main;
import com.tma.game.wh.actors.BackgroundGroup;
import com.tma.game.wh.actors.BoxActor;
import com.tma.game.wh.actors.MenActor;
import com.tma.game.wh.tools.GameSD;
import com.tma.game.wh.tools.GameState;

import java.util.Iterator;

public class GameScreen extends ScreenAdapter {

    public static final float SCREEN_WIDTH = Main.SCREEN_WIDTH;
    public static final float SCREEN_HEIGHT = Main.SCREEN_HEIGHT;

    //Viewports
    private final Main main;
    private Viewport viewport;
    private Skin skin;
    private Stage stage;


    //Table
    private Table table;
    private Image pauseButton;
    private Image settingButton;
    private Label scoreLabel;
    private Label pauseLabel;
    private Image spinButton;
    private Label numSpinsLabel;


    //Actors
    private BackgroundGroup backgroundGroup;
    private Array<BoxActor> boxActors0 = new Array<>();
    private Group boxGroup0 = new Group();
    private Array<BoxActor> boxActors1 = new Array<>();
    private Group boxGroup1 = new Group();
    private Array<BoxActor> boxActors2 = new Array<>();
    private Group boxGroup2 = new Group();
    private MenActor men;


    //Game
    private int score = 0;
    private float speed = 200.0f;
    private Timer timerSpawnBox = new Timer();
    private boolean isSpinActive = false;
    private Timer timerSpinActive = new Timer();
    private Timer timerAddSpin = new Timer();
    private Sound coinSound;


    public GameScreen(Main main) {
        this.main = main;
        GameState.setState(GameState.GAME);
    }


    public void show() {
        showCameraAndStage();

        skin = new Skin(Gdx.files.internal("skin.json"));

        table = new Table();
        table.setFillParent(true);

        Table table1 = new Table();

        pauseButton = new Image(skin, "pause");
        table1.add(pauseButton).padLeft(25.0f).padTop(27.0f).align(Align.topLeft);

        settingButton = new Image(skin, "setting");
        table1.add(settingButton).padRight(27.0f).padTop(27.0f).expandX().align(Align.topRight);

        table1.row();
        scoreLabel = new Label("Score: 0", skin);
        table1.add(scoreLabel).padTop(19.0f).expandX().align(Align.top).colspan(2);

        table1.row();
        pauseLabel = new Label("PAUSE", skin, "f40");
        table1.add(pauseLabel).padTop(230.0f).expand().align(Align.top).colspan(2);

        table1.row();
        spinButton = new Image(skin, "spin");
        table1.add(spinButton).padLeft(20.0f).padBottom(17.0f).align(Align.bottomLeft);

        numSpinsLabel = new Label("x 0", skin, "f40");
        table1.add(numSpinsLabel).padLeft(8.0f).padBottom(31.0f).expandX().align(Align.bottomLeft).colspan(2);
        table.add(table1).minWidth(360.0f).minHeight(800.0f).maxWidth(360.0f).maxHeight(800.0f);

        pauseLabel.setVisible(false);
        numSpinsLabel.setText("x " + GameSD.getSpin());

        addMyActors();
        addActorsInStage();
        setClickListeners();
        spawnBox();
        addSpin();
        initInputAdapter();

        coinSound = Gdx.audio.newSound(Gdx.files.internal("coin.mp3"));
    }

    private void setClickListeners() {
        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (GameState.getState() != GameState.PAUSE) {
//                    lastState = GameState.getState();
                    pauseLabel.setVisible(true);
//                    ballActor.pauseMovement();
                    GameState.setState(GameState.PAUSE);
                } else {
                    pauseLabel.setVisible(false);
//                    ballActor.resumeMovement();
                    GameState.setState(GameState.GAME);
                }
            }
        });

        settingButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                main.setScreen(new SettingsScreen(main));
            }
        });

        spinButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (!isSpinActive && GameSD.getSpin() > 0) {
                    GameSD.setSpin(GameSD.getSpin() - 1);
                    numSpinsLabel.setText("x " + GameSD.getSpin());
                    isSpinActive = true;
                    spinButton.setDrawable(skin.getDrawable("spin_active"));
                    timerSpinActive.scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {
                            isSpinActive = false;
                            spinButton.setDrawable(skin.getDrawable("spin"));
                        }
                    },10);
                }
            }
        });
    }


    public void render(float delta) {
        renderCamera();
        update(delta);
    }

    private void update(float delta) {
        removeItems();
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
//        Gdx.input.setInputProcessor(stage);
    }

    private void renderCamera() {
        ScreenUtils.clear(Color.OLIVE);

        viewport.apply();
        stage.act();
        stage.draw();
    }

    private void resizeCamera(int width, int height) {
        viewport.update(width, height, true);
    }
    ////////

    private void addMyActors() {
        Image back = new Image(skin, "back_game");
        backgroundGroup = new BackgroundGroup(back,0,SCREEN_HEIGHT-back.getHeight(),back.getWidth(),back.getHeight(),speed);

        men = new MenActor(0,150,0.8f);

    }

    private void addActorsInStage() {
        stage.addActor(backgroundGroup);
        stage.addActor(table);
        stage.addActor(boxGroup0);
        stage.addActor(boxGroup1);
        stage.addActor(boxGroup2);
        stage.addActor(men);
    }

    private void spawnBox() {
        timerSpawnBox.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if (GameState.getState() == GameState.GAME) {
                    int randomImage = MathUtils.random(0, isSpinActive ? 10 : 2);
                    if (randomImage != 0) {
                        Image image = new Image(skin, randomImage == 1 ? "box" : "coin");
                        image.setName(randomImage == 1 ? "box" : "coin");
                        BoxActor boxActor = new BoxActor(image,SCREEN_WIDTH,130, 57, 57, speed);
                        boxActors0.add(boxActor);
                        boxGroup0.addActor(boxActor);
                    }
                    randomImage = MathUtils.random(0, isSpinActive ? 10 : 2);
                    if (randomImage != 0) {
                        Image image = new Image(skin, randomImage == 1 ? "box" : "coin");
                        image.setName(randomImage == 1 ? "box" : "coin");
                        BoxActor boxActor = new BoxActor(image,SCREEN_WIDTH,230, 57, 57, speed);
                        boxActors1.add(boxActor);
                        boxGroup1.addActor(boxActor);
                    }
                    randomImage = MathUtils.random(0, isSpinActive ? 10 : 2);
                    if (randomImage != 0) {
                        Image image = new Image(skin, randomImage == 1 ? "box" : "coin");
                        image.setName(randomImage == 1 ? "box" : "coin");
                        BoxActor boxActor = new BoxActor(image,SCREEN_WIDTH,330, 57, 57, speed);
                        boxActors2.add(boxActor);
                        boxGroup2.addActor(boxActor);
                    }
                }
            }
        }, 0, 1.5f);
    }

    private void removeItems() {
        if (GameState.getState() == GameState.GAME) {
            if (men.currentPosition == 0) {
                for(Iterator<BoxActor> iterator = boxActors0.iterator(); iterator.hasNext();) {
                    BoxActor box = iterator.next();
                    if (Intersector.overlaps(men.getRect(), box.getRect())) { /// remove items after a collision
                        if (box.getName().equals("box")) {
                            GameState.setState(GameState.GAME_OVER);
                            main.setScreen(new GameOverScreen(main, score));
                            timerSpinActive.clear();
                            timerSpawnBox.clear();
                            timerAddSpin.clear();
                        } else {
                            score++;
                            scoreLabel.setText("Score: " + score);
                            if (GameSD.getSFXOn()) {
                                coinSound.play();
                            }
                        }
                        iterator.remove();
                        boxGroup0.removeActor(box);
                    } else if (box.getX() + box.getWidth() < 0) { /// remove items outside the screen
                        iterator.remove();
                        boxGroup0.removeActor(box);
                    }
                }
            } else if (men.currentPosition == 1) {
                for(Iterator<BoxActor> iterator = boxActors1.iterator(); iterator.hasNext();) {
                    BoxActor box = iterator.next();
                    if (Intersector.overlaps(men.getRect(), box.getRect())) { /// remove items after a collision
                        if (box.getName().equals("box")) {
                            GameState.setState(GameState.GAME_OVER);
                            main.setScreen(new GameOverScreen(main, score));
                            timerSpinActive.clear();
                            timerSpawnBox.clear();
                            timerAddSpin.clear();
                        } else {
                            score++;
                            scoreLabel.setText("Score: " + score);
                            if (GameSD.getSFXOn()) {
                                coinSound.play();
                            }
                        }
                        iterator.remove();
                        boxGroup1.removeActor(box);
                    } else if (box.getX() + box.getWidth() < 0) { /// remove items outside the screen
                        iterator.remove();
                        boxGroup1.removeActor(box);
                    }
                }
            } else if (men.currentPosition == 2) {
                for(Iterator<BoxActor> iterator = boxActors2.iterator(); iterator.hasNext();) {
                    BoxActor box = iterator.next();
                    if (Intersector.overlaps(men.getRect(), box.getRect())) { /// remove items after a collision
                        if (box.getName().equals("box")) {
                            GameState.setState(GameState.GAME_OVER);
                            main.setScreen(new GameOverScreen(main, score));
                            timerSpinActive.clear();
                            timerSpawnBox.clear();
                            timerAddSpin.clear();
                        } else {
                            score++;
                            scoreLabel.setText("Score: " + score);
                            if (GameSD.getSFXOn()) {
                                coinSound.play();
                            }
                        }
                        iterator.remove();
                        boxGroup2.removeActor(box);
                    } else if (box.getX() + box.getWidth() < 0) { /// remove items outside the screen
                        iterator.remove();
                        boxGroup2.removeActor(box);
                    }
                }
            }
        }
    }


    private void initInputAdapter() {
        InputMultiplexer inputMultiplexer = new InputMultiplexer(stage, new InputAdapter() {
            float startY;
            final float SWIPE_THRESHOLD = 50;

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (GameState.getState() == GameState.GAME) {
                    startY = screenY;
                }
                return true;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                if (GameState.getState() == GameState.GAME) {
                    float deltaY = startY - screenY;
                    if (Math.abs(deltaY) > SWIPE_THRESHOLD) {
                        if (deltaY > 0) men.swipe(MenActor.SWIPE_UP);
                        else men.swipe(MenActor.SWIPE_DOWN);
                    } else {
                        men.jump();
                    }
                }
                return true;
            }
        });
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    private void addSpin() {
        timerSpinActive.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                if (GameState.getState() == GameState.GAME) {
                    GameSD.setSpin(GameSD.getSpin() + 1);
                    numSpinsLabel.setText("x " + GameSD.getSpin());
                }
            }
        }, 30, 30);
    }


}

