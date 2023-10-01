package main.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/* Graphical interface for calculator.
 * 
 */
public class GuiCalculator extends JFrame {
    private static final Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = scrn.width;
    public static final int HEIGHT = scrn.height;

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
