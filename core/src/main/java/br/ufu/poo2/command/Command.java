package br.ufu.poo2.command;

import br.ufu.poo2.model.Spacecraft;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class Command {

    private Spacecraft spacecraft;

    public Command(Spacecraft sprite) {
        this.spacecraft = sprite;
    }

    public abstract void execute();

    public void testX(Sprite sprite){

        float maxX = 1366 - sprite.getWidth();
        if (sprite.getX() > maxX) {
            sprite.setX(maxX);
        }
        if (sprite.getX() < 0) {
            sprite.setX(0);
        }

    }
    public void testY(Sprite sprite){
        float maxY = 768 - sprite.getHeight();
        if (sprite.getY() > maxY) {
            sprite.setY(maxY);
        }
        if (sprite.getY() < 0) {
            sprite.setY(0);
        }

    }

    public Spacecraft getSpacecraft() {
        return spacecraft;
    }
}
