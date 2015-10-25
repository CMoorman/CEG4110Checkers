package GUIState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class SettingMenuState extends GUIState{

	private int currentChoice = 0;
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	private GUIManager manager;
	

	public SettingMenuState(GUIManager manager) {
			
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
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent k) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent k) {
		// TODO Auto-generated method stub
		
	}

}
