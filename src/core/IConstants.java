package core;

public interface IConstants 
{
	/*Basic Info*/
	public static final String GAME_TITLE = "Test Game";
	public static final String GAME_VERSION = "0.0.0";
	
	/*Timer Related*/
	public static final int TICK_PER_SECOND = 20;
	public static final int TICK_MILLIS = 1000/TICK_PER_SECOND;
	
	/*Exit State Codes*/
	public static final int ILLEGAL_OS_STATE = -1;
}
