import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;
public class Ladder{


    public Ladder(){
    }
    public static void makeGraph(Graph graph, String stopWord){
        Node stopNode = new Node(stopWord);
        Node curNode = graph.origin;
        while(!graph.contains(stopNode)){
            for (Node neighbor : curNode.getNeighbors()){
                graph.addVector(neighbor);
            }
            curNode = curNode.next;
        }
    }

    public static void main(String[] args){
        Graph graph = new Graph();
        graph.readFile(args[0].length());
        Node originNode = new Node(args[0]);
        graph.addVector(originNode);
        makeGraph(graph, args[1]);
        graph.traverseCounts();
        ArrayList<Node> list = graph.findPath(args[1]);
        graph.printWords(list);

    }
}
