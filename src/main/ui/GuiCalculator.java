package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Graphical interface for calculator.
 * 
 */
public class GuiCalculator extends JFrame {
    public static final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
    public static final int WIDTH = device.getDisplayMode().getWidth();
    public static final int HEIGHT = device.getDisplayMode().getHeight();

    /*
     * 
     */
    public GuiCalculator() {
        super("Totally Useful Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(true);

        initGUI();
    }


    // initialize gui components
    private void initGUI() {
        getContentPane().removeAll();
        add(new CalculatorPanel());
        pack();
        setLocation((WIDTH - getWidth()) / 2, (HEIGHT - getHeight()) / 2);
        setVisible(true);
        repaint();
    }
}
