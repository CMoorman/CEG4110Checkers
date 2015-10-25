package GUIState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainMenuState extends GUIState {
	
	private int currentChoice = 0;
	private int nSettingMenuState = 1;
	private String[] options = {
		"Start",
		"Settings",
		"Quit"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	private GUIManager manager;

	public MainMenuState(GUIManager manager) {
			this.manager = manager;
			
			try {
				
				titleColor = new Color(128, 0, 0);
				titleFont = new Font(
						"Century Gothic",
						Font.PLAIN,
						28);
				
				font = new Font("Arial", Font.PLAIN, 12);			
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	
	public void init() {}	
	
	public void draw(Graphics2D g) {	
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Digital Checkers", 50, 70);
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 137, 140 + i * 15);
		}
	}
	
	private void select() {
		if(currentChoice == 0) {
			//manager.setState();
		}
		if(currentChoice == 1) {
			manager.setState( nSettingMenuState );
		}
		if(currentChoice == 2) {
			System.exit(0);
		}
	}
	
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER){
			select();
		}
		if(k == KeyEvent.VK_UP) {
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
	}
	public void keyReleased(int k) {}
	
	// Mouse events
	public void mouseClicked(MouseEvent k) {}	
	public void mousePressed(MouseEvent k) {}	
	public void mouseEntered(MouseEvent k) {}	
	public void mouseExited(MouseEvent k) {}	
	public void mouseReleased(MouseEvent k) {}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}	
}
