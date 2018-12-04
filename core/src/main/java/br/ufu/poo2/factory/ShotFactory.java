package br.ufu.poo2.factory;

import br.ufu.poo2.model.Shot;
import br.ufu.poo2.model.Spacecraft;
import com.badlogic.gdx.graphics.Texture;

public class ShotFactory {

    private static ShotFactory ShotFactory = null;


    private ShotFactory(){}


    public static synchronized ShotFactory getInstance(){
        if(ShotFactory == null){
            ShotFactory = new ShotFactory();
        }
        return ShotFactory;
    }

    public static Shot create(Spacecraft spacecraft){
        Texture texture = new Texture("laserVermelho.png");
        Shot shot = new Shot(texture,100,20);
        shot.setX(spacecraft.getX() + spacecraft.getWidth()/2);
        shot.setY(spacecraft.getY() + spacecraft.getHeight());
        return shot;
    }

}
