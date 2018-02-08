package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;

public class MainMenu implements Screen {
	MyGdxGame game;
	Texture background;
	Texture newGameTexture;
	Texture exitGameTexture;
	MenuButton newGameButton;
	MenuButton exitGameButton;
	private static final int FIRST_BUTTON_Y = 350;
	private static final int SPACING_BEETWEEN_BUTTONS = 10;

	public MainMenu(MyGdxGame game) {
		this.game = game;
		background = new Texture("menuBackground.jpg");
		newGameTexture = new Texture("new_game.jpg");
		exitGameTexture = new Texture("exit_game.jpg");
		newGameButton = new MenuButton(GamePlayScreen.GAME_WIDTH / 2 - newGameTexture.getWidth() / 2, FIRST_BUTTON_Y,
				newGameTexture);
		exitGameButton = new MenuButton(GamePlayScreen.GAME_WIDTH / 2 - exitGameTexture.getWidth() / 2,
				FIRST_BUTTON_Y - exitGameTexture.getHeight() - SPACING_BEETWEEN_BUTTONS, exitGameTexture);
	}

	private void drawButtons() {
		newGameButton.draw(game.getBatch());
		exitGameButton.draw(game.getBatch());
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	private void handleInputs() {
		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();
		if (Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {
			if (newGameButton.isInside(mouseX, mouseY)) {

				game.setScreenToGameplay();
			}
			if (exitGameButton.isInside(mouseX, mouseY)) {

				Gdx.app.exit();
			}

		}
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		update();

		game.getBatch().begin();
		game.getBatch().draw(background, 0, 0);
		drawButtons();
		game.getBatch().end();

	}

	private void update() {
		handleInputs();

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
		// TODO Auto-generated method stub

	}

}
