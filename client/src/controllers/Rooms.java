package controllers;

import java.util.HashMap;

import util.FileUtils;
import world.Room;

public class Rooms {
	static HashMap<String, Room> rooms = new HashMap<>();
	
	public static void loadRooms(){
		String[] lines = FileUtils.readExternalString("a").split("\n");
	}
}
