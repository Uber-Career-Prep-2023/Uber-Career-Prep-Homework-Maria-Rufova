
/* Question 9: DisconnectCycle
Given a singly linked list, disconnect the cycle, if one exists.

Plan:
1. Move Fast pointer by 2 and Slow pointer by 1; if they eventually connect --> there's a cycle
2. Reset the fast pointer to the start and move it until it finds the slow pointer again
3. Disconnect the cycle by assigning fast.next to a null

Time: O(N)
Space: O(1)
Technique: Fast-Slow
Time taken: 40+ minutes (this one was a little weird, took a lot of tries)
Note: not sure how to test
 */
public class Q9_DisconnectCycle extends SingleLinkedList{

    public static void disconnectCycle (SingleLinkedList sll){
        Node fast = sll.head;
        Node slow = sll.head;
        //while fast has not reached the (potential) end
        while (fast != null && fast.next !=null) {
            //move fast 2 steps and slow 1 step
            fast = fast.next.next;
            slow = slow.next;

            //this part was explained to me by a TA
            //if the pointers meet, then there is a cycle. Move slow back to the front,
            //continue incrementing each pointer by 1, and the Node where they reunite is
            //the start of the cycle
            if (fast == slow){
                removeCycle(slow, sll);
            }
        }
    }
    public static void removeCycle(Node slow, SingleLinkedList sll) {
        Node curr = sll.head;
        while (curr != null) {
            Node fast = slow;
            //while fast has not gone through a cycle and met uo with slow again
            while (fast.next != slow && fast.next != curr) {
                //keep looking
                fast = fast.next;
            }
            //if we detected the beginning of a cycle, redirect it to null to make non-cyclical sll
            if (fast.next == curr) {
                fast.next = null;
                return;
            }
            curr = curr.next;
        }
    }

    public static void main (String args[]) {

        //not sure how to best test this
        //Test 1: From spec
        SingleLinkedList sll1 = new SingleLinkedList();
        sll1.insertAtBack(10);
        sll1.insertAtBack(18);
        sll1.insertAtBack(12);
        sll1.insertAtBack(9);
        sll1.insertAtBack(11);
        sll1.insertAtBack(4);

        sll1.printSLL(); //10 18 12 9 11 4

    }
}
