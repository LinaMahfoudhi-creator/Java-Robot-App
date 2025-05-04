import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.File;

public class CardPrincipal extends JPanel implements MouseMotionListener {
    private Image backgroundImage;
    private int eyeX1, eyeY1;
    private int eyeX2, eyeY2;
    private int pupilX1, pupilY1;
    private int pupilX2, pupilY2;
    private boolean initialized = false;

    public CardPrincipal() {
        try {
            backgroundImage = ImageIO.read(new File("Images/BMOO.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        addMouseMotionListener(this);
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(400, 400));
        setBackground(Color.WHITE);

        JPanel titlePanel = new JPanel();
        titlePanel.setOpaque(false);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("🏠 Welcome Home");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(60, 60, 60));

        JLabel subtitle = new JLabel("Your Robot Assistant app!");
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 16));
        subtitle.setForeground(new Color(100, 100, 100));

        titlePanel.add(title);
        titlePanel.add(subtitle);
        add(titlePanel, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setOpaque(false);
        southPanel.setPreferredSize(new Dimension(0, 60));
        add(southPanel, BorderLayout.SOUTH);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        // Wait until layout is complete
        SwingUtilities.invokeLater(() -> {
            int panelWidth = getWidth();
            int panelHeight = getHeight();
            int northHeight = 80;

            eyeX1 = panelWidth / 3;
            eyeY1 = (panelHeight - northHeight) / 4 + northHeight;

            eyeX2 = 2 * panelWidth / 3;
            eyeY2 = eyeY1;

            pupilX1 = eyeX1;
            pupilY1 = eyeY1;

            pupilX2 = eyeX2;
            pupilY2 = eyeY2;

            initialized = true;
            repaint();
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        if (!initialized) return;

        g.setColor(Color.WHITE);
        g.fillOval(eyeX1 - 20, eyeY1 - 20, 40, 40);
        g.fillOval(eyeX2 - 20, eyeY2 - 20, 40, 40);

        g.setColor(Color.BLACK);
        g.fillOval(pupilX1 - 10, pupilY1 - 10, 20, 20);
        g.fillOval(pupilX2 - 10, pupilY2 - 10, 20, 20);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // Not needed
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (!initialized) return;

        int mouseX = e.getX();
        int mouseY = e.getY();

        if (!getBounds().contains(mouseX, mouseY)) return;

        int maxPupilDistance = 15;

        updatePupil(mouseX, mouseY, eyeX1, eyeY1, maxPupilDistance, true);
        updatePupil(mouseX, mouseY, eyeX2, eyeY2, maxPupilDistance, false);

        repaint();
    }

    private void updatePupil(int mouseX, int mouseY, int eyeX, int eyeY, int maxDist, boolean isFirst) {
        int dx = mouseX - eyeX;
        int dy = mouseY - eyeY;
        double dist = Math.sqrt(dx * dx + dy * dy);

        int newX = eyeX + (int)((dx / dist) * Math.min(dist, maxDist));
        int newY = eyeY + (int)((dy / dist) * Math.min(dist, maxDist));

        if (isFirst) {
            pupilX1 = newX;
            pupilY1 = newY;
        } else {
            pupilX2 = newX;
            pupilY2 = newY;
        }
    }
}
