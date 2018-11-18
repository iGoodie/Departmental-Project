package logic;

import controllers.Fonts;
import core.Game;
import logic.elements.Item;

public class ItemStack {
	static final ItemStack INVALID = new ItemStack(null, -1);
	
	Item item;
	int amount = 0;
	
	public ItemStack(Item _item, int _amount) {
		item = _item;
		amount = item!=null ? Math.min(_item.getMaxStack(), _amount) : _amount;
	}
	
	public Item getItem() {
		return item;
	}
	
	public int getAmount() {
		return amount;
	}
	
	public int getType(){
		return item.getType();
	}
	
	public void setAmount(int _amount){
		amount = _amount;
	}
	
	public boolean increaseAmount(int num){
		if(amount+num > item.getMaxStack()){
			return false;
		}
		amount+=num;
		return true;
	}
	
	public boolean decreaseAmount(int num){
		if(amount-num < 0){
			return false;
		}
		amount-=num;
		return true;
	}
	
	public boolean equals(Object obj) {
		return item.equals(((ItemStack)obj).item);
	}
	
	public ItemStack copy(){
		return new ItemStack(item, amount);
	}
	
	public boolean isInvalid(){
		return amount == -1;
	}
	
	public void render(int layerID, float x, float y){
		Game game = Game.getInstance();
		game.image(layerID, item.getTexture(), x, y);
		if(amount != 1){
			game.getLayer(layerID).textFont(Fonts.DPCOMIC_20PT);
			game.textWithStroke(layerID, "x"+amount, x+38-game.getLayer(layerID).textWidth("x"+amount), y+36, 0xFF_000000);			
		}
	}
}
