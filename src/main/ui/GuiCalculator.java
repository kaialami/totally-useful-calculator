package main.ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

/* Graphical interface for calculator.
 * 
 */
public class GuiCalculator extends JFrame {
    private static final Dimension scrn = Toolkit.getDefaultToolkit().getScreenSize();
    public static final int WIDTH = scrn.width;
    public static final int HEIGHT = scrn.height;

    private CalculatorPanel cPanel;

    /*
     * 
     */
    public GuiCalculator() {
        super("Totally Useful Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(true);

        initGUI();
        setExtendedState(MAXIMIZED_BOTH);
    }


    // initialize gui components
    private void initGUI() {
        getContentPane().removeAll();
        cPanel = new CalculatorPanel();
        cPanel.setBackground(Color.white);
        add(cPanel);
        pack();

        setIconImage(new ImageIcon("assets/images/icon.png").getImage());
        
        setLocation((WIDTH - getWidth()) / 2, (HEIGHT - getHeight()) / 2);
        setVisible(true);
        repaint();
    }
}
