package gui;

import core.Game;
import logic.ItemStack;
import processing.core.PImage;
import processing.core.PVector;
import util.FileUtils;

public class HUDInventory {
	static PImage invBg = FileUtils.readExternalPImage("sprites/inventoryHud.png");
	static PVector pos = new PVector(Game.getInstance().width-invBg.width-20, Game.getInstance().height-invBg.height-90);
	static PVector[][] itemPos = new PVector[][]{
		new PVector[] {new PVector(pos.x+7, pos.y+5), new PVector(pos.x+57, pos.y+5), new PVector(pos.x+105, pos.y+7)},
		new PVector[] {new PVector(pos.x+7, pos.y+55), new PVector(pos.x+57, pos.y+53), new PVector(pos.x+105, pos.y+55)},
		new PVector[] {new PVector(pos.x+8, pos.y+103), new PVector(pos.x+57, pos.y+100), new PVector(pos.x+106, pos.y+100)}
	};

	ItemStack[][] items = new ItemStack[3][3];
	int size = 0;
	
	public boolean isFull(){
		return size == 9;
	}
	
	public ItemStack getItemStack(int row, int column){
		return items[row][column];
	}
	
	public ItemStack getItemStack(int index){
		return items[index/items.length][index%items[0].length];
	}
	
	private void setItemStack(int index, ItemStack itemStack){
		items[index/items.length][index%items[0].length] = itemStack;
	}
	
	public boolean addItemStack(ItemStack stack){
		if(!isFull()){
			return false;
		}
		setItemStack(size, stack);
		size++;
		return true;
	}
	
	public void moveItemStack(int index1, int index2){
		ItemStack tmp = getItemStack(index1);
		setItemStack(index1, getItemStack(index2));
		setItemStack(index2, tmp);
	}
	
	public void render(){
		Game game = Game.getInstance();
		game.image(1, invBg, pos.x, pos.y);
		for(int i=0; i<items.length; i++){
			for(int j=0; j<items[0].length; j++){
				if(items[i][j] != null){
					game.image(1, items[i][j].getItem().getTexture(), itemPos[i][j].x, itemPos[i][j].y);
					game.text(1, items[i][j].getAmount()+"", itemPos[i][j].x+32, itemPos[i][j].y+38);
				}
			}
		}
	}
}
