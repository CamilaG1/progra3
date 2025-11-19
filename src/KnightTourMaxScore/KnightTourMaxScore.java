package KnightTourMaxScore;

import java.util.Arrays;


public class KnightTourMaxScore {
    // Movimientos del caballo
    static final int[] DR = {-2,-2,-1,-1, 1, 1, 2, 2};
    static final int[] DC = {-1, 1,-2, 2,-2, 2,-1, 1};

    static boolean in(int r, int c, int n) { return r>=0 && c>=0 && r<n && c<n; }

    /**
     * DP: dp[t][r][c] = mejor puntaje al llegar a (r,c) en exactamente t movimientos
     * Complejidad: O(K * N^2 * 8), memoria O(N^2) con rolling array.
     */
    public static int maxScoreInKMoves(int[][] score, int K, int sr, int sc) {
        int n = score.length;
        final int NEG = -1_000_000_000;

        int[][] prev = new int[n][n];
        int[][] next = new int[n][n];
        for (int[] row : prev) Arrays.fill(row, NEG);
        prev[sr][sc] = score[sr][sc];  // tomar puntaje de la casilla inicial

        for (int t = 1; t <= K; t++) {
            for (int[] row : next) Arrays.fill(row, NEG);
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    if (prev[r][c] == NEG) continue;
                    for (int m = 0; m < 8; m++) {
                        int nr = r + DR[m], nc = c + DC[m];
                        if (in(nr, nc, n)) {
                            next[nr][nc] = Math.max(next[nr][nc], prev[r][c] + score[nr][nc]);
                        }
                    }
                }
            }
            // swap de capas
            int[][] tmp = prev; prev = next; next = tmp;
        }

        int ans = NEG;
        for (int r = 0; r < n; r++)
            for (int c = 0; c < n; c++)
                ans = Math.max(ans, prev[r][c]);
        return ans;
    }

    // Demo sencilla
    public static void main(String[] args) {
        int n = 5, K = 6, sr = 0, sc = 0;
        int[][] score = new int[n][n];
        for (int i = 0; i < n; i++) Arrays.fill(score[i], 1); // ejemplo: todas valen 1
        int best = maxScoreInKMoves(score, K, sr, sc);
        System.out.println("Mejor puntaje en K=" + K + " movimientos: " + best);
    }
}
