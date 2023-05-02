
/* Question 8: IsPalindrome
Given a doubly linked list, determine if it is a palindrome.

Plan:
1. Assign Front and Back pointers
2. Front moves toward the middle from the beginning, Back moved from the end until they meet
3. If their values differ along the way return false

Time: O(N)
Space: O(1)
Technique: Doubly Linked-List Forward-Backward Two-Pointer
Time taken: 19 minutes
 */
public class Q8_IsPalindrome extends DoubleLinkedList{

    public static boolean IsPalindrome(DoubleLinkedList dll){
        if (dll.head == null) {
            return true;
        }
        Node front = dll.head;
        Node back = dll.head;
        //move back pointer to the end
        while (back.next != null){
            back = back.next;
        }
        //while the nodes have not met in the middle
        while (front != back) {
            if (front.data != back.data) {
                return false;
            }
            front = front.next;
            back = back.prev;
        }
        return true;
    }

    public static void main (String args[]) {
        //Test 1: From spec
        DoubleLinkedList dll1 = new DoubleLinkedList();
        dll1.insertAtBack(9);
        dll1.insertAtBack(2);
        dll1.insertAtBack(4);
        dll1.insertAtBack(2);
        dll1.insertAtBack(9);
        System.out.println(IsPalindrome(dll1)); //true

        //Test 2: From spec
        DoubleLinkedList dll2 = new DoubleLinkedList();
        dll2.insertAtBack(9);
        dll2.insertAtBack(12);
        dll2.insertAtBack(4);
        dll2.insertAtBack(2);
        dll2.insertAtBack(9);
        System.out.println(IsPalindrome(dll2)); //false

        //Test 3: Empty
        DoubleLinkedList dll3 = new DoubleLinkedList();
        System.out.println(IsPalindrome(dll3)); //true

        //Test 4: Even length palindrome
        DoubleLinkedList dll4 = new DoubleLinkedList();
        dll4.insertAtBack(45);
        dll4.insertAtBack(92);
        dll4.insertAtBack(1);
        dll4.insertAtBack(1);
        dll4.insertAtBack(92);
        dll4.insertAtBack(45);
        System.out.println(IsPalindrome(dll4)); //true

    }
}
