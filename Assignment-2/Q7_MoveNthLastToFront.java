

/* Question 7: MoveNthLastToFront
Given a singly linked list, move the Nth from the last element to the front of the list.

Plan:
1. Make a "Lead" and "Trail" pointer
2. Make Lead go N nodes forward until there is an N gap between lead and trail
3. Increment both pointers until Lead is at the end
4. Nth's node would be Trail.next. Remove it by reassigning Trail's .next to .next.next
5. Insert new head node with N's previous value

Time: O(N)
Space: O(1)
Technique: Fixed-Distance Two-Pointer
Time taken: 35 minutes (took some time to figure out null pointer exception -->
fixed by adding the second if clause)
 */
public class Q7_MoveNthLastToFront extends SingleLinkedList{

    public static void moveNthLastToFront (SingleLinkedList sll, int N) {
        Node lead = sll.head;
        Node trail = sll.head;
        //move lead N spaces to create N-sized gap between pointers
        if (N > sll.length()) {
            System.out.println("Sorry! The list is too small:(");
            return;
        }
        for (int i = 0; i < N; i++){
            lead = lead.next;
        }
        //in case N = length of the sll; prevents null error
        if (lead == null) {
            return;
        }
        //while Lead has not reached the end, increment both pointers
        while (lead.next != null) {
            lead = lead.next;
            trail = trail.next;
        }
        //once you found the Nth node --> remove it and add it to front
        sll.insertAtFront(trail.next.data);
        trail.next = trail.next.next;
    }
    public static void main (String args[]) {

        //Test 1: From spec
        SingleLinkedList sll1 = new SingleLinkedList();
        sll1.insertAtBack(15);
        sll1.insertAtBack(2);
        sll1.insertAtBack(8);
        sll1.insertAtBack(7);
        sll1.insertAtBack(20);
        sll1.insertAtBack(9);
        sll1.insertAtBack(11);
        sll1.insertAtBack(6);
        sll1.insertAtBack(19);

        sll1.printSLL(); // 15 2 8 7 20 9 11 6 19
        moveNthLastToFront(sll1, 2);
        sll1.printSLL(); // 6 15 2 8 7 20 9 11 19

        //Test 2: From spec
        SingleLinkedList sll2 = new SingleLinkedList();
        sll2.insertAtBack(15);
        sll2.insertAtBack(2);
        sll2.insertAtBack(8);
        sll2.insertAtBack(7);
        sll2.insertAtBack(20);
        sll2.insertAtBack(9);
        sll2.insertAtBack(11);
        sll2.insertAtBack(6);
        sll2.insertAtBack(19);

        sll2.printSLL(); // 15 2 8 7 20 9 11 6 19
        moveNthLastToFront(sll2, 7);
        sll2.printSLL(); // 8 15 2 7 20 9 11 6 19

        //Test 3: N = length of sll (testing if it doesn't cause a null error)
        SingleLinkedList sll3 = new SingleLinkedList();
        sll3.insertAtBack(1);
        sll3.insertAtBack(2);
        sll3.insertAtBack(3);
        sll3.insertAtBack(4);
        sll3.insertAtBack(5);

        sll3.printSLL(); // 1 2 3 4 5
        moveNthLastToFront(sll3, 5);
        sll3.printSLL(); // 1 2 3 4 5

        //Test 4: N > length of sll
        SingleLinkedList sll4 = new SingleLinkedList();
        sll4.insertAtBack(1);
        sll4.insertAtBack(2);
        sll4.insertAtBack(3);

        sll4.printSLL(); // 1 2 3
        moveNthLastToFront(sll4, 5); //Sorry! The list is too small:(
        sll4.printSLL(); // 1 2 3

        //Test 5: Emptry sll
        SingleLinkedList sll5 = new SingleLinkedList();
        moveNthLastToFront(sll4, 5); //Sorry! The list is too small:(
        sll5.printSLL();
    }
}
