import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    class Node implements Comparable<Node> {
        double longitude;
        double latitude;
        HashMap<Node, Double> connections;

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.longitude, other.longitude);
        }
    }

    private static final int DEGREES_PER_RADIAN = 180;
    private static final String INPUT_FILE = "./resources/D1Schools.csv";

    private static TreeSet<Node> nodes;
    private static HashSet<Node> nodesInTree;

    public static void main(String[] args) {
        nodes = new TreeSet<>();
        nodesInTree = new HashSet<>();
        readCSVFile(INPUT_FILE);
    }

    public static void readCSVFile(String filename) {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] college;
            while ((line = br.readLine()) != null) {
                college = line.split(splitBy);
                System.out.print("School location " + college[0] + ", School name " + college[1]);
                if (college.length > 2){
                    System.out.print(", Description " + college[2]);
                }
                System.out.println(" ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
