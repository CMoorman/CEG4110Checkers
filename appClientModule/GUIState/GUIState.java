package GUIState;

import java.awt.event.MouseEvent;

public abstract class GUIState {
	
	// -- Main Menu State Color Settings:
	public String MainMenuTitleColor = "#ff1a1a";
	public String MainMenuOptionTextColor = "#ff1a1a";
	public String MainMenuOptionHighLightColor = "#330000";
	public String MainMenuBackgroundColor = "#ccccff";
	
	// -- Settings State Color Settings
	public String SettingTitleColor = "#ff1a1a";
	
	// -- Login State Color Settings
	public String LoginTitleColor = "#ff1a1a";
	
	
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
