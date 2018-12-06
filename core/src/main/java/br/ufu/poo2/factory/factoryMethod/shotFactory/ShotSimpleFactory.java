package br.ufu.poo2.factory.factoryMethod.shotFactory;

import br.ufu.poo2.model.Spacecraft;
import br.ufu.poo2.model.shot.Shot;
import br.ufu.poo2.model.shot.ShotSimple;
import com.badlogic.gdx.graphics.Texture;

public class ShotSimpleFactory extends ShotFactory {

    private static ShotSimpleFactory shotSimpleFactory = null;


    private ShotSimpleFactory(){}


    public static synchronized ShotFactory getInstance(){
        if(shotSimpleFactory == null){
            shotSimpleFactory = new ShotSimpleFactory();
        }
        return shotSimpleFactory;
    }

    public Shot create(Spacecraft spacecraft){
        Texture texture = new Texture("laserVermelho.png");
        Shot shot = new ShotSimple(texture,100,20);
        shot.setX(spacecraft.getX() + spacecraft.getWidth()/2);
        shot.setY(spacecraft.getY() + spacecraft.getHeight());
        return shot;
    }

}
