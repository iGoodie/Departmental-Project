package physics;

import processing.core.PImage;
import processing.core.PVector;

public class HitTest {
	static Range rangeIntersection(float a1, float b1, float a2, float b2){
		float min1 = Math.min(a1, b1);
		float min2 = Math.min(a2, b2);
		float max1 = Math.max(a1, b1);
		float max2 = Math.max(a2, b2);
		return new Range(Math.max(min1, min2), Math.min(max1, max2));
	}
	
	static int getAlpha(int argb){
		return (argb >>> 24) & 0xFF;
	}
	
	/*A.k.a bitmap collision*/
	public static boolean PImageVsPImage(PVector pos1, PImage img1, PVector pos2, PImage img2){
		Range rangeX = rangeIntersection(pos1.x, pos1.x+img1.width, pos2.x, img2.width);
		Range rangeY = rangeIntersection(pos1.y, pos1.y+img1.height, pos2.y, pos2.y+img2.height);
		int x1=(int)pos1.x, y1=(int)pos1.y;
		int x2=(int)pos2.x, y2=(int)pos2.y;
		for(int i=rangeX.a; i<rangeX.b; i++){
			for(int j=rangeY.a; j<rangeY.b; j++){
				if(getAlpha(img1.get(i-x1, j-y1)) > 0x00){
					if(getAlpha(img2.get(i-x2, j-y2)) > 0x00){
						return true;
					}
				}
			}
		}
		return false;
	}

	public static boolean pointVsPImage(int px, int py, PVector pos, PImage img){
		return getAlpha(img.get(px-(int)pos.x, py-(int)pos.y)) > 0x00;
	}
	
	public static boolean pointVsPImage(int px, int py, int x, int y, PImage img){
		return getAlpha(img.get(px-x, py-y)) > 0x00;
	}
}
class Range{
	int a, b;
	public Range(int _a, int _b){
		a = _a;
		b = _b;
	}
	public Range(float _a, float _b){
		a = (int)_a;
		b = (int)_b;
	}
}
