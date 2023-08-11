/* Question 6: Word Break
"Given a string of characters without spaces and a dictionary of valid words,
determine if it can be broken into a list of valid words by adding spaces. "

Plan:
1. Have a cache with size = length of s + 1 extra space for the base case of the end of s
2. Every index in cache[] initialized as false, bc we have yet to check if it contains any of the words.
3. But base case at chase[s.length()] = true.
4. Iterate s char by char starting at the back of the string
5. Iterate dictinary word by word
6. IF a word can reasonably fit into s, starting from curr index i
7. and IF that substring starting at i is actually equal to the curr word we're considering
8. Mark that spot in the cache as true. So now we know that starting at this index, there is/are valid dict word/s contained in this string s
9. If a spot in the cache has been marked as true, break out of the current i loop to avoid rechecking for every other word in the dict for this spot
that we know will definitely not fit now
10. If the whole s is broken up into smaller words correctly, cache[0] will be true. Else, the s was not composed of valid words from dict.
Return cache[0]

Notes:
1. There's a way to do it dynamically by straversing s from the front or from the back. As far as I can see, both methods are valid and take the same time and space.
2. I think there's a way to do it with Trie too but when I tried to implement it, it seemed too long.
Does it significantly improve time by having a trie? Would it be worth doing over just Tabulation or Memoization?

Time: O(N * M * K) where N = length of s, M = size of dictionary, K = avg length of a word in dictionary
Space: O(N) used to make a cache boolean array
Data Structure: Dynamic Programming - Tabulation (Bottom-up)
Time taken: 40 minutes
 */

public class Q6_WordBreak {

    public static boolean wordBreak(String s, String[] dictionary){
        //boolean array to mark if cache[i] contains the beginning of a valid dictionary word + 1 extra space to mark the end of the word
        //initialzed with all False
        boolean[] cache = new boolean[s.length()+1];
        //the last position out of bounds of the string is out base case --> set to True
        cache[s.length()] = true;

        for (int i = s.length()-1; i > -1; i--){ //iterate the string in reverse
            for(String word : dictionary){ //for every word available in dict
                if (i + word.length() <= s.length()){ //if we're deep enough in s where word can possible fit in as a part s starting at index i
                    if (s.substring(i, i + word.length()).equals(word)){ //AND if that substring starting at i is actually equal to the dictionary word we're considering
                        //cache[i] = true; //mark this index in cache as the beginning of a valid word
                        cache[i] = cache[i + word.length()];
                    }
                }
                if (cache[i]){ //once you found a word that fits in this s interval, no need to check the rest of the words in dict
                    break;
                }
            }
        }

        //if the whole s was successfully broken into smaller words from dict, index 0 in cache will be changed to true
        return cache[0];
    }

    public static void main(String[] args){
        String[] dictionary = new String[]{"elf", "manatee", "quip", "go", "golf",
        "not", "tee", "note", "teen", "man", "pig"};


        System.out.println(wordBreak("mangolf", dictionary)); //true ("man", "golf")

        System.out.println(wordBreak("manateenotelf", dictionary)); //true ("manatee", "not", "elf")

        System.out.println(wordBreak("quipig", dictionary)); //false

    }
}
