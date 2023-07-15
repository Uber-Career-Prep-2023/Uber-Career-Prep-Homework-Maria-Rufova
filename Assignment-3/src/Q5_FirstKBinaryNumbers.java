/*

Question 5: First K Binary Numbers
Given a number, k, return an array of the first k binary numbers, represented as strings.


Example:
Input: 5
Output: ["0", "1", "10", "11", "100"]


Plan:
1. Create an array with size = k
2. Always append 0 at the beginning
2. Use a StringBuilder to add digits and insert new strings into the array until it is full.
4. For every even spot in the array, add a 0
5. For every odd spot, change the most recent 0 to a 1

Time: O(N)
Space: O(N)
Data Structure / Graph Algorithm: Not sure? I just approached it mathematically where I appended a 0 or a 1 to the string depending on what digit is next.
I'm guessing there is a more efficient solution that involves a data structure.
Time taken:  40 minutes

Notes:
1. Took some time to think the math through because sometimes new digits would be appended incorrectly. StringBuilder (esp. the insert() method) helped quite a bit.
2. Reminder to self: get used to using StringBuilder more because it has a lot of useful methods that make problems easier to understand, rather than using just String all the time.
 */

import java.util.Arrays;

public class Q5_FirstKBinaryNumbers {

    public static String[] firstKBinary(int k){
        String[] numbers = new String[k]; //array for exactly k binary numbers
        for (int i = 0; i < k; i++){
            numbers[i] = permutations(i);
        }
        return numbers;
    }

    public static String permutations(int k) { //helper function
        StringBuilder newBinary = new StringBuilder(); //builds up binary numbers
        char nextInt; //next Int (either 0 or 1) to be inserted in a binary num
        if (k == 0){ //first binary number will always be 0
            nextInt = '0';
            newBinary.append(nextInt);
        }
        while (k > 0){ //for the rest of binary numbers
            if (k % 2 == 0) { //add a 0 to the sequence for every next even spot
               nextInt = '0';
               k = k/2;
            } else { //and a 1 for every odd spot
                nextInt = '1';
                k = (k-1)/2;
            }
            newBinary.insert(0, nextInt); //inserts the new digit to the front of the String
        }
        return newBinary.toString();
    }
    public static void main (String args[]) {
        //Test 1: From spec
        System.out.println(Arrays.toString(firstKBinary(5)));
        //["0", "1", "10", "11", "100"]


        //Test 2: From spec
        System.out.println(Arrays.toString(firstKBinary(10)));
        //["0", "1", "10", "11", "100", "101", "110", "111", "1000", "1001"]

        //Test 3: Empty
        System.out.println(Arrays.toString(firstKBinary(0)));
        //[ ]

        //Test 4: Really long
        System.out.println(Arrays.toString(firstKBinary(50)));
        /*
        [0, 1, 10, 11, 100, 101, 110, 111, 1000, 1001, 1010, 1011, 1100, 1101,
         1110, 1111, 10000, 10001, 10010, 10011, 10100, 10101, 10110, 10111,
         11000, 11001, 11010, 11011, 11100, 11101, 11110, 11111, 100000, 100001,
         100010, 100011, 100100, 100101, 100110, 100111, 101000, 101001, 101010,
         101011, 101100, 101101, 101110, 101111, 110000, 110001]
         */

    }
}
