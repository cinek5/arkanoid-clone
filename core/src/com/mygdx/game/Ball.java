package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Ball  extends Sprite{
	
	
	private Vector2 vector;
	private float velocity;
	
	public Ball(Texture ballTexture) {
		super(ballTexture);
		this.vector = new Vector2(0,1);
		this.setVelocity(400) ;
		vector.scl(velocity);
		this.setX(800/2 - this.getWidth()/2);
    	this.setY(24);
    	
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
