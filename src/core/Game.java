package core;

import java.util.Properties;

import controllers.Fonts;
import controllers.StageController;
import de.looksgood.ani.Ani;
import logic.Items;
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
		
		/*Initialize game objects*/
		Fonts.loadFonts();
		Items.loadItems();
		
		Ani.init(this);
		Ani.setDefaultEasing(Ani.LINEAR);
		Ani.setDefaultTimeMode(Ani.SECONDS);
	}
	
	public void draw(){
		/*Time update*/
		int now = millis();
		int dt = now - frameTimer;
		frameTimer = now;
		
		background(0xFF_101010);
		
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
		//popRender();
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
		text(String.format("Mouse:%d,%d", mouseX, mouseY), 10, 56);
	}
	
	/*Overriding Methods*/
	public void mousePressed() { StageController.handleMouse(mouseButton, mouseX, mouseY); }
	public void mouseReleased() {}
	public void keyPressed() {}
	public void keyReleased() { StageController.handleKey(key, keyCode); }
	public void mouseWheel() {}
	
	/*Hand-defined Methods*/
	
	/*Unique Main Method*/
	public static void main(String[] args) { PApplet.main(Game.class.getName()); }
}
