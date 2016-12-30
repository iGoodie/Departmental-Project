package physics.bounds;

import processing.core.PVector;

public class Circle {
	float x, y;
	float radius;
	
	public Circle(float _x, float _y, float _radius){
		x = _x;
		y = _y;
		radius = _radius;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public PVector getCenter(){
		return new PVector(x, y);
	}
}
