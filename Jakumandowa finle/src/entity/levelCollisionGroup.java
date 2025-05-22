package entity;

import java.awt.Graphics2D;
import main.GamePan;
import java.awt.Rectangle;
import java.util.ArrayList;

public class levelCollisionGroup {
    private levelCollision collisionBox;
    private GamePan gp;

    public levelCollisionGroup(GamePan gp) {
        this.gp = gp;
        collisionBox = new levelCollision(); // Initialize levelCollision, which now handles multiple floorboxes
    }

    public void update() {
        // Example: Move right by 2 pixels per frame
        int newX = 0; // Adjust X if needed
        int newY = 0; // Adjust Y if needed
        // You can update the positions of each floorbox here if needed
        // For example, you could loop through all floorboxes and move them
        //collisionBox.updatePosition(newX, newY);
    }

    public void draw(Graphics2D g2) {
      //  collisionBox.draw(g2); // Draw all the floorboxes (collision boxes)
    }

    // For external collision checks
    public ArrayList<Rectangle> getCollisionBoxes() {
        return collisionBox.getCollisionBoxes(); // Return the list of all floorboxes
    }
}
