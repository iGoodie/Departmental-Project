package logic;

import java.util.HashMap;
import java.util.Set;

import util.FileUtils;

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
		String[] lines = FileUtils.readExternalString("iteminfo.txt").split("\n");
		for(int i=0; i<lines.length; i++){
			if(!lines[i].startsWith("//")){
				String[] cells = lines[i].split("\t");
				Item item = new Item(cells[0], Integer.parseInt(cells[3]), "items/"+cells[2]+".png", Integer.parseInt(cells[1]));
				int atk = Integer.parseInt(cells[4]);
				int def = Integer.parseInt(cells[5]);
				int mp = Integer.parseInt(cells[6]);
				int hp = Integer.parseInt(cells[7]);
				int mana = Integer.parseInt(cells[8]);
				int metadata = Integer.parseInt(cells[9]);
				item.setAttributes(atk, def, mp, hp, mana, metadata);
				item.setDescription(cells[10]);
				items.put(item.getName(), item);
			}
		}
	}
}
