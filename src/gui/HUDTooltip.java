package gui;

import controllers.Fonts;
import processing.core.PGraphics;
import core.Game;

public class HUDTooltip {
	static final int LAYER = 1;
	static final int RIGHT_TOP = 0;
	static final int LEFT_TOP = 1;
	static PGraphics gameLayer = Game.getInstance().getLayer(LAYER);
	
	public static void renderTooltip(String text, int x, int y, int type){
		if(type == RIGHT_TOP){
			gameLayer.pushStyle();
			gameLayer.fill(100);
			gameLayer.textFont(Fonts.DPCOMIC_20PT, 15);
			float textWidth = gameLayer.textWidth(text);
			gameLayer.rect(x, y, 20+textWidth, -30);
			gameLayer.fill(0xFF_C7C7C7);
			gameLayer.textAlign(Game.CENTER, Game.CENTER);
			gameLayer.text(text, x+(20+textWidth)/2, y-15);
			gameLayer.popStyle();
		}
		else if(type == LEFT_TOP){
			gameLayer.pushStyle();
			gameLayer.fill(100);
			gameLayer.textFont(Fonts.DPCOMIC_20PT, 15);
			float textWidth = gameLayer.textWidth(text);
			gameLayer.rect(x, y, -20-textWidth, -30);
			gameLayer.fill(0xFF_C7C7C7);
			gameLayer.textAlign(Game.CENTER, Game.CENTER);
			gameLayer.text(text, x-(20+textWidth)/2, y-15);			
			gameLayer.popStyle();
		}
	}
	
	public static void renderText(String text, int x, int y){
		gameLayer.pushStyle();
		gameLayer.textFont(Fonts.DPCOMIC_20PT, 13);
		gameLayer.textAlign(Game.CENTER, Game.CENTER);
		gameLayer.fill(0);
		gameLayer.text(text, x+1, y);
		gameLayer.text(text, x-1, y);
		gameLayer.text(text, x, y+1);
		gameLayer.text(text, x, y-1);
		gameLayer.fill(0xFF_FFFFFF);
		gameLayer.text(text, x, y);
		gameLayer.popStyle();
		
	}
}
