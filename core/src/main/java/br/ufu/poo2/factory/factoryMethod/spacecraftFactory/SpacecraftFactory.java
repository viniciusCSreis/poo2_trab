package br.ufu.poo2.factory.factoryMethod.spacecraftFactory;

import br.ufu.poo2.model.Spacecraft;
import br.ufu.poo2.model.shot.Shot;

public abstract class SpacecraftFactory {

    public abstract Spacecraft create(Spacecraft spacecraft);
}
