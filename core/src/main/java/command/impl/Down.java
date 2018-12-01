package command.impl;

import br.ufu.poo2.model.Spacecraft;
import command.Command;

public class Down extends Command {


    public Down(Spacecraft sprite) {
        super(sprite);
    }

    @Override
    public void execute() {
        Spacecraft spacecraft=super.getSpacecraft();
        spacecraft.setY(spacecraft.getY()+spacecraft.getSpeed());
        testY(spacecraft);
    }
}
