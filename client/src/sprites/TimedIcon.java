package sprites;

import core.Game;
import processing.core.PImage;

public class TimedIcon {
	int currentState = -1;
	int clock = 0;
	PImage[] stateIcons;
	int[] stateMs;
	
	public TimedIcon(PImage[] _icons, int initialState){
		currentState = initialState;
		stateIcons = _icons;
	}
	
	public TimedIcon(int initialStage){
		currentState = initialStage;
	}
	
	public TimedIcon(PImage... icons){
		currentState = 0;
		stateIcons = icons;
	}
	
	public void setIcons(PImage... icons){
		stateIcons = icons;
	}
	
	public void setTimes(int... times){
		stateMs = times;
	}

	public void setClock(int _clock){
		clock = _clock;
	}

	public void reset(){
		currentState = 0;
		clock = 0;
	}
	
	public void update(int dt){
		clock += dt;
		if(clock >= stateMs[currentState]){
			clock = 0;
			currentState = (currentState+1) % stateIcons.length;
		}
	}
	
	public void render(int layer, int x, int y){
		Game.getInstance().getLayer(layer).image(stateIcons[currentState], x, y);
	}
}
