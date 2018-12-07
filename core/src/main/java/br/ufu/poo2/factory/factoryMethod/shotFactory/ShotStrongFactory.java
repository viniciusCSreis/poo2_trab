package br.ufu.poo2.factory.factoryMethod.shotFactory;

import br.ufu.poo2.model.Spacecraft;
import br.ufu.poo2.model.shot.Shot;
import br.ufu.poo2.model.shot.ShotStrong;
import com.badlogic.gdx.graphics.Texture;

public class ShotStrongFactory extends ShotFactory {

    private static ShotStrongFactory shotSimpleFactory = null;


    private ShotStrongFactory(){}


    public static synchronized ShotFactory getInstance(){
        if(shotSimpleFactory == null){
            shotSimpleFactory = new ShotStrongFactory();
        }
        return shotSimpleFactory;
    }

    public Shot create(Spacecraft spacecraft){
        Texture texture = new Texture("laserVerde.png");
        Shot shot = new ShotStrong(texture,100,20);
        shot.setX(spacecraft.getX() + spacecraft.getWidth()/2);
        shot.setY(spacecraft.getY() + spacecraft.getHeight());
        return shot;
    }

}
