package com.tma.game.wh.tools;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Timer;

public class AnimationActor {
    private Timer timer = new Timer();
    private int numFrames;
    private int currentFrame = -1;
    private int frameOffset;
    private Skin skinSpriteSheet;
    private String framePrefix;
    private Image targetImage;
    private float intervalSeconds;
    private float delaySeconds;
    private boolean isStarted = false;


    public AnimationActor(int numFrames, int frameOffset, Skin skinSpriteSheet, String framePrefix, Image targetImage, float intervalSeconds, float delaySeconds) {
        this.numFrames = numFrames;
        this.framePrefix = framePrefix;
        this.skinSpriteSheet = skinSpriteSheet;
        this.targetImage = targetImage;
        this.frameOffset = frameOffset;
        this.intervalSeconds = intervalSeconds;
        this.delaySeconds = delaySeconds;
    }

    public AnimationActor(int numFrames, Skin skinSpriteSheet, String framePrefix, Image targetImage, float intervalSeconds, float delaySeconds) {
        this(numFrames, 0, skinSpriteSheet, framePrefix, targetImage, intervalSeconds, delaySeconds);
    }

    public AnimationActor(int numFrames, int frameOffset, Skin skinSpriteSheet, String framePrefix, Image targetImage) {
        this(numFrames, frameOffset,skinSpriteSheet, framePrefix, targetImage, 0.1f, 0.1f);
    }

    public AnimationActor(int numFrames, Skin skinSpriteSheet, String framePrefix, Image targetImage) {
        this(numFrames, 0, skinSpriteSheet, framePrefix, targetImage, 0.1f, 0.1f);
    }


    public void start() {
        if (!isStarted) {
            isStarted = true;
            timer.scheduleTask(new Timer.Task() {
                @Override
                public void run() {
                    currentFrame = (currentFrame + 1) % numFrames + frameOffset;
                    targetImage.setDrawable(skinSpriteSheet.getDrawable(framePrefix + currentFrame));
                }
            }, delaySeconds, intervalSeconds);
        }
    }

    public void stop() {
        isStarted = false;
        timer.clear();
    }
}

