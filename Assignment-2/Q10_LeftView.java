import java.lang.reflect.Array;
import java.util.ArrayList;

/* Question 10: LeftView
Given a binary tree, create an array of the left view (leftmost elements in each level) of the tree.

Plan:
1.

Time: O(N)
Space: O(N)
Time taken: 33 minutes
 */
public class Q10_LeftView extends BinarySearchTree {

    public static ArrayList<Integer> leftView(BinarySearchTree bst) {
        //create an ArrayList to store leftmost values
        ArrayList<Integer> lefties = new ArrayList<>();
        //iterate through the tree recursively
        helper(lefties, bst.root, 1);
        return lefties;
    }

    public static void helper(ArrayList<Integer> lefties, Node curr, int currLevel) {

        if (curr == null){
            return;
        }
        //if this level HAS NOT already been visited from the left
        //move one level down and save the root value
        if (lastLevel < currLevel) {
            lefties.add(curr.data);
            lastLevel = currLevel;
        }
        helper(lefties, curr.left, currLevel+1);
        helper(lefties, curr.right, currLevel+1);
    }

    public static void main (String args[]) {
        //Test 1
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.insert(7);
        bst1.insert(8);
        bst1.insert(3);
        bst1.insert(9);
        bst1.insert(13);
        bst1.insert(20);
        bst1.insert(14);
        bst1.insert(15);
        System.out.println(leftView(bst1)); //[7, 3, 9, 13, 20, 14, 15]

        //Test 2
        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.insert(4);
        bst2.insert(2);
        bst2.insert(6);
        bst2.insert(1);
        bst2.insert(3);
        bst2.insert(5);
        bst2.insert(7);
        System.out.println(leftView(bst2)); //[4, 2, 1]

        //Test 3: Empty
        BinarySearchTree bst3 = new BinarySearchTree();
        System.out.println(leftView(bst3)); //[]

    }

}
