package sprites;

import java.util.ArrayList;

import animations.Animation;
import animations.Animations;
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
	Animations anims = new Animations(this);
	//ArrayList<Ani> animations = new ArrayList<>();
	public float rotationAngle;
	public float offsetX, offsetY;
	
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
	public void update(float dt) { anims.updateAnis(); }
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
	public void playAnimation(String name) { anims.playAnimation(name); }
	public void putTransition(String name, float duration, float x, float y)
	{
		anims.putTransition(name, duration, x, y);
	}
	public void putRotation(String name, float duration, float ang)
	{
		anims.putRotation(name, duration, ang);
	}
	public void removeAnimations() 
	{ 
		
	}
}
