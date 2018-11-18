package controllers;

import core.Game;
import processing.core.PFont;

public class Fonts {
	public static PFont 
	DPCOMIC_20PT,
	NERVOUS_12PT,
	PIXELFJVERDANA_6PT;
	
	public static void loadFonts(){
		Game game = Game.getInstance();
		DPCOMIC_20PT = game.createFont("fonts/dpcomic.ttf", 20, true); //FileUtils.readExternalPFont("fonts/dpcomic.ttf");
		NERVOUS_12PT = game.createFont("fonts/Nervous.ttf", 12, true); //FileUtils.readExternalPFont("fonts/dpcomic.ttf");
		PIXELFJVERDANA_6PT = game.createFont("fonts/PixelFJVerdana12pt.ttf", 6, true); //FileUtils.readExternalPFont("fonts/dpcomic.ttf");
	}
}
