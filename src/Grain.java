import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;

import javax.swing.JComponent;

public class Grain extends JComponent {
    private final Color COLOR = randColor();
    private final int SIZE = randSize();
    public double xCoord;
    public double yCoord;
    public Velocity velocity;
    

    public Grain(double xCoord, double yCoord) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
    }

    public Grain(Point p) {
        this.xCoord = p.x;
        this.yCoord = p.y;
    }

    public Color getColor() {
        return this.COLOR;
    }

    public int getSIZE(){
        return SIZE;
    }

    public Point getPoint(){
        return new Point((int) xCoord, (int) yCoord);
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
        Random random = new Random(); // Each grain is a random size
        return random.nextInt(7) + 3;
    }

    protected void paintComponent(Graphics g) {
        g.setColor(COLOR);
        g.fillOval(0, 0, SIZE, SIZE);
        super.paintComponent(g);
    }
    
    protected void paintBorder(Graphics g) {
        g.setColor(COLOR);
        g.drawOval(0,0, getSize().width - 1, getSize().height - 1);
    }

    @Override
    public String toString() {
        return String.format("X: %d Y: %d Size: %d", xCoord, yCoord, SIZE);
    }
}
