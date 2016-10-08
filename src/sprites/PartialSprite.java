package sprites;

import processing.core.PImage;
import processing.core.PVector;
import animations.Camera;
import animations.IAnimation;
import animations.PositionAnimation;
import core.Game;

public class PartialSprite 
{
	/*Fields*/
	PImage sprite;
	PVector pivotPoint;
	float rotationAngle;
	
	/*Animation Controllers*/
	PositionAnimation positionAnimation;
	
	/*Construction Methods*/
	public PartialSprite(PImage sprite)
	{
		this.sprite = sprite;
		rotationAngle = 0;
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
	public void render(Camera cam, float x, float y)
	{
		Game game = Game.getInstance();
		PVector offset = positionAnimation.getAnimOffset();
		
		game.pushMatrix();
		{
			game.translate(x+pivotPoint.x, y+pivotPoint.y);
			game.rotate(rotationAngle);
			game.image(sprite, -pivotPoint.x+offset.x, -pivotPoint.y+offset.y);
		}
		game.popMatrix();
	}
	
	public void rotate(float angle) { rotationAngle+=angle; }
	public void pivot(float x, float y) { pivotPoint.set(x, y); }
	
	public void updateAnimations(int dt) 
	{ 
		positionAnimation.update(dt);
	}
	public void addAnimation(IAnimation anim) 
	{
		if(anim instanceof PositionAnimation) positionAnimation = (PositionAnimation)anim;
	}
}
