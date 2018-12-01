package br.ufu.poo2.model;

import br.ufu.poo2.factory.EnemySpacecraftFactory;
import br.ufu.poo2.factory.ShotFactory;
import br.ufu.poo2.observer.IObserver;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class MainSpacecraft extends Spacecraft implements ISubject {

    private ArrayList<EnemySpacecraft> enemySpacecrafts;
    private ArrayList<Shot> shots;

    public MainSpacecraft(Texture texture, int life, int speed) {
        super(texture, life, speed);
        enemySpacecrafts=new ArrayList<>();
        this.shots = new ArrayList<>();

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
        Shot shot = ShotFactory.create(this);
        registerObserver(shot);
    }

    @Override
    public void registerObserver(IObserver observer) {
        shots.add((Shot) observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        shots.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObserver observer: shots ) {

            observer.update(this);

        }
    }
}
