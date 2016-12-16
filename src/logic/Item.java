package logic;

import core.Game;
import processing.core.PImage;
import util.FileUtils;

public class Item {
	static String[] types = new String[]{
		"Necklace", //0
		"Head", //1
		"Wing", //2
		"Weapon", //3
		"Armor", //4
		"Gloves", //5
		"Accessory", //6
		"Boots", //7
		"Consumable", //8
		"Miscellaneous", //9
	};
	
	PImage texture;
	int maxStack;
	int type;
	
	public Item(int _maxStack, String path, int _type) {
		texture = FileUtils.readExternalPImage(path);
		maxStack = _maxStack;
		type = _type;
	}
	
	public PImage getTexture() {
		return texture;
	}
	
	public int getMaxStack(){
		return maxStack;
	}
	
	public int getType(){
		return type;
	}
	
	public void render(int layerID, float x, float y){
		Game.getInstance().image(layerID, texture, x, y);
	}
}
