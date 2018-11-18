package physics;

import physics.bounds.Circle;
import physics.bounds.Rectangle;

public class HitTestBasic {
	static float distance(float x1, float y1, float x2, float y2){
		float sub1 = x1-x2;
		float sub2 = y1-y2;
		return (float)Math.sqrt(sub1*sub1 + sub2*sub2);
	}
	
	static float clamp(float value, float a, float b){
		return (float)Math.max(Math.min(a,b), Math.min(Math.max(a, b), value));
	}
	
	static boolean inRange(float value, float a, float b){
		return value>=Math.min(a, b) && value<=Math.max(a, b);
	}
	
	static boolean rangeIntersect(float a1, float b1, float a2, float b2){
		return Math.max(a1, b1) >= Math.min(a2, b2) && 
				Math.min(a1, b1) <= Math.max(a2, b2);
	}
	
	public static boolean circleVsCircle(Circle c1, Circle c2){
		return distance(c1.getX(), c1.getY(), c2.getX(), c2.getY()) <= c1.getRadius()+c2.getRadius();
	}
	
	public static boolean rectVsRect(Rectangle r1, Rectangle r2){
		return rangeIntersect(r1.getX(), r1.getX()+r1.getWidth(), r2.getX(), r2.getX()+r2.getWidth()) &&
				rangeIntersect(r1.getY(), r1.getY()+r1.getHeight(), r2.getY(), r2.getY()+r2.getHeight());
	}
	
	public static boolean rectVsRect(float x1, float y1, float w1, float h1, float x2, float y2, float w2, float h2){
		return rangeIntersect(x1, x1+w1, x2, x2+w2) &&
				rangeIntersect(y1, y1+h1, y2, y2+h2);
	}
	
	public static boolean pointVsPoint(float p1x, float p1y, float p2x, float p2y){
		return p1x==p2x && p1y==p2y;
	}
		

	public static boolean pointVsCircle(float px, float py, Circle c){
		return distance(px, py, c.getX(), c.getY()) <= c.getRadius();
	}
	
	public static boolean pointVsRect(float px, float py, Rectangle r){
		return inRange(px, r.getX(), r.getX()+r.getWidth()) &&
				inRange(py, r.getY(), r.getY()+r.getHeight());
	}
	
	public static boolean pointVsRect(float px, float py, float rx, float ry, float rw, float rh){
		return inRange(px, rx, rx+rw) &&
				inRange(py, ry, ry+rh);
	}
	
	public static boolean rectVsCircle(Rectangle r, Circle c){
		float closestX = clamp(c.getX(), r.getX(), r.getX()+r.getWidth());
		float closestY = clamp(c.getY(), r.getY(), r.getY()+r.getHeight());
		float distanceX = c.getX() - closestX;
		float distanceY = c.getY() - closestY;
		float distSq = (distanceX*distanceX) + (distanceY*distanceY);
		return distSq < (c.getRadius()*c.getRadius());
	}
	
	public static boolean rectVsCircle(float rx, float ry, float rw, float rh, float cx, float cy, float cr){
		float closestX = clamp(cx, rx, rx+rw);
		float closestY = clamp(cy, ry, ry+rh);
		float distanceX = cx - closestX;
		float distanceY = cy - closestY;
		float distSq = (distanceX*distanceX) + (distanceY*distanceY);
		return distSq < (cr*cr);
	}
}
