package stages;

import java.util.Random;

import core.Game;
import gui.HUDInventory;
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
	
	public PlayStage() {}
	
	public int getID() { return 2; }
	
	public void update(int dt) {}
	
	public void updateTick() {}
	
	public void render() {
		Game game = Game.getInstance();
		game.image(2, viewport, 16, 16);
		HUDInventory.render();
		HUDSlots.render();
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
		switch(new Random().nextInt(3)){
			case 0:	HUDInventory.addItemStack(new ItemStack(Items.getItem("Test Item"), new Random().nextInt(100)+1)); break;
			case 1:	HUDInventory.addItemStack(new ItemStack(Items.getItem("Cleaver"), new Random().nextInt(1)+1)); break;
			case 2:	HUDInventory.addItemStack(new ItemStack(Items.getItem("Claw"), new Random().nextInt(1)+1)); break;
		}
	}
	
	public void handleMouse(int mouse, int x, int y) {
		if(mouse == Game.RIGHT){
			if(!MouseContainer.isEmpty()) {
				MouseContainer.nullify();
			}
		}
		else if(mouse == Game.LEFT){
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
	
	public void dispose() {}
}
