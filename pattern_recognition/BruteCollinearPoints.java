import java.util.*;

public class BruteCollinearPoints {
    private LineSegment[] lines;

    public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
        int len = points.length;
        Arrays.sort(points);
        for (int i = 0; i < len - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        ArrayList<LineSegment> valid = new ArrayList<LineSegment>();
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                for (int k = j + 1; k < len - 1; k++) {
                    for (int l = k + 1; l < len; l++) {
                        if (points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) &&
                            points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])) {
                            valid.add(new LineSegment(points[i], points[l]));
                        }
                    }
                }
            }
        }
        lines = valid.toArray(new LineSegment[valid.size()]);
    }

    public           int numberOfSegments() {       // the number of line segments
        return lines.length;
    }

    public LineSegment[] segments() {               // the line segments
        return Arrays.copyOf(lines, lines.length);
    }
}
