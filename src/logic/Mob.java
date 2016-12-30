package logic;

import processing.core.PVector;
import sprites.AnimatedSprite;

public class Mob {
	public PVector pos;
	int health, atk;
	AnimatedSprite sprite;
	
	public Mob(int x, int y, int _atk, int _health){
		pos = new PVector(x, y);
		health = _health;
		atk = _atk;
	}
	
	public void setSprite(String path, int pivotX, int pivotY){
		sprite = new AnimatedSprite(path, pivotX, pivotY);
	}
	
	public PVector getPos(){
		return pos;
	}
	
	public int getHealth(){
		return health;
	}
	
	public boolean isDead(){
		return health<=0;
	}
	
	public void playAnimation(String name){
		sprite.startAnimation(name);
	}
	
	public void damage(int d){
		health -= d;
	}

	public void update(int dt){
		sprite.update(dt);
	}
	
	public void render(int layer, float x, float y){
		sprite.render(layer, x, y);
	}
}
