public class BinarySearchTree {
    Node root;
    int size;

    public BinarySearchTree() { //do we always need a constructor?
        root = null;
        size = 0;
    }

    public static void main(String[] args) {
        //test the bst here
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(4);
        bst.printBST(bst.root); //4
        System.out.println("Size: " + bst.size); //size = 1

        bst.insert(2);
        bst.insert(6);
        bst.insert(1);
        bst.insert(3);
        bst.insert(5);
        bst.insert(7);
        bst.printBST(bst.root); // 1 2 3 4 5 6 7
        System.out.println("Size: " + bst.size); //size = 7

        System.out.println(bst.max()); //max = 7
        System.out.println(bst.min()); //min = 1

        System.out.println(bst.contains(5)); //true
        System.out.println(bst.contains(2146836487)); //false
        System.out.println(bst.contains(0)); //false
        System.out.println(bst.contains(3)); //true

        bst.delete(3); //case 1: delete Node w/ 0 children
        bst.delete(2); //case 2: 1 child
        bst.delete(6); //case 3: 2 children
        bst.printBST(bst.root); // 1 4 5 7
        System.out.println("Size: " + bst.size); //size = 4


    }

    public class Node {
        int data;
        Node left;
        Node right;
        Node (int i) {
            this.data = i;
        }
    }

    // returns the minimum value in the BST
    public int min() { //--> go as left as you can
        Node temp = root;
        int min = temp.data;
        while (temp.left != null) {
            min = temp.left.data;
            temp = temp.left;
        }
        return min;
    }
    // returns the maximum value in the BST
    public int max() { //--> go as far right as you can
        Node temp = root;
        int max = temp.data;
        while (temp.right != null) {
            max = temp.right.data;
            temp = temp.right;
        }
        return max;
    }



    // returns a boolean indicating whether val is present in the BST
    public boolean contains(int val) {
        return containsHelper(root, val) != null;
    }

    public Node containsHelper(Node root, int val) {
        if (root == null || root.data == val) {
            return root;
        } else if (root.data < val) {
            //Root is smaller --> go right to search among bigger vals
            return containsHelper(root.right, val);
        }
        //Root is larger --> go left to search among smaller vals
        return containsHelper(root.left, val);
    }


    // For simplicity, do not allow duplicates. If val is already present, insert is a no-op
    // creates a new Node with data val in the appropriate location
    public void insert(int val) {
        if (contains(val)) {
            return;
        }
        size += 1;
        root = insertHelper(root, val);
    }
    public Node insertHelper(Node root, int val) {
        if (root == null){
            root = new Node(val);
        } else {
            if (val < root.data) { //smaller --> go left
                root.left = insertHelper(root.left, val);
            } else if (val > root.data) { //bigger --> go right
                root.right = insertHelper(root.right, val);
            }
        }

        return root;
    }

    // deletes the Node with data val, if it exists
    //very helpful: https://www.cs.usfca.edu/~galles/visualization/BST.html
    /*
    how to go about this:
    1. locate the desired node similarly to how it's done in insert
    2. if node has 0 children --> set it to null, return
    3. if node has 1 child --> make that child the child of the deleted node's parent
    4. if node has 2 children --> replace with the max of the left branch
     */
    public void delete(int val) {
        if (contains(val)) {
            size -= 1;
            root = deleteHelper(root, val);
        }
    }

    public Node deleteHelper(Node root, int val) {
        if (root == null){
            return null;
        } else {
            if (val < root.data) { //smaller --> go left
                root.left = deleteHelper(root.left, val);
            } else if (val > root.data) { //bigger --> go right
                root.right = deleteHelper(root.right, val);
            }
        }

        if (val == root.data) { //if we found our node
            //case 1 - no children
            if (root.left == null && root.right == null) {
                root = null;
            }
            //case 2 - 1 child
            //one child on the left only --> make that left child take place of deleted node
            else if (root.right == null) {
                root = root.left;
            }
            //one child on the right only
            else if (root.left == null) {
                root = root.right;
            }
            else {
                //case 3: 2 children
                int max = max(root.left);
                root.data = max;
                root.left = deleteHelper(root.left, max);
            }
        }
        return root;
    }

    //////////////// extra ////////////////////
    //max value within the children of a specific node in the BST
    //(need this for delete() )
    public int max(Node node) { //--> go as far right as you can
        int max = node.data;
        while (node.right != null) {
            max = node.right.data;
            node = node.right;
        }
        return max;
    }

    //prints BST for testing
    // in-order transversal
    public void printBST(Node root) {
        if (root != null){
            printBST(root.left);
            System.out.println(root.data + " ");
            printBST(root.right);
        }
    }

}
