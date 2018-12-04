package command.impl;

import br.ufu.poo2.model.Spacecraft;
import command.Command;

public class Left extends Command {

    public Left(Spacecraft sprite) {
        super(sprite);
    }

    @Override
    public void execute() {
        Spacecraft spacecraft=super.getSpacecraft();
        spacecraft.setX(spacecraft.getX()-10);
        testX(spacecraft);
    }
}
