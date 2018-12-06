package br.ufu.poo2.model;

import br.ufu.poo2.observer.IObserver;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;

public class Star extends Sprite implements IObserver {

    private int type;
    private long time;

    public Star(Texture texture, int type) {
        super(texture);
        this.type = type;
        this.time= TimeUtils.nanoTime();
    }

    @Override
    public boolean update(ISubject p) {
        MainSpacecraft mainSpacecraft = (MainSpacecraft) p;
        if( this.getBoundingRectangle().overlaps( mainSpacecraft.getBoundingRectangle() ) )
        {
            if(type==0){
                mainSpacecraft.setLife(mainSpacecraft.getLife()+10);
                return false;
            }
        }
        if(TimeUtils.millis() - time > 1000000*10)
        {
            return false;
        }


        return true;

    }
}
