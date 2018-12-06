package br.ufu.poo2.factory;

import br.ufu.poo2.model.EnemySpacecraft;
import com.badlogic.gdx.graphics.Texture;

public class EnemySpacecraftFactory{

    private static EnemySpacecraftFactory EnemySpacecraftFactory = null;
    private static int countCreate=0;

    private EnemySpacecraftFactory(){}


    public static synchronized EnemySpacecraftFactory getInstance(){
        if(EnemySpacecraftFactory == null){
            EnemySpacecraftFactory = new EnemySpacecraftFactory();
        }
        return EnemySpacecraftFactory;
    }

    public static EnemySpacecraft create(){

        Texture texture;
        if(countCreate%2 == 0){

            texture =  new Texture("2spaceship100px.png");
        }else{
            texture =  new Texture("5spaceship100px.png");
        }
        countCreate++;
        float x = (float)(Math.random()*1366 * 0.75) + 200;

        EnemySpacecraft enemySpacecraft = new EnemySpacecraft(texture,100,3,10);
        enemySpacecraft.setX(x);
        enemySpacecraft.setY(766);
        return enemySpacecraft;
    }
}
