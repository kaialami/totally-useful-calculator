package main.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TitlePanel extends JPanel {
    private static final String TITLE = "totally useful calculator";
    private static final String BY = "                  kai alami";

    private JLabel titleLabel;
    private JLabel byLabel;

    public TitlePanel() {
        setPreferredSize(new Dimension(GuiCalculator.WIDTH, GuiCalculator.HEIGHT*15/100));
        
        titleLabel = new JLabel(TITLE);
        byLabel = new JLabel(BY);

        titleLabel.setFont(GuiCalculator.FONT.deriveFont(96f));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setVerticalAlignment(SwingConstants.TOP);
        
        byLabel.setFont(GuiCalculator.FONT);
        byLabel.setForeground(Color.black);
        byLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        
        this.add(titleLabel, BorderLayout.WEST);
        this.add(byLabel, BorderLayout.SOUTH);
        
    }
}
