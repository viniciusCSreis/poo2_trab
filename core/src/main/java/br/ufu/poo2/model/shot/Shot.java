package br.ufu.poo2.model.shot;

import br.ufu.poo2.model.EnemySpacecraft;
import br.ufu.poo2.model.MainSpacecraft;
import br.ufu.poo2.observer.IObserver;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Iterator;

public abstract class Shot extends Sprite implements IObserver {

    int damage;
    int speed;

    public Shot(Texture texture, int damage, int speed) {
        super(texture);
        this.damage = damage;
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public abstract boolean update(ISubject p);
}
