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
     * @param panel  Window
     */
    public static void addGrain(Point coords, JPanel panel) {
        Grain grain = new Grain(coords);
        // Check if grain exists on top of point to add new grain
        if (Collisions.collisionInArray(grain, grains.toArray(new Grain[grains.size()])) == null) {
            grains.add(grain);
            panel.add(grain);
        }
    }

    /**
     * Iterates over the list of grains and moves each one down a pixel if another
     * grain is not directly below it
     * 
     * @param panel Window
     */
    public static void updateGrains(JPanel panel) {
        int windowHeight = panel.getHeight() - 40; // Y value to check if grain has hit bottom of window
        int windowWidth = panel.getWidth();

        for (Grain grain : grains) {
            grain.moveGrain(windowWidth, windowHeight); // Update grain location based on velocity

            // TODO: Check if grain is colliding with another
            JComponent colliding = Collisions.collisionInArray(grain, grains.toArray(new Grain[grains.size()]));
            if (colliding != null) {
                // Force = mass (SIZE) * acceleration
                // Mass in this case is based on sphere size
                // Every reaction has an equal and opposite reaction
                System.out.println("Colliding");
            }
        }
        panel.repaint();
    }
}
