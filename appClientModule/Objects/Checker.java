package Objects;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class Checker extends JComponent implements MouseListener{

	public static final int FrameOffset = 100;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int x;
	public int y;
	public Color color; // -- 0 = black, 1 = red;
	public Boolean isCheckerCaptured;
	public static int CheckerRadius = 30;
	
	private Square currentSquare;
	
	// -- Constructor.  Takes in a color and position.
	public Checker(Color color, Square square )
	{
		this.color = color;
		this.currentSquare = square;
		isCheckerCaptured = false;
		
		int CheckerX = square.GetSquareX() - ( CheckerRadius / 2 );
		int CheckerY = square.GetSquareY() - ( CheckerRadius / 2 );

		this.setBounds( CheckerX + FrameOffset + ( square.GetSquareX() / 2 ), 
						CheckerY + FrameOffset + (square.getHeight() / 2), 
						CheckerRadius, CheckerRadius);
		
	}

	// -- Set where the checker is at on the 2D field.
	public void SetCheckerPosition( Square newSquare )
	{
		this.currentSquare = newSquare;
	}
	
	// -- Get where the checker is currently at.
	public Square GetCheckerPosition()
	{
		return currentSquare;
	}
	
	// -- Return the color of the checker. 0 for Black, 1 for red.
	public Color GetCheckerColor()
	{
		return this.color;
	}
	
	public void SetIsCheckerCaptured( Boolean bCaptured )
	{
		this.isCheckerCaptured = bCaptured;
	}
	
	public Boolean GetIsCheckerCaptured()
	{
		return this.isCheckerCaptured;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if( this.getBounds().contains( e.getPoint() ))
		{
			System.out.println( "This checker color is: " + this.GetCheckerColor().toString() );
		}
	}

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
	public void mouseReleased(MouseEvent e) {}
}
