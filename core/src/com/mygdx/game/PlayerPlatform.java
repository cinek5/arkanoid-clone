package com.mygdx.game;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import scenes.GamePlayScreen;

public class PlayerPlatform extends Sprite {

	Texture normalStatePaddleTexture;
	Texture widenStatePaddleTexture;
	Texture shortenStatePaddleTexture;

	public PlayerPlatform() {
		super();
		initTextures();
		switchTexture(normalStatePaddleTexture);
		this.setX(GamePlayScreen.GAME_WIDTH / 2 - this.getWidth() / 2);
		this.setY(0);

	}

	public void dispose() {
		normalStatePaddleTexture.dispose();
		widenStatePaddleTexture.dispose();
		shortenStatePaddleTexture.dispose();
	}

	private void initTextures() {
		normalStatePaddleTexture = new Texture("paddleRed.png");
		widenStatePaddleTexture = new Texture("widen_paddleRed.png");
		shortenStatePaddleTexture = new Texture("shorten_paddleRed.png");

	}

	private void switchTexture(Texture texture) {

		this.setRegion(texture);
		this.setTexture(texture);
		this.setSize(texture.getWidth(), texture.getHeight());

	}

	public void changeState(PaddleState newState) {
		switch (newState) {
		case NORMAL: {
			switchTexture(normalStatePaddleTexture);
			break;
		}
		case WIDEN: {
			switchTexture(widenStatePaddleTexture);
			break;
		}
		case SHORTEN: {
			switchTexture(shortenStatePaddleTexture);
			break;

		}

		}
	}

	public boolean isOutOfWindow() {
		return (this.getX() < 0 || this.getX() + this.getWidth() > GamePlayScreen.GAME_WIDTH);

	}

	public void bounceBall(Ball ball) {
		Vector2 ballVector = ball.getVector();
		float howFar = howFarFromMiddle(ball);
		if (howFar == 0)
			ballVector.set(0, ball.getVelocity());
		else {
			if (howFar > 1)
				howFar = 1;
			float degrees = 60 * (1 - Math.abs(howFar)) + 30;
			if (howFar < 0) {
				degrees = 180 - degrees;
			}
			changeDirectionVector(ballVector, degrees, ball.getVelocity());
		}
	}

	private float howFarFromMiddle(Ball ball) {
		float middleXPlatform = this.getX() + this.getWidth() / 2;

		return ((ball.getX() + ball.getWidth() / 2) - middleXPlatform) / (this.getWidth() / 2);

	}

	private void changeDirectionVector(Vector2 ballVector, float degrees, float velocity) {
		degrees *= MathUtils.degreesToRadians;
		ballVector.set(MathUtils.cos(degrees) * velocity, MathUtils.sin(degrees) * velocity);

	}

}
