import java.awt.Point;
import java.util.ArrayList;

import javax.swing.JPanel;

public final class Sandbox {

    private static ArrayList<Grain> grains = new ArrayList<>();

    /**
     * Adds a grain of sand to the frame at (x, y) and puts it in the grains HashMap
     * 
     * @param x X coordinate of grain
     * @param y Y coordinate of grain
     */
    public static void addGrain(Point coords, JPanel panel) {
        Grain grain = new Grain(coords);
        grain.setBounds((int) grain.xCoord, (int) grain.yCoord, grain.getSIZE(), grain.getSIZE());
        grains.add(grain);
        panel.add(grain);
    }

    /**
     * Iterates over the list of grains and moves each one down a pixel if another
     * grain is not directly below it
     */
    public static void updateGrains(JPanel panel, int height) {
        for (Grain grain : grains) {
            int size = grain.getSIZE();
            // Check if grain is at bottom of window
            if (grain.yCoord < height - grain.getSIZE() - 40) {
                grain.yCoord++;
                grain.setBounds((int) grain.xCoord, (int) grain.yCoord + 1, size, size);
            } else if (grain.yCoord > height){
                grain.setBounds((int) grain.xCoord, height - grain.getSIZE() - 40, size, size);
            }
            // TODO: Check if grain is colliding with another
        }
        panel.repaint();
    }
}
