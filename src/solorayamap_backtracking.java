import java.awt.*;
import javax.swing.*;

public class solorayamap_backtracking {
    private static final String[] REGIONS = {"Surakarta", "Boyolali", "Klaten", "Sukoharjo", "Wonogiri", "Sragen", "Karanganyar"};
    private static final int[][] ADJACENCY_MATRIX = {
            {0, 1, 0, 1, 0, 0, 1}, // Surakarta
            {1, 0, 1, 1, 0, 1, 1}, // Boyolali
            {0, 1, 0, 1, 0, 0, 0}, // Klaten
            {1, 1, 1, 0, 1, 0, 1}, // Sukoharjo
            {0, 0, 0, 1, 0, 0, 1}, // Wonogiri
            {0, 1, 0, 0, 0, 0, 1}, // Sragen
            {1, 1, 0, 1, 1, 1, 0}  // Karanganyar
    };

    private static final Color[] COLORS = {
            Color.RED,
            Color.ORANGE,
            Color.YELLOW,
            Color.GREEN,
            Color.CYAN,
            Color.BLUE,
            Color.MAGENTA,
            Color.PINK,
            Color.DARK_GRAY,
            Color.GRAY,
            Color.LIGHT_GRAY,
            Color.BLACK,
            Color.WHITE};

    // The solution
    private Color[] solution;
    private int minimumColors;

    public solorayamap_backtracking() {
        solution = new Color[REGIONS.length];
        minimumColors = 0;
    }

    public void solve() {
        for (int numColors = 2; numColors <= COLORS.length; numColors++) {
            if (backtrack(0, numColors)) {
                minimumColors = numColors;
                System.out.println("Minimum number of colors required: " + minimumColors);
                printSolution();
                drawMap();
                return;
            }
        }
        System.out.println("No solution found.");
    }

    private boolean backtrack(int regionIndex, int numColors) {
        if (regionIndex == REGIONS.length) {
            return true;
        }

        for (int i = 0; i < numColors; i++) {
            if (isValidColor(regionIndex, COLORS[i])) {
                solution[regionIndex] = COLORS[i];
                if (backtrack(regionIndex + 1, numColors)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean isValidColor(int regionIndex, Color color) {
        for (int i = 0; i < REGIONS.length; i++) {
            if (ADJACENCY_MATRIX[regionIndex][i] == 1 && solution[i] != null && solution[i].equals(color)) {
                return false;
            }
        }
        return true;
    }

    public String color(int index) {
        if (solution[index] == Color.RED)
            return "Merah";
        else if (solution[index] == Color.GREEN)
            return "Hijau";
        else if (solution[index] == Color.BLUE)
            return "Biru";
        else if (solution[index] == Color.YELLOW)
            return "Kuning";
        else
            return "[Unknown Color]";
    }

    private void printSolution() {
        for (int i = 0; i < REGIONS.length; i++) {
            System.out.println(REGIONS[i] + ": " + color(i));
        }
    }

    private void drawMap() {
        MapFrame frame = new MapFrame();
        frame.setVisible(true);
    }

    private class MapFrame extends JFrame {
        public MapFrame() {
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(800, 600);
            setTitle("Solo Raya Map Coloring");
            add(new MapPanel());
        }
    }

    private class MapPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int[][] positions = {
                    {300, 200}, // Surakarta
                    {200, 100}, // Boyolali  
                    {200, 300}, // Klaten
                    {300, 300}, // Sukoharjo
                    {400, 400}, // Wonogiri
                    {400, 100}, // Sragen
                    {500, 200}  // Karanganyar
            };
            int width = 80;
            int height = 80;

            for (int i = 0; i < REGIONS.length; i++) {
                g2d.setColor(solution[i]);
                g2d.fillOval(positions[i][0], positions[i][1], width, height);
                g2d.setColor(Color.BLACK);
                g2d.drawOval(positions[i][0], positions[i][1], width, height);
                g2d.drawString(REGIONS[i], positions[i][0] + 10, positions[i][1] + 40);
            }

            Stroke stroke = new BasicStroke(2);
            g2d.setStroke(stroke);
            g2d.setColor(Color.BLACK);
            for (int i = 0; i < REGIONS.length; i++) {
                for (int j = 0; j < REGIONS.length; j++) {
                    if (ADJACENCY_MATRIX[i][j] == 1) {
                        int x1 = positions[i][0] + width / 2;
                        int y1 = positions[i][1] + height / 2;
                        int x2 = positions[j][0] + width / 2;
                        int y2 = positions[j][1] + height / 2;
                        g2d.drawLine(x1, y1, x2, y2);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        solorayamap_backtracking mapColoring = new solorayamap_backtracking();
        mapColoring.solve();
    }
}
