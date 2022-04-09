import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JComponent;

public class Grain extends JComponent {
    private final Color COLOR;
    private final int SIZE;
    private final double BOUNCE_FACTOR; // Amount of velocity kept when impacting something (e.g. 0.75 = 75%
                                               // velocity retained)
    final double GRAVITY; // Represents y velocity to add to represent gravity
    public Velocity velocity;
    // TODO: add different elasticities so each bounces different??

    public Grain(double x, double y) {
        this.COLOR = randColor();
        this.SIZE = randSize();
        setBounds((int) x - (this.SIZE / 2), (int) y - (this.SIZE / 2), this.SIZE, this.SIZE);
        this.velocity = new Velocity(0, 0);
        GRAVITY = randGravity();
        BOUNCE_FACTOR = randBounce();
        System.out.println(BOUNCE_FACTOR);
    }

    public Grain(Point p) {
        this(p.x, p.y);
    }

    public Color getColor() {
        return this.COLOR;
    }

    public int getSIZE() {
        return SIZE;
    }

    public Velocity getVelocity() {
        return this.velocity;
    }

    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }

    /**
     * Move grain based on its velocity
     * Check if grain is going to go out of bounds and corrects if needed
     * 
     * @param windowWidth  Width of window
     * @param windowHeight Height of window
     */
    public void moveGrain(int windowWidth, int windowHeight) {
        int x = getX() + (int) velocity.getX();
        int y = getY() + (int) velocity.getY();
        // TODO: friction
        // The 16 below is to compensate for window borders 
        // so the grain looks like it's actually bouncing off the side
        if (x >= windowWidth - SIZE - 16) { // Hits right side
            x = windowWidth - SIZE - 16;
            velocity.setX(-velocity.getX() * BOUNCE_FACTOR);
        } else if (x <= 0) { // Hits left side
            x = 0;
            velocity.setX(-velocity.getX() * BOUNCE_FACTOR);
        }
        if (y >= windowHeight - SIZE) { // Hits bottom
            y = windowHeight - SIZE;
            velocity.setY(-velocity.getY() * BOUNCE_FACTOR);
        } else if (y < 0) { // Hits top
            y = 0;
            velocity.setY(-velocity.getY() * BOUNCE_FACTOR);
        } else {
            velocity.setY(velocity.getY() + GRAVITY);
        }
        setBounds(x, y, SIZE, SIZE);
    }

    /**
     * Return a random 'sandy' color
     */
    public static Color randColor() {
        Random random = new Random();
        int num = random.nextInt(7);
        switch (num) {
            case 0: // Dark tan
                return new Color(225, 191, 146);
            case 1: // Lighter dark tan
                return new Color(231, 196, 150);
            case 2: // Tan
                return new Color(236, 204, 162);
            case 3: // Light tan
                return new Color(242, 210, 169);
            case 4: // Whitish-yellow
                return new Color(246, 215, 176);
            case 5: // Dark yellow
                return new Color(198, 170, 55);
            case 6: // Dark gray
                return new Color(35, 35, 35);
            default:
                return Color.BLACK;
        }
    }

    /**
     * Returns a random size for a grain
     * 
     * @return Size of grain as an int
     */
    public static int randSize() {
        Random random = new Random(); 
        // Each grain is a random size from 1 - 10
        return random.nextInt(10) + 1;
    }
    
    private double randGravity() {
        double max = 1.25;
        double min = 0.75;
        return Math.random() * (max - min + 1) + min;
    }

    private double randBounce() {
        double min = 0.1;
        double max = 0.5;
        return min + (max - min) * Math.random();
    }

    protected void paintComponent(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(0, 0, SIZE, SIZE);
        super.paintComponent(g);
    }

    protected void paintBorder(Graphics g) {
        g.setColor(COLOR);
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);
    }

    @Override
    public String toString() {
        return String.format("X: %d Y: %d Size: %d", getX(), getY(), SIZE);
    }
}
