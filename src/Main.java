import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    static class Node implements Comparable<Node> {
        double longitude;
        double latitude;
        String name;
        HashMap<Node, Double> connections;
        HashSet<Node> tree;

        public Node(double longitude, double latitude, String name){
            this.longitude = longitude;
            this.latitude = latitude;
            this.name = name;
            connections = new HashMap<>();
            tree = new HashSet<>();
        }

        @Override
        public int compareTo(Node other) {
            return Double.compare(this.longitude, other.longitude);
        }
    }

    private static final int DEGREES_PER_RADIAN = 180;
    private static final String INPUT_FILE = "./resources/D1Schools.csv";
    private static final String SAVE_FILE = "./resources/D1MinSpanTree.csv";

    private static TreeSet<Node> nodes;
    private static HashSet<Node> nodesInTree;

    public static void main(String[] args) {
        nodes = new TreeSet<>();
        nodesInTree = new HashSet<>();
        readCSVFile(INPUT_FILE);
    }

    private static void readCSVFile(String filename) {
        String line = "";
        String splitBy = ",";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String[] college;
            while ((line = br.readLine()) != null) {
                college = line.split(splitBy);
                System.out.print("School location " + college[0] + ", School name " + college[1]);
                if (college.length > 2) {
                    System.out.print(", Description " + college[2]);
                }
                System.out.println(" ");
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void connectEdges() {
        // Connect all nodes within certain longitude and latitude range
        // Connect Hawaii to all West Coast schools
    }

    private static double greatCircleDistance(Node A, Node B) {
        double aLat = A.latitude / DEGREES_PER_RADIAN, bLat = B.latitude / DEGREES_PER_RADIAN;
        double aLong = A.longitude / DEGREES_PER_RADIAN, bLong = B.longitude / DEGREES_PER_RADIAN;
        return Math.acos(Math.cos(aLat) * Math.cos(bLat) * Math.cos(aLong - bLong) +
                Math.sin(aLat) * Math.sin(bLat));
    }

    private static void minimumSpanningTree() {
        // Use Prim's algorithm
    }

    private static void writeCSV(String saveFile) {
        File csvOutput = new File(saveFile);
        try {
            FileWriter fileWriter = new FileWriter(csvOutput);
            fileWriter.write("WKT,name,description\n");
            String line = "";
            writeLine(fileWriter, line);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLine(FileWriter fileWriter, String line) throws IOException {
        for (Node node : nodes) {
            for (Node edge : node.tree) {
                line = "\"LINESTRING (" + node.longitude + " " + node.latitude + ", " + edge.longitude + " "
                        + edge.latitude + ")\"," + node.name + "-" + edge.name + ",\n";
            }
            fileWriter.write(line);
        }
    }
}
