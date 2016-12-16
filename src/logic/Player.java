package logic;

public class Player {
	boolean alive = true;
	int maxHealth = 20, health = maxHealth;
	int atkMin, atkMax, def;
	
	public int getMaxHealth() {
		return maxHealth;
	}
	
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = Math.min(maxHealth, 100);
	}
	
	public int getHealth() {
		return health;
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
