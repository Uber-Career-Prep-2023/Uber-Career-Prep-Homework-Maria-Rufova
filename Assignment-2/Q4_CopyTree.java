

/* Question 4: CopyTree
Given a binary tree, create a deep copy. Return the root of the new tree.
"A deep copy of an object is a copy whose properties do not share the same references" - mozilla.org

Plan:
1. Make a Copy blank tree
2. Transverse input bst pre-order
3. Get to the next Node --> make a copy of it --> add it to Copy
4. Return when you went through the entire original bst

Time: O(N)
Space: O(N)
Time taken: 10 minutes
 */

public class Q4_CopyTree extends BinarySearchTree{

    public static Node copyTree(BinarySearchTree bst) {
        if (bst.root == null){
            return null;
        }
        return preOrder(bst.root);
    }

    //helper function that makes a deep copy by transversing the original bst pre-order
    public static Node preOrder (Node node) {
        BinarySearchTree copy = new BinarySearchTree();

        if (node == null) {
            return null;
        }
        copy.root = new Node(node.data);
        copy.root.left = preOrder(node.left);
        copy.root.right = preOrder(node.right);

        return copy.root;
    }

    public static void main(String[] args) {
        //Test 1: Normal bushy BST
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.insert(4);
        bst1.insert(2);
        bst1.insert(6);
        bst1.insert(1);
        bst1.insert(3);
        bst1.insert(5);
        bst1.insert(7);
        bst1.printBST(bst1.root); //4
        System.out.println("Size: " + bst1.size); //size = 7

        System.out.println(copyTree(bst1).data); //root of the copy = 4

        /*Test 2: Empty tree
        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.printBST(bst2.root); //
        System.out.println("Size: " + bst2.size); //size = 0

        System.out.println(copyTree(bst2).data); //root of the copy =

         */

        //Test 3: Spindly tree --> 1 long branch to the right
        BinarySearchTree bst3 = new BinarySearchTree();
        bst3.insert(1);
        bst3.insert(2);
        bst3.insert(3);
        bst3.insert(4);
        bst3.insert(5);
        bst3.printBST(bst3.root); // 1
        System.out.println("Size: " + bst3.size); //size = 5

        System.out.println(copyTree(bst3).data); //root of the copy = 1

    }

}
