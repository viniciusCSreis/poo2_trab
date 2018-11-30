package br.ufu.poo2.model;

public class Spacecraft {

    String imgName;
    int life;

    public Spacecraft(String imgName, int life) {
        this.imgName = imgName;
        this.life = life;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }
}
