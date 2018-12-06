package br.ufu.poo2.command.impl;

import br.ufu.poo2.model.Spacecraft;
import br.ufu.poo2.command.Command;

public class Left extends Command {

    public Left(Spacecraft sprite) {
        super(sprite);
    }

    @Override
    public void execute() {
        Spacecraft spacecraft=super.getSpacecraft();
        spacecraft.setX(spacecraft.getX()-spacecraft.getSpeed());
        testX(spacecraft);
    }
}
