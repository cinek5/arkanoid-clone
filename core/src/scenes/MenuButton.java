package scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class MenuButton {
	private int x;
	private int y;
	private Texture texture;
	public MenuButton(int x, int y, Texture texture) {
		this.x = x;
		this.y = y;
		this.texture = texture;
	}
	
	public boolean isInside(int x, int y) {
		int buttonY = Gdx.graphics.getHeight() - 1 - this.y;
		if (x>this.x && x<this.x + texture.getWidth() && y<buttonY && y>buttonY-texture.getHeight()) {
			return true;
		}
		return false;
	}
	public void draw(SpriteBatch batch) {
		batch.draw(texture, x, y);
	}

	

	
}
