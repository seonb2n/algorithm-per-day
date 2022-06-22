import java.util.*;

class Solution {

    public static void main(String[] args) {
        Point2D[] a = new Point2D[7];
        a[0] = new Point2D(0, 0);
        a[1] = new Point2D(1, 1);
        a[2] = new Point2D(2, 2);
        a[3] = new Point2D(3, 3);
        a[4] = new Point2D(3, 2);
        a[5] = new Point2D(4, 2);
        a[6] = new Point2D(5, 1);

        solution(a);
    }

    public static int solution(Point2D[] A) {

        int result = 0;

        for (int i = 0; i < A.length; i++) {
            Point2D firstPoint = A[i];
            for (int j = i+1; j < A.length; j++) {
                Point2D secondPoint = A[j];
                for (int k = j+1; k < A.length; k++) {
                    Point2D thirdPoint = A[k];
                    if(isCollinear(firstPoint, secondPoint, thirdPoint)) {
                        result++;
                        if(result > 100_000_000) {
                            return -1;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static boolean isCollinear(Point2D a, Point2D b, Point2D c) {
        if(a.y == b.y && b.y == c.y) {
            return true;
        }
        if(a.x == b.x && b.x == c.x) {
            return true;
        }
        if(b.y == a.y || a.y == c.y || b.y == c.y) {
            return false;
        }
        if((b.x - a.x) / (b.y - a.y) == (c.x - b.x) / (c.y - b.y)) {
            return true;
        }
        return false;
    }

    public static class Point2D {
        int x;
        int y;

        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
