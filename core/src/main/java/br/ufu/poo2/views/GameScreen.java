package br.ufu.poo2.views;

import br.ufu.poo2.Poo2Game;
import br.ufu.poo2.factory.ControlFactory;
import br.ufu.poo2.factory.MainSpacecraftFactory;
import br.ufu.poo2.model.EnemySpacecraft;
import br.ufu.poo2.model.MainSpacecraft;
import br.ufu.poo2.model.Star;
import br.ufu.poo2.model.shot.Shot;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import br.ufu.poo2.command.Control;

import java.util.Iterator;

public class GameScreen implements Screen {

    final Poo2Game game;
    private Stage stage;
    private SpriteBatch spriteBatch;
    private MainSpacecraft  mainSpaceCraft;
    private Control control;
    private long lastEnemySpacecraftSpawnTime;
    private ShapeRenderer shapeRenderer;
    private BitmapFont font;
    OrthographicCamera camera;
    private String username;
    private int spaceCraft;
    private Sprite background;

    public GameScreen(Poo2Game game, int spaceCraft, String username) {
        this.spaceCraft = spaceCraft;
        this.username = username;
        stage = new Stage(new ScreenViewport());
        mainSpaceCraft = MainSpacecraftFactory.create(spaceCraft);
        spriteBatch = new SpriteBatch();
        control = ControlFactory.create(mainSpaceCraft);
        lastEnemySpacecraftSpawnTime=0;
        this.game=game;
        shapeRenderer=new ShapeRenderer();
        font=new BitmapFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, getWidth(), getHeight());
        Texture backgroundTexture = new Texture("fundo.png");
        background = new Sprite(backgroundTexture);

        Skin skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));

        Label usernameLabel = new Label("Nome:", skin, "default");
        Label userNameInput = new Label(username, skin, "default");
//        Label lifeLabel = new Label("Vida:", skin, "default");
//        Label currentLife = new Label(String.valueOf(mainSpaceCraft.getLife()), skin, "default");
//        Label enemiesLabel = new Label("Inimigos:", skin, "default");


        Table table = new Table(skin);
        table.setFillParent(true);
        table.add(usernameLabel);
        table.add(userNameInput).width(100);
        table.row();
//        table.add(lifeLabel);
//        table.add(currentLife).width(100);
        table.row();
//        table.add(enemiesLabel);
        table.left().top();

        stage.addActor(table);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();









        if(mainSpaceCraft.getLife() <= 0){
            game.setScreen(new MenuScreen(game));
        }


        mainSpaceCraft.notifyObservers();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        spriteBatch.draw(background,background.getX(),background.getY());
        spriteBatch.draw(mainSpaceCraft, mainSpaceCraft.getX(), mainSpaceCraft.getY());

        for (Shot shot : mainSpaceCraft.getShots()) {
            spriteBatch.draw(shot,shot.getX(),shot.getY());
        }

        if (TimeUtils.nanoTime() - lastEnemySpacecraftSpawnTime > 1000000000)
        {
            mainSpaceCraft.createEnemySpaceCraft();
            mainSpaceCraft.createStar();
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

        Iterator<Star> starIterator = mainSpaceCraft.getStars().iterator();
        while (starIterator.hasNext()){
            Star star = starIterator.next();
            if(star.update(mainSpaceCraft))
            {
                spriteBatch.draw(star,star.getX(),star.getY());
            }else{
                starIterator.remove();
            }
        }

        spriteBatch.end();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0,255,0,1);
        shapeRenderer.rect(
                getWidth()-mainSpaceCraft.getLife(),
                getHeight()-100,
                mainSpaceCraft.getLife(),
                100
        );

        stage.act();
        stage.draw();

        shapeRenderer.end();

        spriteBatch.begin();

        font.draw(spriteBatch, String.valueOf(mainSpaceCraft.getLife()), getWidth()-100, getHeight()-50);
        font.draw(spriteBatch, String.valueOf(mainSpaceCraft.getKillEnemies()), 0, getHeight()-50);

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
