package br.ufu.poo2.factory;

import br.ufu.poo2.model.EnemySpacecraft;
import com.badlogic.gdx.graphics.Texture;

public class EnemySpacecraftFactory{

    private static EnemySpacecraftFactory EnemySpacecraftFactory = null;


    private EnemySpacecraftFactory(){}


    public static synchronized EnemySpacecraftFactory getInstance(){
        if(EnemySpacecraftFactory == null){
            EnemySpacecraftFactory = new EnemySpacecraftFactory();
        }
        return EnemySpacecraftFactory;
    }

    public static EnemySpacecraft create(){

        Texture texture =  new Texture("2spaceship100px.png");
        float x = (float)(Math.random()*1366 * 0.75) + 200;

        EnemySpacecraft enemySpacecraft = new EnemySpacecraft(texture,100,3,10);
        enemySpacecraft.setX(x);
        enemySpacecraft.setY(766);
        return enemySpacecraft;
    }
}
