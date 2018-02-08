package scenes;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Ball;
import com.mygdx.game.Block;
import com.mygdx.game.LevelCreator;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.PlayerPlatform;

public class GamePlayScreen implements Screen {
	MyGdxGame game;
	public static final int GAME_WIDTH = 800;
	public static final int GAME_HEIGHT = 600;
	PlayerPlatform playerPlatform;
	Texture playerPlatformTexture;
	Array<Block> blocks;
	Rectangle left, right, up;
	Ball ball;
	Texture backgroundTexture;
	Texture ballTexture;
	boolean isPlaying;
	Sound hitSound;
	boolean gameWon;
	Sprite backgroundSprite;
	LevelCreator levelCreator;
	String currentLevelName = "level1";
	int gameScore;
	BitmapFont font;

	public GamePlayScreen(MyGdxGame game) {
		this.game = game;
		init();
		isPlaying = false;
		gameWon = false;

	}

	private void displayGameScore() {
		font.draw(game.getBatch(), "Score: " + Integer.toString(gameScore), 10, GAME_HEIGHT - 10);
	}

	private void init() {
		font = new BitmapFont();
		font.getData().setScale(3);
		levelCreator = new LevelCreator();
		playerPlatformTexture = new Texture("paddleRed.png");
		backgroundTexture = new Texture("background.jpg");
		backgroundSprite = new Sprite(backgroundTexture);
		playerPlatform = new PlayerPlatform(playerPlatformTexture);
		initializeBlocks();
		ballTexture = new Texture("ballBlue.png");
		ball = new Ball(ballTexture);
		createBordersOfGame();
		hitSound = Gdx.audio.newSound(Gdx.files.internal("sounds/hitSound.wav"));

	}

	private void createBordersOfGame() {
		left = new Rectangle(0, 0, 0, GAME_HEIGHT);
		right = new Rectangle(GAME_WIDTH, 0, 0, GAME_HEIGHT);
		up = new Rectangle(0, GAME_HEIGHT, GAME_WIDTH, 0);
	}

	private void initializeBlocks() {

		blocks = levelCreator.createBlocks(currentLevelName);

	}

	private void renderBackground() {
		backgroundSprite.draw(game.getBatch());
	}

	private void resetGame() {
		isPlaying = false;
		ball.setX(ball.STARTING_X);
		ball.setY(Ball.STARTING_Y);
		initializeBlocks();
		ball.setVelocity(Ball.STARTING_VELOCITY);
		gameScore = 0;

	}

	private void drawBlocks() {
		for (Block block : blocks) {
			block.draw(game.getBatch());
		}
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.getBatch().begin();
		renderBackground();
		playerPlatform.draw(game.getBatch());
		ball.draw(game.getBatch());
		drawBlocks();
		displayGameScore();

		game.getBatch().end();

	}

	private void update() {
		handleInput();
		handleBallMovement();
		handleCollisions();

		checkIfLost();
		checkIfWin();
	}

	private void checkIfLost() {
		if (ball.getY() < 0)
			resetGame();

	}

	private void checkIfWin() {
		if (gameWon) {
			Gdx.app.exit();
		}
	}

	private void handleCollisions() {
		if (ball.getBoundingRectangle().overlaps(playerPlatform.getBoundingRectangle())) {
			playerPlatform.bounceBall(ball);
			hitSound.play(1.0f);
		} else if (ball.getBoundingRectangle().overlaps(left) || ball.getBoundingRectangle().overlaps(right)) {
			ball.getVector().x *= -1;
			hitSound.play(1.0f);

		} else if (ball.getBoundingRectangle().overlaps(up)) {
			ball.getVector().y *= -1;
			hitSound.play(1.0f);
		}
		handleBlockBallCollisions();

	}

	private Rectangle getRectangleCollidingWithBall(Iterator<Block> it) {
		ArrayList<Block> list = new ArrayList<Block>(2);
		while (it.hasNext()) {
			Block block = it.next();
			if (block.getBoundingRectangle().overlaps(ball.getBoundingRectangle())) {
				it.remove();
				gameScore++;
				ball.setVelocity(ball.getVelocity() + 5);
				list.add(block);
			}
		}
		if (list.size() > 1) {
			Rectangle rectangle = new Rectangle(list.get(0).getBoundingRectangle());
			rectangle = rectangle.merge(list.get(1).getBoundingRectangle());
			return rectangle;

		} else if (list.size() > 0) {
			return list.get(0).getBoundingRectangle();
		}
		return null;
	}

	private void handleBlockBallCollisions() {
		Iterator<Block> it = blocks.iterator();
		if (!it.hasNext()) {
			gameWon = true;

		}
		Rectangle rectangle = getRectangleCollidingWithBall(it);
		if (rectangle != null) {
			handleSingleBallBlockCollision(rectangle);
			hitSound.play(1.0f);
		}

	}

	private void handleBallMovement() {
		if (isPlaying) {
			ball.translateX(ball.getVector().x * Gdx.graphics.getDeltaTime());
			ball.translateY(ball.getVector().y * Gdx.graphics.getDeltaTime());
		} else {
			ball.setX(playerPlatform.getX() + playerPlatform.getWidth() / 2 - ball.getWidth() / 2);
		}

	}

	private void handleInput() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			playerPlatform.setX(playerPlatform.getX() - 450 * Gdx.graphics.getDeltaTime());
			if (playerPlatform.isOutOfWindow()) {
				playerPlatform.setX(0);
			}

		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			playerPlatform.setX(playerPlatform.getX() + 450 * Gdx.graphics.getDeltaTime());
			if (playerPlatform.isOutOfWindow()) {
				playerPlatform.setX(GAME_WIDTH - playerPlatform.getWidth());
			}
		}
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			isPlaying = true;
		}

	}

	private void handleSingleBallBlockCollision(Rectangle blockRect) {

		Rectangle ballRect = ball.getBoundingRectangle();
		Rectangle intersection = new Rectangle();
		Intersector.intersectRectangles(ballRect, blockRect, intersection);
		boolean leftCollision = intersection.x > ballRect.x;
		boolean rightCollision = intersection.x + intersection.width < ballRect.x + ballRect.width;
		boolean downCollision = intersection.y > ballRect.y;
		boolean upCollision = intersection.y + intersection.height < ballRect.y + ballRect.height;

		boolean leftDownCorner = leftCollision && downCollision;
		boolean leftUpperCorner = leftCollision && upCollision;
		boolean rightDownCorner = rightCollision && downCollision;
		boolean rightUpperCorner = rightCollision && upCollision;

		if ((leftCollision || rightCollision) && !upCollision && !downCollision) {
			ball.getVector().x *= -1;
		}
		if ((upCollision || downCollision) && !leftCollision && !rightCollision) {
			ball.getVector().y *= -1;
		}
		if (leftDownCorner || leftUpperCorner) {

			if (Math.abs(ballRect.x + ballRect.width - intersection.x) >= Math
					.abs(ballRect.y + ballRect.height - intersection.y))
				ball.getVector().y *= -1;
			else
				ball.getVector().x *= -1;

		}
		if (rightDownCorner || rightUpperCorner) {
			if (Math.abs(ballRect.x + ballRect.width - intersection.x) <= Math
					.abs(ballRect.y + ballRect.height - intersection.y))
				ball.getVector().y *= -1;
			else
				ball.getVector().x *= -1;

		}
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		playerPlatformTexture.dispose();
		ballTexture.dispose();
		disposeBlocks();
		hitSound.dispose();
		backgroundTexture.dispose();

	}

	private void disposeBlocks() {
		for (Block b : blocks) {
			b.getTexture().dispose();
		}

	}

}
