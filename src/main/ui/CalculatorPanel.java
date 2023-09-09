package main.ui;


import javax.swing.*;
import java.awt.*;


/* Custom panel containing calculator (includes clickable buttons, speech bubbles, etc)
 * 
 * 
 */
public class CalculatorPanel extends JPanel {


    public CalculatorPanel() {
        setPreferredSize(new Dimension(GuiCalculator.WIDTH-100, GuiCalculator.HEIGHT-100));
    }
}
