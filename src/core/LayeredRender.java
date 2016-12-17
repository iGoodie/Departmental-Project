package core;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PImage;

public class LayeredRender extends PApplet {
	static final int EMPTY_PIXEL = 0x00_000000;
	
	private PGraphics[] layers;	

	public void initializeLayers(int layerSize) {
		layers = new PGraphics[layerSize];
		for(int i=0; i<layerSize; i++) {
			layers[i]=createGraphics(width, height);
		}
	}
	
	/*Controllers*/
	public void pushRender() {
		for(int i=0; i<layers.length; i++){
			layers[i].beginDraw();
		}
	}
	
	public void renderByOrder() {
		for(int i=layers.length-1; i>=0; i--){
			image(layers[i], 0, 0);
			layers[i].endDraw();
		}
	}
	
	public PGraphics getLayer(int index){
		return layers[index];
	}
	
	public int getPixel(int layerIndex, int x, int y){
		return layers[layerIndex].get(x, y);
	}
	
	public PGraphics getClickedLayer(int x, int y){
		for(int i=1; i<layers.length; i++){
			if(layers[i].get(x, y) != EMPTY_PIXEL){
				return layers[i];
			}
		}
		return layers[layers.length-1];
	}
	
	public int getClickedLayerIndex(int x, int y){
		for(int i=1; i<layers.length; i++){
			if(layers[i].get(x, y) != EMPTY_PIXEL){
				return i;
			}
		}
		return layers.length-1;
	}
	
	/*Rendering Methods*/
	public void image(int layerIndex, PImage img, float a, float b) {
		layers[layerIndex].image(img, a, b);
	}
	
	public void imageCropped(int layerIndex, PImage img, int x, int y, int w, int h){
		PImage imgC = img.get(0, 0, w, h);
		layers[layerIndex].image(imgC, x, y);
	}
	
	public void textFont(int layerIndex, PFont which){
		layers[layerIndex].textFont(which);
	}
	
	public void text(int layerIndex, String str, float x, float y) {
		layers[layerIndex].text(str, x, y);
	}
	
	public void textWithStroke(int layerIndex, String str, float x, float y, int strokeColor){
		layers[layerIndex].pushStyle();
		layers[layerIndex].fill(strokeColor);
		layers[layerIndex].text(str, x-1, y);
		layers[layerIndex].text(str, x+1, y);
		layers[layerIndex].text(str, x, y-1);
		layers[layerIndex].text(str, x, y+1);
		layers[layerIndex].popStyle();
		layers[layerIndex].text(str, x, y);
	}
	
	public void resetLayer(int layerIndex) {
		layers[layerIndex].background(0, 0);
	}
	
	public void resetLayers(){
		for(int i=0; i<layers.length; i++) {
			resetLayer(i);
		}
	}
}
