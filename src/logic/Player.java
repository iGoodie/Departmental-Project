package logic;

import animations.Animations;
import animations.BasicAnimation;
import controllers.Keyboard;
import core.Game;
import gui.HUDSlots;
import processing.core.PGraphics;
import processing.core.PVector;
import sprites.AnimatedSprite;

public class Player {
	static final float SCALE = 1;
	
	boolean alive = true;
	
	PVector pos = new PVector(0, 0);
	PVector velocity = new PVector();
	float speed = 300f;
	
	int facingDir = 0; //0:front, 1:back
	String state = "Kevin-Idle";
	AnimatedSprite bodySpriteFront = new AnimatedSprite("sprites/Kevin.png", 104, 164);
	AnimatedSprite bodySpriteBack= new AnimatedSprite("sprites/KevinBack.png", 94, 164);
	AnimatedSprite weapon = null;
	
	int maxHealth=100, health=maxHealth;
	int maxMana=20, mana=maxMana;
	int atk=0, def=0, magicPt=0;
	int souls=0;
	
	public Player(){
		bodySpriteFront.startAnimation(state);
	}
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public int getMaxMana(){
		return maxMana;
	}
	
	public void setMaxHealth(int _maxHealth) {
		maxHealth = Math.min(_maxHealth, 100);
	}
	
	public void setMaxMana(int _maxMana){
		maxMana = Math.min(_maxMana, 100);
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getMana(){
		return mana;
	}
	
	public int getAtk(){
		return atk;
	}
	
	public int getDef(){
		return def;
	}
	
	public int getMagicPt(){
		return magicPt;
	}
	
	public int getSouls(){
		return souls;
	}
	
	public PVector getPos(){
		return pos;
	}
	
	public void updateStats(){
		maxHealth = 100 + HUDSlots.totalHpBonus();
		maxMana = 20 + HUDSlots.totalManaBonus();
		atk = HUDSlots.totalAtkBonus();
		def = HUDSlots.totalDefBonus();
		magicPt = HUDSlots.totalMagicPtsBonus();
	}
	
	public void damage(int dmg) {
		if(health-dmg<=0){
			health = 0;
			alive = false;
			return;
		}
		health -= dmg;
	}
	
	public void heal(int hp) {
		health = Math.min(maxHealth, health+hp);
	}

	public void setPos(float x, float y){
		pos.set(x, y);
	}
	
	public void playAnimation(String name){
		if(facingDir==0){
			bodySpriteFront.startAnimation(name);
		}
		else{
			bodySpriteBack.startAnimation(name);
		}
		state = name;
	}
	
	public void update(int dt){
		//Animation update
		if(facingDir==0){
			bodySpriteFront.update(dt);
		}
		else{
			bodySpriteBack.update(dt);
		}
		if(weapon!=null){
			weapon.update(dt);
		}
		//Velocity update
		velocity.set(0, 0);
		playAnimation("Kevin-Idle");
		if(Keyboard.isKeyOn('w') || Keyboard.isKeyOn('W')){
			playAnimation("Kevin-Walk");
			velocity.add(0, -1);
		}
		if(Keyboard.isKeyOn('s') || Keyboard.isKeyOn('S')){
			playAnimation("Kevin-Walk");
			velocity.add(0, 1);
		}
		if(Keyboard.isKeyOn('a') || Keyboard.isKeyOn('A')){
			playAnimation("Kevin-Walk");
			velocity.add(-1, 0);
		}
		if(Keyboard.isKeyOn('d') || Keyboard.isKeyOn('D')){
			playAnimation("Kevin-Walk");
			velocity.add(1, 0);
		}
		velocity.normalize().mult(speed);
		pos.add(PVector.mult(velocity, dt/1000f));
	}
	
	public void updateTick(){
		health = Math.min(maxHealth, health+1);
		mana = Math.min(maxMana, mana+1);
	}
	
	public void render(int layer, float x, float y){
		PGraphics gameLayer = Game.getInstance().getLayer(layer);
		gameLayer.scale(SCALE);
		if(facingDir==0){
			BasicAnimation a = Animations.getAnimation(state);
			bodySpriteFront.render(layer, x, y);
			gameLayer.pushMatrix();
			gameLayer.translate(x+a.getTranslateX(), y+a.getTranslateY());
			gameLayer.scale(a.getScaleX(), a.getScaleY());
			gameLayer.rotate(a.getRotation());
			if(HUDSlots.getSlot(1)!=null){
				gameLayer.image(HUDSlots.getSlot(1).getSpriteFront(), -103, -164);
			}
			if(HUDSlots.getSlot(2)!=null){
				gameLayer.image(HUDSlots.getSlot(2).getSpriteFront(), -103, -164);
			}
			if(HUDSlots.getSlot(3)!=null){
				gameLayer.image(HUDSlots.getSlot(3).getSpriteFront(), -103, -164);
			}
			if(HUDSlots.getSlot(4)!=null){
				gameLayer.image(HUDSlots.getSlot(4).getSpriteFront(), -103, -164);
			}
			if(HUDSlots.getSlot(5)!=null){
				gameLayer.image(HUDSlots.getSlot(5).getSpriteFront(), -103, -164);
			}
			if(HUDSlots.getSlot(7)!=null){
				gameLayer.image(HUDSlots.getSlot(7).getSpriteFront(), -103, -164);
			}
			gameLayer.popMatrix();
		}
		else{
			bodySpriteBack.render(layer, x, y);			
		}
	}
}
