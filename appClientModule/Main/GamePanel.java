package Main;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;

import javax.swing.JPanel;

import GUIState.GUIManager;

@SuppressWarnings("serial")
public class GamePanel extends JPanel 
	implements Runnable, KeyListener, MouseListener{
	
	// Dimensions of our window
	public static final int WIDTH = 320;
	public static final int HEIGHT = 240;
	public static final int SCALE = 4;
	
	// Settup the values for the game thread.
	private Thread thread;
	private boolean running;
	private int FPS = 60;
	private long targetTime = 1000 / FPS;
	
	// The image.
	private BufferedImage image;
	private Graphics2D g;
	
	// GUI Manager
	private GUIManager uiManager;
	
	public GamePanel() {
		super();
		setPreferredSize(
			new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();
	}
	
	public void addNotify() {
		super.addNotify();
		if(thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}
	
	private void init() {
		
		image = new BufferedImage(
					WIDTH, HEIGHT,
					BufferedImage.OPAQUE
				);
		
		g = (Graphics2D) image.getGraphics();

		running = true;
		
		uiManager = new GUIManager();
		
		addMouseListener(this);
	}
	
	public void run() {
		
		init();
		
		long start;
		long elapsed;
		long wait;
		
		// Continuously update the game view. 
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
		uiManager.update();
	}
	private void draw() {
		uiManager.draw(g);
	}
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0,
				WIDTH * SCALE, HEIGHT * SCALE,
				null);
		g2.dispose();
	}
	
	public void keyTyped(KeyEvent key) {}
	
	public void keyPressed(KeyEvent key) {
		uiManager.keyPressed(key.getKeyCode());
	}
	public void keyReleased(KeyEvent key) {
		uiManager.keyReleased(key.getKeyCode());
	}
	
	// Mouse Methods.
	public void mouseClicked(MouseEvent e) 	{ uiManager.mouseClicked(e); }	
	public void mouseEntered(MouseEvent e) 	{ uiManager.mouseClicked(e); }	
	public void mouseExited(MouseEvent e) 	{ uiManager.mouseClicked(e); }	
	public void mousePressed(MouseEvent e) 	{ uiManager.mouseClicked(e); }	
	public void mouseReleased(MouseEvent e) { uiManager.mouseClicked(e); }
}
















