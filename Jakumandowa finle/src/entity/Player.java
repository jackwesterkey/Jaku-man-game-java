package entity;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics2D; // Missing import
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePan;
import main.keyinputs;

public class Player extends Entity{
	
	// physics
	//private boolean left, up, right, down, jump;
	private float airSpeed = 0f;
	private float gravity = 0.486f;
	private float  jumpSpeed = -13f;
	private float  fallSpeedAfterCollision = 1.4f;
	private boolean inAir = false;
	private boolean gravityEnabled = true;
	
	GamePan gp;
	keyinputs keyi;
	 levelCollision levelCol;  // Declare a reference to levelCollision
	
	public Player(GamePan gp, keyinputs keyi, levelCollision levelCol) {
		
		this.gp = gp;
		this.keyi = keyi;
		this.levelCol = levelCol;
		
		setDefaultValues();
		  getPlayerImage();
	}
	public void setDefaultValues () {
		
		x = 20;
		y = 100;
		speed = (int) 6.2 ;
		direction = "R1";
	}
	public void getPlayerImage() {
		
		try {
			
			JakuManR1 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manR1.png"));
			JakuManR2 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manR2.png"));
			JakuManR3 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manR3.png"));
			JakuManR4 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manR4.png"));
			JakuManR5 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manR5.png"));
		    JakuManL1 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manL1.png"));
		    JakuManL2 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manL2.png"));
		    JakuManL3 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manL3.png"));
		    JakuManL4 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manL4.png"));
		    JakuManL5 = ImageIO.read(getClass().getResourceAsStream("/player/Jaku-manL5.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
			 
		
	}
	
	public void update() {
		
		// Floorbox debugging and test and physics testing. turns physics off by pressing the 1 Key
	//	if (keyi.onePressed) {
	  //     keyi.onePressed = false;
	   //    gravity = (gravity == 0) ? 0.486f : 0f; // Toggle gravity
	   //     jumpSpeed = (jumpSpeed == 0) ? -13f : 0f; // Toggle jumping
	   //     fallSpeedAfterCollision = (fallSpeedAfterCollision == 0) ? 1.4f : 0f; // Toggle bounce
	 //   }
	 //   if (inAir) {
	  //     airSpeed += gravity;
	   //     y += airSpeed;
	   //}
		
		updatehitbox(x, y);
	  
       
	    // Apply gravity if in air
	    if (inAir) {
	        airSpeed += gravity;
	        y += airSpeed;
	    }
	    updatehitbox(x, y);

	 // Handle collision with multiple floorboxes
        boolean onGround = false;

        for (Rectangle floorBox : levelCol.Floorboxes) {
            if (hitbox.intersects(floorBox)) {
                // Handle collision resolution logic
                float topOverlap = (hitbox.y + hitbox.height) - floorBox.y;
                float leftOverlap = (hitbox.x + hitbox.width) - floorBox.x;
                float rightOverlap = (floorBox.x + floorBox.width) - hitbox.x;
                float bottomOverlap = (floorBox.y + floorBox.height) - hitbox.y;

                // Check if we are landing on the floor
                boolean isLanding = (airSpeed >= 0) && 
                                    (topOverlap < leftOverlap) && 
                                    (topOverlap < rightOverlap);

                if (isLanding) {
                    y -= topOverlap;
                    airSpeed = fallSpeedAfterCollision;
                    inAir = false;
                } else if (bottomOverlap < topOverlap && bottomOverlap < leftOverlap && bottomOverlap < rightOverlap) {
                    y += bottomOverlap; // Push player down
                    airSpeed = 0; // Stop upward momentum
                } else {
                    // Side collision handling (left or right)
                    if (leftOverlap < rightOverlap) {
                        x -= leftOverlap;
                    } else {
                        x += rightOverlap;
                    }
                }

                // Mark the player as on the ground
                onGround = true;
                break; // No need to check further if already landed on a floor
            }
        }

        // If not on the ground, set player to be in air
        if (!onGround) {
            inAir = true;
        }

	    // Handle jumping
	    if (keyi.upPressed && !inAir) {
	        airSpeed = jumpSpeed;
	        inAir = true;
	    }
	        // Handle jumping (only when grounded)
	        if (keyi.upPressed && !inAir) {
	            airSpeed = jumpSpeed;
	            inAir = true;
	        }
	        
	        //horizontal movement
	        if (keyi.leftPressed) {
	            x -= speed;
	            direction = "L1";
	        }
	        if (keyi.rightPressed) {
	            x += speed;
	            direction = "R1";
	        }
	        
	        
	        // turn this one on  when you have turned off physics so you can test the Floor box because i might not know if code stable
		//if (keyi.upPressed == true || keyi.downPressed == true 
	//		|| keyi.leftPressed == true || keyi.rightPressed == true) {
			
		// if (keyi.upPressed == true) {  // Removed the semicolon after the condition
	//	      y -= speed;
		//  }
		// else if (keyi.downPressed == true) {  // Removed the semicolon here as well
		//      y += speed;
		//  }
	        
	        
	        // do not touch i don't know if this is need but it must stay here code might not work with out it
		    //if (keyi.leftPressed == true) {  // Removed the semicolon here as well
		    	//direction = "L1";a
		        //x -= speed;  // Fixed to change playerX for movement in the left direction
		    //}
		    //else if (keyi.rightPressed == true ) {  // Removed the semicolon here as well
		    	//direction = "R1";
		        //x += speed;  // Fixed to change playerX for movement in the right direction
		    //}
	         
	        
	        
	        float animSpeed = 0.26f * 3f; 
    boolean isMovingHorizontallyNow = keyi.leftPressed || keyi.rightPressed; 
    if (isMovingHorizontallyNow && !inAir) {
		    spriteCounter++;
		    if (spriteCounter > 
		    //(int) 2.6)
		    animSpeed)
		    {
		    	if(spriteNum == 5) {
		    		spriteNum = 1;
		    	} else { 
		    		spriteNum++;	
		    	}
		    	spriteCounter = 0;
		     } 
	} else if (!isMovingHorizontallyNow) {
        // Reset when not moving or in air
        spriteNum = 1;
        spriteCounter = 0;
    } }
	
	
	
	
//	}// }
	        // Reset only if STOPPED horizontal movement
	         //  spriteNum = 1;
	         //   spriteCounter = 0; }
		    	//} else //if (!isMovingHorizontallyNow)
		    //		{ 
		 //   spriteNum = 1;
		   // spriteCounter = 0; }
		   	
		    //	else if (spriteNum == 2) {
		    ///		spriteNum = 3;
		    	//}
		    //	else if (spriteNum == 3) {
		    //		spriteNum = 4;
		    //	}
		    //	else if (spriteNum == 4) {
		    //		spriteNum = 5;
		   // 	}
		  //  	else if (spriteNum == 5) {
		  //  		spriteNum = 1;	
		    //      spriteCounter = 0; // Reset sprite counter
     //}
		// Update the player's hitbox to reflect the new position


		// Check for collision with floorBox and resolve it
		//if (hitbox.intersects(floorBox)) {
		    // Get the overlapping rectangle between the player and floorBox
		 //   Rectangle intersection = hitbox.intersection(floorBox);
		    
		    // Resolve the collision by pushing the player out by the smallest distance.
		   // if (intersection.width < intersection.height) {
		        // Collision is more horizontal; resolve on X axis
		   //     if (hitbox.x < floorBox.x) {
		        // Player is on the left side of floorBox
		     //       x -= intersection.width;
		     //   } else {
		          // Player is on the right side of floorBox
		      //      x += intersection.width;
		  //      }
		 //   } else {
		        // Collision is more vertical; resolve on Y axis
		   //     if (hitbox.y < floorBox.y) {
		            // Player is above floorBox
		      //      y -= intersection.height;
		     //   } else {
		            // Player is below floorBox
		       //     y += intersection.height;
		    //    }
		   // }
		    // Update the hitbox again after collision resolution
		
		


	//  Moved draw outside of update
	public void draw (Graphics2D g2) {
		drawhitbox(g2);
	  //  g2.setColor(Color.white); 
	  //  g2.fillRect(x, y, gp.tileSize, gp.tileSize);
		 g2.setColor(Color.WHITE);
		 g2.drawString("Physics: " + airSpeed, x, y-30);
		 g2.drawString("Animation: " + spriteNum, x, y-50); 
		
		BufferedImage image = null;
		
		switch (direction) {
	    case "R1":
	    	if(spriteNum == 1) {
	    		image = JakuManR1;
	    	}
	    	if(spriteNum == 2) {
	    		image = JakuManR2;
	    	}
	    	if(spriteNum == 3) {
	    		  image = JakuManR3;
	    	}
	    	if(spriteNum == 4) {
	    		  image = JakuManR4;
	    	}
	    	if(spriteNum == 5) {
	    		  image = JakuManR5;
	    	}
	        break;
	    case "L1":
	    	if(spriteNum == 1) {
	    		image = JakuManL1;
	    	}
	    	if(spriteNum == 2) {
	    		image = JakuManL2;
	    	}
	    	if(spriteNum == 3) {
	    		image = JakuManL3;
	    	}
	    	if(spriteNum == 4) {
	    		image = JakuManL4;
	    	}
	    	if(spriteNum == 5) {
	    		image = JakuManL5;
	    	}
	        
	        break;
		}
	    if (image != null) {

		int newSize = gp.tileSize * 5; // ✅ Doubles the player size
		g2.drawImage(image, x, y, newSize, newSize, null);
		
	    }
	}
} 
