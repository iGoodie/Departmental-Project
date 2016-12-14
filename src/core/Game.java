package core;

import java.util.Properties;

import controllers.StageController;
import de.looksgood.ani.Ani;
import processing.core.PApplet;

public class Game extends LayeredRender implements IConstants {
	/*Singleton Model*/
	private static Game instance;
	public static Game getInstance() { return instance; }
	
	/*Fields*/
	Properties generalOptions;
	int tickTimer=0, frameTimer=0;
	
	/*Mechanism Methods*/
	public void settings(){
		handleOS();
		instance=this;
		size(ST_WIDTH, ST_HEIGHT);
	}
	
	public void setup(){
		initializeLayers(16);
		surface.setTitle(GAME_TITLE + " - " + GAME_VERSION);
		surface.setIcon(loadImage("icon32.png"));
		handleOptions();
		
		Ani.init(this);
		Ani.setDefaultEasing(Ani.LINEAR);
		Ani.setDefaultTimeMode(Ani.SECONDS);
	}
	
	public void draw(){
		/*Time update*/
		int now = millis();
		int dt = now - frameTimer;
		frameTimer = now;
		
		background(0xFF_4F0202);
		
		/*Delta-time Updates*/
		StageController.update(dt);
		
		/*Tick Updates*/
		if(now-tickTimer >= TICK_MILLIS){
			StageController.updateTick();
			tickTimer = now;
		}
		
		/*Renders*/
		pushRender();
		resetLayers();
		StageController.render();
		renderByOrder();
		if(UNIVERSAL_DEBUG_MODE) renderDebug();
		popRender();
	}
	
	/*Helper Methods*/
	private void handleOS(){
		if(!System.getProperty("os.name").startsWith("Windows")){
			System.err.println("Your OS isn't supported by this game.");
			System.exit(ILLEGAL_OS_STATE);
		}
	}
	private void handleOptions() {}
	private void renderDebug(){
		text(StageController.getStageName(), 10, 20);
		text("FC:"+frameCount, 10, 32);
		text("FPS:"+(int)frameRate, 10, 44);
	}
	
	/*Overriding Methods*/
	public void mousePressed() {}
	public void mouseReleased() {}
	public void keyPressed() { StageController.handleKey(key, keyCode); }
	public void keyReleased() {}
	public void mouseWheel() {}
	
	/*Hand-defined Methods*/
	
	/*Unique Main Method*/
	public static void main(String[] args) { PApplet.main(Game.class.getName()); }
}
