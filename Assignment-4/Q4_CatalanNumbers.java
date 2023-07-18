/* Question 4: Catalan Numbers
"The Catalan numbers are a mathematical sequence of numbers.
The nth Catalan number is defined as (2n)! / (n+1)!n!.
Given a non-negative integer n, return the Catalan numbers 0-n."

Plan:
1.

Notes:
1. This was more of a math and logic problem haha
2.

Time: O(N^2)? Maybe?
Space: O(N)
Data Structure: Binary Search Tree
Time taken: 40 minutes
 */

import java.util.Arrays;

public class Q4_CatalanNumbers {

    public static int[] catalans(int n){
        int[] answer = new int[n + 1];
        if(n == 0){
            return new int[]{1};
        } else if (n == 1) {
            return new int[]{1, 1};
        }
        answer[0] = 1;
        answer[1] = 1;

        for (int i = 2; i <= n; i++){
            answer[i] = 0;
            for (int j = 0; j < i; j++){
                answer[i] += answer[j] * answer[i-j-1];
            }
        }
        return answer;
    }

    public static void main(String[] args){
        //From the spec
        //Test 1
        System.out.println(Arrays.toString(catalans(1))); //[1, 1]
        //Test 2
        System.out.println(Arrays.toString(catalans(5))); //[1, 1, 2, 5, 14, 42]

        //more tests
        //Test 3
        System.out.println(Arrays.toString(catalans(0))); //[1]
        //Test 4
        System.out.println(Arrays.toString(catalans(10))); //[1, 1, 2, 5, 14, 42, 132, 429, 1430, 4862, 16796]
    }
}
