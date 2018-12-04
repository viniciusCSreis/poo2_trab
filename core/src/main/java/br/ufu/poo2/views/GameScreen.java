package br.ufu.poo2.views;

import br.ufu.poo2.Poo2Game;
import br.ufu.poo2.factory.ControlFactory;
import br.ufu.poo2.factory.MainSpacecraftFactory;
import br.ufu.poo2.model.EnemySpacecraft;
import br.ufu.poo2.model.MainSpacecraft;
import br.ufu.poo2.model.Shot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;
import command.Control;

public class GameScreen implements Screen {

    private SpriteBatch spriteBatch;
    private MainSpacecraft mainSpaceCraft;
    private Control control;
    private long lastEnemySpacecraftSpawnTime;

    public GameScreen(Poo2Game poo2Game) {
        mainSpaceCraft = MainSpacecraftFactory.create();
        spriteBatch = new SpriteBatch();
        control = ControlFactory.create(mainSpaceCraft);
        lastEnemySpacecraftSpawnTime=0;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        mainSpaceCraft.notifyObservers();

        spriteBatch.begin();

        spriteBatch.draw(mainSpaceCraft, mainSpaceCraft.getX(), mainSpaceCraft.getY());

        for (Shot shot : mainSpaceCraft.getShots()) {
            spriteBatch.draw(shot,shot.getX(),shot.getY());
        }

        if (TimeUtils.nanoTime() - lastEnemySpacecraftSpawnTime > 1000000000)
        {
            mainSpaceCraft.createEnemySpaceCraft();
            lastEnemySpacecraftSpawnTime=TimeUtils.nanoTime();
        }
        for (EnemySpacecraft enemySpacecraft : mainSpaceCraft.getEnemySpacecrafts()) {
            enemySpacecraft.update(mainSpaceCraft);
            spriteBatch.draw(enemySpacecraft,enemySpacecraft.getX(),enemySpacecraft.getY());
        }

        spriteBatch.end();


        if (Gdx.input.isKeyPressed(Input.Keys.A)){
            control.press(ControlFactory.LEFT);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)){
            control.press(ControlFactory.RIGHT);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)){
            control.press(ControlFactory.UP);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)){
            control.press(ControlFactory.DOWN);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)){
            mainSpaceCraft.createShot();
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
