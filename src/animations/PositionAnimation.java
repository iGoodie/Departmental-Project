package animations;

import java.util.ArrayList;

import processing.core.PVector;
import util.MathUtils;

public class PositionAnimation implements IAnimation
{
	public enum Types { Linear, Parabolic, Cubic, Trigonometric }

	float t = 0;
	ArrayList<PositionKeyframe> keyframes = new ArrayList<>();
	PositionKeyframe min, max;
	PVector animOffset = new PVector(0, 0);

	public PositionAnimation() {}

	public PVector getAnimOffset() { return animOffset; }

	public void update(int dt) 
	{
		if(keyframes.size() < 2) return;
		t = (t+dt) % keyframes.get(keyframes.size()-1).time;
		for(int i=0; i<keyframes.size(); i++)
		{
			if(min.time < t) if(max.time > t) break;
			min = max;
			max = keyframes.get(i);
		}
		if(min.x != max.x) animOffset.x = MathUtils.map(t, min.time, max.time, min.x, max.x); 
		if(min.y != max.y) animOffset.y = MathUtils.map(t, min.time, max.time, min.y, max.y);
	}

	public void addKeyframe(int timeMs, float internalX, float internalY)
	{ 
		PositionKeyframe k = new PositionKeyframe(timeMs, internalX, internalY);
		if(keyframes.size() == 0) min = k;
		else if(keyframes.size() == 1) max = k;
		keyframes.add(k);
	}
}
