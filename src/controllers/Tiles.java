package controllers;

import java.util.ArrayList;

import processing.core.PImage;
import util.FileUtils;

public class Tiles {
	static ArrayList<PImage> tiles = new ArrayList<>();
	static ArrayList<PImage> walls = new ArrayList<>();
	static ArrayList<PImage> corners = new ArrayList<>();
	
	public static void addTile(PImage sprite){
		tiles.add(sprite);
	}
	
	public static void addWall(PImage sprite){
		walls.add(sprite);
	}

	public static void addCorner(PImage sprite){
		corners.add(sprite);
	}
	
	public static PImage getTile(int index){
		return tiles.get(index);
	}

	public static PImage getCorner(int index){
		return corners.get(index);
	}

	public static PImage getWalls(int index){
		return walls.get(index);
	}
	
	public static void loadTiles(){
		String[] lines = FileUtils.readExternalString("tileinfo.txt").split("\n");
		for(int i=0; i<lines.length; i++){
			if(!lines[i].startsWith("//")){
				String[] cells = lines[i].split("\t");
				if(cells[1].equals("0")){ //Tile
					System.out.println(cells[1]); 
					addTile(FileUtils.readExternalPImage("tiles/"+cells[0]+".png"));
				}
				else if(cells[1].equals("1")){ //Walls
					PImage sprite = FileUtils.readExternalPImage("tiles/"+cells[0]+".png");
					addWall(sprite);
					//TODO: Rotate
				}
				else{ //Corners
					addCorner(FileUtils.readExternalPImage("tiles/"+cells[0]+".png"));
					//TODO: Rotate
				}
			}
		}
	}
}
