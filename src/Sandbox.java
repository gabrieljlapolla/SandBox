import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JPanel;

public final class Sandbox {

    private static ArrayList<Grain> grains = new ArrayList<Grain>();

    /**
     * Adds a grain of sand to the frame at (x, y) and puts it in the grains list
     * 
     * @param coords Coordinates of grain
     * @param panel Window
     */
    public static void addGrain(Point coords, JPanel panel) {
        Grain grain = new Grain(coords);
        grains.add(grain);
        panel.add(grain);
    }

    /**
     * Iterates over the list of grains and moves each one down a pixel if another
     * grain is not directly below it
     * 
     * @param panel Window
     */
    public static void updateGrains(JPanel panel) {
        for (Grain grain : grains) {
            // Check if grain is at bottom of window
            // the -40 is used to compensate for the bar at top of the window
            if (grain.getY() < panel.getHeight() - grain.getSIZE() - 40) {
                grain.velocity.y += 0.1; // Gravity
            } else {
                // Check if grain will go through bottom
                // Reduce and reverse grain velocity to make grain bounce
                grain.velocity.y = -grain.velocity.y / 2 > 0 ? 0 : -grain.velocity.y / 2;
            }
            grain.moveGrain(); // Update grain location based on velocity
            // TODO: Check if grain is colliding with another
            JComponent colliding = CollisionCheck.collisionInArray(grain, grains.toArray(new Grain[grains.size()]));
            if (colliding != null) {
                
            }
        }
        panel.repaint();
    }
}
