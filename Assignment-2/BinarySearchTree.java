public class BinarySearchTree {

    Node root;

    private class Node {
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
        } else if (root.data > val) {
            //Root is larger --> go left to search among smaller vals
            return containsHelper(root.left, val);
        }
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
    //spec says return type is int?
    //FINISH later --> I don't know what int it should return if it doesn't find the value.
    public int delete(int val) {
        if (contains(val)) {
            root = deleteHelper(root, val);
            return deleteHelper(root, val).data;
        }
        return -1;
    }

    public Node deleteHelper(Node root, int val) {
        return root;
    }


}
