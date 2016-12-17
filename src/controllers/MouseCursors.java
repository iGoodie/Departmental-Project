package controllers;

import java.util.ArrayList;

import core.Game;
import processing.core.PImage;
import processing.core.PVector;

public class MouseCursors {
	static ArrayList<PImage> cursors = new ArrayList<>();
	static ArrayList<PVector> cursorPos = new ArrayList<>();
	
	public static void changeCursor(int index){
		PVector pos = cursorPos.get(index);
		Game.getInstance().cursor(cursors.get(index), (int)pos.x, (int)pos.y);
	}
	
	public static void loadCursors(){
		Game game = Game.getInstance();
		cursors.add(game.loadImage("cursors/0.png")); //Default cursor - 0
		cursorPos.add(new PVector(4, 2));
	}
}