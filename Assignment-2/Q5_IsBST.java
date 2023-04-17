
/* Question 5: IsBST
Given a binary tree, determine if it is a binary search tree.

Plan:
1. Check if each left value is less than it's parent
2. Check if each right value is greater than it's

! there's probably a better way to approach this! please help
Time: O(N)
Space: O(N)
Time taken: 40 minutes (spend a lot of time overthinking initially.)
 */

public class Q5_IsBST extends BinarySearchTree{

    public static void main(String[] args){
        //Test 1: Normal bushy BST
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.insert(4);
        bst1.insert(2);
        bst1.insert(6);
        bst1.insert(1);
        bst1.insert(3);
        bst1.insert(5);
        bst1.insert(7);

        //Test 2: Not actually a BST
        BinarySearchTree notBST = new BinarySearchTree();
        notBST.insert(4);
        notBST.insert(2);
        notBST.insert(6);
        notBST.insert(1);
        notBST.insert(3);
        notBST.insert(5);
        notBST.insert(7);
        //changing things around so it's no longer a BST
        notBST.root = new Node(100);
        notBST.root.left = new Node(222);
        notBST.root.right = new Node(8);


    }

    public static boolean isBST(BinarySearchTree bst) {

        if (bst.root != null) { //if the tree is null --> return true
            return helper(bst.root);
        }
        return true;
    }

    static boolean helper(Node node){
        return true;
    }
}
