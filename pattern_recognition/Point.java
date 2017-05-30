import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
	private final int x;
	private final int y;

	public Point(int x, int y) {                        // constructs the point (x, y)
		this.x = x;
		this.y = y;
	}

	public   void draw() {                              // draws this point
		StdDraw.point(x, y);
	}

	public   void drawTo(Point that) {                  // draws the line segment from this point to that point
		StdDraw.line(this.x, this.y, that.x, that.y);
	}

	public String toString() {                          // string representation
		return "(" + x + ", " + y + ")";
	}

	public               int compareTo(Point that) {    // compare two points by y-coordinates, breaking ties by x-coordinates
		if (this.y != that.y) return this.y - that.y;
		return this.x - that.x;
	}

	public            double slopeTo(Point that) {      // the slope between this point and that point
		if (this.x == that.x && this.y == that.y) return Double.NEGATIVE_INFINITY;
		if (this.x == that.x) return Double.POSITIVE_INFINITY;
		if (this.y == that.y) return +0.0;
		return (double)(that.y - this.y) / (double)(that.x - this.y);
	}

	public Comparator<Point> slopeOrder() {             // compare two points by slopes they make with this point
		return new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				double s1 = slopeTo(p1);
				double s2 = slopeTo(p2);
				if (s1 > s2) return 1;
				if (s1 < s2) return -1;
				return 0;
			}
		};
	}
}
