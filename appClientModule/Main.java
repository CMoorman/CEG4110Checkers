import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import Objects.Checker;
import Objects.CheckerBoard;

public class Main {

	public static CheckerBoard GameWindow;
	public List<Checker> Checkers = new ArrayList<Checker>();
	
	public static void main(String[] args) {
		SetupGameWindow();
	}
	
	private static void SetupGameWindow()
	{
		//GameWindow = new CheckerBoard();
		
		JFrame mainFrame = new JFrame("GameFrame");
		mainFrame.setContentPane(new CheckerBoard());
		mainFrame.setResizable(true);
		mainFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		mainFrame.pack();
		mainFrame.setVisible( true );
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}
}