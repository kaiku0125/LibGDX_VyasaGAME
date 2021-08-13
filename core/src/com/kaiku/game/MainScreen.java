package com.kaiku.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import libs.Log;
import libs.MyColor;
import libs.MyColors;

public class MainScreen implements Screen {
    private final static String TAG = "MainScreen";
    private final String LOGIN = "登入";
    private Stage stage;
    private Skin skin;
    private Table titleTable;
    private TextButton loginBtn;
    private Label titleLabel;
    VyasaGame game;
    ScreenManager manager;


    public MainScreen(){

//        this.game = game;
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
        titleLabel = new Label("凱酷修真.EXE", skin);
        titleLabel.setSize(Gdx.graphics.getWidth(), 200);
        titleLabel.setPosition(0,stage.getHeight() - 200);
        titleLabel.setSize(stage.getWidth(), 200);
        titleLabel.setAlignment(Align.center);
        Label.LabelStyle style = new Label.LabelStyle();
        style.font = setParameter("凱酷修真", 70, MyColor.TEXT_DARK_BLUE, MyColor.TEXT_SHADOW_GREEN, 1,1);
        titleLabel.setStyle(style);



        loginBtn = new TextButton(LOGIN, skin, "small");

        TextButton.TextButtonStyle loginStyle = new TextButton.TextButtonStyle();
        loginStyle.font = setParameter(LOGIN, 20, Color.RED);


        loginBtn.setStyle(loginStyle);
        loginBtn.setSkin(skin);
        loginBtn.setBounds(20,20,150,50);
        loginBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                Log.e(TAG, "x :" + x + "y : "+ y);
                manager.setScreen(manager.getScreen(ScreenManager.LABYRINTH));
            }
        });


        stage.addActor(titleLabel);
        stage.addActor(loginBtn);
    }

    private BitmapFont setParameter(String ChineseChar, int size, Color textColor){
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + ChineseChar;
        parameter.size = size;
        parameter.color = textColor;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/myFont.ttf"));
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private BitmapFont setParameter(String ChineseChar, int size, Color textColor, Color shadowColor, int offsetX, int offsetY){
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + ChineseChar;
        parameter.size = size;
        parameter.color = textColor;
        parameter.shadowColor = shadowColor;
        parameter.shadowOffsetX = offsetX;
        parameter.shadowOffsetY = offsetY;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/myFont.ttf"));
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }


    @Override
    public void render(float delta) {
        //clear the screen
        Gdx.gl.glClearColor(0.3f, 0.3f, 0.3f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // tell stage to do action and then draw itself
        stage.draw();
        stage.act();
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
        skin.dispose();

    }


}
