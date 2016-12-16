package logic;

import java.util.HashMap;
import java.util.Set;

public final class Items {
	static HashMap<String, Item> items = new HashMap<>();
	
	public static Item getItem(String name){
		return items.get(name);
	}
	
	public static Item getItem(int index){
		return (Item)items.values().toArray()[index];
	}
	
	public static Set<String> getItemNames(){
		return items.keySet();
	}
	
	public static void loadItems(){
		items.put("Test Item", new Item(99, "items/item0.png", 8));
		items.put("Cleaver", new Item(1, "items/cleaver.png", 3));
		items.put("Claw", new Item(1, "items/Claw.png", 2));
	}
}
