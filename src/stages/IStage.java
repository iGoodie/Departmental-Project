package stages;

public interface IStage 
{
	public int getID();
	
	public void update(int dt);
	public void updateTick();
	public void render();
	public void dispose();
}
