package gui;

import logic.Item;
import logic.ItemStack;

public final class MouseContainer {
	public static final int //from
		INVENTORY = 0, 
		SLOTS = 1;
	
	static int from = -1;
	static ItemStack item = null;
	static int index = -1;
	
	public static ItemStack getItemStack(){
		return item;
	}
	
	public static int getGotIndex(){
		return index;
	}
	
	public static Item getItem(){
		return item.getItem();
	}
	
	public static int grabbedFrom(){
		return from;
	}
	
	public static void decreaseAmount(int num){
		item.decreaseAmount(num);
		if(item.getAmount() <= 0){
			deleteItem();
		}
	}
	
	public static void deleteItem(){
		if(from == INVENTORY){
			HUDInventory.removeSlot(index);
			nullify();
		}
		else if(from == SLOTS){
			HUDSlots.removeSlot(index);
			nullify();
		}
	}
	
	public static void setContainer(int _from, ItemStack _item, int _index){
		from = _from;
		item = _item.copy();
		index = _index;
	}
	
	public static void render(int layerID, float x, float y) {
		item.render(layerID, x, y);
	}
	
	public static boolean isEmpty(){
		return index==-1 && item==null && from==-1;
	}
	
	public static void nullify(){
		from = -1;
		item = null;
		index = -1;
	}
}
