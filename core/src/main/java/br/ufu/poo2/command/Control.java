package br.ufu.poo2.command;

import java.util.ArrayList;

public class Control {

    private ArrayList<Command> commands = new ArrayList<>();
    private ArrayList<Command> log = new ArrayList<>();

    public void setCommand(Command command, int i){
        this.commands.add(i,command);
    }

    public void press(int i){
        commands.get(i).execute();
        log.add(commands.get(i));
    }
}
