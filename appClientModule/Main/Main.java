package Main;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Objects.Checker;
import Objects.CheckerBoard;

public class Main {

	public static CheckerBoard GameWindow;
	public List<Checker> Checkers = new ArrayList<Checker>();
	

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public static void main(String[] args)
	{
		JFrame window = new JFrame("Digital Checkers");		
		window.setContentPane( new GamePanel() );		
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.pack();
		window.setVisible(true);
	}
}