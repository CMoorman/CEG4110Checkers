package Objects;

import Objects.Square;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CheckerBoard extends JPanel implements MouseListener, KeyListener, Runnable{
	
	public static List<Square> GameSquares = new ArrayList<Square>();
	public List<Checker> GameCheckers = new ArrayList<>();
	public static final int SquareHeight = 75;
	public static final int SquareWidth  = 75;
	public static final int Rows = 8;
	public static final int Columns = Rows;
	public static final int FrameOffset = 20;
	public static final int CheckerRadius = 30;
	
	private Thread thread;
	private boolean running;
	private int FPS = 30;
	private long targetTime = 1000 / FPS;
	
	private BufferedImage image;
	private Graphics2D g;
	
	public CheckerBoard()
	{
		this.setSize( SquareWidth * ( Columns + 3 ), SquareHeight * ( Columns + 3) );
		this.setVisible( true );
		this.addMouseListener(this);
		setPreferredSize(new Dimension( SquareWidth * ( Columns + 3 ), SquareHeight * ( Columns + 3) ) );
		setFocusable(true);
		requestFocus();
		SetupGameSquares();
		SetupGameChekcers();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	public void paint( Graphics g )
	{
		for( int i = 0; i < GameSquares.size(); i++ )
		{
			Square square = GameSquares.get( i );
			int CheckerX = 0;
			int CheckerY = 0;
			g.setColor( square.GetSquareColor() );
			g.fillRect( square.GetSquareX() + FrameOffset, square.GetSquareY() + FrameOffset, SquareWidth, SquareHeight );

			// -- Red checkers
			// -- This if statement will skip every other square.
			if( (square.GetSquareX() % 2) == (square.GetSquareY() % 2)  && ( square.GetSquareID() < 24 || square.GetSquareID() > 40 ) )
			{
				Checker currentChecker = square.GetSquareChecker();
				
				CheckerX = square.GetSquareX() - ( CheckerRadius / 2 );
				CheckerY = square.GetSquareY() - ( CheckerRadius / 2 );
				
				g.setColor( currentChecker.GetCheckerColor() );	
				g.fillOval(CheckerX + FrameOffset + ( SquareWidth / 2 ), CheckerY + FrameOffset + (SquareHeight / 2), CheckerRadius, CheckerRadius);
			}
		}
	}
	
	private void SetupGameSquares()
	{
		
		int CurrentSquareX = 0;
		int CurrentSquareY = 0;
		int SquareID = 0;

		while( CurrentSquareY < Rows )
		{
			while( CurrentSquareX < Columns )
			{
				Square currentSquare = new Square(CurrentSquareX, CurrentSquareY, SquareID);
				
				if( (CurrentSquareX % 2) == (CurrentSquareY % 2) )
					currentSquare.SetSquareColor( Color.WHITE );
				else
					currentSquare.SetSquareColor( Color.GRAY );
				
				GameSquares.add(currentSquare);
				
				this.addMouseListener( currentSquare );
				
				CurrentSquareX++;
				SquareID++;
			}
			CurrentSquareX = 0;
			CurrentSquareY++;
		}	
	}
	
	public void SetupGameChekcers()
	{
		for( int i = 0; i < GameSquares.size(); i++ )
		{
			Square square = GameSquares.get( i );

			// -- Red checkers
			// -- This if statement will skip every other square.
			if( (square.GetSquareX() % 2) == (square.GetSquareY() % 2)  && square.GetSquareID() < 24 )
			{
				
				Checker currentChecker = new Checker(Color.RED, square);
				GameCheckers.add(currentChecker);
				
				square.SetSquareOccupied();
				square.SetSquareChecker(currentChecker);

				// -- Add the checkers' MouseListener to the Checker Board.
				this.addMouseListener( currentChecker );	
			}
			
			// -- Black checkers
			if( (square.GetSquareX() % 2) == (square.GetSquareY() % 2)  && square.GetSquareID() > 40)
			{
				Checker currentChecker = new Checker(Color.BLACK, square);
				GameCheckers.add( currentChecker );
				
				square.SetSquareOccupied();
				square.SetSquareChecker( currentChecker );
				
				// -- Add the checkers' MouseListener to the Checker Board.
				this.addMouseListener( currentChecker );	
			}
		}
	}
	
	private void init() {
		
		image = new BufferedImage(
					WIDTH, HEIGHT,
					BufferedImage.TYPE_INT_RGB
				);
		g = (Graphics2D) image.getGraphics();
		
		running = true;
		
		addMouseListener(this);
	}
	
	

	@Override
	public void run() {
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// game loop
		while(running) {
			
			start = System.nanoTime();
			
			update();
			draw();
			drawToScreen();
			
			elapsed = System.nanoTime() - start;
			
			wait = targetTime - elapsed / 1000000;
			if(wait < 0) wait = 5;
			
			try {
				Thread.sleep(wait);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void update() {
		//gsm.update();
	}
	private void draw() {
		//gsm.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		//g2.drawImage(image, 0, 0,
		//		WIDTH * SCALE, HEIGHT * SCALE,
		//		null);
		g2.dispose();
	}

	@Override
	public void keyPressed(KeyEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
