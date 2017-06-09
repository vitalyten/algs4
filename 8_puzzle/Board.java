import java.util.Arrays;
import edu.princeton.cs.algs4.Queue;

public class Board {
    private final int n;
    private int[][] blocks;
    private int hamming;
    private int manhattan;

    public Board(int[][] blocks) {          // construct a board from an n-by-n array of blocks (where blocks[i][j] = block in row i, column j)
        n = blocks.length;
        this.blocks = new int[n][];
        for (int i = 0; i < n; i++) {
            this.blocks[i] = blocks[i].clone();
        }
    }

    public int dimension() {                // board dimension n
        return n;
    }

    public int hamming() {                  // number of blocks out of place
        hamming = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != n * i + j + 1) hamming++;
            }
        }
        return hamming;
    }

    public int manhattan() {                // sum of Manhattan distances between blocks and goal
        manhattan = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != n * i + j + 1) {
                    manhattan += Math.abs(((blocks[i][j] - 1) / n) - i) + Math.abs(((blocks[i][j] - 1) % n) - j);
                }
            }
        }
        return manhattan;
    }

    public boolean isGoal() {               // is this board the goal board?
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (blocks[i][j] != 0 && blocks[i][j] != n * i + j + 1) return false;
            }
        }
        return true;
    }

    public Board twin() {                   // a board that is obtained by exchanging any pair of blocks
        Board twin = new Board(blocks);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (blocks[i][j] != 0 && blocks[i][j + 1] != 0) {
                    twin.exch(i, j, i, j + 1);
                    return twin;
                }
            }
        }
        return null;
    }

    public boolean equals(Object y) {       // does this board equal y?
        if (y == null || y.getClass() != this.getClass()) return false;
    }

    public Iterable<Board> neighbors() {    // all neighboring boards

    }

    public String toString() {              // string representation of this board (in the output format specified below)

    }

    public void exch(int i1, int j1, int i2, int j2) {
        int tmp = blocks[i1][j1];
        blocks[i1][j1] = blocks[i2][j2];
        blocks[i2][j2] = tmp;
    }

    // public static void main(String[] args) // unit tests (not graded)
}
