import javax.swing.*;
import java.awt.*;

public class GIFBackgroundPanel extends JPanel {
    private JLabel gifLabel;

    public GIFBackgroundPanel(String gifPath) {
        setLayout(new BorderLayout());

        // Create a label with the GIF
        gifLabel = new JLabel(new ImageIcon(gifPath));

        // Make the label transparent so we can see through to the background
        gifLabel.setOpaque(false);

        // Add the label to the panel
        add(gifLabel, BorderLayout.CENTER);

        // Set the layout manager to null for absolute positioning of other components
        setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Paint the GIF background
        gifLabel.setBounds(0, 0, getWidth(), getHeight());
    }

}