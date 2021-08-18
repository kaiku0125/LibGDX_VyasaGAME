package com.kaiku.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MainScreen implements Screen {
    private final static String TAG = "MainScreen";
    private Stage stage;
    private Skin skin;
    private ScreenManager manager;
    private TextButton endBtn, labyBtn;



    public MainScreen(){

    }

    @Override
    public void show() {
        manager = ScreenManager.getInstance();
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skin/glassy-ui.json"));
        initUI();
    }

    private void initUI(){
        endBtn = new TextButton("End", skin, "small");
        endBtn.setSize(100, 70);
        endBtn.setPosition(10, stage.getHeight() - 80);
        endBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Gdx.app.exit();
            }
        });

        labyBtn = new TextButton("labyrinth", skin, "small");
        labyBtn.setSize(100, 70);
        labyBtn.setPosition(10 , 10);
        labyBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                manager.setScreen(manager.createScreen(ScreenManager.LABYRINTH));
            }
        });

        stage.addActor(endBtn);
        stage.addActor(labyBtn);

    }

    @Override
    public void render(float delta) {
//        Gdx.gl.glClearColor(1f, 0f, 0f, 1f);
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();
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
        stage.dispose();
    }
}
