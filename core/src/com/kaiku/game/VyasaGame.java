package com.kaiku.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import libs.Log;
import static libs.Log.DEBUG;

public class VyasaGame extends Game {
	private final static String TAG = "VyasaGame";

	ScreenManager manager;
	LabyrinthScreen labyrinthScreen;
	LoginScreen loginScreen;
	
	@Override
	public void create () {
		if(DEBUG) Log.e(TAG, "create");

		manager = ScreenManager.getInstance();
		manager.init(this);

		manager.setScreen(manager.createScreen(ScreenManager.LOGIN));
//		manager.setScreen(manager.getScreen(ScreenManager.LOGIN));
		Gdx.input.setCatchBackKey(true);


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
