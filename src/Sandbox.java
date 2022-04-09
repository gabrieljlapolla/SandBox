import java.awt.Point;
import java.util.ArrayList;

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
        Point cursor = SandboxGUI.cursor;
        int cursorRadius = 25; // Radius in which cursor will effect grains
        int cursorStrength = 10; // How much the cursor pushes grains away
        for (Grain grain : grains) {
            grain.moveGrain(windowWidth, windowHeight); // Update grain location based on velocity

            // Check if cursor is near grain
            if (grain.getX() <= cursor.x + cursorRadius 
                    && grain.getX() + grain.getSIZE() >= cursor.x - cursorRadius
                    && grain.getY() <= cursor.y + cursorRadius
                    && grain.getY() + grain.getSIZE() >= cursor.y - cursorRadius) {
                grain.setVelocity(
                        new Velocity(SandboxGUI.cursorVelocity.getX() * cursorStrength - grain.getSIZE(),
                                SandboxGUI.cursorVelocity.getY() * cursorStrength - grain.getSIZE()));
            }

            // TODO: Check if grain is colliding with another
            Grain colliding = (Grain) Collisions.collisionInArray(grain, grains.toArray(new Grain[grains.size()]));
            if (colliding != null) {
                // Force = mass (SIZE) * acceleration
                // Mass in this case is based on sphere size
                // Every reaction has an equal and opposite reaction
                double velocityAngle1 = grain.velocity.getVelocityAngle();
                double directionalVelocity1 = grain.velocity.getDirectionalVelocity();
                double velocityAngle2 = colliding.velocity.getVelocityAngle();
                double directionalVelocity2 = colliding.velocity.getDirectionalVelocity();

                double xForce1 = grain.getSIZE() * grain.velocity.getX();
                double yForce1 = grain.getSIZE() * grain.velocity.getY();
                double xForce2 = colliding.getSIZE() * colliding.velocity.getX();
                double yForce2 = colliding.getSIZE() * colliding.velocity.getY();

            }
        }
        panel.repaint();
    }

    public static void main(String[] args) {
        SandboxGUI gui = SandboxGUI.getInstance();
        gui.setVisible(true);
    }
}
