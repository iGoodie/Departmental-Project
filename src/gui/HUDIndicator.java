package gui;

import core.Game;
import logic.Player;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import util.FileUtils;

public class HUDIndicator {
	static PImage atk = FileUtils.readExternalPImage("ui/atk.png");
	static PImage def = FileUtils.readExternalPImage("ui/def.png");
	static PImage magicPts = FileUtils.readExternalPImage("ui/magicPts.png");
	static PImage soul = FileUtils.readExternalPImage("ui/soul.png");
	static PImage hpPot = FileUtils.readExternalPImage("ui/health.png");
	static PImage mpPot = FileUtils.readExternalPImage("ui/mana.png");
	static PImage bg = FileUtils.readExternalPImage("ui/emptyBar.png");
	static PImage hp = FileUtils.readExternalPImage("ui/healthBar.png");
	static PImage mp = FileUtils.readExternalPImage("ui/manaBar.png");
	static PVector pos = new PVector(40, Game.getInstance().height-bg.height-65);
	
	private static void renderBars(Player player){
		Game game = Game.getInstance();
		game.image(2, bg, pos.x, pos.y);
		game.image(2, bg, pos.x, pos.y+bg.height+5); //BG
		game.imageCropped(2, hp, (int)pos.x+5, (int)pos.y+5, 
				(int)Game.map(player.getHealth(), 0, player.getMaxHealth(), 0, hp.width), hp.height); //HP
		game.imageCropped(2, mp, (int)pos.x+5, (int)pos.y+bg.height+5+5,
				(int)Game.map(player.getMana(), 0, player.getMaxMana(), 0, mp.width), mp.height); //MP
	}
	
	private static void renderPots(){
		PGraphics gameLayer = Game.getInstance().getLayer(2);
		gameLayer.pushMatrix();
		gameLayer.translate(20, gameLayer.height-85);
		gameLayer.scale(.5f);
		gameLayer.image(hpPot, 0, 0);
		gameLayer.popMatrix();
		gameLayer.pushMatrix();
		gameLayer.translate(20, gameLayer.height-60);
		gameLayer.scale(.5f);
		gameLayer.image(mpPot, 0, 0);
		gameLayer.popMatrix();
	}
	
	private static void renderIcons(){
		PGraphics gameLayer = Game.getInstance().getLayer(2);
		gameLayer.image(atk, 200, gameLayer.height-80);
		gameLayer.image(def, 290, gameLayer.height-82);
		gameLayer.image(magicPts, 380, gameLayer.height-80);
		gameLayer.image(soul, 470, gameLayer.height-80);
	}
	
	public static void render(Player player){
		PGraphics gameLayer = Game.getInstance().getLayer(2);
		renderPots();
		renderBars(player);
		renderIcons();
	}
}
