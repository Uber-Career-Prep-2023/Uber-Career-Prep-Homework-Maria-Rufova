/*Notes
- Not a "first-class" structure --> uses underlying SLL
- "you may copy-paste and reuse any parts of your solution to Question 1"
- Need for Breadth-First Search of Graphs and Trees
- First In First Out
 */

public class Queue extends SingleLinkedList {
    // returns the first item in the queue
    //O(1)
    public int peek() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        return head.data;
    }

    /* add x to the back of the queue
     O(1)
     --> NOTE: re-did SLL insertAtBack() method for this because it originally ran in O(N)
         Now O(1) because I added a tail pointer.
     */
    public void enqueue(int x) {
        this.insertAtBack(x);
    }

    /* removes and returns the first item in the queue
     O(1)
    can't just call removeFirst() SLL method, as it returns the NEW head after removal,
    but here we need the OLD removed one
     */
    public int dequeue() {
        //returns Integer.MIN_VALUE if Queue is empty
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        //remember OLD head and shift the NEW head
        Node curr = head;
        head = head.next;
        //if head is now null, make tail also null (so isEmpty() works)
        if (head == null){
            tail = null;
        }
        return curr.data;
    }
    // returns a boolean indicating whether the queue is empty
    public boolean isEmpty() {
        return head == null && tail == null;
    }

    //main
    public static void main(String args[]) {
        Queue queue = new Queue();
        System.out.println(queue.isEmpty()); //true

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(4);
        System.out.println(queue.isEmpty()); //false
        System.out.println(queue.peek()); //1

        System.out.println(queue.dequeue()); //1
        System.out.println(queue.dequeue()); //2
        System.out.println(queue.peek()); //3
        System.out.println(queue.dequeue()); //3
        System.out.println(queue.dequeue()); //4

        System.out.println(queue.peek()); //Integer.MIN_VALUE
        System.out.println(queue.dequeue()); //Integer.MIN_VALUE
        System.out.println(queue.isEmpty()); //true

    }

}
