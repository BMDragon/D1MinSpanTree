public class Main {
    class Node {
        double longitude;
        double latitude;
    }

    class Edge {
        Node a;
        Node b;
    }

    private static final int DEGREES_PER_RADIAN = 180;

    public static void main(String[] args) {

    }

    public static double greatCircleDistance(Node A, Node B) {
        double aLat = A.latitude / DEGREES_PER_RADIAN, bLat = B.latitude / DEGREES_PER_RADIAN;
        double aLong = A.longitude / DEGREES_PER_RADIAN, bLong = B.longitude / DEGREES_PER_RADIAN;
        return Math.acos(Math.cos(aLat) * Math.cos(bLat) * Math.cos(aLong - bLong) +
                Math.sin(aLat) * Math.sin(bLat));
    }
}