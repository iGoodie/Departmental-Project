package stages;

import core.Game;
import gui.HUDInventory;
import logic.Player;
import processing.core.PImage;
import util.FileUtils;

public class PlayStage implements IStage
{
	Player player = new Player();
	PImage hearthOutline = FileUtils.readExternalPImage("sprites/hearthOutline.png");
	PImage hearth= FileUtils.readExternalPImage("sprites/hearth.png");
	HUDInventory inventory = new HUDInventory();
	
	public int getID() { return 2; }
	
	public void update(int dt) {}
	
	public void updateTick() {}
	
	public void render() {
		Game game = Game.getInstance();
		for(int i=0; i<player.getMaxHealth()/2; i++){
			game.image(1, hearthOutline, 15+i*15, game.height-hearthOutline.height-10);
		}
		for(int i=0; i<player.getHealth()/2; i++){
			game.image(1, hearth, 15+i*15, game.height-hearth.height-10);
		}
		inventory.render();
	}
	
	public void handleKey(char key, int keyCode){}
	
	public void dispose() {}
}
