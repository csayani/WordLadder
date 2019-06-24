import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

public class Graph{
    public int edges;
    public int vertices;
    public Node origin;
    public ArrayList<String> dictArray;

    public Graph(){
        // graph constructor
        this.origin = null;

        this.edges = 0;
        this.vertices = 0;
        this.dictArray = new ArrayList<String>();
    }

    public void addVector(Node n){

        makeNeighbors(n);
        boolean stop = false;
        if (this.origin == null){
            this.origin = n;
        }
        else if (this.origin.next == null){
            this.origin.setNext(n);
        }
        else {
            Node lastNode = iterateToEnd();
            lastNode.setNext(n);
        }
        this.vertices = this.vertices + 1;
    }

    public Node iterateToEnd(){
        Node curNode = this.origin;
        while (curNode.next != null){
            curNode = curNode.next;
        }
        return curNode;
    }

    public boolean contains(Node node){
        Node curNode = this.origin;
        while (curNode.next != null){
            if (curNode.getWord().equals(node.getWord().toLowerCase())){
                return true;
            }
            else{
            curNode = curNode.next;
            }

        }
        return false;
    }

    public int numberOfEdges(){
        return this.edges;
    }
    public int numberOfVertices(){
        return this.vertices;
    }


    public void readFile(int wordLength){
        File dictFile = new File("dictionary.txt");
        Scanner scanner = null;
        try {
            scanner = new Scanner(dictFile);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.exit(1);
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            for (String word : words){
                if (word.length() == wordLength){
                    this.dictArray.add(word.toLowerCase());
                }
            }
        }
        scanner.close();
    }

    public boolean isOneOff(String str1, String str2){
        int counter = 0;
        for (int i = 0; i < str1.length(); i++){
            if (str1.charAt(i) == str2.charAt(i)){
                ;
            }
            else{
                counter ++;
            }
        }
        if (counter == 1){
            return true;
        }
        return false;
    }

    public void makeNeighbors(Node n){
        ArrayList<String> neighborArray = new ArrayList<String>();
        for (int i = 0; i < this.dictArray.size(); i++){
            if (isOneOff(n.getWord(), this.dictArray.get(i).toLowerCase())){
                Node newNode = new Node(this.dictArray.get(i));
                n.addToNeighbors(newNode);
            }
        }

    }

    public void traverseCounts(){
        Node curNode = this.origin;
        int counter = 1;
        while (curNode.next != null){
            for (Node node : curNode.getNeighbors()){
                if (node.getCount() == 0){
                    node.setParent(curNode);
                    node.setCount(counter);
                }
                else if (node.getCount() < counter){
                    ;
                }
                else{
                    node.setParent(curNode);
                    node.setCount(counter);
                }
            }
            counter = counter + 1;
            curNode = curNode.next;
        }
    }

    public Node getNode(String s){
        Node curNode = this.origin;
        while (curNode != null){

            if (curNode.getWord().equals(s)){
                return curNode;
            }
            else{
                curNode = curNode.next;
            }

        }
        return this.origin;
    }

    public ArrayList<Node> findPath(String s){
        ArrayList<Node> pathList = new ArrayList<Node>();
        Node curNode = getNode(s);
        while (!curNode.getWord().equals(this.origin.getWord())){
            pathList.add(curNode);
            curNode = curNode.prevNode;
        }
        pathList.add(this.origin);
        return pathList;

    }

    public void printWords(ArrayList<Node> list){
        for (int i = list.size()-1; i >= 0; i--){
            System.out.println(list.get(i));
        }
    }



}
