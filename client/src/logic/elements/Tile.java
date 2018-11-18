package logic.elements;

import core.Game;
import processing.core.PImage;
import util.FileUtils;

public class Tile {
	String name;
	PImage tileTexture;
	int type;
	
	public Tile(String _name, String path, int _type){
		name = _name;
		tileTexture = FileUtils.readExternalPImage(path);
		type = _type;
	}
	
	public void render(int layer, int x, int y){
		Game.getInstance().getLayer(layer).image(tileTexture, x, y);
	}
}
