package br.ufu.poo2.factory;

import br.ufu.poo2.model.MainSpacecraft;
import br.ufu.poo2.model.Star;
import br.ufu.poo2.views.GameScreen;
import com.badlogic.gdx.graphics.Texture;

public class StarFactory {


    private static StarFactory StarFactory = null;


    private StarFactory(){}


    public static synchronized StarFactory getInstance(){
        if(StarFactory == null){
            StarFactory = new StarFactory();
        }
        return StarFactory;
    }


    public static Star create(){


        double x = Math.random() * GameScreen.getWidth();
        double y = Math.random() * GameScreen.getHeight();
        int type = (int)(Math.random() * 3) % 3 ;
        Texture texture = new Texture("star50px.png");
        Star star = new Star(texture,type);
        star.setX((float) x);
        star.setY((float) y);
        return star;

    }

}
