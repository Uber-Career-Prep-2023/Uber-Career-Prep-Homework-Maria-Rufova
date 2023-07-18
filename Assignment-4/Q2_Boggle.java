/* Question 2: Boggle
"Boggle is a word game in which players compete to find the most words on a square grid of random letters.
Valid words must be at least three characters and formed from non-overlapping (i.e., a position on the
board can only be used once in a word) adjacent (including diagonal) letters. Given a Boggle board and
a dictionary of valid words, return all valid words on the board."

Plan:
1.

Notes:
1. This seemed easier until I actually started implementing it and realized that I'm only allowed to form words from
    adjacent letters
2.

Time:
Space:
Data Structure: Trie(to store words) + dfs (to traverse until you find a complete word)
Time taken: 40+ minutes (a lot of thinking, couldn't figure out how to use only adjacent letters)
 */

import java.util.ArrayList;

public class Q2_Boggle extends Q1_Trie {

    public ArrayList<String> boggle (String[][] board, String[] dictionary){
        //Q2_Boggle trie = new Q2_Boggle();
        ArrayList<String> answer = new ArrayList<>();
        int[][] directions = {
                {0, 1}, //up
                {0, -1}, //down
                {1, 0}, //right
                {-1, 0}, //left
                {1, 1}, //up right
                {-1, 1}, //down right
                {1, -1}, //down left
                {-1, -1} //down right
        };

        int rows = board.length;
        int cols = board.length;
        for(int r = 0; r < rows; r++){
            for(int c =0; c < cols; c++){
                dfs(r, c);
            }
        }

        return answer;
    }

    public String dfs(int row, int col){
        String word = "";
        return word;
    }


    public static void main(String[] args) {
        //Test here
        Q2_Boggle t = new Q2_Boggle();

        //all words
        String dictionary[] = {"Ace", "Ape", "Cape", "Clap", "Clay", "Gape", "Grape", "Lace",
                                "Lap", "Lay", "Mace", "Map", "May", "Pace", "Pay", "Rap",
                                "Ray", "Tap", "Tape", "Trace", "Trap", "Tray", "Yap"};
        //the board
        String board[][] = {{"A", "D", "E"},
                            {"R", "C", "P"},
                            {"L", "A", "Y"}};

        for(String word : t.boggle(board, dictionary)){
            System.out.print(word + " ");
            //
        }

    }
}
