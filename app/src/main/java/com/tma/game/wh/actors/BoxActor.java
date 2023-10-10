package com.tma.game.wh.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.tma.game.wh.tools.GameState;

public class BoxActor extends Actor {

    private Image image;
    public float width;
    public float height;
    private float speed;
    private Rectangle rect = new Rectangle();


    public BoxActor(Image image, float x, float y, float width, float height, float speed) {
        this.image = image;
        this.width = width;
        this.height = height;
        this.speed = speed;
        setBounds(x, y, width, height);
        this.setName(image.getName());
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
            moveBy(-speed * delta, 0);
        }
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public Rectangle getRect() {
        rect.x = getX();
        rect.y = getY();
        rect.width = getWidth();
        rect.height = getHeight();
        return rect;
    }

    public Image getImage() {
        return image;
    }

}
