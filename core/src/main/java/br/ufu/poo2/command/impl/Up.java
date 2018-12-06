package br.ufu.poo2.command.impl;

import br.ufu.poo2.model.Spacecraft;
import br.ufu.poo2.command.Command;

public class Up extends Command {

    public Up(Spacecraft sprite) {
        super(sprite);
    }

    @Override
    public void execute() {
        Spacecraft spacecraft=super.getSpacecraft();
        spacecraft.setY(spacecraft.getY()-spacecraft.getSpeed());
        testY(spacecraft);
    }
}
