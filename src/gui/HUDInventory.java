package gui;

import core.Game;
import logic.elements.ItemStack;
import physics.HitTestBasic;
import processing.core.PImage;
import processing.core.PVector;
import util.FileUtils;

public class HUDInventory {
	static final int LAYER = 2;
	static PImage invBg = FileUtils.readExternalPImage("ui/Inventory.png");
	static PImage selector = FileUtils.readExternalPImage("ui/itemSelector2.png");
	static PVector pos = new PVector(Game.getInstance().width-invBg.width-16, 140+32);

	static ItemStack[][] items = new ItemStack[3][3];
	static int size = 0;

	public static boolean isFull() {
		return size == 9;
	}

	public static ItemStack getItemStack(int row, int column) {
		return items[row][column];
	}

	public static ItemStack getItemStack(int index) {
		return items[index/items.length][index%items.length];
	}

	public static void setItemStack(int index, ItemStack itemStack) {
		items[index/items.length][index%items.length] = itemStack==null ? null : itemStack.copy();
	}

	private static PVector getItemPos(int index) {
		return new PVector(pos.x+40*(index%items.length), pos.y+20+40*(index/items.length));
	}

	private static PVector getItemPos(int row, int col){
		return new PVector(pos.x+40*col, pos.y+20+40*row);
	}

	public static ItemStack combineItemStack(ItemStack stackIn, int index){
		ItemStack stack = getItemStack(index);
		if(stack.equals(stackIn)){ //Is same?
			int max = stack.getItem().getMaxStack();
			if(stack.getAmount()+stackIn.getAmount() > max){ //With carry
				stackIn.setAmount(stack.getAmount()+stackIn.getAmount()-max);
				stack.setAmount(max);
				return stackIn;
			}
			else{ //Without carry
				stackIn.increaseAmount(stack.getAmount());
				setItemStack(index, stackIn);
			}
		}
		return null;
	}

	public static ItemStack hoveredItemStack(int x, int y){
		for(int i=0; i<9; i++){
			PVector itemPos = getItemPos(i);
			if(HitTestBasic.pointVsRect(x, y, itemPos.x, itemPos.y, 40, 40)){
				return getItemStack(i);
			}
		}
		return null;
	}

	public static void decreaseItemStack(int index, int num){
		ItemStack stack = getItemStack(index);
		stack.decreaseAmount(num);
		if(stack.getAmount()<=0){
			removeSlot(index);
		}
	}

	public static boolean addItemStack(ItemStack stack) {
		if(isFull()){
			return false;
		}
		for(int i=0; i<items.length * items[0].length; i++){
			if(getItemStack(i)==null){
				setItemStack(i, stack);
				size++;
				return true;		
			}
		}
		return false;
	}

	public static boolean removeSlot(int index){
		setItemStack(index, null);
		size--;
		return true;
	}

	public static void moveItemStack(int index1, int index2) {
		ItemStack tmp = getItemStack(index1);
		setItemStack(index1, getItemStack(index2));
		setItemStack(index2, tmp);
	}

	public static boolean isSlotEmpty(int index){
		return getItemStack(index) == null;
	}

	public static boolean isMouseOnGrid(int x, int y){
		if(x >= pos.x && x <= pos.x+invBg.width){
			if(y >= pos.y+20 && y <= pos.y+invBg.height){
				return true;
			}
		}
		return false;
	}

	private static int posToIndex(int mouseX, int mouseY){
		for(int i=0; i<items.length * items[0].length; i++){
			PVector pos = getItemPos(i);
			if(mouseX >= pos.x && mouseX <= pos.x+40){
				if(mouseY >= pos.y && mouseY <= pos.y+40){
					return i;
				}
			}
		}
		return -1;
	}

	public static void mouseClickedPut(int x, int y){
		int clickedIndex = posToIndex(x, y);
		ItemStack clickedStack = getItemStack(clickedIndex);
		if(MouseContainer.grabbedFrom() == MouseContainer.INVENTORY){
			if(MouseContainer.getGotIndex() == clickedIndex){ //Identity
				MouseContainer.nullify();
				return;
			}
			if(clickedStack == null){ //Move
				setItemStack(clickedIndex, MouseContainer.getItemStack());
				removeSlot(MouseContainer.getGotIndex());
				MouseContainer.nullify();
				return;
			}
			if(MouseContainer.getItemStack().equals(clickedStack)){ //Combine stack
				ItemStack carry = combineItemStack(MouseContainer.getItemStack(), clickedIndex);
				if(carry != null){ //With carry
					setItemStack(MouseContainer.getGotIndex(), carry);
					MouseContainer.setContainer(MouseContainer.from, carry, MouseContainer.index);
					return;
				} //Without carry
				removeSlot(MouseContainer.getGotIndex());
				MouseContainer.nullify();
				return;
			} //Can't combine
			moveItemStack(MouseContainer.getGotIndex(), clickedIndex);
			MouseContainer.nullify();
		}
		else if(MouseContainer.grabbedFrom() == MouseContainer.SLOTS){
			if(isSlotEmpty(clickedIndex)){
				setItemStack(clickedIndex, MouseContainer.getItemStack());
				MouseContainer.decreaseAmount(1);
			}
		}
	}

	public static void mouseClickedGrab(int x, int y) {
		for(int i=0; i<items.length * items[0].length; i++){
			if(getItemStack(i) != null){
				PVector pos = getItemPos(i);
				if(x >= pos.x && x <= pos.x+40){
					if(y >= pos.y && y <= pos.y+40){
						MouseContainer.setContainer(MouseContainer.INVENTORY, getItemStack(i), i);
					}
				}
			}
		}
	}

	public static void handleMouseStand(int x, int y){
		if(Game.getInstance().getClickedLayerIndex(x, y) == LAYER && !Game.getInstance().isClickedLayerPopup(x, y)){
			if(isMouseOnGrid(x, y) && MouseContainer.isEmpty()){
				ItemStack hoveredItem = hoveredItemStack(x, y);
				if(hoveredItem != null){
					HUDTooltip.renderTooltip(hoveredItem.getItem().getName(), x-10, y-5, 1);
				}
			}
		}
	}

	public static void renderItemBorder(int index){
		PVector itemPos = getItemPos(index);
		Game.getInstance().image(LAYER, selector, itemPos.x, itemPos.y);
	}

	public static void render() {
		Game game = Game.getInstance();
		game.image(LAYER, invBg, pos.x, pos.y);
		for(int r=0; r<items.length; r++){
			for(int c=0; c<items[0].length; c++){
				if(items[r][c] != null){
					PVector pos = getItemPos(r, c);
					items[r][c].render(LAYER, pos.x, pos.y);
				}
			}
		}
	}
}
