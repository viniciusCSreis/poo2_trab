package br.ufu.poo2.factory;

import br.ufu.poo2.model.MainSpacecraft;
import br.ufu.poo2.model.Spacecraft;
import com.badlogic.gdx.graphics.Texture;

public class MainSpacecraftFactory {

    private static MainSpacecraftFactory mainSpacecraftFactory = null;


    private MainSpacecraftFactory(){}


    public static synchronized MainSpacecraftFactory getInstance(){
        if(mainSpacecraftFactory == null){
            mainSpacecraftFactory = new MainSpacecraftFactory();
        }
        return mainSpacecraftFactory;
    }

    public static MainSpacecraft create(){
        Texture texture = new Texture("1spaceship200px.png");
        MainSpacecraft mainSpacecraft = new MainSpacecraft(texture,100,10);
        mainSpacecraft.setPosition(0, 0);
        return mainSpacecraft;
    }


}
