package br.ufu.poo2.chainofResponsibility;

public class ShieldType1 extends Shield{


    @Override
    public boolean block(int type) {
        if(type == 1){
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
