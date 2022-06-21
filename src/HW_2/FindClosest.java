package HW_2;



import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;

public class FindClosest {

    private PointPair closestPointPair;
    private final QuickSort quicksort = new QuickSort();

    /**
     * Constructor
     *
     * @param points --> point array
     */
    public FindClosest(Point2D.Double[] points) {
        //Sort points by X coordinate
        quicksort.sort(points, 0, points.length - 1, "compareX");

        this.closestPointPair = calculateClosestPointPair(points, 0, points.length - 1);

        //*********************************do nothing***************************************//
    }

    /**
     * Get closest Point Pair
     *
     * @return closestPointPair
     */
    public PointPair getClosestPointPair() {
        return this.closestPointPair;
    }

    /**
     * Main method for calculate and return closest point pair
     *
     * @param p          --> point array
     * @param startIndex --> First index of p[]
     * @param lastIndex  --> last index of p[]
     * @return
     */
    private PointPair calculateClosestPointPair(Point2D.Double[] p, int startIndex, int lastIndex) {

        int n = lastIndex - startIndex;
        int mid = n / 2;

        int size = p.length;

        if (size == 3) {
            return getClosestPointPair(p[0], p[1], p[2]);
        }
        if (size<3) {
            return getClosestPointPair(p[0], new Point2D.Double(), p[1]);
        }

        Point2D.Double[] left = Arrays.copyOfRange(p, 0, p.length / 2);
        Point2D.Double[] right = Arrays.copyOfRange(p, p.length / 2, p.length);




        PointPair pairLeft = calculateClosestPointPair(left, 0, left.length-1);
        PointPair pairRight = calculateClosestPointPair(right, 0, right.length-1);

        PointPair pointPair = getClosestPointPair(pairLeft, pairRight);

        Point2D.Double strip[] = new Point2D.Double[n];
        int j = 0;
        for (int i = 0; i < n; i++) {

            if (Math.abs(p[i].getX() - p[mid].getX()) < pointPair.getDistance()) {
                strip[j] = p[i];
                j++;
            }
        }
        return getClosestPointPair(pointPair, stripClosest(strip, j, pointPair));
    }

    /**
     * calculate and return closest point pair from 3 points
     *
     * @param p1 --> point 1
     * @param p2 --> point 2
     * @param p3 --> point 3
     * @return
     */
    private PointPair getClosestPointPair(Point2D.Double p1, Point2D.Double p2, Point2D.Double p3) {
        PointPair pair1_2 = new PointPair(p1, p2);
        PointPair pair1_3 = new PointPair(p1, p3);
        PointPair pair2_4 = new PointPair(p2, p3);
        return getClosestPointPair(pair1_2, getClosestPointPair(pair1_3, pair2_4));
    }

    private PointPair getClosestPointPair(PointPair p1, PointPair p2) {
        if (p1.getDistance() < p2.getDistance()) {
            return p1;
        }
        else
            return p2;
    }

    /**
     * A utility function to find the distance between the closest points of
     * strip of given size. All points in strip[] are sorted according to
     * y coordinate. They all have an upper bound on minimum distance as d.
     * Note that this method seems to be a O(n^2) method, but it's a O(n)
     * method as the inner loop runs at most 6 times
     *
     * @param strip        --> point array
     * @param size         --> strip array element count
     * @param shortestLine --> shortest line calculated so far
     * @return --> new shortest line
     */
    private PointPair stripClosest(Point2D.Double strip[], int size, PointPair shortestLine) {

        PointPair closestPair = shortestLine;
        for (int i = 0; i < size - 1; i++) {
            for (int j = i + 1; j < size ; j++) {
                if ((strip[j].getY() - strip[i].getY()) < shortestLine.getDistance()) {
                    PointPair strippedPair = new PointPair(strip[i], strip[j]);
                    if (strippedPair.getDistance() <= closestPair.getDistance())
                        closestPair = strippedPair;
                }
            }
        }
        return closestPair;
    }

}