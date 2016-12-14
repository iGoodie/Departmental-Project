package stages;

import controllers.StageController;

public class InitialStage implements IStage
{
	public int getID() { return 0; }
	
	public InitialStage() {}
	
	public void update(int dt) {}
	
	public void updateTick() { StageController.changeStage(new MenuStage()); }
	
	public void render() {}

	public void handleKey(char key, int keyCode){}
	
	public void dispose() {}
}
