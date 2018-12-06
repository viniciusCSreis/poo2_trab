package br.ufu.poo2.factory.factoryMethod.shotFactory;

import br.ufu.poo2.model.shot.Shot;
import br.ufu.poo2.model.Spacecraft;
import com.badlogic.gdx.graphics.Texture;

public abstract class ShotFactory {


    public abstract Shot create(Spacecraft spacecraft);

}
