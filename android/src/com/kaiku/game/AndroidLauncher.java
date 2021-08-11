package com.kaiku.game;


import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.kaiku.game.VyasaGame;

public class AndroidLauncher extends AndroidApplication {
	private final static String TAG = "AndroidLauncher";
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

//		LinearLayout layout = findViewById(R.id.linear_test);
//		View view = initializeForView(new VyasaGame());
//		view.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
//		layout.addView(view);
		initialize(new VyasaGame(), config);
		log(TAG, "AndroidLauncher");

	}


}
