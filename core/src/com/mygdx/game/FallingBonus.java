package com.mygdx.game;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class FallingBonus extends Sprite {
	
	
	private float velocity;
	static final float GRAVITY = 1.025f;
	private static final float INIT_VELOCITY = 30;
	private BonusType bonusType;
	
	
	public FallingBonus(Texture bonusTexture,float x, float y, BonusType bonusType) {
       super(bonusTexture);
	   this.setX(x);
	   this.setY(y);
	   this.bonusType = bonusType;
	   velocity = INIT_VELOCITY;
	}
	public static FallingBonus createNewFallingBonus(float x, float y) {
		int numberOfBonuses = BonusType.values().length;
		Random random = new Random();
		int randomIndex = random.nextInt(numberOfBonuses);
		BonusType bonusType = BonusType.values()[randomIndex];
		Texture bonusTexture = new Texture(bonusType.toString()+"_BONUS.png");
		return new FallingBonus(bonusTexture,x,y,bonusType);
		
	}
	public void dispose() {
		getTexture().dispose();
	}


	public float getVelocity() {
		return velocity;
	}


	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}


	public BonusType getBonusType() {
		return bonusType;
	}


	public void setBonusType(BonusType bonusType) {
		this.bonusType = bonusType;
	}
	
    public void changePaddleState(PlayerPlatform paddle) {
    	 switch (bonusType) {
    	 case WIDEN_PADDLE: {
    		 paddle.changeState(PaddleState.WIDEN);
    		 break;
    	 }
    	 case SHORTEN_PADDLE: {
    		 paddle.changeState(PaddleState.SHORTEN);
    		 break;
    	 }
    	 }
    }




	public static float getInitVelocity() {
		return INIT_VELOCITY;
	}
	public void fallDown() {
		translateY(-1*velocity*Gdx.graphics.getDeltaTime());
		velocity*= GRAVITY;
	}
	

}
