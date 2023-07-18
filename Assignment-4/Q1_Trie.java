/*
Question 1: Build a Tree
"Implement a trie class, including the insert, search, and delete methods."

Notes:
1. Trie advantages:
    a. Fast search (search time is dir. proportional to the length of the key)
    b. Space-efficient (stores only chars present in keys, and not entire keys themmselves)
    c. Efficient insertion, deletion, sorting. Used widely in autocomplete.
2. Why use Trie?
    a. Insert and find strings in O(L) time, where L is the length of a word --> faster than BST and Hashing
    b. Easily print all words in alphabetical order
    c. Efficient prefix search and autocomplete
3. Disadvantages:
    a. A lot of memory usage to stor strings

References:
 1. Lecture slides: https://docs.google.com/presentation/d/1Xvm1BB6WeUwYrwRe8QmcRscD0ivqOlLyWBhYQrDxzHQ/edit#slide=id.g46b429e30_0384
 2. Trie Intro / Advantages: https://www.geeksforgeeks.org/advantages-trie-data-structure/
 3. Insert/search examples: https://www.geeksforgeeks.org/trie-insert-and-search/
 4. Delete: https://www.geeksforgeeks.org/trie-delete/

 Time of search and insert: O(key_length)
 Space of a whole trie: O(Alphabet_size * key_length * number_of_keys_in_trie)
 */


import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.Locale;

public class Q1_Trie {

    private TrieNode root;
    public Q1_Trie() {
        root = new TrieNode(); //initialize the trie
    }

    private class TrieNode {
        private TrieNode[] children; //a (resizeable or fixed size) array of size 26
        private boolean validWord; // boolean to indicate if this node marks the end of a word
        public TrieNode(){
            this.children = new TrieNode[26];
            this.validWord = false;
        }
    }

    //adds a word to the Trie
    // Time: O(L), where L is the length of the key
    public void insert(String word) {
        word = word.toLowerCase();
        TrieNode curr = root; //start at root
        for(int i = 0; i < word.length(); i++){ //for every letter in the word
            char letter = word.charAt(i); //the letter
            int index = letter - 'a'; //the letter's number in an alphabet (ex: a=0, b = 1, c = 2, ..., z = 25)
            //so if word is cat, then when i =0, letter = 'c', and index = 2
            if(curr.children[index] == null){ //if a letter is not one of curr's branches yet
                TrieNode node = new TrieNode(); //make a new TrieNode
                curr.children[index] = node; //this letter's spot in the children array of curr is now filled! meaning this letter now exists!
                /*
                 o for example, after inserting a 'c', root's children now looks like [null, null, *TrieNode(), null...]
                 */
                curr = node; //update curr to the letter you just inserted
            } else { //else if letter is already in the Trie, move to it
                curr = curr.children[index];
            }
        }
        curr.validWord = true; //after you finished inserting the word, mark the last letter as the end of it
    }

    //returns a boolean indicating whether word is in the trie
    // Time: O(L)
    public boolean isValidWord(String word) {
        TrieNode curr = root;
        for (int i =0; i < word.length(); i++){
            char letter = word.charAt(i); //current letter (like 'c')
            int index = letter - 'a'; //letter's index in alphabet (like 'c' = 2)
            if (curr.children[index] == null) { //if this letter is not a Trie node, the word is not in Trie
                return false;
            }
            curr = curr.children[index]; //if the letter is found, keep checkng next letters
        }
        return true; //return true if all letters in 'word' has been found
    }

    //removes word from the trie & deletes unused nodes
    //Time: O(L)
    //Note: delete bottom-up using recursion. if we want to delete "bye", we should not also accidentally delete "by"

    public void remove(String word) { //recursive
        removeHelper(root, word, 0); //start at the empty root of the whole Trie
    }
    public TrieNode removeHelper(TrieNode curr, String word, int depth){
        if (curr == null) {
            return null;
        }
        if(depth == word.length()){ //if we're at the last letter
            if (curr.validWord){
                curr.validWord = false; //it
            }
            for(int i=0; i < curr.children.length; i++){ //empty the children in case word was a prefix to other later words
                curr.children[i] = null;
            }
            return curr;
        }
        //if not the last character, keep recurring
        int index = word.charAt(depth)-'a';
        curr.children[index] = removeHelper(curr.children[index], word, depth+1);

        return curr;
    }


    public static void main(String[] args) {
        //Test here
        Q1_Trie t = new Q1_Trie();

        String keys[] = {"the", "a", "there", "answer", "any", "by", "bye", "their"};
        for (String word : keys){
            t.insert(word);
        }
        System.out.println("Words inserted.");

        System.out.println(t.isValidWord("answer")); //true, "answer" is in Trie
        System.out.println(t.isValidWord("ans")); //false, "ans" is in Trie, but it is not a complete word
        System.out.println(t.isValidWord("bye")); //true
        System.out.println(t.isValidWord("sunshine")); //false

        t.remove("bye");
        System.out.println(t.isValidWord("bye")); //false
        System.out.println(t.isValidWord("by")); //true


    }
}
