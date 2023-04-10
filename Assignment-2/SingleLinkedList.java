public class SingleLinkedList {

    //instance variables
    private Node head; //head of the linked list
    private int size; //size of the linkedlist
    
    //main
    public static void main(String[] args) {

        SingleLinkedList sll = new SingleLinkedList;

    }

    //Node class
    private class Node {
        int data;
        Node next;
        Node (int i) {
            this.data = i;
            this.next = null;
        }
    }

    //creates new Node with data val at front; returns new Head
    public Node insertAtFront(Node head, int val) {

        Node first = new Node(val);
        if (head == null) {
            this.head = first;
        } else {
            first.next = head;
            this.head = first;
        }
        return this.head;
    }
    
}