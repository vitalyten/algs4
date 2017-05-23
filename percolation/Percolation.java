import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid; // true == open
    private WeightedQuickUnionUF sites;
    private int height;
    private int totalSites;

    public Percolation(int n) {                // create n-by-n grid, with all sites blocked
        if (n < 1) {
            throw new IllegalArgumentException("Invalid size " + n);
        }

        this.height = n;
        this.totalSites = n * n + 2;
        this.grid = new boolean[n][n];
        this.sites = new WeightedQuickUnionUF(totalSites);
    }

    public    void open(int row, int col) {    // open site (row, col) if it is not open already
        if (col < 1 || row < 1 || col > this.height || row > this.height) {
            throw new IndexOutOfBoundsException();
        }
        row--;
        col--;
        if (!grid[row][col])
        {
            grid[row][col] = true;
            if (row - 1 >= 0 && grid[row - 1][col]) {
                sites.union(height * row + col, height * (row - 1) + col);
            }
            if (row + 1 < height && grid[row + 1][col]) {
                sites.union(height * row + col, height * (row + 1) + col);
            }
            if (col - 1 >= 0 && grid[row][col - 1]) {
                sites.union(height * row + col, height * row + col - 1);
            }
            if (col + 1 < height && grid[row][col + 1]) {
                sites.union(height * row + col, height * row + col + 1);
            }
            if (row == height - 1) {
                sites.union(height * row + col, totalSites - 1);
            }
            if (row == 0) {
                sites.union(col, 0);
            }
        }
    }

    public boolean isOpen(int row, int col) {  // is site (row, col) open?
        if (col < 1 || row < 1 || col > this.height || row > this.height) {
            throw new IndexOutOfBoundsException();
        }
        return grid[row - 1][col - 1];
    }

    public boolean isFull(int row, int col) {  // is site (row, col) full?
        if (col < 1 || row < 1 || col > this.height || row > this.height) {
            throw new IndexOutOfBoundsException();
        }
        return sites.connected(0, (row - 1) * height + col - 1) && grid[row - 1][col - 1];
    }

    public     int numberOfOpenSites() {       // number of open sites
        int num = 0;
        for (int row = 0; row < height; row++) {
            for (int col = 0; col < height; col++) {
                if (grid[row][col]) num++;
            }
        }
        return num;
    }

    public boolean percolates() {              // does the system percolate?
        return sites.connected(0, totalSites - 1);
    }

    // public static void main(String[] args) {   // test client (optional)

    // }
}
