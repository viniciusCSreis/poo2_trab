package br.ufu.poo2.factory;

import br.ufu.poo2.model.Spacecraft;
import command.Control;
import command.impl.Down;
import command.impl.Left;
import command.impl.Right;
import command.impl.Up;

public class ControlFactory {
    private static ControlFactory controlFactory = null;
    public static final int LEFT = 0;
    public static final int RIGHT=1;
    public static final int UP=2;
    public static final int DOWN=3;

    private ControlFactory(){}


    public static synchronized ControlFactory getInstance(){
        if(controlFactory == null){
            controlFactory = new ControlFactory();
        }
        return controlFactory;
    }

    public static Control create(Spacecraft spacecraft){
        Control control = new Control();
        Left left = new Left(spacecraft);
        Right right =  new Right(spacecraft);
        Down down = new Down(spacecraft);
        Up up = new Up(spacecraft);

        control.setCommand(left,LEFT);
        control.setCommand(right,RIGHT);
        control.setCommand(down,UP);
        control.setCommand(up,DOWN);

        return control;

    }

}
