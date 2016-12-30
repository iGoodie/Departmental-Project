package gui;

import controllers.Fonts;
import logic.elements.Item;
import core.Game;
import processing.core.PGraphics;
import processing.core.PImage;
import sprites.TimedIcon;
import util.FileUtils;

public class HUDPopup {
	static final int LAYER = 0;
	static TimedIcon saveIcon;
	static PImage saveBg = FileUtils.readExternalPImage("ui/saveBg.png");
	static boolean on = false;
	static boolean saving = false;

	public static boolean isOn(){
		return on;
	}
	
	public static void showItem(Item item){
		
		on = true;
	}
	
	public static void showSaving(){
		if(saveIcon == null){
			saveIcon = new TimedIcon(
					FileUtils.readExternalPImage("stated_images/save_0.png"),
					FileUtils.readExternalPImage("stated_images/save_1.png"));
			saveIcon.setTimes(300, 300);
		}
		saveIcon.reset();
		on = true;
		saving = true;
	}
	
	public static void update(int dt){
		if(saving){
			saveIcon.update(dt);
		}
	}
	
	public static void render(){
		if(on){
			PGraphics gameLayer = Game.getInstance().getLayer(LAYER);
			gameLayer.fill(0, 150);
			gameLayer.rect(0, 0, gameLayer.width, gameLayer.height);
			if(saving){
				gameLayer.image(saveBg, (gameLayer.width-saveBg.width)/2, (gameLayer.height-saveBg.height)/2);
				gameLayer.pushMatrix();
				gameLayer.imageMode(Game.CENTER);
				gameLayer.translate(250, 200);
				gameLayer.scale(.7f);
				saveIcon.render(LAYER, 0, 0);
				gameLayer.imageMode(Game.CORNER);
				gameLayer.popMatrix();
				gameLayer.fill(255);
				gameLayer.textFont(Fonts.DPCOMIC_20PT, 25);
				gameLayer.text("Saving..", 300, 206);
			}
		}
	}
	
	public static void close(){
		on = false;
		saving = false;
	}
}
