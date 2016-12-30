package physics.bounds;

import processing.core.PVector;

public class Rectangle {
	float x, y;
	float w, h;
	
	public Rectangle(float _x, float _y, float _width, float _height){
		x = _x;
		y = _y;
		w = _width;
		h = _height;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getWidth(){
		return w;
	}
	
	public float getHeight(){
		return h;
	}
	
	public PVector getBeginning(){
		return new PVector(x, y);
	}
	
	public PVector getMidpoint(){
		return new PVector((x+w)/2, (y+h)/2);
	}
}
