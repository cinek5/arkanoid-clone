package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class PlayerPlatform  extends Sprite{
     

	
    public PlayerPlatform(Texture playerPlatformTexture) {
    	super(playerPlatformTexture);
    	this.setX(MyGdxGame.GAME_WIDTH/2 - this.getWidth()/2);
    	this.setY(0);
    	
    }
    
   public boolean isOutOfWindow() {
	return (this.getX()<0 || this.getX()+this.getWidth()>MyGdxGame.GAME_WIDTH);
	   
   }
   
   public void bounceBall(Ball ball) {
	   Vector2 ballVector = ball.getVector();
	   float howFar = howFarFromMiddle(ball);
	   if (howFar==0) ballVector.set(0,ball.getVelocity()); else {
		   if (howFar>1) howFar=1;
	   float degrees = 60*(1-Math.abs(howFar)) + 30;
	   if (howFar<0) {
		   degrees = 180 - degrees;
	   }
	   	changeDirectionVector(ballVector,degrees,ball.getVelocity());
	   }
   }
	private float howFarFromMiddle(Ball ball) {
		float middleXPlatform = this.getX()+this.getWidth()/2;
		
		return ((ball.getX()+ball.getWidth()/2) - middleXPlatform)/(this.getWidth()/2);
		
	}
	private void changeDirectionVector(Vector2 ballVector,float degrees, float velocity) {
		degrees *= MathUtils.degreesToRadians;
		ballVector.set(MathUtils.cos(degrees)*velocity,MathUtils.sin(degrees)*velocity);
		
	}

	
	
 
 
 }
