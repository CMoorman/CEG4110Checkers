package GUIState;

public class ColorStates {

	
	// -- Main Menu State Color Settings:
	public String MainMenuTitleColor = "";
	public String MainMenuOptionTextColor = "";
	public String MainMenuOptionHighLightColor = "";
	public String MainMenuBackgroundColor = "";
	
	
	// -- Settings State Color Settings
	
	// -- Game Board State Color Settings
	public String YourCheckerColor = "";
	public String OpponentCheckerColor = "";
	
	

	public boolean IsValidColor(String hexValue){
		
		// -- Make sure we limit it to 6 characters
		if( hexValue.length() > 0 && hexValue.length() <= 6){
			return hexValue.matches("-?[0-9a-fA-F]+");
		}
		
		return false;
	}
}
