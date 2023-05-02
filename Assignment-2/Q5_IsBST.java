
/* Question 5: IsBST
Given a binary tree, determine if it is a binary search tree.

Plan:
1. Check if the Node is empty (if it is --> this is BST)
2. Check root is greater than its left child and smaller than its right (if not --> return false)
3. Else keep iterating down the branches, adjusting for the min or max value
Time: O(N)
Space: O(N)
Time taken: 40 minutes (spend a lot of time overthinking initially.)
 */

public class Q5_IsBST extends BinarySearchTree{

    public static boolean isBST(BinarySearchTree bst) {
        //got a hint to use MIN and MAX from office hours
        return helper(bst.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    static boolean helper(Node root, int min, int max){
        //emptry root = empty tree = technically a bst
        if (root == null) {
            return true;
            //if root is less than its left child or greater than its right child = wrong!
        } else if (root.data <= min || root.data >= max) {
            return false;
        }
        //else keep going down the branches
        return helper(root.left, min, root.data) && helper(root.right, root.data, max);
    }

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
        System.out.println(isBST(bst1)); //true

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
        System.out.println(isBST(notBST)); //false

        //Test 3: An Empty Tree
        BinarySearchTree empty = new BinarySearchTree();
        System.out.println(isBST(empty)); //true

        //Test 4: Spindly BST
        BinarySearchTree bst4 = new BinarySearchTree();
        bst4.insert(1);
        bst4.insert(2);
        bst4.insert(3);
        bst4.insert(4);
        bst4.insert(5);
        bst4.insert(6);
        bst4.insert(7);
        System.out.println(isBST(bst4)); //true

    }
}
