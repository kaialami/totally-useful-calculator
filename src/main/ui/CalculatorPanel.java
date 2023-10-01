package main.ui;


import javax.imageio.ImageIO;
import javax.swing.*;

import static org.junit.Assert.fail;

import java.awt.*;
import java.io.File;
import java.io.IOException;


/* Custom panel containing calculator (includes clickable buttons, speech bubbles, etc)
 * 
 * 
 */
public class CalculatorPanel extends JPanel {
    private Image calculatorImage;
    private int imageWidth;
    private int imageHeight;


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
