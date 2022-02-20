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

    private Point cursor;
    private Point prevCursor;

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

    public static void main(String[] args) {
        SandboxGUI gui = new SandboxGUI();
        gui.setVisible(true);
    }
}