package controllers;

import core.Game;

public class Keyboard {
	static boolean[] ascii = new boolean[256];
	
	public static void setKeyPressed(char key, int keyCode){
		if(key!=Game.CODED){
			ascii[key] = true;
		}
	}
	
	public static void setKeyReleased(char key, int keyCode){
		if(key!=Game.CODED){
			ascii[key] = false;
		}
	}
	
	public static boolean isKeyOn(char key){
		return ascii[key];
	}
}
