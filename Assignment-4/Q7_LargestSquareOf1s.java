/* Question 7: Largest Square of 1s
"Given a square matrix of 0s and 1s, find the dimension of the largest square consisting only of 1s."

Plan:
1. Memoization --> create a cache to compute partially computed dimensions of squares of 1s from earlier in the matrix.
Avoids visiting the same square more than once.
2. Prefill every row and col in cache with -1 to mark the squares as unvisited
3. Implement a recursive function helper that checks the values of surrounding squares in a cache before computing a cache value
for the curr coordinate
4. If trying to access a square out of bounds of the matrix, return a 0. It cannot be used in our square of 1s.
5. If a cache already contains a value for a neighbor square you're trying to visit, return that value stored in the cache.
No need to do extra work and recompute it!
6. Use recursive calls to helper to find the cache values in the squares to the right, down, and diagonal of the current square
7. If the curr square is a 0 in our matrix, mark it as 0 in the cache. It can't be used as a part of a square of 1s!
8. If its a 1 however, store at as 1 in the cache + the min found among its neighbors. This lets us know if the neighbors are a part of
any smaller squares of 1s that can be complied into a bigger square.
    (if the min is a 0, then the neighbors cannot be incorporated into a square of 1s. if the min is a 1, that means that
    every neighbor is a part of at least another square of 1s and it can be compiled into a bigger square)
9. If the curr value we computed for the cache exceeds the max dimension value found so far, update the max
10. Return the value you just saved in cache, and pass it up to be used in the higher level of Memoization until
the entire original matrix has been traversed.


Notes:
1. I did the coin problem (Q#8) before this and it helped because the though process was really similar
2. Still often have trouble coming up with the Tabulation approach. Memoization and Tabulation solutions often have the same time
complexity, but Tabulation often has better space. Hopefully in an interview implmenting at least one of them correctly will
count as passing. At least I think it will be better than just brute-forcing with no cache.

Time: O(M x N) --> passing over the input matrix only once
Space: for Memoization, space is O(M x N) as another MxN matrix is used a cache
There is also a way to do it with tabulation which reduces space to O(N) --> an array with # of cols is used as a cache to store partial dimensions
Data Structure: Dynamic Programming - Memoization (Top-Down)
(but can also be solved using Tabulation (Bottom-Up), saving some space)
Time taken: 40 min
 */


import java.util.Arrays;

public class Q7_LargestSquareOf1s {
    //variable max to store the max dimension of a square of 1s
    static int max = 0;
    public int largestSquare(int[][] matrix){
        this.max = 0; //make sure to reset it for every new grid
        //int rows = matrix.length;
        //int cols = matrix[0].length;

        //cache that stores the dimensions of the largest square of 1s this matrix square is a part of
        //extra row and col bc getting out of bounds errors righ now. potentially remove later.
        int[][] cache = new int[matrix.length+1][matrix[0].length+1];
        for (int[] row : cache) { //prefil cache with -1 to mark every square as unvisited
            Arrays.fill(row, -1);
        }
        helper(0, 0, matrix, cache); //start Memoization in the top left corner of the matrix
        return max; //return the updated max
    }

    public static int helper(int row, int col, int[][] matrix, int[][] cache) {
        if (row >= matrix.length || col >= matrix[0].length){ //if we're trying to check a square out of matrix bounds
            return 0; //return a 0 --> that part can't be used in our square of 1s
        }
        if (cache[row][col] != -1){ //if this square has already been visited (aka there's a value stored for it in cache)
            return cache[row][col]; //retrieve that previously calculated value
        }

        //count curr (row, col) square as a left corner and visit its neighbors to the right, diagonal, and down
        int right = helper(row, col +1, matrix, cache);
        int diagonal = helper(row+1, col +1, matrix, cache);
        int down = helper(row+1, col, matrix, cache);

        int value = 0; //the value that will fill this (row, col) square in our cache
        //if the curr square is a 0 in our matrix, just leave it as 0, as it can't be used as a part of any square of 1s.
        //BUT, if it is a 1
        if(matrix[row][col] == 1) {
            //its value in the cache should be = to its matrix value of 1 + the min of its neighbor, indication that it may potentially contain a smaller square of 1s found prior
            //for example, if our min neighbor is a 0, we know that cannot be used to make a part of a larger square of 1s. The curr value should just be stored as 1 by itself in the cache,
            //since it can't use any of its neighbors to make a larger square
            //but say right, diagonal, and down all == 1, then we know the curr square is surrounded by 1s, and can be used along with all its neighbors in a larger 2X2 square of 1s
            value = 1 + Math.min(right, Math.min(diagonal, down));
            max = Math.max(max, value); //update the max if curr value has exceeded it
        } else {
            return cache[row][col]=0; //if curr (row, col) square is a 0 in the matrix, store it as a 0 in the cache
        }
        return cache[row][col] = value; //update the cache with the newly computed value and return it (pass it up for memoization)

    }

    public static void main (String[] args) {

        Q7_LargestSquareOf1s ls = new Q7_LargestSquareOf1s();

        //Test 1
        int[][] matrix1 = new int[][]{
                {0, 1, 0, 1},
                {0, 0, 1, 1},
                {0, 1, 1, 1},
                {0, 1, 1, 1}};

        System.out.println(ls.largestSquare(matrix1)); //2 (the 2x2 square of 1s on bottom right)

        //Test 2
        int[][] matrix2 = new int[][]{
                {0, 1, 0, 1, 1},
                {0, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {0, 1, 1, 0, 0}};

        System.out.println(ls.largestSquare(matrix2)); //3 (the 3x3 square toward the right side of the matrix)

        //Test 3
        int[][] matrix3 = new int[][]{
                {0, 1},
                {0, 0}};
        System.out.println(ls.largestSquare(matrix3)); //1 (the sole 1 at the top right)

        //Test 4
        int[][] matrix4 = new int[][]{
                {0, 0},
                {0, 0}};
        System.out.println(ls.largestSquare(matrix4)); //0 (no 1s present in this matrix)
    }

}
