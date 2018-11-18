package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import core.Game;
import physics.HitTest;
import processing.core.PImage;
import processing.core.PVector;

public class Button {
	PVector pos;
	ActionListener action;
	PImage[] states = new PImage[3];
	int curState = 0;
	boolean mousePressed;
	
	public Button(int x, int y){
		pos = new PVector(x, y);
	}
	
	public void setStates(PImage...images){
		states = images;
	}
	
	public void setAction(ActionListener a){
		action = a;
	}
	
	public void update(int x, int y){
		if(HitTest.pointVsPImage(x, y, pos, states[curState])){
			if(Game.getInstance().mousePressed && Game.getInstance().mouseButton==Game.LEFT){
				mousePressed = true;
				curState = 2;
			}
			else{
				if(mousePressed) {
					action.actionPerformed(null);
				}
				
				mousePressed = false;
				curState = 1;
			}
		}
		else{
			mousePressed = false;
			curState = 0;
		}
	}
	
	public void render(){
		Game.getInstance()
		.getLayer(1)
		.image(states[curState], pos.x, pos.y);
	}
}
