package controllers;

import core.Game;
import processing.core.PFont;

public class Fonts {
	public static PFont 
	DPCOMIC,
	NERVOUS,
	PIXELFJVERDANA12PT;
	
	public static void loadFonts(){
		Game game = Game.getInstance();
		DPCOMIC = game.createFont("fonts/dpcomic.ttf", 20, true); //FileUtils.readExternalPFont("fonts/dpcomic.ttf");
		NERVOUS = game.createFont("fonts/Nervous.ttf", 20, true); //FileUtils.readExternalPFont("fonts/dpcomic.ttf");
		PIXELFJVERDANA12PT = game.createFont("fonts/PixelFJVerdana12pt.ttf", 12, true); //FileUtils.readExternalPFont("fonts/dpcomic.ttf");
	}
}
