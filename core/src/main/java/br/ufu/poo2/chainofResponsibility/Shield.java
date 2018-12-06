package br.ufu.poo2.chainofResponsibility;

public abstract class Shield {

    private Shield next;

    public Shield getNext() {
        return next;
    }

    public void setNext(Shield next) {
        this.next = next;
    }

    public abstract boolean block(int type);

}
