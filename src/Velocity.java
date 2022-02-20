/*
 * Class to get the velocity of an object between two coordinate points
 */

public class Velocity {
    private static final int MAX_VELOCITY = 10; // Calculated velocities cannot exceed this amount from 0
    public double x = 0; // X component of velocity
    public double y = 0; // Y component of velocity

    Velocity() {

    }

    Velocity(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the velocity of an object moving between two points
     * 
     * @param x1 X coordinate of the first point
     * @param y1 Y coordinate of the first point
     * @param x2 X coordinate of the second point
     * @param y2 Y coordinate of the second point
     * @return Velocity between the two points over 1 time unit
     */
    public static Velocity calcVelocity(double x1, double y1, double x2, double y2) {
        double xDistance = x2 - x1;
        double yDistance = y2 - y1;

        // If velocity exceeds maximum, set to maximum
        if (xDistance > MAX_VELOCITY) {
            xDistance = MAX_VELOCITY;
        } else if (xDistance < -MAX_VELOCITY) {
            xDistance = -MAX_VELOCITY;
        }
        if (yDistance > MAX_VELOCITY) {
            yDistance = MAX_VELOCITY;
        } else if (yDistance < -MAX_VELOCITY) {
            yDistance = -MAX_VELOCITY;
        }

        return new Velocity(xDistance, yDistance);
    }

    /**
     * Calculates the velocity of an object moving between two points in a given
     * time
     * 
     * @param x1 X coordinate of the first point
     * @param y1 Y coordinate of the first point
     * @param x2 X coordinate of the second point
     * @param y2 Y coordinate of the second point
     * @return Velocity between the two points over a given amount of time
     */
    public static Velocity calcVelocity(double x1, double y1, double x2, double y2, double time) {
        double xDistance = (x2 - x1) / time;
        double yDistance = (y2 - y1) / time;

        if (xDistance > MAX_VELOCITY) {
            xDistance = MAX_VELOCITY;
        }
        if (yDistance > MAX_VELOCITY) {
            yDistance = MAX_VELOCITY;
        }

        return new Velocity(xDistance, yDistance);
    }

    /**
     * Calculates the directional velocity of an object betwen two points
     * 
     * @param x1 X coordinate of the first point
     * @param y1 Y coordinate of the first point
     * @param x2 X coordinate of the second point
     * @param y2 Y coordinate of the second point
     * @return Velocity between the two points
     */
    public static double getDirectionalVelocity(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * Get magnitude of directional velocity of current velocity object
     * 
     * @return Magnitude of the velocity of the current object
     */
    public double getDirectionalVelocity() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Get direction of current velocity in radians based on typical unit circle
     *      pi/2
     *       I
     * pi ---O--- 0
     *       I
     *     3pi/2
     * 
     * @return Direction of current velocity in radians
     */
    public double getVelocityAngle() {
        if (x < 0 && y < 0) {
            return Math.atan(y / x) + Math.PI;
        } else if (x < 0) {
            return Math.atan(y / x) + Math.PI;
        } else if (y < 0) {
            return Math.atan(y / x) + (2 * Math.PI);
        } else if (x == 0 && y == 0) {
            return 0;
        }
        return Math.atan(y / x);
    }

    @Override
    public String toString() {
        return String.format("X-Velocity: %f Y-Velocity: %f", x, y);
    }
}
