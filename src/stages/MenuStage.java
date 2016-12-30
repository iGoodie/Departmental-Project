package stages;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controllers.StageController;
import core.Game;
import gui.Button;
import logic.Player;
import processing.core.PImage;
import util.FileUtils;

public class MenuStage implements IStage
{	
	Button[] button = new Button[3];
	Player player = new Player();
	PImage circle = FileUtils.readExternalPImage("sprites/KevinCircle.png");
	PImage logo = FileUtils.readExternalPImage("ui/KevinsInferno.png");
	PImage credits = FileUtils.readExternalPImage("ui/Credits.png");
	boolean creditsOn = false;
	
	public int getID() { return 1; }
	
	public MenuStage(){
		button[0] = new Button(50, 160);
		button[0].setStates(
				FileUtils.readExternalPImage("buttons/PlayN.png"),
				FileUtils.readExternalPImage("buttons/PlayH.png"),
				FileUtils.readExternalPImage("buttons/PlayA.png"));
		button[0].setAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StageController.changeStage(new PlayStage());
			}
		});
		button[1] = new Button(50, 300);
		button[1].setStates(
				FileUtils.readExternalPImage("buttons/ExitN.png"),
				FileUtils.readExternalPImage("buttons/ExitH.png"),
				FileUtils.readExternalPImage("buttons/ExitA.png"));
		button[1].setAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game.getInstance().exit();
			}
		});
		button[2] = new Button(50, 230);
		button[2].setStates(
				FileUtils.readExternalPImage("buttons/CreditsN.png"),
				FileUtils.readExternalPImage("buttons/CreditsH.png"),
				FileUtils.readExternalPImage("buttons/CreditsA.png"));
		button[2].setAction(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creditsOn = true;
			}
		});
		player.playAnimation("Kevin-Idle");
	}
	
	public void update(int dt) {
		for(int i=0; i<button.length; i++){
			button[i].update(Game.getInstance().mouseX, Game.getInstance().mouseY);
		}
		player.update(dt);
	}
	
	public void updateTick() {}
	
	public void render() {
		Game.getInstance().background(0xFF_330000);
		Game.getInstance().getLayer(1).scale(0.6f);
		Game.getInstance().getLayer(1).image(logo, 280, 10);
		Game.getInstance().getLayer(1).scale(10/6f);
		for(int i=0; i<button.length; i++){
			button[i].render();
		}		
		Game.getInstance().getLayer(2).image(circle, 375, 285);
		player.render(2, 450, 300);
		if(creditsOn){
			Game.getInstance().getLayer(1).image(credits, 150, 40);			
		}
	}
	
	public void handleKeyRelease(char key, int keyCode){
		if(key == Game.ESC){
			creditsOn = false;
		}
	}
	
	public void handleMousePressed(int layerClicked, int mouse, int x, int y){}
	
	public void dispose() {}
}
