package logic.elements;

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
	PImage spriteFront, spriteBack;
	int maxStack;
	int type;
	int atk=0, def=0;
	int magicPoint=0;
	int hp=0, mana=0;
	int metadata=0;
	String name, description;
	
	public Item(String _name, int _maxStack, String path, int _type) {
		texture = FileUtils.readExternalPImage(path);
		maxStack = _maxStack;
		type = _type;
		name = _name;
	}
	
	public PImage getTexture() {
		return texture;
	}
	
	public PImage getSpriteFront(){
		return spriteFront;
	}
	
	public PImage getSpriteBack(){
		return spriteBack;
	}
	
	public int getMaxStack(){
		return maxStack;
	}
	
	public int getAtk(){
		return atk;
	}
	
	public int getDef(){
		return def;
	}
	
	public int getMagicPts(){
		return magicPoint;
	}
	
	public int getHp(){
		return hp;
	}
	
	public int getMana(){
		return mana;
	}
	
	public int getType(){
		return type;
	}
	
	public String getName(){
		return name;
	}
	
	public void setAttributes(int _atk, int _def, int _magicPoint, int _hp, int _mana, int _metadata){
		atk = _atk;
		def = _def;
		magicPoint = _magicPoint;
		hp = _hp;
		mana = _mana;
		metadata = _metadata;
	}
	
	public void setDescription(String _description){
		description = _description;
	}
	
	public void setSprite(String nameFront, String nameBack){
		spriteFront = FileUtils.readExternalPImage("sprites/"+nameFront+".png");
		spriteBack = FileUtils.readExternalPImage("sprites/"+nameBack+".png");
	}
	
	public void render(int layerID, float x, float y){
		Game.getInstance().image(layerID, texture, x, y);
	}
}
