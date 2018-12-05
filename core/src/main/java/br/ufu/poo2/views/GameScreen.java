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
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.TimeUtils;
import command.Control;

import java.awt.*;
import java.util.Iterator;

public class GameScreen implements Screen {

    private SpriteBatch spriteBatch;
    private MainSpacecraft mainSpaceCraft;
    private Control control;
    private long lastEnemySpacecraftSpawnTime;
    final Poo2Game game;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    OrthographicCamera camera;
    public GameScreen(Poo2Game game) {
        mainSpaceCraft = MainSpacecraftFactory.create();
        spriteBatch = new SpriteBatch();
        control = ControlFactory.create(mainSpaceCraft);
        lastEnemySpacecraftSpawnTime=0;
        this.game=game;
        shapeRenderer=new ShapeRenderer();
        font=new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, getWidth(), getHeight());
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();


        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,255,0,1);
        shapeRenderer.rect(
                getWidth()-mainSpaceCraft.getLife(),
                getHeight()-100,
                mainSpaceCraft.getLife(),
               100
        );

        shapeRenderer.end();






        if(mainSpaceCraft.getLife() <= 0){
            game.setScreen(new GameScreen(game));
        }


        mainSpaceCraft.notifyObservers();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        font.draw(spriteBatch, String.valueOf(mainSpaceCraft.getLife()), getWidth()-100, getHeight()-50);
        font.draw(spriteBatch, String.valueOf(mainSpaceCraft.getKillEnemies()), 0, getHeight()-50);
        spriteBatch.draw(mainSpaceCraft, mainSpaceCraft.getX(), mainSpaceCraft.getY());

        for (Shot shot : mainSpaceCraft.getShots()) {
            spriteBatch.draw(shot,shot.getX(),shot.getY());
        }

        if (TimeUtils.nanoTime() - lastEnemySpacecraftSpawnTime > 1000000000)
        {
            mainSpaceCraft.createEnemySpaceCraft();
            lastEnemySpacecraftSpawnTime=TimeUtils.nanoTime();
        }

        Iterator<EnemySpacecraft> enemySpacecraftIterator = mainSpaceCraft.getEnemySpacecrafts().iterator();
        while (enemySpacecraftIterator.hasNext()){
            EnemySpacecraft enemySpacecraft = enemySpacecraftIterator.next();
            if(enemySpacecraft.update(mainSpaceCraft))
            {
                spriteBatch.draw(enemySpacecraft,enemySpacecraft.getX(),enemySpacecraft.getY());
            }else{
                enemySpacecraftIterator.remove();
            }
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

    public static int getWidth(){
        return 1366;
    }

    public static int getHeight(){
        return 768;
    }
}
