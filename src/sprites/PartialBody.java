package sprites;

import java.util.ArrayList;

import processing.core.PVector;

public class PartialBody 
{
	public PVector worldPos = new PVector();
	ArrayList<PartialSprite> partialSprites = new ArrayList<>();
	ArrayList<PVector> offsets = new ArrayList<>();
	
	public PartialBody() {}
	
	public void update(float dt) 
	{
		for(PartialSprite p:partialSprites) p.update(dt);
	}
	public void updateTick() {}
	public void render() 
	{
		for(int i=0; i<partialSprites.size(); i++)
		{
			PVector offset = offsets.get(i);
			partialSprites.get(i).render(worldPos.x+offset.x, worldPos.y+offset.y);
		}
	}
	public void dispose() {}
	
	public void setWorldPos(float x, float y) { worldPos.set(x, y); }
	
	public void addPartialSprite(PartialSprite partialSprite, float offsetX, float offsetY) 
	{
		partialSprites.add(partialSprite);
		offsets.add(new PVector(offsetX, offsetY));
	}
	public void playAnimation(String name) {}
}
