package br.ufu.poo2.model;

import br.ufu.poo2.observer.IObserver;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;

public class EnemySpacecraft extends Spacecraft implements IObserver {

    private int damage;
    private int type;

    public EnemySpacecraft(Texture texture, int life, int speed, int damage,int type) {
        super(texture, life, speed);
        this.damage = damage;
        this.type=type;
    }

    @Override
    public boolean update(ISubject p) {
        MainSpacecraft mainSpacecraft = (MainSpacecraft) p;
        this.setY(this.getY()-this.getSpeed());
        if( this.getY() <= 0 ||  this.getBoundingRectangle().overlaps( mainSpacecraft.getBoundingRectangle() ) )
        {
            if(this.getY() <= 0 || mainSpacecraft.getShield() == null || !mainSpacecraft.getShield().block(type)){
                mainSpacecraft.setLife(mainSpacecraft.getLife()-damage);
            }
            return false;
        }

        return true;

    }
}
