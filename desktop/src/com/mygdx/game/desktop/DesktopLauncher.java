package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.MyGdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = MyGdxGame.GAME_HEIGHT;
		config.width = MyGdxGame.GAME_WIDTH;
		config.resizable = false;
		new LwjglApplication(new MyGdxGame(), config);
	}
}
