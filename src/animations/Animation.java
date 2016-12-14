package animations;

import java.util.ArrayList;

import de.looksgood.ani.Ani;
import sprites.PartialSprite;

public class Animation 
{
	ArrayList<Ani> animations = new ArrayList<>();
	public ArrayList<Ani> getKeyAnimations() { return animations; }
	public Ani getKeyAnimation(int index) { return animations.get(index); } 
	
	public void putTransition(PartialSprite parent, float duration, float x, float y)
	{
		Ani xanim = Ani.to(parent, duration, "offsetX", parent.offsetX+x, Ani.LINEAR);
		Ani yanim = Ani.to(parent, duration, "offsetY", parent.offsetY+y, Ani.LINEAR);
		xanim.seek(0);
		xanim.pause();
		yanim.seek(0);
		yanim.pause();
		animations.add(xanim);
		animations.add(yanim);
	}	
	public void putRotation(PartialSprite parent, float duration, float ang)
	{
		Ani rotanim = Ani.to(parent, duration, "rotationAngle", parent.rotationAngle+ang, Ani.LINEAR);
		rotanim.seek(0);
		rotanim.pause();
		animations.add(rotanim);
	}

	public void pushAnis() { for(Ani a:animations) {a.start();} }
	public void stopAnis() { for(Ani a:animations) {a.seek(0); a.pause();} }
}
