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

import static jdk.nashorn.internal.objects.NativeMath.log;

public class VyasaGame extends Game {
	private final static String TAG = "VyasaGame";
	private final static boolean DEBUG = true;


	LabyrinthScreen labyrinthScreen;
	
	@Override
	public void create () {
		if(DEBUG) log(TAG, "create");
		labyrinthScreen = new LabyrinthScreen();
		setScreen(labyrinthScreen);

	}

	@Override
	public void render () {
		super.render();

//		Gdx.app.error(TAG, String.valueOf(Gdx.graphics.getFramesPerSecond()));
	}
	
	@Override
	public void dispose () {
		if(DEBUG) log(TAG, "dispose");
		labyrinthScreen.dispose();
	}

	private void log(String tag, String msg){
		Gdx.app.error(tag, msg);
	}


}
