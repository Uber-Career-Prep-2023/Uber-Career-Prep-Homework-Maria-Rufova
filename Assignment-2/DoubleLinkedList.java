public class DoubleLinkedList {

    //instance variables
    private Node head; //head of the linked list
    private int size; //size of the linkedlist

    //constructor
    public DoubleLinkedList() {
        head = null;
        size = 0;
    }

    //main
    public static void main(String[] args) {

        DoubleLinkedList dll = new DoubleLinkedList();
        dll.insertAtFront(5);
        dll.insertAtFront(4);
        dll.insertAtFront(1);
        dll.printSLL(); //1 4 5

        dll.insertAtBack(6);
        dll.insertAtBack(7);
        dll.printSLL(); //1 4 5 6 7

        dll.insertAfter(2, dll.head);
        dll.insertAfter(3, dll.head.next);
        dll.printSLL(); //1 2 3 4 5 6 7
        System.out.println(dll.size); //7

        dll.deleteFront();
        dll.printSLL(); // 2 3 4 5 6 7
        dll.deleteBack();
        dll.printSLL(); // 2 3 4 5 6
        dll.deleteNode(dll.head.next.next);
        dll.printSLL(); // 2 3 5 6
        System.out.println(dll.size); //4

        dll.reverseIterative();
        dll.printSLL(); // 6 5 3 2
        dll.reverseRecursive(dll.head);
        dll.printSLL(); // 2 3 5 6

    }

    //Node class
    public class Node {
        int data;
        Node next;
        Node prev;
        Node (int i) {
            this.data = i;
        }
    }

    /*creates new Node with data val at front; returns new Head
    Time / Space: O(1)
     */
    public Node insertAtFront(int val) {
        Node first = new Node(val);
        if (head == null) {
            head = first;
        } else {
            first.prev = null;
            first.next = head;
            head.prev = first;
            head = first;
        }

        size += 1;
        return head;
    }

    /* creates new Node with data val at end
    Time: O(N) / Space: O(1)
     */
    public void insertAtBack(int val) {
        Node last = new Node(val);
        last.next = null;

        if (head == null) {
            head = last;
        } else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = last;
            last.prev = tail;
        }

        size += 1;
    }

    /* creates new Node with data val after Node loc
    Time / Space : O(1)
     */

    public void insertAfter(int val, Node loc) {
        Node newNode = new Node(val);
        newNode.next = loc.next;
        loc.next = newNode;
        newNode.prev = loc;

        size += 1;
    }

    /* removes first Node; returns new head
    Time / Space: O(1)
     */
    public Node deleteFront() {
        //head.next.prev = head.prev; <-- this made the list circular, which I don't think we need to do?
        head = head.next;
        head.prev = null;
        size -= 1;
        return head;

    }

    /* removes last Node
    Time: O(N) / Space: O(1)
     */

    public void deleteBack() {
        Node curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }
        curr.next = null;
        size -= 1;
    }

    /* deletes Node loc; returns head
    Time: O(N) / Space: O(1)
     */
    public Node deleteNode(Node loc) {

        Node curr = head;

        while (curr != loc) {
            curr = curr.next;
        }
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;

        size -= 1;

        return head;
    }

    /* returns length of the list
    Time / Space : O(1)
     */

    public int length() {
        return size;
    }
    /* reverses the linked list iteratively
    Time: O(N) / Space: O(1)
   spec said this should return Node but which one? head?
    */
    public Node reverseIterative() {

        Node prev = null;
        Node curr = head;

        while (curr != null) {
            prev = curr.prev;
            curr.prev = curr.next;
            curr.next = prev;
            curr = curr.prev;
        }
        head = prev.prev;
        return head;
    }
    /* reverses the linked list recursively (Hint: you will need a helper function)
    Time: O(N) ? / Space: O(1)
     */


    public Node reverseRecursive(Node curr){

        Node rest;

        rest = curr.next;
        curr.next = curr.prev;
        curr.prev = rest;

        if (curr.prev == null){
            return curr;
        }

        return reverseRecursive(curr.prev);
    }


    //////////////// extra ////////////////////

    /*
    prints sll for testing
    Time: O(N) / Space: O(1)
     */
    public void printSLL() {
        String output = "";
        Node curr = head;
        while (curr != null){
            output += curr.data + " ";
            curr = curr.next;
        }
        System.out.println(output);
    }

}