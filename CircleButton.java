import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class CircleButton extends JButton {
    private int diameter;

    public CircleButton(int diameter) {
        this.diameter = diameter;

        // Make the button transparent and remove the border for the circle illusion
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setPreferredSize(new Dimension(diameter, diameter)); // Set the preferred size to the diameter

        // Optional: Add a mouse listener to change color on hover
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(Color.RED);  // Change to red when hovered
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(null);  // Reset when mouse leaves
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the red circle button with diameter
        g.setColor(Color.RED);
        g.fillOval(0, 0, diameter, diameter); // Use diameter directly here to draw the circle
    }
}
