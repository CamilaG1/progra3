package ejBacktracking;

// =============================
// BacktrackingKnightTour.java
// =============================

import java.util.Arrays;

public class BacktrackingKnightTour {
    static final int[] DR = {-2,-2,-1,-1, 1, 1, 2, 2};
    static final int[] DC = {-1, 1,-2, 2,-2, 2,-1, 1};

    static boolean in(int r, int c, int n) { return r>=0 && c>=0 && r<n && c<n; }

    static boolean solveKTUtil(int[][] board, int r, int c, int step) {
        int n = board.length;
        if (step == n*n) return true; // cubrió todas las casillas
        for (int k = 0; k < 8; k++) {
            int nr = r + DR[k], nc = c + DC[k];
            if (in(nr, nc, n) && board[nr][nc] == -1) {
                board[nr][nc] = step;
                if (solveKTUtil(board, nr, nc, step + 1)) return true;
                board[nr][nc] = -1; // backtrack
            }
        }
        return false;
    }

    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int v : row) System.out.printf("%3d", v);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 6; // tamaño del tablero
        int sr = 0, sc = 0; // inicio
        int[][] board = new int[n][n];
        for (int[] row : board) Arrays.fill(row, -1);
        board[sr][sc] = 0;
        boolean ok = solveKTUtil(board, sr, sc, 1);
        if (ok) printBoard(board);
        else System.out.println("No hay solución desde (" + sr + "," + sc + ") para n=" + n);
    }
}
