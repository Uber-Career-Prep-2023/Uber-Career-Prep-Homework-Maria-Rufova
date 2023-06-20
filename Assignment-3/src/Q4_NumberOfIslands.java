import java.util.HashSet;
import java.util.Set;

/* Question 4: NumberOfIslands
Given a binary matrix in which 1s represent land and 0s represent water.
Return the number of islands (contiguous 1s surrounded by 0s or the edge of the matrix).

Example:
 1 0 1 1 1
 1 1 0 1 1
 0 1 0 0 0
 0 0 0 1 0
 0 0 0 0 0    --> 3 islands

Plan:
   I had an assignment that was a variant of this problem, but there we had to use DFS to find a water path going around all the islands.
   So it sounds like this will be the opposite: use BFS to map an island, then once you hit water, keep looking for another island and
   use BFS to map all of it again.

   1. Starting in the top left corner of the grid, visit each node and mark its (row, col) coordinate as visited in a separate array
   2. If the current node is a 1, call BST on its neighbors
   3. Visit a neighbor if it is (a) also a 1 (b) within the grid dimensions and (c) was not already visited
   4. After the bst call ends (when the island hits 0s), increment the counter
   5. Repeat until all the nodes are marked as visited

Time: O(N*M) --> for a matrix with NxM dimensions
Space: O(N*M)
Data Structure / Graph Algorithm: Graph - BST
Time taken:  40 minutes (struggled with how to navigate around the grid)
 */
public class Q4_NumberOfIslands {
    //instance variables
    static int islands; //island counter
    static int matrix[][]; //passed in matrix
    static int rows;
    static int cols;
    static boolean[][] visited; //storing visited nodes during bfs

    public Q4_NumberOfIslands(int[][] m){
        this.islands = 0;
        this.matrix = m;
        this.rows = matrix.length;
        this.cols = matrix[0].length;
        this.visited = new boolean[rows][cols];
    }

    public static int numberOfIslands(){
        for(int row=0; row < rows; row++){ //for every node represented by a (row, col) coordinate
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1 && !visited[row][col]) { //if the node is an unvisited 1
                    bfs(row, col); //star bfs
                    islands+=1; //increment counter when current bfs ends
                }
            }
        }
        return islands;
    }

    private static void bfs(int row, int col) {
        visited[row][col] = true; //mark the current node as visited so we don't check it again

        //visit each neighbor by taking 1 step in 1 of the four directions
        int[][] neightbors = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        for (int[] n : neightbors){ //if the neighbor is:
            if (row + n[0] < rows && row + n[0] >= 0 && //within row space and
                    col + n[1] < cols && col + n[1] >= 0 && //within column space and
                    matrix[row + n[0]][col + n[1]] == 1 && //is part of an island and
                    !visited[row + n[0]][col + n[1]]) { //has not been visited yet

                bfs(row + n[0], col + n[1]); //keep performing bfs until all the island is captured

            }
        }

    }

    public static void main (String args[]) {
        //Test 1: from spec
        int[][] matrix1 = { {1, 0, 1, 1, 1},
                            {1, 1, 0, 1, 1},
                            {0, 1, 0, 0, 0},
                            {0, 0, 0, 1, 0},
                            {0, 0, 0, 0, 0}};
        Q4_NumberOfIslands map1 = new Q4_NumberOfIslands(matrix1);
        System.out.println(map1.numberOfIslands()); //3

        //Test 2: from spec
        int[][] matrix2 = { {1, 0, 0},
                            {0, 0, 0}};
        Q4_NumberOfIslands map2 = new Q4_NumberOfIslands(matrix2);
        System.out.println(map2.numberOfIslands()); //1

        //Test 3: empty
        int[][] matrix3 = {{ }};
        Q4_NumberOfIslands map3 = new Q4_NumberOfIslands(matrix3);
        System.out.println(map3.numberOfIslands()); //0

        //Test 4: 5 islands in a diagonal
        int[][] matrix4 = { {1, 0, 0, 0, 0},
                            {0, 1, 0, 0, 0},
                            {0, 0, 1, 0, 0},
                            {0, 0, 0, 1, 0},
                            {0, 0, 0, 0, 1}};
        Q4_NumberOfIslands map4 = new Q4_NumberOfIslands(matrix4);
        System.out.println(map4.numberOfIslands()); //5
    }
}