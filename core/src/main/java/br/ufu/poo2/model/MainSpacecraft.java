package br.ufu.poo2.model;

import br.ufu.poo2.chainofResponsibility.Shield;
import br.ufu.poo2.factory.EnemySpacecraftFactory;
import br.ufu.poo2.factory.StarFactory;
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
    private ArrayList<Star> stars;
    private Shield shield;
    private ShotFactory shotFactory;
    private int killEnemies;
    private long lastShotTime;
    private double shotSpeed;
    private long timeShild;
    private int segsShild;

    public MainSpacecraft(Texture texture, int life, int speed) {
        super(texture, life, speed);
        enemySpacecrafts=new ArrayList<>();
        this.shots = new ArrayList<>();
        this.stars = new ArrayList<>();
        this.killEnemies = 0;
        this.lastShotTime = 0;
        this.shotSpeed = 0.2;
        this.timeShild=0;
        this.segsShild=0;
        shotFactory= ShotSimpleFactory.getInstance();

    }

    public ArrayList<Shot> getShots() {
        return shots;
    }

    public void createEnemySpaceCraft(){
        EnemySpacecraft enemySpacecraft = EnemySpacecraftFactory.create();
        enemySpacecrafts.add(enemySpacecraft);
    }

    public void createStar(){
        if(Math.random() < 0.2){
            Star star = StarFactory.create();
            stars.add(star);
        }

    }

    public Shield getShield() {
        if(segsShild>10)
        {
            shield=null;
            segsShild=0;
            timeShild=0;

        }
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
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

    public void calcTime(){
        if(TimeUtils.nanoTime() - timeShild > 1000000000)
        {
            timeShild =  TimeUtils.nanoTime();
            segsShild++;
        }
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

    public ArrayList<Star> getStars() {
        return stars;
    }

    public void setShotSpeed(double shotSpeed) {
        this.shotSpeed = shotSpeed;
    }

    public void setEnemySpacecrafts(ArrayList<EnemySpacecraft> enemySpacecrafts) {
        this.enemySpacecrafts = enemySpacecrafts;
    }

    public void addKillEnemies() {
        this.killEnemies++;
    }
}
