package stages;

public interface IStage 
{
	public int getID();
	
	public void update(int dt);
	public void updateTick();
	public void render();
	public void dispose();
	
	//Handled once
	public void handleKeyRelease(char key, int keyCode);
	public void handleMousePressed(int layerClicked, int mouse, int x, int y);
}
