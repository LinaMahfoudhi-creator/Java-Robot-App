import javax.swing.*;
import java.awt.*;

public class RobotGridPanel extends JPanel {
    protected static final int GRID_SIZE = 10;
    protected static final int CELL_SIZE = 40;

    private int robotX;
    private int robotY;
    private boolean[][] trash;

    public RobotGridPanel(int startX, int startY) {
        this.robotX = startX;
        this.robotY = startY;
        this.trash = new boolean[GRID_SIZE][GRID_SIZE];

        // Illustration de la poubelle
        generateTrash();
    }

    // Génération aléatoire de la poubelle
    private void generateTrash() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                trash[i][j] = Math.random() < 0.2; // 20% chance of trash in each cell
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Grille
        g.setColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= GRID_SIZE; i++) {
            g.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE, GRID_SIZE * CELL_SIZE);
            g.drawLine(0, i * CELL_SIZE, GRID_SIZE * CELL_SIZE, i * CELL_SIZE);
        }

        // Poubelle
        g.setColor(Color.GRAY);
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (trash[i][j])  {
                    g.fillOval(i * CELL_SIZE + 5, j * CELL_SIZE + 5, 20, 20);
                    //illustré uniquement si la case est occupée par une poubelle et n'avait pas été ramassée
                }
            }
        }

        // Illustration du robot
        int px = robotX * CELL_SIZE;
        int py = robotY * CELL_SIZE;

        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Robot dimensions and position
        int robotWidth = 18;
        int robotHeight = 24;
        int startX = px + (CELL_SIZE - robotWidth) / 2;
        int startY = py + (CELL_SIZE - robotHeight) / 2;

        // Antenne du robot
        g2.setColor(Color.DARK_GRAY);
        g2.drawLine(startX + robotWidth / 2, startY - 4, startX + robotWidth / 2, startY);
        g2.fillOval(startX + robotWidth / 2 - 2, startY - 6, 4, 4);

        // Tete robot
        g2.setColor(Color.GRAY);
        g2.fillRect(startX, startY, robotWidth, 10);

        // Yeux du robot
        g2.setColor(Color.WHITE);
        g2.fillOval(startX + 3, startY + 2, 3, 3);
        g2.fillOval(startX + robotWidth - 6, startY + 2, 3, 3);

        // Corps du robot
        g2.setColor(Color.LIGHT_GRAY);
        g2.fillRect(startX + 2, startY + 10, robotWidth - 4, 12);

        // Bras robot
        g2.setColor(Color.GRAY);
        g2.drawLine(startX - 2, startY + 12, startX + 2, startY + 12); // Left arm
        g2.drawLine(startX + robotWidth - 2, startY + 12, startX + robotWidth + 2, startY + 12); // Right arm

        // Jambes robot
        g2.drawLine(startX + 5, startY + robotHeight, startX + 5, startY + robotHeight + 4);
        g2.drawLine(startX + robotWidth - 6, startY + robotHeight, startX + robotWidth - 6, startY + robotHeight + 4);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(GRID_SIZE * CELL_SIZE, GRID_SIZE * CELL_SIZE);
    }

    // Update position et ramasser la poubelle
    public String setRobotPosition(int x, int y) {
        this.robotX = x;
        this.robotY = y;
        repaint();
        if (trash[robotX][robotY]) {
            trash[robotX][robotY] = false; // Trash is picked up
            return "Poubelle Ramassée à (" + robotX + ", " + robotY + ")";
        }
        return null;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Robot Grid Simulation");
        RobotGridPanel panel = new RobotGridPanel(0, 0); // Initial robot position (0, 0)

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setSize(panel.getPreferredSize());
        frame.setVisible(true);

        new Timer(1000, e -> {
            int newX = (int) (Math.random() * GRID_SIZE);
            int newY = (int) (Math.random() * GRID_SIZE);
            panel.setRobotPosition(newX, newY);
        }).start();
    }
}
