package br.ufu.poo2.factory;

import br.ufu.poo2.model.MainSpacecraft;
import br.ufu.poo2.model.Spacecraft;
import com.badlogic.gdx.graphics.Texture;

public class MainSpacecraftFactory{

    private static MainSpacecraftFactory mainSpacecraftFactory = null;


    private MainSpacecraftFactory(){}


    public static synchronized MainSpacecraftFactory getInstance(){
        if(mainSpacecraftFactory == null){
            mainSpacecraftFactory = new MainSpacecraftFactory();
        }
        return mainSpacecraftFactory;
    }


    public static MainSpacecraft create(int spaceCraft){
        Texture texture = new Texture("1spaceship200px.png");
        MainSpacecraft mainSpacecraft = new MainSpacecraft(texture,100,10);

        if (spaceCraft == 2) {
            texture = new Texture("3spaceship100px.png");
            mainSpacecraft = new MainSpacecraft(texture,50,15);
            mainSpacecraft.setShotSpeed(0.1);
        } else if (spaceCraft == 3) {
            texture = new Texture("4spaceship100px.png");
            mainSpacecraft = new MainSpacecraft(texture,200,5);
            mainSpacecraft.setShotSpeed(0.3);
        }

        mainSpacecraft.setPosition(0, 0);
        return mainSpacecraft;
    }


}
