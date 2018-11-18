package world;

import java.util.ArrayList;

import controllers.Tiles;
import core.Game;
import logic.Player;
import physics.HitResolution;
import physics.HitTest;
import physics.HitTestBasic;
import processing.core.PGraphics;
import processing.core.PVector;

public class Room {
	ArrayList<Obstacle> obstacles = new ArrayList<>();
	
	int[][] ground;
	int wall;
	int corner;
	
	public Room(int row, int column){
		ground = new int[row][column];
		obstacles.add(new Obstacle(-75, -75, column*75+75, 0));
		obstacles.add(new Obstacle(-75, -75, 0, row*75+75));
		obstacles.add(new Obstacle(-75, row*75, column*75+150, 75));
		obstacles.add(new Obstacle(column*75, -75, 75, row*75+150));
	}
	
	public void setTiles(int...nums){
		for(int i=0; i<nums.length; i++){
			ground[i/ground.length][i%ground.length] = nums[i];
		}
	}
	
	public void setWall(int _wall){
		wall = _wall;
	}
	
	public void setCorner(int _corner){
		corner = _corner;
	}

	public void update(float dt, Player player){
		PVector pos = player.getPos();
		for(int i=0; i<obstacles.size(); i++){
			Obstacle o = obstacles.get(i);
			if(HitTestBasic.rectVsCircle(o.pos.x, o.pos.y, o.width, o.height, pos.x, pos.y, 75)){
				PVector intersect = HitResolution.rectVsCircleIntersect(o.pos.x, o.pos.y, o.width, o.height, pos.x, pos.y, 75);
				PVector diff = PVector.sub(pos, intersect);
				float len = diff.mag();
				diff.normalize().mult(75-len);
				player.setPos(pos.x+diff.x, pos.y+diff.y);
			}
		}
	}
	
	public void render(int layer, float x, float y){
		PGraphics gameLayer = Game.getInstance().getLayer(layer);
		gameLayer.pushMatrix();
		gameLayer.pushStyle();
		gameLayer.imageMode(Game.CENTER);
		for(int r=0; r<ground.length; r++){
			for(int c=0; c<ground[0].length; c++){
				gameLayer.image(Tiles.getTile(ground[r][c]), x+75*r, y+75*c);
			}
		}
		gameLayer.image(Tiles.getCorner(corner), x-75, y-75);
		for(int i=0; i<ground.length; i++){
			gameLayer.image(Tiles.getWalls(wall), x+i*75, y-75);
		}
		//
		gameLayer.pushMatrix();
		gameLayer.translate(x+ground.length*75, y-75);
		gameLayer.rotate(Game.HALF_PI);
		gameLayer.image(Tiles.getCorner(corner), 0, 0);
		for(int i=0; i<ground.length; i++){
			gameLayer.image(Tiles.getWalls(wall), (i+1)*75, 0);
		}
		gameLayer.popMatrix();
		//
		gameLayer.pushMatrix();
		gameLayer.translate(x+ground.length*75, y+ground[0].length*75);
		gameLayer.rotate(Game.PI);
		gameLayer.image(Tiles.getCorner(corner), 0, 0);
		for(int i=0; i<ground.length; i++){
			gameLayer.image(Tiles.getWalls(wall), (i+1)*75, 0);
		}
		gameLayer.popMatrix();
		//
		gameLayer.translate(x-75, y+ground[0].length*75);
		gameLayer.rotate(Game.PI+Game.HALF_PI);
		gameLayer.image(Tiles.getCorner(corner), 0, 0);
		for(int i=0; i<ground.length; i++){
			gameLayer.image(Tiles.getWalls(wall), (i+1)*75, 0);
		}
		gameLayer.popStyle();
		gameLayer.popMatrix();
	}
}
class Obstacle{
	PVector pos;
	float width, height;
	
	public Obstacle(float x, float y, float _width, float _height) {
		pos = new PVector(x, y);
		width = _width;
		height = _height;
	}
}