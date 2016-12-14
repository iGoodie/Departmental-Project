package stages;

public interface IStage 
{
	public int getID();
	
	public void update(int dt);
	public void updateTick();
	public void render();
	public void handleKey(char key, int keyCode);
	public void dispose();
}
