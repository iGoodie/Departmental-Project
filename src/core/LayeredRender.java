package core;

import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

public class LayeredRender extends PApplet {
	private PGraphics[] layers;	

	public void initializeLayers(int layerSize) {
		Game game = Game.getInstance();
		layers = new PGraphics[layerSize];
		for(int i=0; i<layerSize; i++) {
			layers[i]=game.createGraphics(game.width, game.height);
		}
	}
	
	public void pushRender() {
		for(int i=0; i<layers.length; i++){
			layers[i].beginDraw();
		}
	}
	
	public void renderByOrder() {
		Game game = Game.getInstance();
		for(int i=layers.length-1; i>=0; i--){
			game.image(layers[i], 0, 0);
		}
	}
	
	public void popRender() {
		for(int i=0; i<layers.length; i++){
			layers[i].endDraw();
		}
	}
	
	public void image(int layerIndex, PImage img, float a, float b) {
		layers[layerIndex].image(img, a, b);
	}
	
	public void text(int layerIndex, String str, float x, float y) {
		layers[layerIndex].text(str, x, y);
	}
	
	public void resetLayer(int layerIndex) {
		layers[layerIndex].background(0x00_FFFFFF);
	}
	
	public void resetLayers(){
		for(int i=0; i<layers.length; i++) {
			resetLayer(i);
		}
	}
}
