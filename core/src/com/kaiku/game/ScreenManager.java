package com.kaiku.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import java.util.HashMap;

import libs.Log;

public class ScreenManager {
    private static final String TAG = "ScreenManager";
    public static final String MAIN = "main";
    public static final String LABYRINTH = "labyrinth";
    private static volatile ScreenManager instance;
    private Game game;
    private Screen currentScreen;
    private HashMap<String, Screen> screenFactory;


    private ScreenManager(){
        screenFactory = new HashMap<String, Screen>();
        screenFactory.put(MAIN, new MainScreen());
        screenFactory.put(LABYRINTH, new LabyrinthScreen());

    }

    public static ScreenManager getInstance(){
        if (instance == null){
            synchronized (ScreenManager.class){
                if(instance == null){
                    instance = new ScreenManager();
                }
            }
        }
        return instance;
    }

    public void init(Game game){
        this.game = game;

    }

    public Screen getScreen(String key){
        return screenFactory.get(key);
    }

    public void setScreen(Screen screen){
        if(screen != null){
            if(currentScreen != null)
                currentScreen.dispose();
            currentScreen = screen;
            game.setScreen(screen);
        }else{
            Log.e(TAG, "show screen error");
        }

    }

    public Screen getCurrentScreen(){
        return currentScreen;
    }
}
