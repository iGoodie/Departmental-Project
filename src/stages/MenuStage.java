package stages;

import controllers.StageController;

public class MenuStage implements IStage
{	
	public int getID() { return 1; }
	
	public void update(int dt) {}
	
	public void updateTick() { StageController.changeStage(new PlayStage()); }
	
	public void render() {}

	public void handleKey(char key, int keyCode){}
	
	public void handleMouse(int layerClicked, int mouse, int x, int y){}
	
	public void dispose() {}
}
