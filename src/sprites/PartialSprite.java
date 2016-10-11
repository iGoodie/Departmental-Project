package sprites;

import java.util.ArrayList;

import processing.core.PImage;
import processing.core.PVector;
import core.Game;
import de.looksgood.ani.Ani;

public class PartialSprite 
{
	/*Fields*/
	PImage sprite;
	PVector pivotPoint;
	
	/*Animation Controllers*/
	ArrayList<Ani> animations = new ArrayList<>();
	float rotationAngle;
	float offsetX, offsetY;
	
	/*Construction Methods*/
	public PartialSprite(PImage sprite)
	{
		this.sprite = sprite;
		pivotPoint = new PVector(0, 0);
	}
	public PartialSprite(PImage sprite, float angle, float pivotX, float pivotY)
	{
		this.sprite = sprite;
		rotationAngle = angle;
		pivotPoint = new PVector(pivotX, pivotY);
	}
	public PartialSprite(PImage sprite, float angle, PVector pivot)
	{
		this.sprite = sprite;
		rotationAngle = angle;
		pivotPoint = pivot;
	}
	
	/*Non-static Methods*/
	public void update(float dt) { updateAnimations(); }
	public void updateTick() {}
	public void render(float x, float y)
	{
		Game game = Game.getInstance();
		
		game.pushMatrix();
		game.translate(x+offsetX, y+offsetY);
		game.rotate(rotationAngle);
		game.image(sprite, -pivotPoint.x, -pivotPoint.y);
		game.popMatrix();
	}
	public void dispose() {}
	
	public void rotate(float angle) { rotationAngle+=angle; }
	public void pivot(float x, float y) { pivotPoint.set(x, y); }
	
	public void playTransformationAnimation(float duration, float x, float y) 
	{
		Ani.to(this, duration, "offsetX", offsetX+x);
		Ani.to(this, duration, "offsetY", offsetY+y);
	}
	public void playRotationAnimation(float duration, float ang) 
	{
		Ani.to(this, duration, "rotationAngle", rotationAngle+ang);
	}
	public void loopTransformationAnimation(float duration, float x, float y)
	{
		animations.add(Ani.to(this, duration, "offsetX", offsetX+x));
		animations.add(Ani.to(this, duration, "offsetY", offsetY+y));
	}
	public void loopRotationAnimation(float duration, float ang)
	{
		animations.add(Ani.to(this, duration, "rotationAngle", rotationAngle+ang));
	}
	private void updateAnimations() { for(Ani a:animations) if(a.isEnded()) {a.reverse();a.start();} }
	public void removeAnimations() 
	{ 
		for(Ani a:animations) a.pause();
		animations.removeAll(animations);
	}
}
