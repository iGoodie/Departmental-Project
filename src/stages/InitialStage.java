package stages;

import core.Game;
import sprites.PartialBody;
import sprites.PartialSprite;

public class InitialStage implements IStage
{
	public int getID() { return 0; }
	
	PartialBody b = new PartialBody();
	public InitialStage() 
	{
		Game game = Game.getInstance();
		PartialSprite ps1 = new PartialSprite(game.loadImage("body.png"), 0, 50, 50);
		PartialSprite ps2 = new PartialSprite(game.loadImage("legs.png"), 0, 50, 50);
		PartialSprite ps3 = new PartialSprite(game.loadImage("tail.png"), 0, 50, 50);
		ps1.loopTransformationAnimation(1f, 0f, 5f);
		ps3.loopTransformationAnimation(1f, 0f, 5f);
		ps3.loopRotationAnimation(1f, 0.3f);
		
		b.addPartialSprite(ps3, 10, 0);
		b.addPartialSprite(ps2, 0, 0);
		b.addPartialSprite(ps1, 0, 0);
	}
	
	public void update(int dt) { b.update(dt); }
	
	public void updateTick() {  b.setWorldPos(b.worldPos.x+1, 100); }
	
	public void render() { Game.getInstance().scale(.5f); b.render(); Game.getInstance().scale(2f);}
	
	public void dispose() {}
}
