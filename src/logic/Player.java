package logic;

public class Player {
	boolean alive = true;
	
	int maxHealth=20, health=maxHealth;
	int maxMana=20, mana=maxMana;
	int atk=0, def=0, magicPt=0;
	int souls=0;
	
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
}
