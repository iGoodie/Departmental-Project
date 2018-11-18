package animations;

import core.Game;

public class PairAnimation implements Cloneable {
	int index = 0;
	int clock = 0;
	int[] timePts;
	float[] value1Pts, value2Pts;
	float value1, value2;
	
	public void setValue1Pts(float...pts){
		value1Pts = pts;
	}
	
	public float[] getValue1Pts(){
		return value1Pts;
	}
	
	public void setValue2Pts(float...pts){
		value2Pts = pts;
	}

	public float[] getValue2Pts(){
		return value2Pts;
	}
	
	public void setTimePts(int...pts){
		timePts = pts;
	}
	
	public int[] getTimePts(){
		return timePts;
	}

	public float getValue1(){
		return value1Pts != null ? value1 : Float.NaN;
	}
	
	public float getValue2(){
		return value2Pts != null ? value2 : Float.NaN;
	}
	
	public void update(int dt){
		clock += dt;
		if(clock > timePts[index+1]){
			clock = clock % timePts[timePts.length-1];
			index = (index+1) % (timePts.length-1);
		}
		if(value1Pts != null){
			value1 = Game.map(clock, timePts[index], timePts[index+1], value1Pts[index], value1Pts[index+1]);
		}
		if(value2Pts != null){
			value2 = Game.map(clock, timePts[index], timePts[index+1], value2Pts[index], value2Pts[index+1]);			
		}
	}

	public void reset(){
		index = 0;
		clock = 0;
	}
}
