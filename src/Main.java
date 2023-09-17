import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
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

        public Node(double longitude, double latitude, String name) {
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

    private static ArrayList<Node> nodes;
    private static HashSet<Node> nodesInTree;

    public static void main(String[] args) {
        nodes = new ArrayList<>();
        nodesInTree = new HashSet<>();
        readCSVFile(INPUT_FILE);
        connectEdges();
    }

    private static void readCSVFile(String filename) {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            while ((line = br.readLine()) != null) {
                makeNode(line);
            }
            br.close();
            Collections.sort(nodes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void makeNode(String line) {
        String splitBy = ",", coords, name;
        String[] college;
        int openDex, spaceDex, closedDex;
        double ptLong, ptLat;
        college = line.split(splitBy);
        coords = college[0];
        openDex = coords.indexOf('(');
        coords = coords.substring(openDex + 1);
        spaceDex = coords.indexOf(' ');
        closedDex = coords.indexOf(')');
        ptLong = Double.parseDouble(coords.substring(0, spaceDex));
        ptLat = Double.parseDouble(coords.substring(spaceDex + 1, closedDex));

        name = college[1];
        if (college.length > 2) {
            name = name + college[2];
        }
        nodes.add(new Node(ptLong, ptLat, name));
    }

    private static void connectEdges() {
        double hawaiiCutOff = -118, degreeDifference, distance;
        int j;
        Node current, connect;
        for (int i = 0; i < nodes.size() - 1; i++) {
            degreeDifference = 10;
            current = nodes.get(i);
            if (i == 0) { // Hawaii
                degreeDifference = hawaiiCutOff - current.longitude;
            }
            j = i + 1;
            connect = nodes.get(j);
            while (connect.longitude <= current.longitude + degreeDifference && j < nodes.size()) {
                connect = nodes.get(j);
                if (connect.latitude >= current.latitude - degreeDifference
                        && connect.latitude <= current.latitude + degreeDifference) {
                    distance = greatCircleDistance(current, connect);
                    current.connections.put(connect, distance);
                    connect.connections.put(current, distance);
                }
                j++;
            }
        }
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
            writeLines(fileWriter);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeLines(FileWriter fileWriter) throws IOException {
        String line = "";
        for (Node node : nodes) {
            for (Node edge : node.tree) {
                line = "\"LINESTRING (" + node.longitude + " " + node.latitude + ", " + edge.longitude + " "
                        + edge.latitude + ")\"," + node.name + " & " + edge.name + ",\n";
            }
            fileWriter.write(line);
        }
    }
}
