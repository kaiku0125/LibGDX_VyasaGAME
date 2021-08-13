package com.kaiku.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

import libs.Log;
import static libs.Log.DEBUG;

public class VyasaGame extends Game {
	private final static String TAG = "VyasaGame";

	ScreenManager manager;
	LabyrinthScreen labyrinthScreen;
	MainScreen mainScreen;
	
	@Override
	public void create () {
		if(DEBUG) Log.e(TAG, "create");

		manager = ScreenManager.getInstance();
		manager.init(this);

		manager.setScreen(manager.getScreen(ScreenManager.MAIN));

//		labyrinthScreen = new LabyrinthScreen();
//		mainScreen = new MainScreen();
//		setScreen(mainScreen);

	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		if(DEBUG) Log.e(TAG, "dispose");
//		labyrinthScreen.dispose();
	}





}
