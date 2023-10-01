package main.ui;


import javax.imageio.ImageIO;
import javax.swing.*;

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

    private JButton zero;

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

        buildButtons();
    }

    private void buildButtons() {
        setLayout(null);
        zero = new JButton("0", null);
        zero.setActionCommand("0");
        zero.addActionListener(this);
        zero.setBounds(GuiCalculator.WIDTH/2 + 50, GuiCalculator.HEIGHT/2+45, 
                        GuiCalculator.WIDTH/20-10, GuiCalculator.HEIGHT/20+25);
        add(zero);
    }

    public void actionPerformed(ActionEvent e) {
        if ("0".equals(e.getActionCommand())) {
            System.out.println("0");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(calculatorImage, 
                    GuiCalculator.WIDTH/2, GuiCalculator.HEIGHT/2-imageHeight/2-150, this);
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
