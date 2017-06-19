import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import java.util.LinkedList;

public class KdTree {
    private Node root;
    private int size;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D p, RectHV rect) {
            if (p == null || rect == null) throw new NullPointerException();
            this.p = p;
            this.rect = rect;
        }
    }

    public         KdTree() {                              // construct an empty set of points
        root = null;
        size = 0;
    }

    public           boolean isEmpty() {                     // is the set empty?
        return size == 0;
    }

    public               int size() {                        // number of points in the set
        return size;
    }

    public              void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        if (p == null) throw new NullPointerException();
        root = insert(root, p, 0.0, 0.0, 1.0, 1.0, true);
    }

    private Node insert(Node n, Point2D p, double x1, double y1, double x2, double y2, boolean vertical) {             // add the point to the set (if it is not already in the set)1root = insert();
        if (n == null) {
            RectHV rect = new RectHV(x1, y1, x2, y2);
            size++;
            return new Node(p, rect);
        } else if (p.equals(n.p)) {
            return n;
        }
        if (vertical) {
            double cmp = p.x() - n.p.x();
            if (cmp < 0) {
                n.lb = insert(n.lb, p, x1, y1, n.p.x(), y2, !vertical);
            } else {
                n.rt = insert(n.rt, p, n.p.x(), y1, x2, y2, !vertical);
            }
        } else {
            double cmp = p.y() - n.p.y();
            if (cmp < 0) {
                n.lb = insert(n.lb, p, x1, y1, x2, n.p.y(), !vertical);
            } else {
                n.rt = insert(n.rt, p, x1, n.p.y(), x2, y2, !vertical);
            }
        }
        return n;
    }

    public           boolean contains(Point2D p) {           // does the set contain point p?
        if (p == null) throw new NullPointerException();
        return contains(root, p, true);
    }

    private           boolean contains(Node n, Point2D p, boolean vertical) {           // does the set contain point p?
        double cmp;

        if (n == null) return false;
        if (p.equals(n.p)) return true;
        if (vertical) {
            cmp = p.x() - n.p.x();
        } else {
            cmp = p.y() - n.p.y();
        }
        if (cmp < 0) {
            return contains(n.lb, p, !vertical);
        } else {
            return contains(n.rt, p, !vertical);
        }
    }

    public              void draw() {                        // draw all points to standard draw
        draw(root);
    }

    private void draw(Node n) {
        if (n == null) return;
        StdDraw.filledCircle(n.p.x(), n.p.y(), 0.002);
        draw(n.lb);
        draw(n.rt);
    }

    public Iterable<Point2D> range(RectHV rect) {            // all points that are inside the rectangle
        if (rect == null) throw new NullPointerException();
        LinkedList<Point2D> list = new LinkedList<Point2D>();
        range(root, rect, list);
        return list;
    }

    private void range(Node n, RectHV rect, LinkedList<Point2D> list) {            // all points that are inside the rectangle
        if (n == null) return;
        if (rect.contains(n.p)) list.add(n.p);
        if (rect.intersects(n.rect)) {
            range(n.lb, rect, list);
            range(n.rt, rect, list);
        }
    }

    public           Point2D nearest(Point2D p) {            // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null) throw new NullPointerException();
        if (root == null) return null;
        return nearest(root, p, root.p, true);
    }

    private           Point2D nearest(Node n, Point2D p, Point2D nearest, boolean vertical) {            // a nearest neighbor in the set to point p; null if the set is empty
        if (n == null) return nearest;
        if (p.distanceTo(n.p) < p.distanceTo(nearest)) nearest = n.p;
        if (n.rect.distanceTo(p) < p.distanceTo(nearest)) {
            if ((vertical && (p.x() < n.p.x())) || (!vertical && (p.y() < n.p.y()))) {
                nearest = nearest(n.lb, p, nearest, !vertical);
                nearest = nearest(n.rt, p, nearest, !vertical);
            } else {
                nearest = nearest(n.rt, p, nearest, !vertical);
                nearest = nearest(n.lb, p, nearest, !vertical);
            }
        }
        return nearest;
    }

    // public static void main(String[] args)                  // unit testing of the methods (optional)
}
