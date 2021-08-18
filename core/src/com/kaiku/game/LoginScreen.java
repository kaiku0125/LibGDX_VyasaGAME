package com.kaiku.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.net.HttpRequestBuilder;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import libs.Log;
import libs.MyColor;

public class LoginScreen implements Screen {
    private final static String TAG = "LoginScreen";
    private final String LOGIN = "登入";
    private static boolean focus = false;
    private Stage stage;
    private Skin skin;
    private TextButton loginBtn;
    private TextField passwordField;
    private Label titleLabel, passwordLabel;
    VyasaGame game;
    ScreenManager manager;


    public LoginScreen(){

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
        //處理 textfield focus 問題
        stage.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                if(passwordField.getX() < x && x < passwordField.getX() + passwordField.getWidth()){
                    if(passwordField.getY() < y && y < passwordField.getY() + passwordField.getHeight()){
                        Log.e(TAG, "框框內");
                    }
                }else{
                    if(stage.getKeyboardFocus() == passwordField){
                        stage.unfocus(passwordField);
                        Gdx.input.setOnscreenKeyboardVisible(false);
                    }
                }
            }
        });

        titleLabel = new Label("凱酷修真.EXE", skin);
        titleLabel.setSize(Gdx.graphics.getWidth(), 200);
        titleLabel.setPosition(0,stage.getHeight() - 200);
        titleLabel.setSize(stage.getWidth(), 200);
        titleLabel.setAlignment(Align.center);
        Label.LabelStyle style = new Label.LabelStyle();
//        style.background = new Image(new Texture(setLabelColor(titleLabel.getWidth(), titleLabel.getHeight(), Color.SKY))).getDrawable();
        style.font = setParameter("凱酷修真", 90, MyColor.ALMOND, MyColor.TEXT_SHADOW_GREEN, 1,1);
        titleLabel.setStyle(style);

        passwordLabel = new Label("密碼", skin);
        passwordLabel.setSize(100, 50);
        passwordLabel.setPosition(stage.getWidth() / 3 - 50 , stage.getHeight()/2);
        Label.LabelStyle style_password = new Label.LabelStyle();
        style_password.font = setParameter("密碼", 70, MyColor.ALMOND, MyColor.TEXT_SHADOW_GREEN, 1,1);
        passwordLabel.setStyle(style_password);

        passwordField  = new TextField("", skin);
        passwordField.setSize(300 ,70);
        passwordField.setPosition(stage.getWidth() / 3 + passwordLabel.getWidth(), stage.getHeight() / 2);


        loginBtn = new TextButton("login", skin, "small");
        loginBtn.setSize(200,70);
        loginBtn.setPosition(stage.getWidth() / 2 - loginBtn.getWidth() / 2,stage.getHeight()/4);
        loginBtn.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
//                Log.e(TAG, "x :" + x + "y : "+ y);
//                manager.setScreen(manager.getScreen(ScreenManager.LABYRINTH));

                manager.setScreen(manager.createScreen(ScreenManager.MAIN));

            }
        });


        stage.addActor(titleLabel);
        stage.addActor(passwordLabel);
        stage.addActor(passwordField);
        stage.addActor(loginBtn);
    }

    private BitmapFont setParameter(String ChineseChar, int size, Color textColor){
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.characters = FreeTypeFontGenerator.DEFAULT_CHARS + ChineseChar;
        parameter.size = size;
        parameter.color = textColor;
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/myFont2.ttf"));
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
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/myFont2.ttf"));
        BitmapFont font = generator.generateFont(parameter);
        generator.dispose();
        return font;
    }

    private Pixmap setLabelColor(float width, float height, Color color){
        Pixmap labelColor = new Pixmap((int)width, (int)height, Pixmap.Format.RGB888);
        labelColor.setColor(color);
        labelColor.fill();
        return labelColor;
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

    private void reg(){
        String url = "";
        HttpRequestBuilder builder = new HttpRequestBuilder();
        Net.HttpRequest request = builder.newRequest().method(Net.HttpMethods.GET).url(url).build();
    }




}
