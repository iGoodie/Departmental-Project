package logic;

import controllers.Fonts;
import animations.Animations;
import animations.BasicAnimation;
import core.Game;
import processing.core.PGraphics;
import processing.core.PVector;
import sprites.AnimatedSprite;

public class Mob {
	public PVector pos;
	int health, atk;
	String state;
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
		if(!name.equals(state)) {
			sprite.startAnimation(name);
		}
		state = name;
	}
	
	public void damage(int d){
		health -= d;
	}

	public void update(int dt){
		sprite.update(dt);
	}
	
	public void render(int layer, float x, float y){
		PGraphics g = Game.getInstance().getLayer(layer);
		sprite.render(layer, x, y);
		BasicAnimation a = sprite.getAnimation();
		g.pushMatrix();
		g.translate(x+a.getTranslateX(), y+a.getTranslateY());
		g.scale(a.getScaleX(), a.getScaleY());
		g.rotate(a.getRotation());
		g.textFont(Fonts.DPCOMIC_20PT);
		g.text(health+" HP", -30, -90);
		g.popMatrix();
	}
}
