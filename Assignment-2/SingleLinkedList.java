public class SingleLinkedList {

    //instance variables
    public Node head; //head of the linked list
    public Node tail; //tail of the linked list
    public int size; //size of the linked list

    //single
    public SingleLinkedList() {
        head = null;
        size = 0;
    }

    //Node class
    public class Node {
        int data;
        Node next;
        Node (int i) {
            this.data = i;
            this.next = null;
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
            first.next = head;
            head = first;
        }

        size += 1;
        return head;
    }

    /* creates new Node with data val at end
    Time: O(N) --> IMPROVED to O(1)
    Space: O(1)
     */
    public void insertAtBack(int val) {
        Node last = new Node(val);
        if (head == null) {
            head = tail = last;
        } else {
            tail.next = last;
            tail = last;

            /* OLD - O(N) - iterates over the whole list

            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = last;

             */
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

        size += 1;
    }

    /* removes first Node; returns new head
    Time / Space: O(1)
     */
    public Node deleteFront() {
        if (head == null) {
            return null;
        }
        head = head.next;
        size -= 1;
        return head;

    }

    /* removes last Node
    Time: O(N) / Space: O(1)
     */

    public void deleteBack() {
        Node prev = null;
        Node curr = head;
        while (curr.next != null) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = null;
        size -= 1;
    }

    /* deletes Node loc; returns head
    Time: O(N) / Space: O(1)
     */
    public Node deleteNode(Node loc) {

        Node prev = null;
        Node curr = head;

        while (curr != loc) {
            prev = curr;
            curr = curr.next;
        }
        prev.next = curr.next;
        curr.next = null;
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
        Node next = null;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        return head;
    }
    /* reverses the linked list recursively (Hint: you will need a helper function)
    Time: O(N) ? / Space: O(1)
     */

    public Node reverseRecursive(Node curr){

        Node rest;
        if (curr.next == null || curr == null){
            return curr;
        }
        rest = reverseRecursive(curr.next);
        curr.next.next = curr;
        curr.next = null;

        return rest;
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


    //main
    public static void main(String[] args) {

        SingleLinkedList sll = new SingleLinkedList();
        sll.insertAtFront(5);
        sll.insertAtFront(4);
        sll.insertAtFront(1);
        sll.printSLL(); //1 4 5

        sll.insertAtBack(6);
        sll.insertAtBack(7);
        sll.printSLL(); //1 4 5 6 7

        sll.insertAfter(2, sll.head);
        sll.insertAfter(3, sll.head.next);
        sll.printSLL(); //1 2 3 4 5 6 7
        System.out.println(sll.size); //7

        sll.deleteFront();
        sll.printSLL(); // 2 3 4 5 6 7
        sll.deleteBack();
        sll.printSLL(); // 2 3 4 5 6
        sll.deleteNode(sll.head.next.next);
        sll.printSLL(); // 2 3 5 6
        System.out.println(sll.size); //4

        sll.reverseIterative();
        sll.printSLL(); // 6 5 3 2
        sll.reverseRecursive(sll.head);
        sll.printSLL(); // 2 3 5 6

    }


}