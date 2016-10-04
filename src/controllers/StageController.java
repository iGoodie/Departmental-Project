package controllers;

import stages.IStage;
import stages.InitialStage;

public class StageController 
{
	public static IStage currentStage = new InitialStage();
	
	public static void changeStage(IStage stage)
	{
		IStage old = currentStage;
		currentStage = stage;
		old.dispose();
	}
	
	public static void update(int dt) {}
	
	public static void updateTick() {}
	
	public static void render() {}
}
