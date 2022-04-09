import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class SandboxGUI extends JFrame implements ComponentListener {
    private JPanel panel;
    private final int DELAY = 20;

    public static Point cursor;
    public static Velocity cursorVelocity;
    private static Point prevCursor;

    private static SandboxGUI sandbox = new SandboxGUI();

    /**
     * Create the GUI window
     */
    private SandboxGUI() {
        initialize();
        createEvents();
    }

    /**
     * SandboxGUI follows the singleton pattern
     * 
     * @return The single SandboxGUI instance
     */
    public static SandboxGUI getInstance() {
        return sandbox;
    }

    /**
     * Add MouseListeners and an ActionListener to repeatedly be called
     */
    private void createEvents() {
        // Gets mouse location when pressed
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Add grain on mouse right click
                if (e.getButton() == MouseEvent.BUTTON3) {
                    Sandbox.addGrain(e.getPoint(), panel);
                }
            }
        });
        // Add grains when cursor is dragged
        panel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if ((cursor != prevCursor)) {
                    prevCursor = cursor;
                    cursor = e.getPoint();
                    Sandbox.addGrain(cursor, panel);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                prevCursor = cursor;
                cursor = e.getPoint();
                cursorVelocity.calcVelocity(prevCursor.x, prevCursor.y, cursor.x, cursor.y);
                // TODO: cursor velocity to move grains when clicked
            }
        });
        ActionListener update = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sandbox.updateGrains(panel);
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
        setSize(500, 500);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        addComponentListener(this);

        cursorVelocity = new Velocity(0, 0);
        cursor = new Point(0, 0);

        panel = new JPanel();
        panel.setBounds(0, 0, getWidth(), getHeight());
        panel.setBackground(new Color(30, 60, 220));
        add(panel);
    }

    public void componentResized(ComponentEvent e) {
        panel.setBounds(0, 0, getWidth(), getHeight());
    }

    public void componentShown(ComponentEvent e) {
    }

    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {
    }
}