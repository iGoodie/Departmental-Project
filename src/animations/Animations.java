package animations;

import java.util.HashMap;

import de.looksgood.ani.Ani;
import sprites.PartialSprite;

public class Animations 
{
	PartialSprite p;
	String cur;
	//Animation cur = new Animation();
	HashMap<String, Animation> anims = new HashMap<>();
	public Animation getAnimation(String name) { return anims.get(name);}
	public String currentAnimation() { return cur; }

	public Animations(PartialSprite parent) { p=parent; }

	public void putTransition(String name, float duration, float x, float y) 
	{
		if(anims.containsKey(name))
			anims.get(name).putTransition(p, duration, x, y);
		else{
			Animation a = new Animation();
			a.putTransition(p, duration, x, y);
			anims.put(name, a);
		}
	}

	public void putRotation(String name, float duration, float ang) 
	{
		if(anims.containsKey(name))
			anims.get(name).putRotation(p, duration, ang);
		else {
			Animation a = new Animation();
			a.putRotation(p, duration, ang);
			anims.put(name, a);
		}
	}

	public void playAnimation(String name) 
	{
		if(!anims.containsKey(name)) return;
		if(cur!=null) anims.get(cur).stopAnis();
		cur = name;
		anims.get(name).pushAnis(); 
	}

	public void updateAnis() {
		for(Object an:anims.values().toArray()) {
			for(Ani a:((Animation)an).getKeyAnimations()){
				if(a.isEnded()){
					a.reverse();
					a.start();
				}
			}
		}
	}
}
