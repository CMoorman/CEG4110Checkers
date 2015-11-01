package GUIState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;

public class SettingMenuState extends GUIState{

	private int currentChoice = 0;
	private final int nMainMenuState = 0;
	
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
	public void init() {}	
	

	private void select() {
		if(currentChoice == 0) {
			//manager.setState( nLoginState );
		}
		else if(currentChoice == 1) {
			manager.setState( nMainMenuState );
		}
	}
	
	public void draw(Graphics2D g) {	
		
		// -- Setup a white background.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		g.setColor( Color.GRAY );
		g.fill( new Rectangle( screenSize.width, screenSize.height ));
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Settings", 50, 70);
	}

	@Override
	public void update() {
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
