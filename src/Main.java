import java.util.ArrayList;

public class Main {
    class Node implements Comparable<Node> {
        double longitude;
        double latitude;

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.longitude, other.longitude);
        }
    }

    class Edge implements Comparable<Edge> {
        Node a;
        Node b;
        double length;

        @Override
        public int compareTo(Edge other) {
            return Double.compare(this.length, other.length);
        }
    }

    private static final int DEGREES_PER_RADIAN = 180;
    private static final String INPUT_FILE = "../resources/D1Schools.csv";

    private static ArrayList<Node> nodes;
    private static ArrayList<Edge> edges;

    public static void main(String[] args) {

    }

    public static void readCSVFile(String filename) {

    }

    public static void connectEdges() {
        // Connect all nodes within certain longitude and latitude range
        // Connect Hawaii to all West Coast schools
    }

    public static double greatCircleDistance(Node A, Node B) {
        double aLat = A.latitude / DEGREES_PER_RADIAN, bLat = B.latitude / DEGREES_PER_RADIAN;
        double aLong = A.longitude / DEGREES_PER_RADIAN, bLong = B.longitude / DEGREES_PER_RADIAN;
        return Math.acos(Math.cos(aLat) * Math.cos(bLat) * Math.cos(aLong - bLong) +
                Math.sin(aLat) * Math.sin(bLat));
    }

    public static void minimumSpanningTree() {
        // Use Prim's algorithm
    }

    public static void writeCSV() {

    }
}