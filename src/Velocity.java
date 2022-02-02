/*
 * Class to get the velocity of an object between two coordinate points
 */

public class Velocity {
    private static final int MAX_VELOCITY = 10; // Calculated velocities cannot exceed this amount from 0
    public double xVelocity = 0;
    public double yVelocity = 0;

    Velocity() {

    }

    Velocity(double xVelocity, double yVelocity) {
        this.xVelocity = xVelocity;
        this.yVelocity = yVelocity;
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

        if (xDistance > MAX_VELOCITY) {
            xDistance = MAX_VELOCITY;
        }
        if (yDistance > MAX_VELOCITY) {
            yDistance = MAX_VELOCITY;
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
    public static double getDirectionalVelocity(double x1, double y1, double x2, double y2){
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * Get magnitude of directional velocity of current velocity object
     * 
     * @return Magnitude of the velocity of the current object
     */
    public double getDirectionalVelocity() {
        return Math.sqrt(Math.pow(xVelocity, 2) + Math.pow(yVelocity, 2));
    }

    /**
     * Get direction of current velocity in radians
     * 
     * @return Direction of current velocity in radians
     */
    public double getVelocityAngle(){
        if (xVelocity < 0){
            return -Math.atan(yVelocity / xVelocity) - ((3 * Math.PI) / 4);
        }
        if (yVelocity < 0) {
            return -Math.atan(yVelocity / xVelocity) - ((3 * Math.PI) / 4); // TODO: https://cdn.imgbin.com/25/19/22/imgbin-degree-right-angle-radian-unit-circle-radian-line-2WmmJpR5yWeuNwMQtsuHUiVGh.jpg
            // TODO: GIT
        }
        return -Math.atan(yVelocity / xVelocity);
    }

    @Override
    public String toString() {
        return String.format("X-Velocity: %f Y-Velocity: %f", xVelocity, yVelocity);
    }

    public static void main(String[] args) {
        Velocity test = Velocity.calcVelocity(0, 0, 0 , 1);
        System.out.println(Math.toDegrees(test.getVelocityAngle()));
    }
}
