import java.util.Arrays;
import java.util.ArrayList;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lines;

    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 or more points
        lines = new ArrayList<LineSegment>();
        int len = points.length;
        if (len == 0) throw new IllegalArgumentException();
        Arrays.sort(points);
        for (int i = 0; i < len - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        for (int i = 0; i < len; i++) {
            Arrays.sort(points);
            Point p = points[i];
            Arrays.sort(points, p.slopeOrder());
            for (int next = 1, last = 2; last < len - 1; last++) {
                double s1 = p.slopeTo(points[next]);
                double s2 = p.slopeTo(points[last]);

                while (++last < len && Double.compare(s1, s2) == 0) {
                    s2 = p.slopeTo(points[last]);
                }
                if (last - next > 2 && p.compareTo(points[next]) < 0) {
                    lines.add(new LineSegment(p, points[last - 1]));
                }
                next = last;
            }
        }
    }

    public           int numberOfSegments() {       // the number of line segments
        return lines.size();
    }

    public LineSegment[] segments() {               // the line segments
        return lines.toArray(new LineSegment[lines.size()]);
    }
}
