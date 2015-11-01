package GUIState;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class LoginState extends GUIState{

	private int currentChoice 		= 0;
	private final int nMainMenuState = 0;
	
	// -- The text strings that will be used for the options on the main menu.
	private String[] options = {
		"Login",
		"Cancel"
	};
	
	private Color titleColor;
	private Font titleFont;
	
	private Font font;
	private GUIManager manager;

	public LoginState(GUIManager manager) {
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
		
		// -- Setup a white background.
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		g.setColor( Color.GRAY );
		g.fill( new Rectangle( screenSize.width, screenSize.height ));
		
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Please login.", 75, 70);
		
		// -- Draw the menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 137, 180 + i * 15);
		}
	}

	private void select() {
		if(currentChoice == 0) {
			//manager.setState( nLoginState );
		}
		else if(currentChoice == 1) {
			manager.setState( nMainMenuState );
		}
	}
	
	@Override
	public void keyPressed(int k) {
		if( k == KeyEvent.VK_ENTER ){
			select();
		}
		else if( k == KeyEvent.VK_UP ){
			currentChoice--;
			if(currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}
		else if(k == KeyEvent.VK_DOWN) {
			currentChoice++;
			if(currentChoice == options.length) {
				currentChoice = 0;
			}
		}
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
