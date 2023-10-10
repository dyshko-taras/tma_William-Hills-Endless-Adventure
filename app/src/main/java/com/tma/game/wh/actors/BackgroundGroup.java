package com.tma.game.wh.actors;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tma.game.wh.tools.GameState;

public class BackgroundGroup extends Group {
    private Image background1;
    private Image background2;
    private final float speed;
    private float width;
    private float height;


    public BackgroundGroup(Image background, float x, float y, float width, float height, float speed) {
        this.background1 = new Image(background.getDrawable());
        this.background2 = new Image(background.getDrawable());
        this.speed = speed;

        System.out.println(background1.getWidth());

        this.width = width;
        this.height = height;

        background1.setBounds(x, y, width, height);
        background2.setBounds(x + width, y, width, height);

        System.out.println(background1.getWidth());
        this.addActor(background1);
        this.addActor(background2);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (GameState.getState() == GameState.GAME) {
            background1.moveBy(-speed * delta, 0);
            background2.moveBy(-speed * delta, 0);
            updateBackgrounds();
        }
    }

    private void updateBackgrounds() {
        if (background1.getX() + width < 0) {
            background1.setPosition(background2.getX() + width, background1.getY());
        }
        if (background2.getX() + width < 0) {
            background2.setPosition(background1.getX() + width, background2.getY());
        }
    }
}

