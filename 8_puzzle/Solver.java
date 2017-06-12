import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private boolean solvable = false;
    private Stack<Board> solution = new Stack<Board>();

    private class SearchNode implements Comparable<SearchNode> {
        private Board board;
        private int moves;
        private SearchNode prev;

        public SearchNode(Board board, SearchNode prev, int moves) {
            this.board = board;
            this.prev = prev;
            this.moves = moves;
        }

        public int compareTo(SearchNode that) {
            int priority = this.board.manhattan() + this.moves;
            int thatPriority = that.board.manhattan() + that.moves;
            if (priority > thatPriority) return 1;
            if (priority < thatPriority) return -1;
            return 0;
        }
    }

    public Solver(Board initial) {          // find a solution to the initial board (using the A* algorithm)
        if (initial == null) throw new NullPointerException();
        Board board = initial;
        Board twin = initial.twin();
        SearchNode sn = new SearchNode(board, null, 0);
        SearchNode snTwin = new SearchNode(twin, null, 0);
        MinPQ<SearchNode> mpq = new MinPQ<SearchNode>();
        MinPQ<SearchNode> mpqTwin = new MinPQ<SearchNode>();

        mpq.insert(sn);
        mpqTwin.insert(snTwin);
        while (true) {
            sn = mpq.delMin();
            snTwin = mpqTwin.delMin();
            board = sn.board;
            twin = snTwin.board;
            if (board.isGoal()) {
                solvable = true;
                solution.push(sn.board);
                while (sn.prev != null) {
                    sn = sn.prev;
                    solution.push(sn.board);
                }
                return;
            }
            if (twin.isGoal()) return;
            sn.moves++;
            snTwin.moves++;
            for (Board nbr : board.neighbors()) {
                if (sn.prev == null || !nbr.equals(sn.prev.board)) {
                    mpq.insert(new SearchNode(nbr, sn, sn.moves));
                }
            }
            for (Board nbr : twin.neighbors()) {
                if (snTwin.prev == null || !nbr.equals(snTwin.prev.board)) {
                    mpqTwin.insert(new SearchNode(nbr, snTwin, snTwin.moves));
                }
            }
        }
    }

    public boolean isSolvable() {           // is the initial board solvable?
        return solvable;
    }

    public int moves() {                    // min number of moves to solve initial board; -1 if unsolvable
        if (solvable) return (solution.size() - 1);
        return -1;
    }

    public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable
        if (solvable) return solution;
        return null;
    }

    public static void main(String[] args) {// solve a slider puzzle (given below)
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
