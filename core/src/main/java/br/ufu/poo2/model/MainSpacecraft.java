package br.ufu.poo2.model;

import br.ufu.poo2.factory.factoryMethod.spacecraftFactory.EnemySpacecraftFactory;
import br.ufu.poo2.factory.factoryMethod.shotFactory.ShotFactory;
import br.ufu.poo2.factory.factoryMethod.shotFactory.ShotSimpleFactory;
import br.ufu.poo2.factory.factoryMethod.shotFactory.ShotStrongFactory;
import br.ufu.poo2.model.shot.Shot;
import br.ufu.poo2.observer.IObserver;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Iterator;

public class MainSpacecraft extends Spacecraft implements ISubject {

    private ArrayList<EnemySpacecraft> enemySpacecrafts;
    private ArrayList<Shot> shots;
    private ShotFactory shotFactory;
    private int killEnemies;
    private long lastShotTime;
    private double shotSpeed;

    public MainSpacecraft(Texture texture, int life, int speed) {
        super(texture, life, speed);
        enemySpacecrafts=new ArrayList<>();
        this.shots = new ArrayList<>();
        this.killEnemies = 0;
        this.lastShotTime = 0;
        this.shotSpeed = 0.2;
        shotFactory= ShotSimpleFactory.getInstance();

    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void createEnemySpaceCraft(){
        EnemySpacecraft enemySpacecraft = EnemySpacecraftFactory.create();
        enemySpacecrafts.add(enemySpacecraft);
    }

    public ArrayList<EnemySpacecraft> getEnemySpacecrafts() {
        return enemySpacecrafts;
    }

    public void createShot(){
        if(killEnemies>10){
            shotFactory= ShotStrongFactory.getInstance();
        }
        if(TimeUtils.nanoTime() - lastShotTime > 1000000000 * shotSpeed)
        {
            Shot shot = shotFactory.create(this);
            registerObserver(shot);
            lastShotTime = TimeUtils.nanoTime();
        }

    }

    @Override
    public void registerObserver(IObserver observer) {
        shots.add((Shot) observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        shots.remove(observer);
    }

    public int getKillEnemies() {
        return killEnemies;
    }

    @Override
    public void notifyObservers() {
        Iterator<Shot> shotIterator = shots.iterator();
        while (shotIterator.hasNext()){
            Shot shot = shotIterator.next();
            if(!shot.update(this)){
                shotIterator.remove();
            }

        }
    }

    public void setEnemySpacecrafts(ArrayList<EnemySpacecraft> enemySpacecrafts) {
        this.enemySpacecrafts = enemySpacecrafts;
    }

    public void addKillEnemies() {
        this.killEnemies++;
    }
}
