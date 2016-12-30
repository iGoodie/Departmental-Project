package sprites;

import animations.Animations;
import animations.BasicAnimation;
import core.Game;
import processing.core.PGraphics;
import processing.core.PImage;
import processing.core.PVector;
import util.FileUtils;

public class AnimatedSprite {
	PImage sprite;
	PVector internalPivot;
	BasicAnimation currentAnimation;

	public AnimatedSprite(String path, int pivotX, int pivotY){
		sprite = FileUtils.readExternalPImage(path);
		internalPivot = new PVector(pivotX, pivotY);
	}

	public void update(int dt){
		if(currentAnimation != null){
			currentAnimation.update(dt);
		}
	}

	public void startAnimation(String name){
		currentAnimation=Animations.getAnimation(name);
	}

	public void render(int layer, float x, float y){
		PGraphics gameLayers = Game.getInstance().getLayer(layer);
		if(currentAnimation!=null){
			gameLayers.pushMatrix();
			gameLayers.translate(x+currentAnimation.getTranslateX(), y+currentAnimation.getTranslateY());
			gameLayers.scale(currentAnimation.getScaleX(), currentAnimation.getScaleY());
			gameLayers.rotate(currentAnimation.getRotation());
			gameLayers.image(sprite, -internalPivot.x, -internalPivot.y);
			gameLayers.popMatrix();
		}
		else{
			gameLayers.pushMatrix();
			gameLayers.translate(x, y);
			gameLayers.image(sprite, -internalPivot.x, -internalPivot.y);
			gameLayers.popMatrix();
		}
	}
}
