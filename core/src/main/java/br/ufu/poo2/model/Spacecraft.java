package br.ufu.poo2.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Spacecraft extends Sprite {

    private int life;

    private int speed;

    public Spacecraft(Texture texture, int life,int speed) {
        super(texture);
        this.life = life;
        this.speed= speed;
    }



    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }



}
