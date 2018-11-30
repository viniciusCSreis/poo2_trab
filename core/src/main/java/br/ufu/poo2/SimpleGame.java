package br.ufu.poo2;

import br.ufu.poo2.factory.MainSpacecraft;
import br.ufu.poo2.model.Spacecraft;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class SimpleGame extends ApplicationAdapter {


    SpriteBatch spriteBatch;
    Spacecraft spacecraft;
    Rectangle rectangle1;
    Rectangle rectangle2;
    Sprite sprite1;
    Sprite sprite2;
    float red;

    @Override
    public void create () {

        spacecraft= MainSpacecraft.createSpacecraft();
        spriteBatch = new SpriteBatch();
        rectangle1 = new Rectangle();
        Texture texture1 = new Texture(spacecraft.getImgName());
        sprite1= new Sprite(texture1);
        sprite1.setPosition(0, 0);

        rectangle1.height = sprite1.getHeight();
        rectangle1.width = sprite1.getWidth();
        rectangle1.x = 0;
        rectangle1.y = 0;

        rectangle2 = new Rectangle();
        Texture texture2 = new Texture("badlogic.jpg");
        sprite2 = new Sprite(texture2);
        rectangle2.height = sprite2.getHeight();
        rectangle2.width = sprite2.getWidth();
        rectangle2.x = 200;
        rectangle2.y = 300;

        red=0;
    }

    @Override
    public void render () {

        Gdx.gl.glClearColor(red,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(sprite1, rectangle1.x, rectangle1.y);
        spriteBatch.draw(sprite2, rectangle2.x, rectangle2.y);
        spriteBatch.end();

        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            rectangle1.x = rectangle1.x - 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            rectangle1.x = rectangle1.x + 10;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            rectangle1.y = rectangle1.y + 10;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            rectangle1.y = rectangle1.y - 10;
        }

        if(rectangle1.overlaps(rectangle2)){
            red=255;
        }else{
            red=0;
        }

    }

    @Override
    public void dispose () {
    }


}
