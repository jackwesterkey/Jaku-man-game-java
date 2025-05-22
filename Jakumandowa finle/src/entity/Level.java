package entity;
//you can change this

import java.awt.Graphics2D; // Missing import

import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePan;

public class Level extends LevelOrganizer {

    GamePan gp;
    
    public Level(GamePan gp) {
        this.gp = gp;
        setDefaultValues();
        getLevelImage();
    }

    public void setDefaultValues() {
        x = -30;
        y = -507;
    }

    public void getLevelImage() {
        try {
            Level1 = ImageIO.read(getClass().getResourceAsStream("/player/Level1.png"));
            
            if (Level1 == null) {
                System.out.println("Image not loaded correctly.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        
    } // Missing closing brace for the update() method

    public void draw(Graphics2D g2) {
   	
      
        //g2.setColor(Color.white); 
       // g2.fillRect(x, y, gp.tileSize, gp.tileSize);

        

        // Get the image dimensions
        int imageWidth = 8680;
        int imageHeight = 4096;

        int offsetX = 21;  // Positive offset for right movement
	    int offsetY = 58;  // Y offset
       

        // Draw the image at (x, y) with the new scaled dimensions
        g2.drawImage(Level1, x + offsetX, y + offsetY, imageWidth, imageHeight, null);
    }

  }
     
