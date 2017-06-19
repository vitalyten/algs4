import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import java.util.TreeSet;
import java.util.ArrayList;

public class PointSET {
    private TreeSet<Point2D> points;

    public         PointSET() {                              // construct an empty set of points
        points = new TreeSet<Point2D>();
    }

    public           boolean isEmpty() {                     // is the set empty?
        return points.isEmpty();
    }

    public               int size() {                        // number of points in the set
        return points.size();
    }

    public              void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        if (p == null) throw new NullPointerException();
        points.add(p);
    }

    public           boolean contains(Point2D p) {           // does the set contain point p?
        if (p == null) throw new NullPointerException();
        return points.contains(p);
    }

    public              void draw() {                        // draw all points to standard draw
        for (Point2D p : points) {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect) {            // all points that are inside the rectangle
        if (rect == null) throw new NullPointerException();
        ArrayList<Point2D> inside = new ArrayList<Point2D>();
        for (Point2D p : points) {
            if (rect.contains(p)) {
                inside.add(p);
            }
        }
        return inside;
    }

    public           Point2D nearest(Point2D p) {            // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new NullPointerException();
        Point2D np = null;
        double nDist = Double.MAX_VALUE;
        for (Point2D point : points) {
            double dist = point.distanceTo(p);
            if (dist < nDist) {
                nDist = dist;
                np = point;
            }
        }
        return np;
    }

    // public static void main(String[] args)                  // unit testing of the methods (optional)
}
