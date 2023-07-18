/* Question 5: MinCostStairClimbing
"A staircase on a hiking trail implements a rather unusual toll system to cover trail maintenance costs.
Each stair in the staircase has a different toll which you only have to pay if you step on that stair.
Due to the height of the stairs, you can only climb one or two stairs at once.
This means that from the ground you must initially step on either stair 0 or stair 1 and that,
if there are n stairs, the last stair you step on can be either stair n-1 or n-2.
Given an array representing the costs per stair, what is the minimum possible toll you can pay to climb the staircase?"

Plan:
1. Find the least costly way of reaching the end of the array from array[i]. But to find that you first need
to find array[i+1], and to find that you need to find array[i+2], etc.
AKA solve from right to left of the array, not left to right


Notes:
1. I've done a kind of similar problem before with Fibonacci numbers
2.

Time: O(N)
Space: O(N)
Data Structure: Dynamic programming - Memoization (a map of partial results is initialized,
and every time a recursive call is made, the map is checked for the parameters of the call.)
Time taken: 30 minutes
 */

import java.util.Arrays;

public class Q5_MinCoststairClimbing {
    public static int minCost(int[] stairs){
        //make an array to store total cost from a step to the end, where the end is represented by an additional last index
        int[] costs = new int[stairs.length+1];
        costs[stairs.length] = 0; //the cost of reaching the last step from the last step is 0
        for (int i = stairs.length - 1; i >= 0; i--){
            if (i == stairs.length-1){ //to prevent OutOfBoundsError
                costs[i] = stairs[i]; //the cost of jumping from last stair to end is just the last stair
            } else {
                //besides the first stair, the cost of jumping from a stair i is its individual cost + the min of total cost from either 1 or 2 stairs ahead
                costs[i] = stairs[i] + Math.min(costs[i+1], costs[i+2]);
            }
        }
        return Math.min(costs[0], costs[1]); //returns whatever is less--> starting from the 1st or the 2nd stair
    }
    public static void main(String[] args){
        //From the spec
        //Test 1
        System.out.println(minCost(new int[]{4, 1, 6, 3, 5, 8}));
        //9 (step on stairs 1, 3, 4 for a cost of 1+3+5)

        //Test 2
        System.out.println(minCost(new int[]{11, 8, 3, 4, 9, 13, 10}));
        //25 (step on stairs 1, 3, 5 for a cost of 8+4+13)

        //More
        //Test 3
        System.out.println(minCost(new int[]{1, 8, 3, 7, 7, 5, 5, 2, 1})); //17

        //Test 4
        System.out.println(minCost(new int[]{4, 9, 10, 7})); //14

        //Test 5
        System.out.println(minCost(new int[]{1, 2, 3})); //2

    }
}
