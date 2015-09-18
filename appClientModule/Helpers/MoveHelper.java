package Helpers;

import java.awt.event.MouseEvent;

import Objects.Checker;
import Objects.CheckerBoard;
import Objects.Square;

public class MoveHelper extends CheckerBoard{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MoveHelper()
	{
		
	}

	public Boolean MoveChecker( Checker checker, Square newSquare )
	{
		// -- First check to see if the square is available.
		if( newSquare.GetIsSquareOccupied() == false ) 
		{
			if( CalculateValidDistance(checker, newSquare ) == true ) 
			{
				// -- Valid move, move the checker to this position.
				checker.SetCheckerPosition( newSquare );
			}
		} 
		
		// -- Invalid move.
		return false;
	}
	
	// Valid distance would be ( at this time ):
	/*
	 * x + 0, y - 1 // Down
	 * x + 0, y + 1 // Up
	 * x + 1, y + 0 // Right
	 * x + 1, y + 1 // Right, Up
	 * x + 1, y - 1 // Right, Down
	 * x - 1, y + 0 // Left
	 * x - 1, y + 1 // Left, Up
	 * x - 1, y - 1 // Left, Down
	 * 
	 */
	private boolean CalculateValidDistance(Checker checker, Square newSquare )
	{	
		Boolean isJumping = false;
		if( isJumping )
			if( !( newSquare.GetSquareX() - checker.x > 1 ) 	&& 
					!( newSquare.GetSquareX() - checker.x < -1 ) 	&&
					!( newSquare.GetSquareY() - checker.y > 1 ) 	&& 
					!( newSquare.GetSquareY() - checker.y < -1 ) )
				return true;
		else
		{
			if( !( newSquare.GetSquareX() - checker.x > 2 ) 	&& 
					 !( newSquare.GetSquareX() - checker.x < -2 ) 	&&
					 !( newSquare.GetSquareY() - checker.y > 2 ) 	&& 
					 !( newSquare.GetSquareY() - checker.y < -2 ) )
				return true;
		}
		
		return false;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Move helper is listening");
	}
	
}
