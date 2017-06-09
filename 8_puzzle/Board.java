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
        for (int row = 0; row < n; row++) {
            this.blocks[row] = blocks[row].clone();
        }
    }

    public int dimension() {                // board dimension n
        return n;
    }

    public int hamming() {                  // number of blocks out of place
        hamming = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != n * row + col + 1) hamming++;
            }
        }
        return hamming;
    }

    public int manhattan() {                // sum of Manhattan distances between blocks and goal
        manhattan = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != n * row + col + 1) {
                    manhattan += Math.abs(((blocks[row][col] - 1) / n) - row) + Math.abs(((blocks[row][col] - 1) % n) - col);
                }
            }
        }
        return manhattan;
    }

    public boolean isGoal() {               // is this board the goal board?
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] != 0 && blocks[row][col] != n * row + col + 1) return false;
            }
        }
        return true;
    }

    public Board twin() {                   // a board that is obtained by exchanging any pair of blocks
        Board twin = new Board(blocks);
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n - 1; col++) {
                if (blocks[row][col] != 0 && blocks[row][col + 1] != 0) {
                    twin.exch(row, col, row, col + 1);
                    return twin;
                }
            }
        }
        return null;
    }

    public boolean equals(Object y) {       // does this board equal y?
        if (y == null || y.getClass() != this.getClass()) return false;
        if ((Board) y == this) return true;
        Board that = (Board) y;
        if (Arrays.deepEquals(blocks, that.blocks)) return true;
        return false;
    }

    public Iterable<Board> neighbors() {    // all neighboring boards
        Queue<Board> neighbors = new Queue<Board>();

        int row0 = -1, col0 = -1;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (blocks[row][col] == 0) {
                    row0 = row;
                    col0 = col;
                }
            }
        }
        if (col0 > 0) {
            Board neighbor = new Board(blocks);
            neighbor.exch(row0, col0, row0, col0 - 1);
            neighbors.enqueue(neighbor);
        }
        if (row0 > 0) {
            Board neighbor = new Board(blocks);
            neighbor.exch(row0, col0, row0 - 1, col0);
            neighbors.enqueue(neighbor);
        }
        if (col0 < n - 1) {
            Board neighbor = new Board(blocks);
            neighbor.exch(row0, col0, row0, col0 + 1);
            neighbors.enqueue(neighbor);
        }
        if (row0 < n - 1) {
            Board neighbor = new Board(blocks);
            neighbor.exch(row0, col0, row0 + 1, col0);
            neighbors.enqueue(neighbor);
        }
        return neighbors;
    }

    public String toString() {              // string representation of this board (in the output format specified below)
        StringBuilder str = new StringBuilder();
        str.append(n + "\n");
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                    str.append(String.format("%2d ", blocks[row][col]));
            }
            str.append("\n");
        }
        return str.toString();
    }

    public void exch(int row1, int col1, int row2, int col2) {
        int tmp = blocks[row1][col1];
        blocks[row1][col1] = blocks[row2][col2];
        blocks[row2][col2] = tmp;
    }

    // public static void main(String[] args) // unit tests (not graded)
}
