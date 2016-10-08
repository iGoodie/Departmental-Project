package stages;

import sprites.PartialSprite;
import animations.Camera;
import animations.PositionAnimation;
import core.Game;

public class InitialStage implements IStage
{
	public int getID() { return 0; }
	
	PartialSprite sp;
	public InitialStage()
	{
		sp = new PartialSprite(Game.getInstance().loadImage("sprite.png"));
		PositionAnimation a = new PositionAnimation();
		a.addKeyframe(1, 0, 0);
		a.addKeyframe(1000, 100, 0);
		a.addKeyframe(2000, 0, 0);
		sp.addAnimation(a);
		
	}
	
	public void update(int dt) { sp.updateAnimations(dt); }
	
	public void updateTick() {}
	
	public void render() { sp.render(new Camera(), 50, 50); }
	
	public void dispose() {}
}
