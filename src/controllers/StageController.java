package controllers;

import core.IConstants;
import stages.IStage;
import stages.InitialStage;
import util.FileUtils;

public class StageController implements IConstants
{
	public static IStage currentStage = new InitialStage();
	public static String[] stageNames;
	
	public static String getStageName()
	{
		if(stageNames==null) stageNames = FileUtils.readExternalString(STAGE_NAME_INFO).split("\n");
		return stageNames[currentStage.getID()];
	}
	
	public static void changeStage(IStage stage)
	{
		IStage old = currentStage;
		currentStage = stage;
		old.dispose();
	}
	
	public static void update(int dt) 
	{
		currentStage.update(dt);
	}
	
	public static void updateTick() 
	{
		currentStage.updateTick();
	}
	
	public static void render() 
	{
		currentStage.render();
	}
}
