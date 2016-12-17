package gui;

import core.Game;
import logic.Item;
import logic.ItemStack;
import processing.core.PImage;
import processing.core.PVector;
import util.FileUtils;

public class HUDSlots {
	static PImage bgStats = FileUtils.readExternalPImage("ui/Kharacter.png");
	static PImage selector = FileUtils.readExternalPImage("ui/itemSelector2.png");
	static PVector pos = new PVector(Game.getInstance().width-bgStats.width-16, 16);
	static String[] slotNames = new String[]{
			"Necklace", "Head",	"Wing",
			"Weapon", "Armor", "Gloves",
			"Accessory", "Boots", "Crystal",
	};
	static int[] slotTypes = new int[]{
			0, 1, 2,
			3, 4, 5,
			6, 7, 6
	};

	static Item[] slots = new Item[9];

	public static String getSlotName(int index){
		return slotNames[index];
	}

	public static Item getSlot(int index){
		return slots[index];
	}

	private static PVector getSlotPos(int index) {
		return new PVector(
				pos.x + 40*(index%3),
				pos.y+20 + 40*(index/3));
	}

	public static void setSlot(Item item, int index){
		slots[index] = item;
	}
	
	public static void removeSlot(int index){
		slots[index] = null;
	}
	
	public static int equipableSlot(ItemStack item){
		int type = item.getType();
		for(int i=0; i<slots.length; i++){ //Iterate slots
			if(slotTypes[i] == type){
				return i;
			}	
		}
		return -1;
	}

	public static boolean doesTypeMatch(Item item, int index){
		return item.getType() == slotTypes[index];
	}
	
	public static boolean isSlotEmpty(int index){
		return slots[index] == null;
	}

	public static boolean isEquipable(Item item, int index){
		return isSlotEmpty(index) && doesTypeMatch(item, index);
	}
	
	public static boolean isMouseOnGrid(int x, int y){
		if(x >= pos.x && x <= pos.x+bgStats.width){
			if(y >= pos.y+20 && y <= pos.y+bgStats.height){
				return true;
			}
		}
		return false;
	}

	private static int posToIndex(float mouseX, float mouseY){
		for(int i=0; i<slots.length; i++){
			PVector pos = getSlotPos(i);
			if(mouseX >= pos.x && mouseX <= pos.x+40){
				if(mouseY >= pos.y && mouseY <= pos.y+40){
					return i;
				}
			}
		}
		return -1;
	}

	public static void mouseClickedPut(int x, int y) {
		int clickedIndex = posToIndex(x, y);
		Item clickedItem = getSlot(clickedIndex);
		if(MouseContainer.grabbedFrom() == MouseContainer.INVENTORY){ //Got from Inventory?
			if(isSlotEmpty(clickedIndex)){ //Is Slot Empty?
				if(doesTypeMatch(MouseContainer.getItem(), clickedIndex)){ // Type Match?
					HUDLogger.setMessage(HUDLogger.INFO, MouseContainer.getItem().getName()+" equiped.");
					setSlot(MouseContainer.getItem(), clickedIndex);
					MouseContainer.decreaseAmount(1);
					return;
				}
			}
			else{ // Isn't slot empty?
				if(doesTypeMatch(MouseContainer.getItem(), clickedIndex)){
					HUDLogger.setMessage(HUDLogger.INFO, MouseContainer.getItem().getName()+" equiped.");
					HUDInventory.setItemStack(MouseContainer.getGotIndex(), new ItemStack(clickedItem, 1));
					setSlot(MouseContainer.getItem(), clickedIndex);
					MouseContainer.nullify();
					return;
				}
			}
		}
		if(MouseContainer.grabbedFrom() == MouseContainer.SLOTS){ //Got from Slots?
			
		}
	}

	public static void mouseClickedGrab(int x, int y) {
		int clickedIndex = posToIndex(x, y);
		if(clickedIndex != -1){
			Item clickedItem = getSlot(clickedIndex);
			if(clickedItem != null){
				MouseContainer.setContainer(MouseContainer.SLOTS, new ItemStack(clickedItem, 1), clickedIndex);
			}
		}
	}
	
	public static void renderItemBorder(int index){
		PVector itemPos = getSlotPos(index);
		Game.getInstance().image(2, selector, itemPos.x, itemPos.y);
	}

	public static void render(){
		Game game = Game.getInstance();
		game.image(2, bgStats, pos.x, pos.y);
		for(int i=0; i<slots.length; i++){
			if(slots[i] != null){
				PVector pos = getSlotPos(i);
				slots[i].render(2, pos.x, pos.y);
			}
		}
	}
}
