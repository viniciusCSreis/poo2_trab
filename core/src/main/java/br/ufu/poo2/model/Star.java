package br.ufu.poo2.model;

import br.ufu.poo2.chainofResponsibility.ShieldType1;
import br.ufu.poo2.chainofResponsibility.ShieldType2;
import br.ufu.poo2.observer.IObserver;
import br.ufu.poo2.observer.ISubject;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;

public class Star extends Sprite implements IObserver {

    private int type;
    private long time;
    private int segs;
    public Star(Texture texture, int type) {
        super(texture);
        this.type = type;
        this.time= 0;
        this.segs = 0;
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
            if(type==1){

                if(mainSpacecraft.getShield() == null){
                    mainSpacecraft.setShield(new ShieldType1());
                }
                else {

                    if(mainSpacecraft.getShield().block(1)){
                        return false;
                    }
                    mainSpacecraft.getShield().setNext(new ShieldType1());
                }
                return false;
            }
            if(type==2){



                if(mainSpacecraft.getShield() == null){
                    mainSpacecraft.setShield(new ShieldType2());
                }
                else {
                    if(mainSpacecraft.getShield().block(2)){
                        return false;
                    }
                    mainSpacecraft.getShield().setNext(new ShieldType2());
                }
                return false;
            }

        }
        if(TimeUtils.nanoTime() - time > 1000000000)
        {
            time =  TimeUtils.nanoTime();
            segs++;
        }
        if(segs==5){
            return false;
        }

        return true;

    }
}
