package br.ufu.poo2.model.shot;

import br.ufu.poo2.model.EnemySpacecraft;
import br.ufu.poo2.model.MainSpacecraft;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;

import java.util.Iterator;

public class ShotStrong extends Shot{


    public ShotStrong(Texture texture, int damage, int speed) {
        super(texture, damage, speed);
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
                return true;
            }
        }
        this.setY(this.getY()+this.speed);
        return true;
    }
}
