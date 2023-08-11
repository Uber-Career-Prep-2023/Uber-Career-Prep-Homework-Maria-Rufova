/* Question 9: Merge K Sorted Arrays
Given an array of k sorted arrays, merge the k arrays into a single sorted array.

Example:
Input: 2, [[1, 2, 3, 4, 5], [1, 3, 5, 7, 9]]
Output: [1, 1, 2, 3, 3, 4, 5, 5, 7, 9]

Plan:
1. Put every element into a min heap
2. Keep removing from the top of min heap until its empty
3. Remove values from min heap into a merged array
4. Return the merged array

Time: O(N log N) (i think?)
Space: O(N)
Data Structure / Graph Algorithm: Min Heap
Time taken:  10 minutes
 */

import java.util.Arrays;
public class Q9_MergeKSortedArrays extends Q2_MinHeap{
    public static int[] mergedArrays(int k, int[][] arrays){
        Q2_MinHeap minHeap = new Q2_MinHeap();
        int size = 0;

        for(int i = 0; i < arrays.length; i++) {
            for (int j = 0; j < arrays[i].length; j++){
                minHeap.insert(arrays[i][j]);
                size++;
            }
        }

        int[] merged = new int[size];
        for (int elem = 0; elem < size; elem++) {
            merged[elem] = minHeap.top();
            minHeap.remove();
        }

        return merged;
    }

    public static void main (String args[]) {
        //Test 1
        int[][] arr1 = {{1, 2, 3, 4, 5}, {1, 3, 5, 7, 9}};
        System.out.println(Arrays.toString(mergedArrays(2, arr1)));
        // [1, 1, 2, 3, 3, 4, 5, 5, 7, 9]

        //Test 2
        int[][] arr2 = {{1, 4, 7, 9}, {2, 6, 7, 10, 11, 13, 15}, {3, 8, 12, 13, 16}};
        System.out.println(Arrays.toString(mergedArrays(3, arr2)));
        // [1, 2, 3, 4, 6, 7, 7, 8, 9, 10, 11, 12, 13, 13, 15, 16]

        //Test 3 (empty)
        int[][] arr3 = {{ }}; //

    }
}


