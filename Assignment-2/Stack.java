/*Notes
- Not a "first-class" structure --> uses underlying SLL
- "you may copy-paste and reuse any parts of your solution to Question 1"
- Last In First Out
- "You will likely implement  your depth-first traversals of graphs and trees recursively;
however, if you chose to do them iteratively, you would need to use a stack. "
 */

public class Stack extends SingleLinkedList {

    //returns the top item in the stack (peek)
    int top() {
        if (head == null) {
            return Integer.MIN_VALUE;
        }
        return head.data;
    }
    //adds x to the top of the stack
    void push(int x) {
        this.insertAtFront(x);
    }

    //removes and returns the top item in the stack
    //same as Queue --> can't use deleteFront() cause it returns the new head
    int pop() {
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

    //returns a boolean indicating whether the stack is empty
    boolean isEmpty() {
        return head == null && tail == null;
    }

    //main
    public static void main (String args[]) {
        Stack stack = new Stack();
        System.out.println(stack.isEmpty()); //true

        stack.push(1);
        System.out.println(stack.top()); //1
        stack.push(2);
        stack.push(3);
        System.out.println(stack.top()); //3
        stack.printSLL();; //3 2 1

        stack.push(4);
        stack.push(5);
        stack.printSLL();; //5 4 3 2 1

        System.out.println(stack.pop()); //5
        System.out.println(stack.top()); //4
        stack.printSLL(); //4 3 2 1
        System.out.println(stack.isEmpty()); //false

    }
}
