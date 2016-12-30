package gui;

import logic.Player;
import processing.core.PGraphics;
import sprites.ExternalIcon;
import controllers.Fonts;
import core.Game;

public class HUDIndicator {
	static final int LAYER = 2;
	//Load the amazing artwork of Bahunaz Çevik (Khamidova) into the random access memory
	static ExternalIcon bghp = new ExternalIcon("ui/emptyBar.png", 40, 316, LAYER);
	static ExternalIcon bgmp = new ExternalIcon("ui/emptyBar.png", 40, 340, LAYER);
	static ExternalIcon hp = new ExternalIcon("ui/healthBar.png", 45, 321, LAYER);
	static ExternalIcon mp = new ExternalIcon("ui/manaBar.png", 45, 345, LAYER);
	static ExternalIcon hpPot = new ExternalIcon("ui/health.png", 20, 315, LAYER);
	static ExternalIcon mpPot = new ExternalIcon("ui/mana.png", 20, 340, LAYER);
	static ExternalIcon atk = new ExternalIcon("ui/atk.png", 220, 320, LAYER);
	static ExternalIcon def = new ExternalIcon("ui/def.png", 325, 318, LAYER);
	static ExternalIcon magicPts = new ExternalIcon("ui/magicPts.png", 270, 320, LAYER);
	static ExternalIcon soul = new ExternalIcon("ui/soul.png", 540, 316, LAYER);

	private static void renderBars(Player player){
		Game game = Game.getInstance();
		bghp.render();
		bgmp.render(); //BG
		game.imageCropped(LAYER, hp.getImage(), hp.getX(), hp.getY(), 
				(int)Game.map(player.getHealth(), 0, player.getMaxHealth(), 0, hp.getWidth()), hp.getHeight()); //HP
		game.imageCropped(LAYER, mp.getImage(), mp.getX(), mp.getY(),
				(int)Game.map(player.getMana(), 0, player.getMaxMana(), 0, mp.getWidth()), mp.getHeight()); //MP
	}

	private static void renderPots(){
		PGraphics gameLayer = Game.getInstance().getLayer(LAYER);
		gameLayer.pushMatrix();
		gameLayer.translate(hpPot.getX(), hpPot.getY());
		gameLayer.scale(.5f);
		gameLayer.image(hpPot.getImage(), 0, 0);
		gameLayer.popMatrix();
		gameLayer.pushMatrix();
		gameLayer.translate(mpPot.getX(), mpPot.getY());
		gameLayer.scale(.5f);
		gameLayer.image(mpPot.getImage(), 0, 0);
		gameLayer.popMatrix();
	}

	private static void renderIcons(){
		atk.render();
		def.render();
		magicPts.render();
		soul.render();
	}

	public static void handleMouseStand(Player player, int x, int y){
		if(Game.getInstance().getClickedLayerIndex(x, y) == LAYER && !Game.getInstance().isClickedLayerPopup(x, y)){
			if(bghp.isMouseOn(x, y)){
				HUDTooltip.renderText(String.format("Health: %d / %d", player.getHealth(), player.getMaxHealth()), 125, 324);
			}
			else if(bgmp.isMouseOn(x, y)){
				HUDTooltip.renderText(String.format("Mana: %d / %d", player.getMana(), player.getMaxMana()), 125, 348);
			}
			else if(atk.isMouseOn(x, y)){
				HUDTooltip.renderTooltip("Attack Points", x+10, y-5, 0);
			}
			else if(magicPts.isMouseOn(x, y)){
				HUDTooltip.renderTooltip("Magic Points", x+10, y-5, 0);
			}
			else if(def.isMouseOn(x, y)){
				HUDTooltip.renderTooltip("Defense Points", x+10, y-5, 0);
			}
			else if(soul.isMouseOn(x, y)){
				HUDTooltip.renderTooltip("Souls", x-10, y-5, 1);
			}
		}
	}

	public static void render(Player player){
		PGraphics gameLayer = Game.getInstance().getLayer(LAYER);
		renderPots();
		renderBars(player);
		renderIcons();
		gameLayer.pushStyle();
		gameLayer.textFont(Fonts.DPCOMIC_20PT, 15);
		gameLayer.fill(0xFF_989491);
		gameLayer.text("x"+player.getAtk(), 240, 355);
		gameLayer.fill(0xFF_3280FD);
		gameLayer.text("x"+player.getDef(), 357, 355);
		gameLayer.fill(0xFF_D8A727);
		gameLayer.text("x"+player.getMagicPt(), 295, 355);
		gameLayer.fill(0xFF_CCCCCC);
		gameLayer.text(String.format("%06d", player.getSouls()), 540, 360);
		gameLayer.popStyle();
	}
}
