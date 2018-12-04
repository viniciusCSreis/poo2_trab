package command.impl;

import br.ufu.poo2.model.Spacecraft;
import command.Command;

public class Up extends Command {

    public Up(Spacecraft sprite) {
        super(sprite);
    }

    @Override
    public void execute() {
        Spacecraft spacecraft=super.getSpacecraft();
        spacecraft.setY(spacecraft.getY()-10);
        testY(spacecraft);
    }
}
