package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface Statemethods {
	public void updat();
	public void draw(Graphics g);
	//public void mouseClicked(MouseEvent e);
	//public void mousePressed(MouseEvent e);
	//public void mouseReleased(MouseEvent e);
	//public void mouseMoved(MouseEvent e);
	public void keyPressed(KeyEvent e);
    public void keyReleased(KeyEvent e);
    
}
