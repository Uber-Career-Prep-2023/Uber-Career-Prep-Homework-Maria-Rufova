
/* Question 11: FloorInBST
Given a target numeric value and a binary search tree, return the floor (greatest element less than or equal to the target) in the BST.

Plan:
1. Get root value
2. If target < root, go left
3. If target > root, go right

Time: O(N)
Space: O(1)
Time taken: 25 minutes
 */
public class Q11_FloorInBST extends BinarySearchTree{

    public static int floorInBST(int target, Node root) {

        if (root == null){
            return -1;
        }

        int best = root.data;
        if (root.data == target){
            return root.data;
        } //target is bigger --> go right
        else if (root.data < target) {
            best = floorInBST(target, root.right);
        } else { //target is smaller --> go left
            best = floorInBST(target, root.left);
        }

        if(best == -1) {
            return root.data;
        }
        else if (best > target) { //if no values are less than or equal to target (Test 5)
            return -1;
        }
        else {
            return best;
        }
    }
    public static void main (String args[]) {
        //Test 1: From the spec
        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.insert(10);
        bst1.insert(8);
        bst1.insert(16);
        bst1.insert(9);
        bst1.insert(13);
        bst1.insert(17);
        bst1.insert(20);
        System.out.println(floorInBST(13, bst1.root)); //13

        //Test 2: From the spec
        BinarySearchTree bst2 = new BinarySearchTree();
        bst2.insert(10);
        bst2.insert(8);
        bst2.insert(16);
        bst2.insert(9);
        bst2.insert(13);
        bst2.insert(17);
        bst2.insert(20);
        System.out.println(floorInBST(15, bst2.root)); //13

        //Test 3: Empty BST
        BinarySearchTree bst3 = new BinarySearchTree();
        System.out.println(floorInBST(15, bst3.root)); //-1

        //Test 4: Spindly BST - exact value
        BinarySearchTree bst4 = new BinarySearchTree();
        bst4.insert(1);
        bst4.insert(2);
        bst4.insert(3);
        bst4.insert(4);
        bst4.insert(5);
        bst4.insert(6);
        bst4.insert(7);
        System.out.println(floorInBST(7, bst4.root)); //7

        //Test 4: Spindly BST - not exact value
        BinarySearchTree bst5 = new BinarySearchTree();
        bst5.insert(1);
        bst5.insert(2);
        bst5.insert(3);
        bst5.insert(4);
        bst5.insert(5);
        bst5.insert(6);
        bst5.insert(7);
        System.out.println(floorInBST(9, bst5.root)); //7

        //Test 5: Normal bushy BST - no values less than or equal to target
        BinarySearchTree bst6 = new BinarySearchTree();
        bst6.insert(14);
        bst6.insert(12);
        bst6.insert(16);
        bst6.insert(11);
        bst6.insert(13);
        bst6.insert(15);
        bst6.insert(17);
        System.out.println(floorInBST(5, bst6.root)); //-1

    }
}
