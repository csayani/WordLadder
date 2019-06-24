import java.util.*;

public class Node{
  Node prevNode;
  String word;
  int count;
  ArrayList<Node> neighbors;
  Node next;

  public Node(String startWord){
    this.word = startWord;
    this.neighbors = new ArrayList<Node>();
    this.next = null;
    this.prevNode = null;
    this.count = 0;
  }

  public Node getPrevNode(){
    return this.prevNode;
  }

  public ArrayList<Node> getNeighbors(){
    return this.neighbors;
  }

  public String getWord(){
    return this.word;
  }

  public int getCount(){
    return this.count;
  }

  public Node getNext(){
    return this.next;
  }

  public void setParent(Node n){
    this.prevNode = n;
  }

  public void setCount(int i){
    this.count = i;
  }

  public void addToNeighbors(Node n){
    this.neighbors.add(n);
  }

  public void setNext(Node n){
    this.next = n;
  }

  public String toString(){
      return this.word;
  }

}
