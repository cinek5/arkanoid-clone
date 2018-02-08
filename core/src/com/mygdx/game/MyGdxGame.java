package com.mygdx.game;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;

import scenes.GamePlayScreen;
import scenes.MainMenu;

public class MyGdxGame extends Game {
	private SpriteBatch batch;
	MainMenu menuScreen;
	GamePlayScreen gameplayScreen;

	public SpriteBatch getBatch() {
		return batch;
	}

	@Override
	public void create() {
		batch = new SpriteBatch();
		menuScreen = new MainMenu(this);
	    gameplayScreen = new GamePlayScreen(this);
		setScreen(menuScreen);

	}
	public void setScreenToGameplay() {
		setScreen(gameplayScreen);
	}

	@Override
	public void render() {
		super.render();

	}

	@Override
	public void dispose() {
		batch.dispose();

	}

}
