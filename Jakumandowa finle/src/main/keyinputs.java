package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyinputs implements KeyListener {

	public boolean upPressed, downPressed, leftPressed, rightPressed;
	 public boolean onePressed = false; // Added for gravity toggle
	public void keyheld(KeyEvent e) {
		//keyheld or keyhold have to code and see wtf does if it even works
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {


		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = true;			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressed = true;
		}
	    if(code == KeyEvent.VK_D) {
	    	rightPressed = true;		
    }
		if(code == KeyEvent.VK_UP) {
			upPressed = true;			
		}
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed = true;
		}
	    if(code == KeyEvent.VK_RIGHT) {
	    	rightPressed = true;		
    }

	    if(code == KeyEvent.VK_1) {  // Added 1 key detection
            onePressed = true;
        }
    }

	@Override
	public void keyReleased(KeyEvent e) {


		int code = e.getKeyCode();
		if(code == KeyEvent.VK_W) {
			upPressed = false;			
		}
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
			
		}
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
	    if(code == KeyEvent.VK_D) {
	    	rightPressed = false;	
    }
	    if(code == KeyEvent.VK_UP) {
			upPressed = false;			
		}
		if(code == KeyEvent.VK_LEFT) {
			leftPressed = false;
			
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressed = false;
		}
	    if(code == KeyEvent.VK_RIGHT) {
	    	rightPressed = false;		
    
	    }
	    if(code == KeyEvent.VK_1) {  // Added 1 key release
            onePressed = false;
        }

	}}
