package GUIState;

import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class GUIManager {

	private ArrayList<GUIState> guiStates;
	private int currentState;

	public static final int MAINMENUSTATE = 0;
	public static final int SETTINGMENUSTATE = 1;
	
	public GUIManager() {
		guiStates = new ArrayList<GUIState>();

		currentState = MAINMENUSTATE;

		// -- Main Menu State
		guiStates.add( new MainMenuState(this) ); 
		
		// -- Settings Menu State
		guiStates.add( new SettingMenuState(this) );
	}
	

	public void setState(int state) {
		currentState = state;
		guiStates.get(currentState).init();
	}

	public void update() {
		guiStates.get(currentState).update();
	}

	public void draw(java.awt.Graphics2D g) {
		guiStates.get(currentState).draw(g);		
	}

	public void keyPressed(int k) {
		guiStates.get(currentState).keyPressed(k);
	}

	public void keyReleased(int k) {
		guiStates.get(currentState).keyReleased(k);
	}	
	
	// Mouse event handlers.
	public void mouseClicked(MouseEvent k) {		
		guiStates.get(currentState).mouseClicked(k);
		
	}
	public void mousePressed(MouseEvent k) {		
		guiStates.get(currentState).mousePressed(k);
	}
	public void mouseEntered(MouseEvent k) {		
		guiStates.get(currentState).mouseEntered(k);
	}	
	public void mouseExited(MouseEvent k) {
		guiStates.get(currentState).mouseExited(k);
	}
	public void mouseReleased(MouseEvent k) {
		guiStates.get(currentState).mouseReleased(k);
	}
	
}
