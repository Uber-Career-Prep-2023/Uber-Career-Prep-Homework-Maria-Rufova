/* Question 8: Coin Change
"Given a list of coin denominations and a target sum, return the number of possible ways to make change for that sum."

Plan:
1. Memoization, or Top-Down, recursive solution. "The recursive solution is used with one simple addition:
a map of partial results is initialized, and every time a recursive call is made, the map is checked
for the parameters of the call. "

Notes:
1. I've definitely seen this in class before, but I don't remember how it was solved
2.

Time: O(C * S) where C is the total number of coins, and S is the sum we're adding up to
Space: O (C * S)
Data Structure: DFS,  Memoization (Top-Down)
Time taken: 40 minutes
 */

import java.util.Arrays;

public class Q8_CoinChange {
    static int[][] counts; //a 2D array representing all possible sums up to the target sum, and all possible combination of coins to reach those sums
    static int[] coins; //provided coins

    public static int coinChange(int target, int[] c){
        coins = c;
        counts = new int[coins.length][target+1];
        /*
        a 2d Array of coin-amount arrays (for {2, 5, 10} = 3 arrays},
        each of target + 1 length (for target = 5, target + 1 = 6)
         */
        for (int[] row : counts){ //for every coin's row in the 2D grid
            Arrays.fill(row, -1); //fill with -1 to signify as unfilled for now
        }
        return dfs(0, target);
    }
    //dfs to traverse every possible coin combination
    //"index: is the coin in the coins array we're currently at and "amount" is the part of the target sum that we've yet to cover
    public static int dfs(int index, int amount){
        if (amount == 0){ //if amount left to cover is 0, then we're done bc we found 1 possible combination
            return 1;
        }
        else if (index == coins.length){ //if we're reached the end of our coins list w/o summing to target, this is not a valid combination
            return 0;
        }
        else if (counts[index][amount] != -1){ //if possible sums for this index and this amount have been found before, return that saved info
            return counts[index][amount];
        }
        if (coins[index] > amount){ //if this coin is greater than the amount that we still need to cover, we can't use it
            return counts[index][amount] = dfs(index + 1, amount); //so find the sums amount from the next coin index and
            // save it under counts[index][amount] for future ease of access
        }
        //otherwise,
        //the sum value in this box = sums that consider this coin + sums that do NOT consider this coin
        counts[index][amount] = dfs(index, amount - coins[index]) + dfs(index + 1, amount);
        //return the total amount of combination saved at the (top right) of the counts grid unded the <smallest coin> and <amount=target> square
        return counts[index][amount];
    }

    public static void main (String[] args){
        //Sum: 20 , Coins: [2, 5, 10]
        System.out.println(coinChange(20, new int[]{2, 5, 10}));
        //Output: 6 (Options are: 10 2s; 4 5s; 2 10s; 5 2s & 2 5s; 5 2s & 1 10; 2 5s & 1 10)

        //Sum: 15 , Coins: [2, 5, 10]
        System.out.println(coinChange(15, new int[]{2, 5, 10}));
        //Output: 3 (Options are: 5 2s & 1 5; 1 5 & 1 10)

    }
}
