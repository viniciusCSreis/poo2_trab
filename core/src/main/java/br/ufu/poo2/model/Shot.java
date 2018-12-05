package br.ufu.poo2.model;

import br.ufu.poo2.observer.IObserver;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.Iterator;

public class Shot extends Sprite implements IObserver {

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

    @Override
    public boolean update(ISubject p) {
        MainSpacecraft mainSpacecraft = (MainSpacecraft) p;
        Iterator<EnemySpacecraft> enemySpacecrafts = mainSpacecraft.getEnemySpacecrafts().iterator();
        while (enemySpacecrafts.hasNext()) {
            EnemySpacecraft enemySpacecraft = enemySpacecrafts.next();
            if(this.getBoundingRectangle().overlaps(enemySpacecraft.getBoundingRectangle())){
                enemySpacecraft.setLife(enemySpacecraft.getLife()-this.damage);
                if(enemySpacecraft.getLife()<=0){
                    enemySpacecrafts.remove();
                    mainSpacecraft.addKillEnemies();
                }
                return false;
            }
        }
        this.setY(this.getY()+this.speed);
        return true;
    }
}
