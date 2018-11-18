package stages;

public class LoadingStage implements IStage
{
	public int getID() { return 4; }
	
	public void update(int dt) {}
	public void updateTick() {}
	public void render() {}
	public void handleKeyRelease(char key, int keyCode){}
	public void handleMousePressed(int layerClicked, int mouse, int x, int y){}
	public void dispose() {}
}
