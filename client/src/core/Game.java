package core;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.Properties;

import com.jogamp.newt.event.KeyEvent;

import animations.Animations;
import controllers.Fonts;
import controllers.Items;
import controllers.Keyboard;
import controllers.MouseCursors;
import controllers.StageController;
import controllers.Tiles;
import de.looksgood.ani.Ani;
import gui.HUDLogger;
import processing.core.PApplet;
import processing.opengl.PJOGL;

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
		size(ST_WIDTH, ST_HEIGHT, P2D);
		PJOGL.setIcon("icon32.png");
	}
	
	public void setup(){
		initializeLayers(3 + 2);
		surface.setTitle(GAME_TITLE + " - " + GAME_VERSION);
		handleOptions();
		
		/*Initialize game objects*/
		MouseCursors.loadCursors();
		Fonts.loadFonts();
		Items.loadItems();
		Tiles.loadTiles();
		Animations.loadAnimations();
		
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
		pushRender();
		background(0xFF_101010);

		/*Delta-time Updates*/
		StageController.update(dt);
		
		/*Tick Updates*/
		if(now-tickTimer >= TICK_MILLIS){
			StageController.updateTick();
			tickTimer = now;
		}
		
		/*Actual Rendering*/
		setViewportScale(0.5f);
		resetLayers();
		StageController.render();
		renderByOrder();
		if(UNIVERSAL_DEBUG_MODE) renderDebug();
		//popRender();
	}

	public void terminate(){
		exit();
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
		text("ARGB: "+hex(getGraphics().get(mouseX, mouseY)).replaceFirst("FF", "FF_"), 10, 68);
	}
	
	/*Overriding Methods*/
	public void mousePressed() { 
		int clickedLayer = getClickedLayerIndex(mouseX, mouseY);
		StageController.handleMouse(clickedLayer, mouseButton, mouseX, mouseY);
	}
	public void mouseReleased() {}
	public void keyPressed() {
		Keyboard.setKeyPressed(key, keyCode);
		if(key==ESC){
			key=0;
		}
	}
	public void keyReleased() {
		Keyboard.setKeyReleased(key, keyCode);
		if(key == CODED || key == 0x0000){
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
	public static void main(String[] args) {
		PApplet.main(mergeArrays(new String[]{
			"--display="+primaryMonitorIndex(),
			"--present-stop-color=#000000",
			//"--sketch-path="+System.getProperty("user.dir"),
			Game.class.getName()
		}, args));
	}
	
	private static int primaryMonitorIndex() {
		GraphicsDevice[] monitors = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices();
		GraphicsDevice primaryMonitor = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		for(int i=0; i<monitors.length; i++) {
			if(monitors[i].equals(primaryMonitor)) {
				return i+1;
			}
		}
		return -1;
	}
	
	private static String[] mergeArrays(String[]...arrays) {
		int length = 0;
	    for (String[] array : arrays) {
	        length += array.length;
	    }
	    String[] result = new String[length];
	    int pos = 0;
	    for (String[] array : arrays) {
	        for (String element : array) {
	            result[pos] = element;
	            pos++;
	        }
	    }
	    return result;
	}
}
