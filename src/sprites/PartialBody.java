package sprites;

import java.util.ArrayList;

import processing.core.PVector;
import util.Pair;

public class PartialBody 
{
	public PVector worldPos = new PVector();
	//TODO: starting pt
	ArrayList<Pair<PartialSprite, PVector>> sprites = new ArrayList<>();
	
	public PartialBody() {}
	
	public void update(float dt) 
	{
		for(Pair<PartialSprite, ?> p:sprites) p.getFirst().update(dt);
	}
	public void updateTick() {}
	public void render() 
	{
		for(int i=0; i<sprites.size(); i++)
		{
			PVector offset = sprites.get(i).getSecond();
			sprites.get(i).getFirst().render(worldPos.x+offset.x, worldPos.y+offset.y);
		}
	}
	public void dispose() {}
	
	public void setWorldPos(float x, float y) { worldPos.set(x, y); }
	
	public void addPartialSprite(PartialSprite partialSprite, float offsetX, float offsetY) 
	{
		sprites.add(new Pair<PartialSprite, PVector>(partialSprite, new PVector(offsetX, offsetY)));
	}
	public void playAnimation(String name) { for(Pair<PartialSprite, ?> p:sprites) p.getFirst().playAnimation(name); }
	
}
