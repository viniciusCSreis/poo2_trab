package br.ufu.poo2.views;

import br.ufu.poo2.Poo2Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class GameScreen implements Screen {

    final Poo2Game game;

    SpriteBatch spriteBatch;
    Rectangle rectangle1;
    Rectangle rectangle2;
    Texture texture;
    float red;

    public GameScreen(Poo2Game poo2Game) {
        game = poo2Game;
        spriteBatch = new SpriteBatch();
        rectangle1 = new Rectangle();
        texture = new Texture("badlogic.jpg");
        rectangle1.height = texture.getHeight();
        rectangle1.width = texture.getWidth();
        rectangle1.x = 0;
        rectangle1.y = 0;

        rectangle2 = new Rectangle();
        texture = new Texture("badlogic.jpg");
        rectangle2.height = texture.getHeight();
        rectangle2.width = texture.getWidth();
        rectangle2.x = 200;
        rectangle2.y = 300;

        red=0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(red,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();

        spriteBatch.draw(texture, rectangle1.x, rectangle1.y);
        spriteBatch.draw(texture, rectangle2.x, rectangle2.y);
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
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
