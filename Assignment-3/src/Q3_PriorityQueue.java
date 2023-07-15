/*
A priority queue functions like a queue except that elements are removed in order of priority rather than insertion.
This is typically implemented as a max heap that stores pairs of elements and numeric weights,
with the weights used as the values in the heap. Implement a priority queue according to the following API using a
heap as the underlying data structure. For simplicity, you can assume the priority queue stores string elements with
integer priorities. Start by copy-pasting your heap implementation from question 2 and making modifications.


Notes:
1. Spec says this is a MAX heap. Put elements with higher weights first. [10, 8, 6, 4, ...]
2. Spec uses a Pair class to store PQ elements with their weight. This is a C++ feature. There is a Pair class in java,
but it doesn't work as well because it is not a build-in data structure. After struggling for some time with trying to
import and use Pair, I felt it would be more efficient to just write my own Pair class. I would definitely rather write my
own helper class in an interview than struggle to import something that may or may not work on the interviewing site.
 */


import java.util.ArrayList;
import java.util.Arrays;

public class Q3_PriorityQueue {

    //helper Pair class
    public class Pair {
        private final String item;
        private final int value;
        public Pair(String item, int value) {
            this.item = item;
            this.value = value;
        }
    }


    private Pair[] arr; //underlying array
    private int nextIndex; //keep track of the next free index
    private int capacity; //keep track of arr's capacity for resizing

    public Q3_PriorityQueue(){
        arr = new Pair[10];
        this.nextIndex = 0;
        this.capacity = 10;
    }

    //returns the highest priority(first) element in the PQ
    //don't remove!
    //Time: O(1)
    String top() {
        if (nextIndex == 0){ //empty array
            return "The PQ is empty.";
        }
        return arr[0].item; //gets first item in Pair at arr[0]
    }

    //adds String x to the PQ with priority weight
    //Time: O(log N)
    void insert(String x, int weight) {
        //insert
       arr[nextIndex] = new Pair(x, weight);

        //then heapify
        int curr = nextIndex;
        while (arr[curr].value > arr[parent(curr)].value) { //while the current node's weight is greater than its parent's weight
            swap(curr, parent(curr)); //swap around the node and it's parent
            curr = parent(curr); //update the curr pointer
        }
        nextIndex++;
    }

    //removes the min (top) element in the heap
    //Time: O(log N)
    void remove() {
        //remove
        arr[0] = arr[--nextIndex]; //move the last element in the array to the top of the heap (and update the next free index)
        arr[nextIndex] = new Pair("", 0); //the leaf that just got moved to the top frees the last used index in the array
        //then heapify
        removeHelper(0);
    }

    void removeHelper(int index) {
        if (!leaf(index)) { //if the heap is not just 1 node / if we're not at the bottom of the heap yet
            //if curr node is less than either of its children
            if (arr[index].value < arr[leftChild(index)].value || arr[index].value < arr[rightChild(index)].value) {
                //swap current index with the larger child
                if (arr[leftChild(index)].value > arr[rightChild(index)].value) {
                    swap(index, leftChild(index));
                    removeHelper(leftChild(index)); //fix the rest of the heap if needed
                } else {
                    swap(index, rightChild(index));
                    removeHelper(rightChild(index));
                }
            }

        }

    }

    ////////////////////Helper Functions//////////////////////////
    private int leftChild(int index){
        return (index * 2) + 1;
    }
    private int rightChild(int index){
        return (index * 2) + 2;
    }
    private int parent(int index){
        return (index - 1) / 2;
    }

    private boolean leaf(int index){ //check if the curr node is only a leaf
        //if either of the node's children has index above the capacity, the node must be a leaf
        if (rightChild(index) >= nextIndex || leftChild(index) >= nextIndex) {
            return true;
        }
        return false;
    }

    //swapping nodes around (moved to a helper function bc it got repetitive)
    void swap(int one, int two) {
        Pair temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
    }

    //print
    void printPQ(){
        String array = "";
        for(int i = 0; i < nextIndex; i++){
            array += arr[i].item + ": " + arr[i].value+", ";
        }
        System.out.println(array);
    }

    public static void main (String[] args) {
        Q3_PriorityQueue pq = new Q3_PriorityQueue();
        System.out.println(pq.top()); //The PQ is empty
        pq.insert("one", 1);
        pq.insert("four", 4);
        pq.insert("seven", 7);
        pq.insert("two", 2);
        pq.insert("three", 3);
        pq.insert("six", 6);
        pq.insert("five", 5);

        System.out.println(pq.top()); //seven
        pq.printPQ(); //[seven: 7, three: 3, six: 6, one: 1, two: 2, four: 4, five: 5]
        /*
                7
            3      6
          1   2  4   5
         */

        pq.remove();
        System.out.println(pq.top()); //six
        pq.printPQ(); //[six: 6, three: 3, five: 5, one: 1, two: 2, four: 4]
        /*
                6
            3      5
          1   2  4
         */
        pq.remove();
        pq.remove();
        pq.remove();
        pq.insert("eleven", 11);
        pq.insert("eight", 8);
        pq.insert("twelve", 12);
        System.out.println(pq.top()); //twelve
        pq.printPQ(); //[twelve: 12, eight: 8, eleven: 11, one: 1, three: 3, two: 2]
         /*
                12
            8      11
          1   3  2
         */
        for (int i =0; i <6; i++){
            pq.remove();
        }
        System.out.println(pq.top()); //The PQ is empty.
        pq.printPQ(); //[ ]
    }
}
