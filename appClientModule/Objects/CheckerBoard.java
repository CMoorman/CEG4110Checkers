package Objects;

import Objects.Square;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class CheckerBoard extends JPanel implements MouseListener{
	
	public static List<Square> GameSquares = new ArrayList<Square>();
	public List<Checker> GameCheckers = new ArrayList<>();
	public static final int SquareHeight = 75;
	public static final int SquareWidth  = 75;
	public static final int Rows = 8;
	public static final int Columns = Rows;
	public static final int FrameOffset = 20;
	public static final int CheckerRadius = 30;
	
	private Square FirstSelectedSquare;
	private Square SecondSelectedSquare;
	
	public CheckerBoard()
	{
		this.setSize( SquareWidth * ( Columns + 3 ), SquareHeight * ( Columns + 3) );
		this.setVisible( true );
		this.addMouseListener(this);
		SetupGameSquares();
		SetupGameChekcers();
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
		int CheckerX = 0;
		int CheckerY = 0;
		
		for( int i = 0; i < GameSquares.size(); i++ )
		{
			Square square = GameSquares.get( i );

			// -- Red checkers
			// -- This if statement will skip every other square.
			if( (square.GetSquareX() % 2) == (square.GetSquareY() % 2)  && square.GetSquareID() < 24 )
			{
				
				Checker currentChecker = new Checker(Color.RED, square);
				GameCheckers.add(currentChecker);
				
				CheckerX = square.GetSquareX() - ( CheckerRadius / 2 );
				CheckerY = square.GetSquareY() - ( CheckerRadius / 2 );
				
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
				
				CheckerX = square.GetSquareX()  - ( CheckerRadius / 2 );
				CheckerY = square.GetSquareY() - ( CheckerRadius / 2 );
				
				square.SetSquareOccupied();
				square.SetSquareChecker( currentChecker );
				
				// -- Add the checkers' MouseListener to the Checker Board.
				this.addMouseListener( currentChecker );	
			}
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
