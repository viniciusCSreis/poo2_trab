package br.ufu.poo2.command.impl;

import br.ufu.poo2.model.Spacecraft;
import br.ufu.poo2.command.Command;

public class Right extends Command {

    public Right(Spacecraft sprite) {
        super(sprite);
    }

    @Override
    public void execute() {
        Spacecraft spacecraft=super.getSpacecraft();
        spacecraft.setX(spacecraft.getX()+10);
        testX(spacecraft);
    }
}
