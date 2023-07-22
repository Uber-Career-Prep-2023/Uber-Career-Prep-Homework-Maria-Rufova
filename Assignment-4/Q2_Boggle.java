/* Question 2: Boggle
"Boggle is a word game in which players compete to find the most words on a square grid of random letters.
Valid words must be at least three characters and formed from non-overlapping (i.e., a position on the
board can only be used once in a word) adjacent (including diagonal) letters. Given a Boggle board and
a dictionary of valid words, return all valid words on the board."

Plan:
1. The general idea is to traverse the entire grid letter by letter. Use dfs on each new letter to find all the possible words
that can be formed starting from each square. Use a Trie to check only for words that start with that letter / word fragment.
So if I'm on square "A", I would use my Trie to look up only the words that begin with "A". I would NOT
need to traverse the whole input dictionary when I know that cetrain words, like "Bat", would not work for a starting square
"A", so it would save time to just not check. One grid square could be used for a max of 8 DFS searches (the center one;
4 cardinal directions + 4 diagonals). Add a word to the answer array any time we find a "validWord" in the trie.
Return the answer array once all paths have been explored.

Notes:
1. This seemed easier until I actually started implementing it and realized that I'm only allowed to form words from
    adjacent letters
2. I got stuck on solving this using Trie structure from Q1 where the children array os each Node was represented by an
array. I switched to a Trie implementation that uses a HashMap for each TrieNode to map the node to all its child letters.

Time: O(L+N) wherw N is the total size of the dictionary and L is the length of each word? not sure about this
Space: O(N) (where N is the size of the dictionary)
Data Structure: Trie(to store words) + dfs (to traverse until you find a complete word)
Time taken: 40+ minutes (a lot of thinking, couldn't figure out how to use only adjacent letters)
 */

import java.util.*;

public class Q2_Boggle {

    static class TrieNode{ //Trie (!Important! Uses hashmap for children instead of arr like in Q1)
        HashMap<Character, TrieNode> children; //map a letter to all its next TrieNode children
        boolean validWord; //marks the end of a complete word
        TrieNode(){
            children = new HashMap<>();
            validWord = false;
        }
        public void insert(String word){
            TrieNode curr = this;  //starting at the root of the trie
            for(int i = 0; i < word.length(); i++){ //go through the word letter by letter
                if(!curr.children.containsKey(word.charAt(i))){ //if this letter is not yet present in Trie (on a particular branch)
                    curr.children.put(word.charAt(i), new TrieNode()); //insert it as a new TrieNode
                }
                curr = curr.children.get(word.charAt(i)); //move to the next letter down the word/down the trie
            }
            curr.validWord = true; //mark the last node of inserted word as a complete word
        }
    }

    /////////////////global variables///////////////////
    static Character[][] board; //the 3-by-3 boggle board
    static String[] dictionary; //the dictionary of all words
    static Set<String> answer; //set to store answer words. !set instead of arraylist to avoid duplicates!
    static Set<String> visited; //to keep track of nodes visited during dfs
    static int rows; // rows of the boggle board
    static int cols; //cols of the boggle board

    static TrieNode root; //a trie to organize the dictionary's words

    public static ArrayList<String> boggle(Character[][] b, String[] dict){
        board = b;
        dictionary = dict;
        rows = board.length;
        cols = board[0].length;

        //1. Create a new Trie and insert all dictionary words into it
        root = new TrieNode();
        for(String word : dictionary){
            root.insert(word.toUpperCase());
        }

        //2. Call DFS for every square of the board
        answer = new HashSet<>();
        visited = new HashSet<>();
        for (int row = 0; row < rows; row++){
            for (int col = 0; col < cols; col++){
                dfs(row, col, root, ""); //perform dfs on every letter of the board
            }
        }
        return new ArrayList<>(answer); //reform the set of answers as a list
    }

    //3. For every letter, perform dfs in any possible direction (including diagonals)
    public static void dfs(int row, int col, TrieNode node, String word){
        //skip if trying to access a square out of bounds of the board
        if ( row < 0 || col <0 || row >= rows || col >= cols) {
            return;
        }
        //if a letter on the board is NOT in the trie (meaning its not in the dictionary either)
        // OR this letter has already been visited on this particular dfs iteration
        //then skip
        if (!node.children.containsKey(board[row][col]) || visited.contains(row + "," + col)){
            return;
        }

        //but if it is a valid letter that has not been visited yet
        TrieNode curr = node.children.get(board[row][col]); //find this letter in the trie
        word += board[row][col]; //add this letter to the ongoing word we're building
        if(curr.validWord){ //if this letter is the end of a word, add it to our set of answers
            answer.add(word);
        }
        visited.add(row + "," + col); //add this letter to visited set so we don't accidentally use it again when doing dfs on its neighbors

        //perform dfs in all the cardinal directions and diagonals
        //cardinals
        dfs(row+1, col, curr, word); //right
        dfs(row-1, col, curr, word); //left
        dfs(row, col + 1, curr, word); //up
        dfs(row, col - 1, curr, word); //down
        //diagonals
        dfs(row+1, col+1, curr, word); //up-right
        dfs(row+1, col-1, curr, word); //down-right
        dfs(row-1, col+1, curr, word); //up-left
        dfs(row-1, col-1, curr, word); //down-left

        visited.remove(row + "," + col); //remove the letter from visited so it can be used for future dfs of different dictionary words

        //pruning words from the Trie once we find them
        if(curr.children.isEmpty()){ //if curr node has no children to chack for
            node.children.remove(board[row][col]); //remove that node from trie
        }
    }


    public static void main(String[] args) {
        //Test here

        //all words
        String dictionary[] = {"Ace", "Ape", "Cape", "Clap", "Clay", "Gape", "Grape", "Lace",
                                "Lap", "Lay", "Mace", "Map", "May", "Pace", "Pay", "Rap",
                                "Ray", "Tap", "Tape", "Trace", "Trap", "Tray", "Yap"};
        //the board
        Character[][] board = {{'A', 'D', 'E'},
                            {'R', 'C', 'P'},
                            {'L', 'A', 'Y'}};


        System.out.println(boggle(board, dictionary)); //[RAP, ACE, LAY, CLAP, PAY, YAP, RAY, CAPE, LACE, PACE, APE, LAP, CLAY]

    }
}
