package animations;

import core.Game;

public class ValueAnimation implements Cloneable {
	int index = 0;
	int clock = 0;
	int[] timePts;
	float[] valuePts;
	float value;

	public void setValuePts(float...pts){
		valuePts = pts;
	}
	
	public float[] getValuePts(){
		return valuePts;
	}
	
	public void setTimePts(int..._timePts){
		timePts = _timePts;
	}
	
	public int[] getTimePts(){
		return timePts;
	}
	
	public float getValue(){
		return value;
	}
	
	public void update(int dt){
		clock += dt;
		if(clock > timePts[index+1]){
			clock = clock % timePts[timePts.length-1];
			index = (index+1) % (timePts.length-1);
		}
		value = Game.map(clock, timePts[index], timePts[index+1], valuePts[index], valuePts[index+1]);
	}

	public void reset(){
		index = 0;
		clock = 0;
	}
}
