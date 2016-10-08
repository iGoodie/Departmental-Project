package core;

import java.util.Properties;

import processing.core.PApplet;
import controllers.StageController;

public class Game extends PApplet implements IConstants
{
	/*Singleton Model*/
	private static Game instance;
	public static Game getInstance() { return instance; }
	
	/*Fields*/
	Properties generalOptions;
	int tickTimer=0, frameTimer=0;
	
	/*Mechanism Methods*/
	public void settings()
	{
		handleOS();
		instance=this;
		size(640, 480);
	}
	
	public void setup()
	{
		surface.setTitle(GAME_TITLE + " - " + GAME_VERSION);
		surface.setIcon(loadImage("icon32.png"));
		handleOptions();
	}
	
	public void draw() 
	{
		int now = millis();
		int dt = now - frameTimer;
		frameTimer = now;
		
		background(0);
		
		StageController.update(dt);
		
		if(now-tickTimer >= TICK_MILLIS)
		{
			StageController.updateTick();
			
			tickTimer = now;
		}
		
		StageController.render();
		renderDebug();
	}
	
	/*Helper Methods*/
	private void handleOS()
	{
		if(!System.getProperty("os.name").startsWith("Windows"))
		{
			System.err.println("Your OS isn't supported by this game.");
			System.exit(ILLEGAL_OS_STATE);
		}
	}
	private void handleOptions() {}
	private void renderDebug() 
	{
		text(StageController.getStageName(), 10, 20);
		text("FC:"+frameCount, 10, 32);
		text("FPS:"+(int)frameRate, 10, 44);
	}
	
	/*Overriding Methods*/
	public void mouseClicked() {}
	public void mouseReleased() {}
	public void keyPressed() {}
	public void keyReleased() {}
	public void mouseWheel() {}
	
	/*Unique Main Method*/
	public static void main(String[] args) { PApplet.main(Game.class.getName()); }
}
