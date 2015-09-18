package Objects;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

public class Square extends JComponent implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private int SquareID;
	private Color SquareColor;
	private Checker CurrentChecker;
	
	private static final int SquareHeight = 75;
	private static final int SquareWidth  = 75;
	private static final int FrameOffset = 20;
	
	private boolean occupied;
	
	public Square(int x, int y, int SquareID)
	{
		// -- Setup the initial information for the square.
		this.occupied = false;
		this.x = x * SquareWidth;
		this.y = y * SquareHeight;
		this.SquareID = SquareID;
		
		// -- Set the bounds of the square so we can click it.
		this.setBounds( new Rectangle( ( x * SquareHeight )+ FrameOffset, (y * SquareHeight) + FrameOffset, SquareWidth, SquareHeight ));
		
		// -- This will cause problems.
		this.CurrentChecker = null;
		this.addMouseListener(this);
	}
	
	public void SetSquareX( int x )
	{
		this.x = x;
	}
	
	public void SetSquareY( int y )
	{
		this.y = y;
	}
	
	public int GetSquareX()
	{
		return this.x;
	}
	
	public int GetSquareY()
	{
		return this.y;
	}
	
	public boolean GetIsSquareOccupied()
	{
		return this.occupied;
	}
	
	public void SetSquareChecker( Checker checker )
	{
		this.CurrentChecker = checker;
	}
	
	public Checker GetSquareChecker()
	{
		return this.CurrentChecker;
	}
	
	public void SetSquareOccupied()
	{
		this.occupied = true;
	}
	
	public void SetSquareColor( Color color )
	{
		this.SquareColor = color;
	}
	
	public Color GetSquareColor()
	{
		return this.SquareColor;
	}
	
	public int GetSquareID()
	{
		return this.SquareID;
	}

	@SuppressWarnings("null")
	public int[] GetSquareCoordinates()
	{
		int[] coords = null;
		coords[0] = x;
		coords[1] = y;
		return coords;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
		// -- Calling this so we don't have to keep calling GetPoint.
		Point pointClicked = e.getPoint();
		
		if( this.CurrentChecker != null )
		{
			if( this.getBounds().contains( pointClicked ) &&
					!this.CurrentChecker.getBounds().contains( pointClicked ) )
			{
				System.out.println( "Clicked Square " + this.SquareID );
				System.out.println( "Is this square occupied? " + GetIsSquareOccupied() );
			}
		}
		else
		{
			if( this.getBounds().contains( pointClicked ))
			{
				System.out.println( "Clicked Square " + this.SquareID );
				System.out.println( "Is this square occupied? " + GetIsSquareOccupied() );
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
