package br.ufu.poo2.views;

import br.ufu.poo2.Poo2Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen implements Screen {

    final Poo2Game game;
    private Stage stage;

    public MenuScreen(Poo2Game poo2) {

        game = poo2;
        stage = new Stage(new ScreenViewport());
        Skin skin = new Skin(Gdx.files.internal("skin/neon-ui.json"));
        Label title = new Label("OOSPACE2",skin, "default");
        title.setAlignment(Align.center);
        title.setY(Gdx.graphics.getHeight()*2/3);
        title.setWidth(Gdx.graphics.getWidth());
        stage.addActor(title);

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        TextButton spaceCraft01 = new TextButton("Nave 01", skin);
        TextButton spaceCraft02 = new TextButton("Nave 02", skin);
        TextButton spaceCraft03 = new TextButton("Nave 03", skin);

        TextField usernameTextField = new TextField("", skin);

        spaceCraft01.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, 1, usernameTextField.getText()));
            }
        });

        spaceCraft02.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, 2, usernameTextField.getText()));
            }
        });

        spaceCraft03.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game, 3, usernameTextField.getText()));
            }
        });

        table.defaults();
        table.add(usernameTextField).fillX().uniformX().width(Value.percentWidth(.30F, table));
        table.row().pad(5, 0, 10, 0);
        table.add(spaceCraft01).fillX().uniformX().width(Value.percentWidth(.75F, table));
        table.row().pad(10, 0, 10, 0);
        table.add(spaceCraft02).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);
        table.add(spaceCraft03).fillX().uniformX();
        table.row().pad(10, 0, 10, 0);

    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 10);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
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
        stage.dispose();
    }
}
