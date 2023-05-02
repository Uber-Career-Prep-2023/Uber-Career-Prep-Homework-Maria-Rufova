
/* Question 6: DedupSortedList
Given a sorted singly linked list, remove any duplicates so that no value appears more than once.

Plan:
1.Have a curr node and its next
2. If curr and next are same, reassign curr's next pointer to the following value
3. Otherwise, keep looking through the list

Time: O(N)
Space: O(1)
Time taken: 10 minutes (we did this in class before:)
 */

public class Q6_DedupSortedList extends SingleLinkedList{

    public static void dedupSortedList(SingleLinkedList sll) {
        Node curr = sll.head;
        //while you have not run out of nodes
        while (curr != null && curr.next != null) {
            //if curr is followed by a duplicate
            if (curr.data == curr.next.data) {
                //remove the duplicate by re-appointing next
                curr.next = curr.next.next;
            } else {
                //if no duplicates, keep looking at the next node
                curr = curr.next;
            }
        }
    }

    public static void main(String[] args) {

        //tests. Important: spec specifies that linked lists are sorted
        //Test 1: No duplicates SLL
        SingleLinkedList sll1 = new SingleLinkedList();
        for (int i = 0; i <10; i++){
            sll1.insertAtBack(i);
        }
        dedupSortedList(sll1);
        sll1.printSLL(); //  0 1 2 3 4 5 6 7 8 9

        //Test 2: Empty SLL
        SingleLinkedList sll2 = new SingleLinkedList();
        dedupSortedList(sll2);
        sll2.printSLL(); //

        //Test 3: A bunch of random duplicates
        SingleLinkedList sll3 = new SingleLinkedList();
        sll3.insertAtBack(1);
        sll3.insertAtBack(1);
        sll3.insertAtBack(2);
        sll3.insertAtBack(2);
        sll3.insertAtBack(3);
        sll3.insertAtBack(4);
        sll3.insertAtBack(4);
        sll3.insertAtBack(4);
        sll3.insertAtBack(4);
        sll3.insertAtBack(5);
        sll3.insertAtBack(5);
        sll3.printSLL();  // 1 1 2 2 3 4 4 4 4 5 5
        dedupSortedList(sll3);
        sll3.printSLL(); // 1 2 3 4 5

        //Test 4:Same number SLL
        SingleLinkedList sll4 = new SingleLinkedList();
        for (int i = 0; i < 5; i++){
            sll4.insertAtBack(7);
        }
        sll4.printSLL(); // 7 7 7 7 7
        dedupSortedList(sll4);
        sll4.printSLL(); // 7

        //Test 5: Only one node SLL
        SingleLinkedList sll5 = new SingleLinkedList();
        sll5.insertAtBack(2);
        dedupSortedList(sll5);
        sll5.printSLL(); // 2

        //Test 6: Negatives SLL
        SingleLinkedList sll6 = new SingleLinkedList();
        sll6.insertAtBack(-9);
        sll6.insertAtBack(-8);
        sll6.insertAtBack(-7);
        sll6.insertAtBack(-7);
        sll6.insertAtBack(-7);
        sll6.insertAtBack(-6);
        sll6.insertAtBack(-3);
        sll6.insertAtBack(-3);
        sll6.insertAtBack(-2);
        sll6.insertAtBack(-2);
        sll6.insertAtBack(-2);
        sll6.printSLL();  // -9 -8 -7 -7 -7 -6 -3 -3 -2 -2 -2
        dedupSortedList(sll6);
        sll6.printSLL(); // -9 -8 -7 -6 -3 -2
    }
}
