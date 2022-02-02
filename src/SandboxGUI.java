import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class SandboxGUI extends JFrame {
    private JPanel panel;
    private static final int DELAY = 20;

    private Point cursor;
    private Point prevCursor;

    private static final int WINDOW_HEIGHT = 500;
    private static final int WINDOW_WIDTH = 500;

    /**
     * Create the GUI window
     */
    public SandboxGUI() {
        initialize();
        createEvents();
    }

    /**
     * Add MouseListeners and an ActionListener to repeatedly be called
     */
    private void createEvents() {

        // Gets mouse location when pressed
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cursor = e.getPoint();
                System.out.println(cursor.x + ", " + cursor.y);
                Sandbox.addGrain(cursor, panel);
            }
        });
        // Add grains when cursor is dragged
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (cursor != prevCursor) {
                    prevCursor = cursor;
                    cursor = e.getPoint();
                    Sandbox.addGrain(cursor, panel);
                }

            }
        });
        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sandbox.updateGrains(panel, WINDOW_HEIGHT);
            }
        };
        Timer timer = new Timer(DELAY, update);
        timer.start();
    }

    /**
     * Sets aspects of the Sandbox and adds Listeners
     */
    public void initialize() {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        setSize(WINDOW_HEIGHT, WINDOW_WIDTH);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        panel = new JPanel();
        panel.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
        panel.setBackground(new Color(30, 60, 220));
        add(panel);

    }

    public static void main(String[] args) {
        SandboxGUI gui = new SandboxGUI();
        gui.setVisible(true);
    }
}