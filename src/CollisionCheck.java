
/*
 * Class to check if JComponents are colliding
 * 1/27/2022 - Methods isColliding and collisionInArray written by Gabriel Lapolla
 */
import java.awt.Rectangle;

import javax.swing.JComponent;

public final class CollisionCheck {

    /**
     * Checks if the borders of two JComponents are colliding
     * 
     * @param c1 First component
     * @param c2 Second component
     * @return True if colliding, false otherwise
     */
    public static boolean isColliding(JComponent c1, JComponent c2) {
        Rectangle bounds1 = c1.getBounds();
        Rectangle bounds2 = c2.getBounds();

        // Check if x borders are over lapping
        if (bounds1.x >= bounds2.x && bounds1.x <= bounds2.x + bounds2.width
                || bounds1.x + bounds1.width >= bounds2.x && bounds1.x + bounds1.width <= bounds2.x + bounds2.width) {
            // Check if y borders are over lapping
            if (bounds1.y >= bounds2.y && bounds1.y <= bounds2.y + bounds2.height
                    || bounds1.y + bounds1.height >= bounds2.y
                            && bounds1.y + bounds1.height <= bounds2.y + bounds2.height) {
                // Both x and y values are overlapping which means objects are colliding
                return true;
            }
        }
        return false;
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
