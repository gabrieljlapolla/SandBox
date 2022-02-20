
/*
 * Various methods to check if objects are colliding
 */
import java.awt.Point;

import javax.swing.JComponent;

public final class Collisions {

    /**
     * Checks if teh given point is within the bounds of the given JComponent
     * 
     * @param p Point to check
     * @param j JComponent to check
     * @return True if point is within bounds of JComponent
     */
    public static boolean pointCollision(Point p, JComponent j) {
        return j.getBounds().contains(p);
    }

    /**
     * Checks if the borders of two JComponents are colliding
     * 
     * @param c1 First component
     * @param c2 Second component
     * @return True if colliding, false otherwise
     */
    public static boolean isColliding(JComponent c1, JComponent c2) {
        return c1.getBounds().intersects(c2.getBounds());
    }

    /**
     * Checks if a JComponent is colliding with any given JComponent in an array
     * 
     * @param c1    JComponent
     * @param array Array of JComponents to check against
     * @return JComponent that given JComponent is colliding with or null if no
     *         collisions
     */
    public static JComponent collisionInArray(JComponent c1, JComponent[] array) {
        for (JComponent c2 : array) {
            if (c1 != c2 && isColliding(c1, c2)) {
                return c2;
            }
        }
        return null;
    }

    // TODO: collision in map???
}
