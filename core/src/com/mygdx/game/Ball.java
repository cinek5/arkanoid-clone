package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Sprite {

	public static final int STARTING_VELOCITY = 400;
	public final float STARTING_X = MyGdxGame.GAME_WIDTH / 2 - this.getWidth() / 2;
	public static final float STARTING_Y = 24;
	private Vector2 vector;
	private float velocity;

	public Ball(Texture ballTexture) {
		super(ballTexture);
		this.vector = new Vector2(0, 1);
		this.setVelocity(STARTING_VELOCITY);
		vector.scl(velocity);
		this.setX(STARTING_X);
		this.setY(STARTING_Y);

	}

	public Vector2 getVector() {
		return vector;
	}

	public void setVector(Vector2 vector) {
		this.vector = vector;
	}

	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

}
