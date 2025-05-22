package entity;

import java.awt.Color;
//import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {

	public int  x, y;
	public int speed;
	protected Rectangle hitbox;

	public BufferedImage JakuManR1, JakuManR2, JakuManR3, JakuManR4, JakuManR5, 
    JakuManL1, JakuManL2, JakuManL3, JakuManL4, JakuManL5;
	public String direction;
    

	public int  spriteCounter = 0;
	public int  spriteNum = 1;
	
	{
	inithitbox();
	}
	protected void drawhitbox(Graphics2D g2) {
		g2.setColor(Color.PINK);
		g2.drawRect(hitbox.x, hitbox.y,  hitbox.width, hitbox.height);  
	}
	
	 private void inithitbox() {
		 hitbox = new Rectangle((int)x, (int)y, 156, 130);
	 }
	 protected void updatehitbox (int customX, int customY) {
		    // Default offset values
		    int offsetX = 21;  // Positive offset for right movement
		    int offsetY = 56;  // Y offset

		    // Flip the hitbox (change the offsets) when moving left
		    if (direction.equals("L1")) {
		        offsetX = 66;  // Negative offset for left movement (flipped)
		        offsetY = 56;  // Negative offset for left movement (flipped)
		    }

		    // Set the location of the hitbox with the adjusted offsets
		    hitbox.setLocation(customX + offsetX, customY + offsetY);
		}
	 public Rectangle hitbox() {
		 return hitbox;
	 }
}
