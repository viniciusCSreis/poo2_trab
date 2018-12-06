package br.ufu.poo2.chainofResponsibility;

public class ShieldType2 extends Shield{


    @Override
    public boolean block(int type) {
        if(type == 2){
            return true;
        }
        if(getNext() !=null){
            return getNext().block(type);
        }
        else{
            return false;
        }
    }
}
