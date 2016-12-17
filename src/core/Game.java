package core;

import java.awt.event.KeyEvent;
import java.util.Properties;

import controllers.Fonts;
import controllers.MouseCursors;
import controllers.StageController;
import de.looksgood.ani.Ani;
import gui.HUDLogger;
import logic.Items;
import processing.core.PApplet;

public class Game extends LayeredRender implements IConstants {
	/*Singleton Model*/
	private static Game instance;
	public static Game getInstance() { return instance; }
	
	/*Fields*/
	public static boolean UNIVERSAL_DEBUG_MODE = true;
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
		MouseCursors.loadCursors();
		Fonts.loadFonts();
		Items.loadItems();
		
		Ani.init(this);
		Ani.setDefaultEasing(Ani.LINEAR);
		Ani.setDefaultTimeMode(Ani.SECONDS);
		
		MouseCursors.changeCursor(0);
	}
	
	public void draw(){
		/*Time update*/
		int now = millis();
		int dt = now - frameTimer;
		frameTimer = now;
		
		/*Rendering Initializers*/
		background(0xFF_101010);
		pushRender();

		/*Delta-time Updates*/
		StageController.update(dt);
		
		/*Tick Updates*/
		if(now-tickTimer >= TICK_MILLIS){
			StageController.updateTick();
			tickTimer = now;
		}
		
		/*Actual Rendering*/
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
	public void mousePressed() { 
		int clickedLayer = getClickedLayerIndex(mouseX, mouseY);
		StageController.handleMouse(clickedLayer, mouseButton, mouseX, mouseY);
	}
	public void mouseReleased() {}
	public void keyPressed() {}
	public void keyReleased() {
		if(key == CODED){
			if(keyCode == KeyEvent.VK_F11){
				UNIVERSAL_DEBUG_MODE = !UNIVERSAL_DEBUG_MODE;
				HUDLogger.setMessage(HUDLogger.SYSTEM_DEBUG, "Toggled debug mode.");
			}
			else if(keyCode == KeyEvent.VK_F10){
				HUDLogger.digitalFormat = !HUDLogger.digitalFormat;
				HUDLogger.setMessage(HUDLogger.SYSTEM_DEBUG, "Clock mode changed.");
			}
		}
		StageController.handleKey(key, keyCode); 
	}
	public void mouseWheel() {}
	
	/*Hand-defined Methods*/
	
	/*Unique Main Method*/
	public static void main(String[] args) { PApplet.main(Game.class.getName()); }
}
