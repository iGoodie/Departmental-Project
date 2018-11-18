package sprites;

import core.Game;
import physics.HitTest;
import physics.HitTestBasic;
import processing.core.PImage;
import util.FileUtils;

public class ExternalIcon {	
	int layer;
	PImage icon;
	float x, y;
	
	public ExternalIcon(String path, float _x, float _y, int _layer){
		icon = FileUtils.readExternalPImage(path);
		x = _x; 
		y = _y;
		layer = _layer;
	}
	
	public ExternalIcon(PImage _icon, float _x, float _y, int _layer){
		icon = _icon;
		x = _x;
		y = _y;
		layer = _layer;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public PImage getImage(){
		return icon;
	}

	public int getWidth(){
		return icon.width;
	}
	
	public int getHeight(){
		return icon.height;
	}
	
	public int getLayer(){
		return layer;
	}

	public boolean isMouseOn(int mouseX, int mouseY){
		return HitTest.pointVsPImage(mouseX, mouseY, (int)x, (int)y, icon);
	}
	
 	public void render(){
		Game.getInstance().getLayer(layer).image(icon, x, y);
	}
	
	public void render(float _x, float _y){
		Game.getInstance().getLayer(layer).image(icon, _x, _y);
	}
}
