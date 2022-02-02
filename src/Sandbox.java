import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.Timer;

public class Sandbox extends JFrame {

    private ArrayList<Grain> grains = new ArrayList<>();
    private Point cursor;
    private Point prev;
    private static final int DELAY = 20;
    private final int WINDOW_HEIGHT = 500;
    private final int WINDOW_WIDTH = 500;

    public Sandbox() {
        super("Sandbox");
    }

    /**
     * Sets aspects of the Sandbox and adds Listeners
     */
    public void initialize() {
        
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        getContentPane().setBackground(new Color(30, 60, 220));
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        // Gets mouse location when pressed
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cursor = e.getPoint();
                addGrain(cursor);
            }
        });
        // Add grains when cursor is dragged
        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (cursor != prev) {
                    prev = cursor;
                    cursor = e.getPoint();
                    addGrain(cursor);
                }

            }
        });
        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateGrains();
            }
        };
        Timer timer = new Timer(DELAY, update);
        timer.start();
    }

    /**
     * Adds a grain of sand to the frame at (x, y) and puts it in the grains HashMap
     * 
     * @param x X coordinate of grain
     * @param y Y coordinate of grain
     */
    public void addGrain(Point coords) {
        Grain grain = new Grain(coords);
        grain.setBounds((int) grain.xCoord, (int) grain.yCoord, grain.getSIZE(), grain.getSIZE());
        grains.add(grain);
        add(grain);
        setVisible(true);
    }

    /**
     * Iterates over the list of grains and moves each one down a pixel if another
     * grain is not directly below it
     */
    public void updateGrains() {
        for (Grain grain : grains) {
            int size = grain.getSIZE();
            // Check if grain is at bottom of window
            if (grain.yCoord < getHeight() - grain.getSIZE() - 40) {
                grain.yCoord++;
                grain.setBounds((int) grain.xCoord, (int) grain.yCoord + 1, size, size);
            } else if (grain.yCoord > getHeight()){
                grain.setBounds((int) grain.xCoord, getHeight() - grain.getSIZE() - 40, size, size);
            }
            // TODO: Check if grain is colliding with another
        }
        repaint();
    }

    public static void main(String[] args) throws Exception {
        Sandbox sandbox = new Sandbox();
        sandbox.initialize();
    }
}
