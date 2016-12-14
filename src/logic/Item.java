package logic;

import processing.core.PImage;
import util.FileUtils;

public class Item {
	PImage texture;
	String name;
	int maxStack;
	
	public Item(String name, int maxStack, String path){
		texture = FileUtils.readExternalPImage(path);
		this.name = name;
		this.maxStack = maxStack;
	}
	
	public PImage getTexture(){
		return texture;
	}
	
	public String getName(){
		return name;
	}
}
