package GUIState;

import java.awt.event.MouseEvent;

public abstract class GUIState {
	
	// -- Main Menu State Color Settings:
	public String MainMenuTitleColor = "#ff1a1a";
	public String MainMenuOptionTextColor = "";
	public String MainMenuOptionHighLightColor = "";
	public String MainMenuBackgroundColor = "";
	
	// -- Settings State Color Settings
	public String SettingTitleColor = "";
	
	// -- Login State Color Settings
	
	
	// -- Game Board State Color Settings
	public String YourCheckerColor = "";
	public String OpponentCheckerColor = "";
	
	// -- Chat Room Color Settings
	
	// -- 

protected GUIState UIState;
	
	public abstract void init();
	public abstract void update();
	public abstract void draw(java.awt.Graphics2D g);
	
	// Keyboard methods.
	public abstract void keyPressed(int k);
	public abstract void keyReleased(int k);
	
	// Mouse Event methods
	public abstract void mouseClicked(MouseEvent k);
	public abstract void mousePressed(MouseEvent k);	
	public abstract void mouseEntered(MouseEvent k);	
	public abstract void mouseExited(MouseEvent k);
	public abstract void mouseReleased(MouseEvent k);
}
