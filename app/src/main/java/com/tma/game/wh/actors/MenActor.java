package com.tma.game.wh.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.tma.game.wh.tools.AnimationActor;
import com.tma.game.wh.tools.GameSD;
import com.tma.game.wh.tools.GameState;

public class MenActor extends Actor {

    private Skin skin;
    private Image image;
    private float width;
    private float height;
    private float startY;
    private Rectangle rect = new Rectangle();

    private boolean isJumping = false;
    private float jumpVelocity = 300; // Швидкість прижку
    private float gravity = -500; // Гравітація

    public int currentPosition = 0;
    private boolean isSwiping = false;
    public static final int SWIPE_UP = 1;
    public static final int SWIPE_DOWN = -1;
    private Action swipeAction;

    private Sound jumpSound;
    private Sound swipeSound;

    private AnimationActor runAnimation;
    private AnimationActor jumpAnimation;

    public MenActor(float x, float y, float scale) {
        skin = new Skin(Gdx.files.internal("skinSpriteSheet.json"));
        image = new Image(skin.getDrawable("s0"));

        this.width = image.getWidth() * scale;
        this.height = image.getHeight() * scale;
        startY = y;
        setBounds(x, y, width, height);

        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.mp3"));
        swipeSound = Gdx.audio.newSound(Gdx.files.internal("swipe.mp3"));
        runAnimation = new AnimationActor(8, skin, "s", image);
        jumpAnimation = new AnimationActor(2, 20, skin, "s", image, 0.8f, 0);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        image.setBounds(getX(), getY(), getWidth(), getHeight());
        image.setOrigin(getOriginX(), getOriginY());
        image.setRotation(getRotation());
        image.setScale(getScaleX(), getScaleY());
        image.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameState.getState() == GameState.GAME) {
            runAnimation.start();
            if (isJumping && !isSwiping) {
                runAnimation.stop();
                jumpAnimation.start();
                float deltaY = jumpVelocity * delta + 0.5f * gravity * delta * delta;
                setY(getY() + deltaY);
                jumpVelocity += gravity * delta;

                if (getY() <= startY) {
                    setY(startY);
                    jumpVelocity = 300;
                    isJumping = false;
                    jumpAnimation.stop();
                    runAnimation.start();
                }
            }

            if (isSwiping && !isJumping) {

                addAction(swipeAction);
                isSwiping = false;
                startY = getY();
            }
        } else {
            runAnimation.stop();
            jumpAnimation.stop();
        }
    }

    public void jump() {
        if (!isJumping && !isSwiping) {
            isJumping = true;
            playSoundJump();
        }
    }

    private void playSoundJump() {
        if (GameSD.getSFXOn()) {
            jumpSound.play();
        }
    }

    private void playSoundSwipe() {
        if (GameSD.getSFXOn()) {
            swipeSound.play();
        }
    }

    public Rectangle getRect() {
        rect.x = getX() + 20;
        rect.y = getY();
        rect.width = getWidth() - 40;
        rect.height = getHeight();
        return rect;
    }

    public void swipe(int direction) {
        if (!isSwiping && !isJumping) {
            if (direction == SWIPE_UP && currentPosition < 2) {
                currentPosition++;
                isSwiping = true;
                swipeAction = Actions.sequence(Actions.moveTo(getX(), getY() + (direction * 100), 0.2f), new Action() {
                            @Override
                            public boolean act(float delta) {
                                startY = getY();
                                return true;
                            }
                        }

                );
                playSoundSwipe();
            } else if (direction == SWIPE_DOWN && currentPosition > 0) {
                currentPosition--;
                isSwiping = true;
                swipeAction = Actions.sequence(Actions.moveTo(getX(), getY() + (direction * 100), 0.2f), new Action() {
                            @Override
                            public boolean act(float delta) {
                                startY = getY();
                                return true;
                            }
                        }
                );
                playSoundSwipe();
            }

        }
    }
}
