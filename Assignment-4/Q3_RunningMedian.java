/* Question 3: Running Median
"You will be given a stream of numbers, one by one. After each new number, return the median of the numbers so far."

Plan:
Brute Force: Use insertion sort. Every time a new element is added, compare it with all other
elements in the array at insert at the appropriate position.
 O(Log N) for Binary search to find the correct position + O(N) to potentially shift all elements a space
 to the left or a space to the right.

Efficient: Two Heaps
O(log N) to insert an elem into a heap + O(1) to look up the root = O(log N)

1. Let's say we have an array of input values. Create two heaps to store the smaller and the larger halves of the array.
2. Small Heap is a Max Heap, bc we need to get the largest element (as it will be in the middle of the whole array)
3. Big Heap is a Min Heap, bc we need to get the smallest element (as it will be in the middle of the whole array)
So for input values like [1, 2, 3, 4, 5]
Small heap is a Max Heap containins [1, 2] with 2 being the root
and Large heap is a Min Heap contaiins [3, 4, 5] with 3 as the root
4. Heaps Rules:
    a. Heaps are either the same size, or one is larger than the other by ONE elem
    b. All elems of Small heap < all elems of Large heap
Insertion:
5. Insert into small heap if have empty heaps OR root of small heap is greater than the elem we're inserting (proper max heap)
6. Insert into large heap id root of large heap is less then the elem (proper min heap)
7. if the sizes of heaps are no longer equal, balance them out by transfering the root of the bigger one into the smaller one
Returning Median:
6. If size of Small heap > size of Large heap,return root of Small.
7. Else if size of Large > size of Small, return root of Large
8. Else if the sizes are the same, get the roots of both, and return their average

Notes:
1.
2.

Time: O(log N) --> O(log N) for insertion + O(1) for look-up
Space: O(N) --> to store all inputed elements in two heaps (each elem only goes to one of the heaps)
Data Structure: Either Insertion Sort or Min Heap (correction: TWO heaps - one MIN and one MAX)
Time taken: 40+ minutes (...looked easier on paper)
 */

import java.util.PriorityQueue;

public class Q3_RunningMedian {

    PriorityQueue<Integer> small = null; //a Max Heap for the smaller half of array
    PriorityQueue<Integer> large = null; //a Min Heap for the larger half of array

    public Q3_RunningMedian(){ //initialize a new array here to keep track of the current stream of numbers
        //max heap
        small = new PriorityQueue<>((x, y) -> ( y - x)); //basically a PQ but with reverse order of elems, making higher values go first
        //min heap
        large = new PriorityQueue<>(); //in java, a PQ already works like a min heap
    }

    public void insert(int i){ //insert a new elem into the array
        if (small.size() == 0 || small.peek() >= i){ //is small heap is empty, or it's root is larger than i
            small.offer(i); //we can insert this elem into this max heap
        } else { //if the elem is greater than the root of this max heap
            large.offer(i);//insert it into the large (min heap) instead
        }
        //check if the heap sizes are no longer roughy the same, and if so, balance them
        balance();

    }
    public double runningMedian(){ //return the current median
        if(small.peek() == null && large.peek() == null){ //if calling median on empty heaps
            return 0;
        }
        else if (small.size() > large.size()) { //if small heap is larger than the large heap
            return small.peek();
        } else if (large.size() > small.size()) { //or if the large is bigger than the small
            return large.peek(); //remove the root of small and move it to large
        } else {
            return (small.peek() + large.peek()) / 2.0;
        }

    }

    public void balance(){
        if (small.size() > large.size() + 1) { //if small heap is larger than the large heap
            large.offer(small.poll()); //remove root of small and move it to large
        } else if (large.size() > small.size() + 1) { //or if the large is bigger than the small
            small.offer(large.poll()); //remove the root of small and move it to large
        }
        //if they're roughy the same size, no balancing is required atm
    }


    public static void main(String[] args){
        Q3_RunningMedian stream = new Q3_RunningMedian();

        System.out.println(stream.runningMedian()); //0.0 ->  empty stream []

        stream.insert(1); // [1]
        System.out.println(stream.runningMedian()); //1.0

        stream.insert(11); // [1, 11]
        System.out.println(stream.runningMedian()); //6.0

        stream.insert(4); // [1, 11, 4]
        System.out.println(stream.runningMedian()); //4.0

        stream.insert(15); // [1, 11, 4, 15]
        System.out.println(stream.runningMedian()); //7.5

        stream.insert(12); // [1, 11, 4, 15, 12]
        System.out.println(stream.runningMedian()); //11.0
    }
}
