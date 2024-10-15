public class bruteforce {

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

        for (int i = 1; i <= COLORS.length; i++) {
            colors[0] = i;
            for (int j = 1; j <= COLORS.length; j++) {
                colors[1] = j;
                for (int k = 1; k <= COLORS.length; k++) {
                    colors[2] = k;
                    for (int l = 1; l <= COLORS.length; l++) {
                        colors[3] = l;
                        for (int m = 1; m <= COLORS.length; m++) {
                            colors[4] = m;
                            for (int n = 1; n <= COLORS.length; n++) {
                                colors[5] = n;
                                for (int o = 1; o <= COLORS.length; o++) {
                                    colors[6] = o;

                                    if (isValid(colors)) {
                                        printSolution(colors);
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        System.out.println("No solution found.");
    }

    private boolean isValid(int[] colors) {
        for (int i = 0; i < ADJACENCY_MATRIX.length; i++) {
            for (int j = 0; j < ADJACENCY_MATRIX[i].length; j++) {
                if (ADJACENCY_MATRIX[i][j] == 1 && colors[i] == colors[j]) {
                    return false;
                }
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
        bruteforce solver = new bruteforce();

        System.out.println("Bruteforce (Iterative)\n");
        long startTime = System.currentTimeMillis();
        solver.solve();
        long endTime = System.currentTimeMillis();

        long duration = endTime - startTime;
        System.out.println("\nBrute Force Iterative : " + duration + " ms");
    }
}
