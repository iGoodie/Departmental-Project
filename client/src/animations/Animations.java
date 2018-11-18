package animations;

import java.util.HashMap;

import util.FileUtils;

public class Animations {
	static HashMap<String, BasicAnimation> basicAnims = new HashMap<>();
	
	public static BasicAnimation getAnimation(String name){
		return basicAnims.get(name).copy();
	}
	
	private static int[] parseIntArray(String array){
		String[] elems = array.split(",");
		int[] arr = new int[elems.length];
		for(int i=0; i<elems.length; i++){
			arr[i] = Integer.parseInt(elems[i]);
		}
		return arr;
	}
	
	private static float[] parseFloatArray(String array){
		String[] elems = array.split(",");
		float[] arr = new float[elems.length];
		for(int i=0; i<elems.length; i++){
			arr[i] = Float.parseFloat(elems[i]);
		}
		return arr;
	}
	
	public static void loadAnimations(){
		String[] lines = FileUtils.readExternalString("animationinfo.txt").split("\n");
		for(int i=0; i<lines.length; i++){
			if(!lines[i].startsWith("//")){
				String[] cells = lines[i].split("\t");
				boolean tx=cells[0].contains("T"), 
						ty=cells[0].contains("t"), 
						sx=cells[0].contains("S"),
						sy=cells[0].contains("s"), 
						r=cells[0].contains("r");
				BasicAnimation anim = new BasicAnimation(sx||sy, tx||ty, r);
				if(tx || ty){
					anim.setTranslateTimes(parseIntArray(cells[2]));
				}
				if(tx){
					anim.setTranslateXValues(parseFloatArray(cells[5]));
				}
				if(ty){
					anim.setTranslateYValues(parseFloatArray(cells[6]));
				}
				if(sx || sy){
					anim.setScaleTimes(parseIntArray(cells[3]));
				}
				if(sx){
					anim.setScaleXValues(parseFloatArray(cells[7]));
				}
				if(sy){
					anim.setScaleYValue(parseFloatArray(cells[8]));
				}
				if(r){
					anim.setRotationTimes(parseIntArray(cells[4]));
					anim.setRotationValues(parseFloatArray(cells[9]));
				}
				basicAnims.put(cells[1], anim);
			}
		}
	}
}