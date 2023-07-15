/*
Write a min heap class according to the following API using an array as the underlying data structure.
Notes:
1. A node's:
    left child: arr[ (index * 2) + 1 ]
    right child: arr[ (index * 2) + 2 ]
    parent: arr[ (index - 1) / 2 ]

2. The spec said to use an array as the underlying data structure. Could I have used an arraylist instead?
For simplicity, I just set the array's size to 10 for now, but I recognize that for more complex examples I
would need to implement a resizing method.
 */

import java.util.Arrays;

public class Q2_MinHeap {
    private int[] arr;  //underlying array
    private int nextIndex; //keep track of the next free index
    private int capacity; //keep track of arr's capacity for resizing
    //doubles with every resize (8, 16, 32, ...)
    public Q2_MinHeap(){
        arr = new int [20];
        this.nextIndex = 0;
        this.capacity = 20;
    }


    //returns the min (top) element in the heap (peek)
    //don't remove!
    //Time: O(1)
    int top() {
        if (nextIndex == 0){ //empty array
            return -1000000;
        }
        return arr[0];
    }

    //adds int x to the heap in the appropriate position
    //Time: O(log N)
    void insert(int x) {
        //insert
        arr[nextIndex] = x;

        //then heapify
        int curr = nextIndex;
        while (arr[curr] < arr[parent(curr)]) { //while the current node is less than it's parent
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
        arr[nextIndex] = 0; //the leaf that just got moved to the top frees the last used index in the array
        //then heapify
        removeHelper(0);
    }

    void removeHelper(int index) {
        if (!leaf(index)) { //if the heap is not just 1 node / if we're not at the bottom of the heap yet
            //if curr node is greater than either of its children
            if (arr[index] > arr[leftChild(index)] || arr[index] > arr[rightChild(index)]) {
                //swap current index with the smaller child
                if (arr[leftChild(index)] < arr[rightChild(index)]) {
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
        int temp = arr[one];
        arr[one] = arr[two];
        arr[two] = temp;
    }

    //main
    public static void main (String[] args) {
        Q2_MinHeap minHeap = new Q2_MinHeap();
        System.out.println(minHeap.top()); //-1000000 (empty)
        minHeap.insert(7);
        minHeap.insert(6);
        minHeap.insert(14);
        minHeap.insert(11);
        minHeap.insert(4);
        minHeap.insert(12);
        minHeap.insert(9);

        System.out.println(minHeap.top()); //4
        System.out.println(Arrays.toString(minHeap.arr)); //[4, 6, 9, 11, 7, 14, 12, 0, 0, 0]
        /*
                4
            6      9
        11   7   14   12
         */

        minHeap.remove();
        System.out.println(minHeap.top()); //6
        System.out.println(Arrays.toString(minHeap.arr)); //[[6, 7, 9, 11, 12, 14, 0, 0, 0, 0]
        /*
                6
            7      9
        11   12   14
         */
        minHeap.remove();
        minHeap.remove();
        minHeap.remove();
        System.out.println(minHeap.top()); //11
        System.out.println(Arrays.toString(minHeap.arr)); //[11, 14, 12, 0, 0, 0, 0, 0, 0, 0]
        /*
                11
            14      12
         */

        minHeap.insert(1);
        minHeap.insert(20);
        minHeap.insert(3);
        minHeap.insert(5);
        minHeap.insert(25);
        System.out.println(minHeap.top()); //1
        System.out.println(Arrays.toString(minHeap.arr)); //[1, 11, 3, 14, 20, 12, 5, 25, 0, 0]
         /*
                    1
                11      3
             14   20   12   5
          25

         */
        for (int i =0; i <8; i++){
            minHeap.remove();
        }
        System.out.println(minHeap.top()); //-1000000
        System.out.println(Arrays.toString(minHeap.arr)); //[0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    }
}
