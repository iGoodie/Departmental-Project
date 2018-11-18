package core;

public interface IConstants 
{
	/*Basic Info*/
	public static final String GAME_TITLE = "Kevin's Inferno";
	public static final String GAME_VERSION = "0.0.0";
	public static final int ST_WIDTH=600, ST_HEIGHT=400;
	
	/*Timer Related*/
	public static final int TICK_PER_SECOND = 2;
	public static final int TICK_MILLIS = 1000/TICK_PER_SECOND;
	
	/*File Names*/
	public static final String DATA_FOLDER = "data";
	public static final String STAGE_NAME_INFO = "stagenameinfo.txt";
	
	/*Exit State Codes*/
	public static final int ILLEGAL_OS_STATE = -1;
}
