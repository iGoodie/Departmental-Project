package gui;

import java.util.Calendar;

import controllers.Fonts;
import core.Game;

public class HUDLogger {
	public static final String SYSTEM = "System";
	public static final String SYSTEM_DEBUG = "System-Debug";
	public static final String INFO = "Information";
	public static final String ERROR = "Error";
	public static final String WARNING = "Warning";
	static final long DISPLAY_MS = 3L * 1000L;
	
	public static boolean digitalFormat = false;
	
	static String header = "";
	static String message = "";
	static long displayUntil = -1L;
	
	public static void setMessage(String _header, String _message, int _displayTime){
		header = _header;
		message = _message;
		displayUntil = System.currentTimeMillis() + _displayTime;
	}
	
	public static void setMessage(String _header, String _message){
		header = _header;
		message = _message;
		displayUntil = System.currentTimeMillis() + DISPLAY_MS;
	}
	
	public static void reset(){
		header = "";
		message = "";
		displayUntil = -1L;
	}
	
	public static boolean isEmpty(){
		return header=="" && message=="" && displayUntil==-1L;
	}
	
	public static void update(){
		if(System.currentTimeMillis() > displayUntil){
			reset();
		}
	}
	
	public static String formatTime(){
		Calendar calendar = Calendar.getInstance();
		int minute = calendar.get(Calendar.MINUTE);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if(digitalFormat){			
			return String.format("%02d:%02d", hour, minute);
		}
		if(hour >= 12){
			return String.format("%02d:%02d PM", hour-12, minute);
		}
		return String.format("%02d:%02d AM", hour, minute);
	}
	
	public static void render(){
		Game game = Game.getInstance();
		game.pushStyle();
		game.getLayer(2).textFont(Fonts.NERVOUS_12PT);
		game.text(2, header, 18+(102-game.getLayer(2).textWidth(header))/2, 380);
		game.getLayer(2).textFont(Fonts.PIXELFJVERDANA_6PT);
		game.text(2, message, 130, 378);
		if(digitalFormat){
			game.text(2, formatTime(), 530, 378);
		}
		else{			
			game.text(2, formatTime(), 520, 378);
		}
		game.popStyle();
	}
}
