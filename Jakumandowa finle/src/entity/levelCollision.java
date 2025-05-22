package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class levelCollision {
    public ArrayList<Rectangle> Floorboxes; // List of collision boxes (floorboxes)

    public levelCollision() {
        Floorboxes = new ArrayList<>(); // Initialize the ArrayList to hold multiple floorboxes
        
        // Example: Add default floorboxes (you can add more as needed)
        Floorboxes.add(new Rectangle(8, 287, 200, 190));  // Example of one floorbox
        Floorboxes.add(new Rectangle(200, 287, 250, 50)); // Another floorbox
        Floorboxes.add(new Rectangle(600, 320, 300, 70)); // Another example floorbox
    }

    // Draw each floorbox
    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        for (Rectangle floorBox : Floorboxes) {
            g2.fillRect(floorBox.x, floorBox.y, floorBox.width, floorBox.height); // Draw each floorbox
        }
    }

    // Get the list of collision boxes for detection
    public ArrayList<Rectangle> getCollisionBoxes() {
        return Floorboxes;
    }
}
