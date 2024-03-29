package main.ui;

import main.model.Summer;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

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
            InputStream is = getClass().getClassLoader().getResourceAsStream("assets/fonts/Architex.ttf");
            FONT = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(56f);
        } catch (IOException e) {
            e.printStackTrace();

        } catch (FontFormatException e) {
            e.printStackTrace();
        }

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(FONT);

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
        setIconImage(new ImageIcon(getClass().getClassLoader().getResource("assets/images/characters/0.png")).getImage());
        
        setLocation((WIDTH - getWidth()) / 2, (HEIGHT - getHeight()) / 2);
        setVisible(true);
        repaint();
    }
}
