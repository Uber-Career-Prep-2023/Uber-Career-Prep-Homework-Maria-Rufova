public class BinarySearchTree {
    Node root;

    public static void main(String[] args) {
        //test the bst here
        BinarySearchTree bst = new BinarySearchTree();
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
        int min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }
    // returns the maximum value in the BST
    public int max() { //--> go as far right as you can
        int max = root.data;
        while (root.right != null) {
            max = root.right.data;
            root = root.right;
        }
        return max;
    }

    //max value within the children of a specific node in the BST
    public int max(Node node) { //--> go as far right as you can
        int max = node.data;
        while (node.right != null) {
            max = node.right.data;
            node = node.right;
        }
        return max;
    }

    // returns a boolean indicating whether val is present in the BST
    public boolean contains(int val) {
        return containsHelper(root, val) != null;
    }

    public Node containsHelper(Node root, int val) {
        if (root.data == val || root== null) {
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


}
