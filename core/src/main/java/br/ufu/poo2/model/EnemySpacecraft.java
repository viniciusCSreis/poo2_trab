package br.ufu.poo2.model;

import br.ufu.poo2.observer.IObserver;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;

public class EnemySpacecraft extends Spacecraft implements IObserver {
    public EnemySpacecraft(Texture texture, int life, int speed) {
        super(texture, life, speed);
    }

    @Override
    public void update(ISubject p) {
        this.setY(this.getY()-this.getSpeed());
    }
}
