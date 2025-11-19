package WarnsdorffKnightTour;

import java.util.Arrays;

public class WarnsdorffKnightTour {
    // Movimientos del caballo (pares DR/DC)
    static final int[] DR = {-2,-2,-1,-1, 1, 1, 2, 2};
    static final int[] DC = {-1, 1,-2, 2,-2, 2,-1, 1};

    static boolean in(int r, int c, int n) { return r>=0 && c>=0 && r<n && c<n; }

    // Cantidad de movimientos disponibles desde (r,c) en el estado actual del tablero
    static int degree(int[][] board, int r, int c) {
        int n = board.length, cnt = 0;
        for (int k = 0; k < 8; k++) {
            int nr = r + DR[k], nc = c + DC[k];
            if (in(nr, nc, n) && board[nr][nc] == -1) cnt++;
        }
        return cnt;
    }

    // Regla de Warnsdorff: siempre elegir la casilla con menor grado
    static int[][] run(int n, int sr, int sc) {
        int[][] board = new int[n][n];
        for (int[] row : board) Arrays.fill(row, -1);

        int r = sr, c = sc;
        board[r][c] = 0;

        for (int step = 1; step < n*n; step++) {
            int bestR = -1, bestC = -1, bestDeg = Integer.MAX_VALUE;

            for (int k = 0; k < 8; k++) {
                int nr = r + DR[k], nc = c + DC[k];
                if (!in(nr, nc, n) || board[nr][nc] != -1) continue;

                int deg = degree(board, nr, nc);
                // Tie-break: si empatan en grado, podés dejar el primero o elegir el de menor (nr,nc)
                if (deg < bestDeg) {
                    bestDeg = deg;
                    bestR = nr;
                    bestC = nc;
                }
            }

            if (bestR == -1) {
                System.out.println("Atascado en step=" + step + ". No se pudo completar el tour.");
                return board;
            }

            r = bestR;
            c = bestC;
            board[r][c] = step;
        }
        return board;
    }

    static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int v : row) System.out.printf("%3d", v);
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 6;          // tamaño del tablero
        int sr = 0, sc = 0; // posición inicial
        int[][] res = run(n, sr, sc);
        printBoard(res);
    }
}

