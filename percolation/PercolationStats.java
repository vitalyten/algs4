import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private int ntrials;
    private double[] res;
    private double mean;
    private double stddev;

    public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
        if (n < 1 || trials < 1) {
            throw new IllegalArgumentException();
        }
        ntrials = trials;
        res = new double[ntrials];
        for (int i = 0; i < ntrials; i++) {
            Percolation trial = new Percolation(n);
            int[] rand = new int[n * n];
            for (int j = 0; j < n * n; j++) {
                rand[j] = j;
            }
            StdRandom.shuffle(rand);
            int open = 0;
            while (!trial.percolates()) {
                int row = rand[open] / n + 1;
                int col = rand[open] % n + 1;
                trial.open(row, col);
                open++;
            }
            res[i] = (double) open / (n * n);
        }
        mean = StdStats.mean(res);
        stddev = StdStats.stddev(res);
    }

    public double mean() {                          // sample mean of percolation threshold
        return mean;
    }

    public double stddev() {                        // sample standard deviation of percolation threshold
        return stddev;
    }

    public double confidenceLo() {                  // low  endpoint of 95% confidence interval
        return mean - 1.96 * stddev / ntrials;
    }

    public double confidenceHi() {                  // high endpoint of 95% confidence interval
        return mean + 1.96 * stddev / ntrials;
    }

    public static void main(String[] args) {        // test client (described below)
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        System.out.println("mean \t\t= " + stats.mean());
        System.out.println("stddev \t\t= " + stats.stddev());
        System.out.println("95% confidence interval \t\t= ["
            + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }
}
