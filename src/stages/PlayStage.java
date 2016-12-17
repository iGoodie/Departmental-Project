package stages;

import java.awt.event.KeyEvent;
import java.util.Random;

import core.Game;
import gui.HUDIndicator;
import gui.HUDInventory;
import gui.HUDLogger;
import gui.HUDSlots;
import gui.MouseContainer;
import logic.ItemStack;
import logic.Items;
import logic.Player;
import processing.core.PImage;
import util.FileUtils;

public class PlayStage implements IStage
{
	Player player = new Player();
	PImage viewport = FileUtils.readExternalPImage("ui/viewport.png");
	PImage systemLog = FileUtils.readExternalPImage("ui/systemLog.png");
	
	public PlayStage() {}
	
	public int getID() { return 2; }
	
	public void update(int dt) {
		HUDLogger.update();
	}
	
	public void updateTick() {}
	
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
			if(MouseContainer.grabbedFrom() == MouseContainer.INVENTORY){
				HUDInventory.renderItemBorder(MouseContainer.getGotIndex());
			}
			else if(MouseContainer.grabbedFrom() == MouseContainer.SLOTS){
				HUDSlots.renderItemBorder(MouseContainer.getGotIndex());
			}
		}
	}
	
	public void handleKey(char key, int keyCode) {
		if(key == Game.CODED){
			if(keyCode == KeyEvent.VK_F9){
				switch(new Random().nextInt(3)){
				case 0:	HUDInventory.addItemStack(new ItemStack(Items.getItem("Angelic Wings"), new Random().nextInt(100)+1)); break;
				case 1:	HUDInventory.addItemStack(new ItemStack(Items.getItem("Katana"), new Random().nextInt(1)+1)); break;
				case 2:	HUDInventory.addItemStack(new ItemStack(Items.getItem("Axe"), new Random().nextInt(1)+1)); break;
			}
			HUDLogger.setMessage(HUDLogger.SYSTEM_DEBUG, "Random item added.");
			}
		}
	}
	
	public void handleMouse(int layerClicked, int mouse, int x, int y) {
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
					}
				}
				else if(HUDSlots.isMouseOnGrid(x, y)){ //Clicked on Slots?
					if(MouseContainer.isEmpty()){ //Is Empty?
						HUDSlots.mouseClickedGrab(x, y);
					}
					else{ // Contains something?
						HUDSlots.mouseClickedPut(x, y);
					}
				}
			}
		}
	}
	
	public void dispose() {}
}
