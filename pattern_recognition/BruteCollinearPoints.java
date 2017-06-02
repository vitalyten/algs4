import java.util.Arrays;
import java.util.ArrayList;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lines;

    public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
        lines = new ArrayList<LineSegment>();
        int len = points.length;
        if (len == 0) throw new IllegalArgumentException();
        Arrays.sort(points);
        for (int i = 0; i < len - 1; i++) {
            if (points[i].compareTo(points[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        ArrayList<LineSegment> valid = new ArrayList<LineSegment>();
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                double slopeIJ = points[i].slopeTo(points[j]);
                for (int k = j + 1; k < len - 1; k++) {
                    if (slopeIJ == points[i].slopeTo(points[k])) {
                        for (int l = k + 1; l < len; l++) {
                            if (slopeIJ == points[i].slopeTo(points[l])) {
                                lines.add(new LineSegment(points[i], points[l]));
                            }
                        }
                    }
                }
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
