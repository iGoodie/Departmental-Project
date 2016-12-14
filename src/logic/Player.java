package logic;

public class Player {
	boolean alive = true;
	int maxHealth = 20;
	int health = maxHealth;
	
	public int getMaxHealth(){
		return maxHealth;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void damage(int dmg){
		if(health-dmg<=0){
			health = 0;
			alive = false;
			return;
		}
		health -= dmg;
	}
	
	public void heal(int hp){
		health = Math.min(maxHealth, health+hp);
	}
}
