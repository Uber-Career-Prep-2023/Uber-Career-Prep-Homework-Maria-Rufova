/*

Question 5: First K Binary Numbers
Given a number, k, return an array of the first k binary numbers, represented as strings.


Example:
Input: 5
Output: ["0", "1", "10", "11", "100"]


Plan:
1.

Time: O(N)
Space: O(N)
Data Structure / Graph Algorithm: Queue? A min heap?
Time taken:  40 minutes
 */

public class Q5_FirstKBinaryNumbers {

    public static String[] firstKBinary(int k){
        String[] numbers = new String[k];
        int index = 0;
        int digits = 1;
        while(index < k) {
            int[] currBinary = new int[digits];
            permutations(digits);
            index++;
        }

        return numbers;
    }

    public static void permutations(int size) {

    }
    public static void main (String args[]) {
        //Test 1: From spec
        firstKBinary(5);

        //Test 2: From spec
        firstKBinary(10);

        //Test 3: Empty
        firstKBinary(0);

        //Test 4: Really long
        firstKBinary(50);

    }
}
