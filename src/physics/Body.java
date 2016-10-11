package physics;

import java.util.ArrayList;

import processing.core.PVector;
import sprites.PartialSprite;

public class Body 
{
	IBounding bound;
	float weight=1;
	ArrayList<PartialSprite> partialSprites = new ArrayList<>();
	PVector worldPos = new PVector();
	
	public Body() {}
	
	public void update(float dt) {}
	public void updateTick() {}
	public void render() {}
	public void dispose() {}
	
	public void setBounding(IBounding bounding) { bound=bounding; }
	public void setWeight(float weight) { this.weight=weight; }
	public void setWorldPos(float x, float y) { worldPos.set(x, y); }
	public void addPartialSprite(PartialSprite ps) { partialSprites.add(ps); }
}
