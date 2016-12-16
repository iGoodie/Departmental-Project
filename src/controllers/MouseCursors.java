package controllers;

import java.util.ArrayList;

import core.Game;
import processing.core.PImage;

public class MouseCursors {
	static ArrayList<PImage> cursors = new ArrayList<>();
	
	public static void changeCursor(int index){
		Game.getInstance().cursor(cursors.get(index));
	}
	
	public static void loadCursors(){
		//TODO
	}
}
