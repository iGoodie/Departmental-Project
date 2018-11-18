package physics;

import processing.core.PVector;

public class HitResolution {
	public static PVector rectVsCircleIntersect(float rx, float ry, float rw, float rh, float cx, float cy, float cr){
		PVector p = new PVector();
		p.x = HitTestBasic.clamp(cx, rx, rx+rw);
		p.y = HitTestBasic.clamp(cy, ry, ry+rh);
		return p;
	}
}
