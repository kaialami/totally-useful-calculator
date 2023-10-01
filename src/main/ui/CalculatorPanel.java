package main.ui;


import javax.imageio.ImageIO;
import javax.swing.*;

import main.model.Summer;

import static org.junit.Assert.fail;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/* Custom panel containing calculator (includes clickable buttons, speech bubbles, etc)
 * 
 * 
 */
public class CalculatorPanel extends JPanel implements ActionListener {
    private Image calculatorImage;
    private int imageWidth;
    private int imageHeight;

    private Summer summer;

    private JButton zero;
    private JButton one;
    private JButton two;
    private JButton three;
    private JButton four;
    private JButton five;
    private JButton six;
    private JButton seven;
    private JButton eight;
    private JButton nine;
    private JButton decimal;
    private JButton openParenth;
    private JButton closeParenth;
    private JButton equals;
    private JButton plus;
    private JButton minus;
    private JButton multiply;
    private JButton divide;
    private JButton clear;

    public CalculatorPanel() {
        setPreferredSize(new Dimension(GuiCalculator.WIDTH, GuiCalculator.HEIGHT-300));
        try {
            File f = new File("assets/images/smile-crop.png");
            calculatorImage = ImageIO.read(f);
            imageWidth = calculatorImage.getWidth(getFocusCycleRootAncestor());
            imageHeight = calculatorImage.getHeight(getFocusCycleRootAncestor());
            scaleBackground(imageWidth*GuiCalculator.WIDTH/2000, imageHeight*GuiCalculator.WIDTH/2000);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to load image - aborting");
        }

        summer = new Summer();
        buildButtons();
    }

    private void buildButtons() {
        int baseWidth = GuiCalculator.WIDTH/20;
        int baseHeight = GuiCalculator.HEIGHT/20;
        setLayout(null);

        // row 1
        zero = setupButton( "0", 
                            GuiCalculator.WIDTH/2 + GuiCalculator.WIDTH/39, 
                            GuiCalculator.HEIGHT/2 + GuiCalculator.HEIGHT/55, 
                            baseWidth - baseWidth/8, baseHeight + baseHeight/2);
        add(zero);

        decimal = setupButton( ".", 
                            GuiCalculator.WIDTH/2 + GuiCalculator.WIDTH/13, 
                            GuiCalculator.HEIGHT/2 + GuiCalculator.HEIGHT/55, 
                            baseWidth - baseWidth/6, baseHeight + baseHeight/2);
        add(decimal);

        plus = setupButton( "+", 
                            GuiCalculator.WIDTH/2 + GuiCalculator.WIDTH/8, 
                            GuiCalculator.HEIGHT/2 + GuiCalculator.HEIGHT/55, 
                            baseWidth - baseWidth/6, baseHeight + baseHeight/2);
        add(plus);        

        equals = setupButton( "=", 
                            GuiCalculator.WIDTH/2 + GuiCalculator.WIDTH*17/100, 
                            GuiCalculator.HEIGHT/2 + GuiCalculator.HEIGHT/55, 
                            baseWidth - baseWidth/6, baseHeight + baseHeight/2);
        add(equals);        



        // row 2
        one = setupButton( "1", 
                            GuiCalculator.WIDTH/2 + GuiCalculator.WIDTH/25, 
                            GuiCalculator.HEIGHT/2 - GuiCalculator.HEIGHT/15, 
                            baseWidth - baseWidth/8, baseHeight + baseHeight/2);
        add(one);
        
        two = setupButton( "2", 
                            GuiCalculator.WIDTH/2 + GuiCalculator.WIDTH/11, 
                            GuiCalculator.HEIGHT/2 - GuiCalculator.HEIGHT/15, 
                            baseWidth - baseWidth/5, baseHeight + baseHeight/2);
        add(two);

        three = setupButton( "3", 
                            GuiCalculator.WIDTH/2 + GuiCalculator.WIDTH*14/100, 
                            GuiCalculator.HEIGHT/2 - GuiCalculator.HEIGHT/15, 
                            baseWidth - baseWidth/5, baseHeight + baseHeight/2);
        add(three);

        minus = setupButton( "-", 
                            GuiCalculator.WIDTH/2 + GuiCalculator.WIDTH*19/100, 
                            GuiCalculator.HEIGHT/2 - GuiCalculator.HEIGHT/15, 
                            baseWidth - baseWidth/5, baseHeight + baseHeight/2);
        add(minus);


    }

    private JButton setupButton(String label, int x, int y, int width, int height) {
        JButton b = new JButton(label, null);
        b.setActionCommand(label);
        b.addActionListener(this);
        b.setBounds(x, y, width, height);

        // b.setOpaque(false);
        // b.setContentAreaFilled(false);
        // b.setBorderPainted(false);

        return b;
    }

    public void actionPerformed(ActionEvent e) {
        // if ("0".equals(e.getActionCommand())) {
            System.out.println(e.getActionCommand());
        // }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(calculatorImage, 
                    GuiCalculator.WIDTH/2, GuiCalculator.HEIGHT/20, this);
    }

    private void scaleBackground(int x, int y) {
        calculatorImage = calculatorImage.getScaledInstance(x, y, Image.SCALE_DEFAULT);
        imageWidth = calculatorImage.getWidth(getFocusCycleRootAncestor());
        imageHeight = calculatorImage.getHeight(getFocusCycleRootAncestor());
    }

    public void setBackgroundImage(String path) {
        try {
            File f = new File(path);
            calculatorImage = ImageIO.read(f);
            imageWidth = calculatorImage.getWidth(getFocusCycleRootAncestor());
            imageHeight = calculatorImage.getHeight(getFocusCycleRootAncestor());
            scaleBackground(imageWidth*GuiCalculator.WIDTH/2000, imageHeight*GuiCalculator.WIDTH/2000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
