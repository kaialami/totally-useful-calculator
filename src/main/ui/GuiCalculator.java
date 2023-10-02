package main.ui;

import javax.imageio.ImageIO;
import javax.swing.*;

import main.model.Summer;

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

    public static Font FONT;


    public Summer summer;

    private CalculatorPanel cPanel;
    private TitlePanel tPanel;

    /*
     * 
     */
    public GuiCalculator() {
        super("Totally Useful Calculator");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(false);
        setResizable(true);

        initFont();
        initGUI();

        summer = cPanel.getSummer();

        setExtendedState(MAXIMIZED_BOTH);
    }


    private void initFont() {
        try {
            FONT = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/Architex.ttf")).deriveFont(56f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(FONT);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }


    }


    // initialize gui components
    private void initGUI() {
        getContentPane().removeAll();

        tPanel = new TitlePanel();
        tPanel.setBackground(Color.white);

        cPanel = new CalculatorPanel();
        cPanel.setBackground(Color.white);


        add(tPanel, BorderLayout.NORTH);
        add(cPanel, BorderLayout.CENTER);
        pack();

        setIconImage(new ImageIcon("assets/images/icon2.png").getImage());
        
        setLocation((WIDTH - getWidth()) / 2, (HEIGHT - getHeight()) / 2);
        setVisible(true);
        repaint();
    }
}
