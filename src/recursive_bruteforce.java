import java.util.Arrays;

public class recursive_bruteforce {

    private static final String[] REGIONS = { "Surakarta", "Boyolali", "Klaten", "Sukoharjo", "Wonogiri", "Sragen", "Karanganyar" };
    private static final int[][] ADJACENCY_MATRIX = {
            { 0, 1, 0, 1, 0, 0, 1 }, // Surakarta
            { 1, 0, 1, 1, 0, 1, 1 }, // Boyolali
            { 0, 1, 0, 1, 0, 0, 0 }, // Klaten
            { 1, 1, 1, 0, 1, 0, 1 }, // Sukoharjo
            { 0, 0, 0, 1, 0, 0, 1 }, // Wonogiri
            { 0, 1, 0, 0, 0, 0, 1 }, // Sragen
            { 1, 1, 0, 1, 1, 1, 0 } // Karanganyar
    };

    private static final String[] COLORS = { "Merah", "Hijau", "Biru", "Kuning" };

    public void solve() {
        int[] colors = new int[REGIONS.length];
        Arrays.fill(colors, -1); // Initialize all colors to -1 (unassigned)

        if (colorMap(0, colors)) {
            printSolution(colors);
        } else {
            System.out.println("No solution found.");
        }
    }

    private boolean colorMap(int regionIndex, int[] colors) {
        if (regionIndex == REGIONS.length) {
            return true;
        }

        for (int color = 1; color <= COLORS.length; color++) {
            colors[regionIndex] = color;
            if (isValid(colors, regionIndex)) {
                if (colorMap(regionIndex + 1, colors)) {
                    return true;
                }
            }
        }

        colors[regionIndex] = -1;
        return false;
    }

    private boolean isValid(int[] colors, int regionIndex) {
        for (int j = 0; j < ADJACENCY_MATRIX[regionIndex].length; j++) {
            if (ADJACENCY_MATRIX[regionIndex][j] == 1 && colors[regionIndex] == colors[j]) {
                return false;
            }
        }
        return true;
    }

    private void printSolution(int[] colors) {
        System.out.println("SOLUTION:");
        for (int i = 0; i < REGIONS.length; i++) {
            System.out.println(REGIONS[i] + " -> " + COLORS[colors[i] - 1]);
        }
    }

    public static void main(String[] args) {
        recursive_bruteforce solver = new recursive_bruteforce();

        System.out.println("Bruteforce (Recursive)\n");
        long startTime = System.currentTimeMillis();
        solver.solve();
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("\nBrute Force Recursive : " + duration + " ms");
    }
}
