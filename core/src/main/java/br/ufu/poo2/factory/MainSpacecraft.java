package br.ufu.poo2.factory;

import br.ufu.poo2.model.Spacecraft;

public class MainSpacecraft {

    private static MainSpacecraft mainSpacecraft = null;


    private MainSpacecraft(){}


    public static synchronized MainSpacecraft getInstance(){
        if(mainSpacecraft == null){
            mainSpacecraft= new MainSpacecraft();
        }
        return mainSpacecraft;
    }

    public static Spacecraft createSpacecraft(){
        return new Spacecraft("1spaceship200px.png",100);
    }


}
