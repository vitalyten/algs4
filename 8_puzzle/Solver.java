import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    private Stack<Board> boards = new Stack<Board>();

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

    }

    public boolean isSolvable() {           // is the initial board solvable?

    }

    public int moves() {                    // min number of moves to solve initial board; -1 if unsolvable

    }

    public Iterable<Board> solution() {     // sequence of boards in a shortest solution; null if unsolvable

    }

    public static void main(String[] args) {// solve a slider puzzle (given below)

    }
}
