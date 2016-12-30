package stages;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import controllers.Items;
import core.Game;
import gui.HUDIndicator;
import gui.HUDInventory;
import gui.HUDLogger;
import gui.HUDPopup;
import gui.HUDSlots;
import gui.MouseContainer;
import logic.Mob;
import logic.Player;
import logic.elements.ItemStack;
import processing.core.PImage;
import util.FileUtils;
import world.Room;

public class PlayStage implements IStage
{
	boolean paused = false;
	
	Player player = new Player();
	Room currentRoom = new Room(10, 10);
	ArrayList<Mob> mobs = new ArrayList<>();
	
	PImage viewport = FileUtils.readExternalPImage("ui/viewport.png");
	PImage systemLog = FileUtils.readExternalPImage("ui/systemLog.png");

	public PlayStage() {}

	public int getID() { return 2; }

	public void update(int dt) {
		HUDLogger.update();
		HUDPopup.update(dt);
		if(!paused){
			player.update(dt);
			for(int i=0; i<mobs.size(); i++){
				mobs.get(i).update(dt);
			}
			currentRoom.update(dt, player);
		}
	}

	public void updateTick() {
		player.updateTick();
	}

	public void render() {
		Game game = Game.getInstance();
		game.image(2, viewport, 16, 16);
		game.image(2, systemLog, 16, 400-36);
		HUDInventory.render();
		HUDSlots.render();
		HUDLogger.render();
		HUDIndicator.render(player);
		if(!MouseContainer.isEmpty()) {
			MouseContainer.render(0, game.mouseX-10, game.mouseY-10);
			if(MouseContainer.grabbedFrom() == MouseContainer.INVENTORY){ //Item borders
				HUDInventory.renderItemBorder(MouseContainer.getGotIndex());
			}
			else if(MouseContainer.grabbedFrom() == MouseContainer.SLOTS){
				HUDSlots.renderItemBorder(MouseContainer.getGotIndex());
			}
		}
		HUDPopup.render();
		HUDIndicator.handleMouseStand(player, game.mouseX, game.mouseY);
		HUDSlots.handleMouseStand(game.mouseX, game.mouseY);
		HUDInventory.handleMouseStand(game.mouseX, game.mouseY);
		player.render(3, 450, 400);
		currentRoom.render(4, 450-player.getPos().x, 400-player.getPos().y);
		for(int i=0; i<mobs.size(); i++){
			mobs.get(i).render(3, 450+mobs.get(i).getPos().x-player.getPos().x, 400+mobs.get(i).getPos().y-player.getPos().y);
		}
	}

	public void handleKeyRelease(char key, int keyCode) {
		if(key == Game.CODED){
			if(keyCode == KeyEvent.VK_F9){
				HUDInventory.addItemStack(new ItemStack(Items.getRandomItem(), (int) (Math.random()*99)));
				HUDLogger.setMessage(HUDLogger.SYSTEM_DEBUG, "Random item added.");
			}
			if(keyCode == KeyEvent.VK_F8){
				//HUDPopup.showItem(Items.getRandomItem());
				HUDPopup.showSaving();
			}
		}
		else if(key == 'r' || key == 'R'){
			Mob a = new Mob((int)(Math.random()*750), (int)(Math.random()*750), 10, 100);
			a.setSprite("sprites/RetardedMobFront.png", 52, 88);
			a.playAnimation("Kevin-Idle");
			mobs.add(a);
		}
	}

	public void handleMousePressed(int layerClicked, int mouse, int x, int y) {
		if(mouse == Game.RIGHT){
			if(!MouseContainer.isEmpty()) {
				MouseContainer.nullify();
			}
		}
		else if(mouse == Game.LEFT){
			if(layerClicked == 2){
				if(HUDInventory.isMouseOnGrid(x, y)){ //Clicked on Inventory?
					if(MouseContainer.isEmpty()){ //Is Empty?
						HUDInventory.mouseClickedGrab(x, y);
					}
					else{ //Contains Something?
						HUDInventory.mouseClickedPut(x, y);
						player.updateStats();
					}
				}
				else if(HUDSlots.isMouseOnGrid(x, y)){ //Clicked on Slots?
					if(MouseContainer.isEmpty()){ //Is Empty?
						HUDSlots.mouseClickedGrab(x, y);
					}
					else{ // Contains something?
						HUDSlots.mouseClickedPut(x, y);
						player.updateStats();
					}
				}
			}
		}
	}

	public void dispose() {}
}
