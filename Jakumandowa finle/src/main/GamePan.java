package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import entity.Player;
import entity.Level;
import entity.levelCollisionGroup;
//import gamestates.Gamestate;
import entity.levelCollision;

public class GamePan extends JPanel implements Runnable {
	
  // screen sets
	final int originalTileSize = 16; // 32 x self tile
	final int scale = 3;
	
	public int tileSize = originalTileSize * scale; // 48 fuck do i know if this work for 32 pxiels
	final int maxScreenCol = 16; // this will be dumb 32
	final int maxScreenRow = 12; // 12 
	final int screenWidth = tileSize * maxScreenCol; // this math is sajt
	final int screenHeight = tileSize * maxScreenRow; // i dont know math
	
	//fps
	int FPS = 38;
	levelCollision levelCol = new levelCollision(); // Initialize here first
	keyinputs keyi = new keyinputs();
	Thread gameThread;
	Player player = new Player(this, keyi, levelCol); // Fixed variable name to lowercase "player"
	Level level = new Level(this); // i Fixed somthing fu do i know what it is
	levelCollisionGroup levelCollisionGroup = new levelCollisionGroup (this);

	
	// player place
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
	// Camera
	  private int cameraX = 0;
      private int cameraY = 0;
      private boolean cameraLocked = true; // Starts locked

	
	public GamePan() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyi);
		this.setFocusable(true);
		

	}

	
	public void startGameThread () {
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	
	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS; // Fixed variable name from "drewInterval" to "drawInterval"
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		int drawCount = 0;
		
		while(gameThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			// This for debug 
			//long currentTime = System.nanoTime();
			//System.out.println("current Time:" +currentTime);
			//  System.out.println("howdy");
			
			if (timer >= 1000000000) {
				System.out.println("FPS:" + drawCount);
				drawCount = 0;
				timer = 0;
			}
			
			
		// some sh idk
			//update();
			
			// some GPU
		
			// ();
			
		}
		
	}
	public void update() {
		player.update(); 
		level.update();
		levelCollisionGroup.update();
		
		//switch(Gamestate.state) {
		//case MENU:
			
		//	break;
		//case PLAYING:
	
		//	break;
		//default:
			//break;
		
		//}
		
		// cameraX = player.x - screenWidth/2;
	   //  cameraY = player.y - screenHeight/2;
	    cameraX = player.x - getWidth()/2;
	   
	    // Prevent camera from showing empty left area
	    if (cameraX < 0) { 
	        cameraX = 0;
	    }
	    cameraY = player.y - getHeight()/7; // Your specific vertical centering
	}

	
    public void paintComponent(Graphics g) {
		
	 super.paintComponent(g);
	 
	 Graphics2D g2 = (Graphics2D) g;
	// g2.setColor(Color.WHITE);  
	 
	 // g2.translate(-player.x + getWidth()/31, -player.y + getHeight()/7);
	 g2.translate(-cameraX, -cameraY);

	    g2.fillRect(0, 0, getWidth(), getHeight());
     level.draw(g2);
	 player.draw(g2); // Fixed missing argument for draw method
	 levelCollisionGroup.draw(g2);
	 

	 g2.dispose();
	}
}
