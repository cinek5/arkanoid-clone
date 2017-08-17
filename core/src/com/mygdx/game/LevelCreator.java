package com.mygdx.game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class LevelCreator {
	static final String[] colors = { "blue", "green", "purple", "red", "yellow" };
	static final int startingX = 16;
	static final int startingY = 500;

	public Array<Block> createBlocks(String levelName) {
		Array<Block> blocks = new Array<Block>();
		
		FileHandle  file = Gdx.files.internal("levels/"+levelName+".txt");
		
		Scanner reader = null;
		reader = new Scanner (file.readString());
		int j = 0;
		while (reader.hasNextLine()) {
			String line = reader.nextLine();
			for (int i = 0; i < line.length(); i++) {
				int number = line.charAt(i)-48;
				if (number > 0) {
					blocks.add(createBlock(number - 1, startingX + i * 64, startingY - j * 32));
				}
			}
			j++;
		}
		reader.close();
		return blocks;
	}

	private Block createBlock(int colorIndex, int x, int y) {
		Block block = new Block(new Texture("element_" + colors[colorIndex] + "_rectangle.png"));
		block.setX(x);
		block.setY(y);

		return block;

	}

}
