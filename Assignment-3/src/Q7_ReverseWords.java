import java.util.Stack;

/* Question 7: Reverse Words
Given a string, return the string with the order of the space-separated words reversed

Example:
Input: "Emma lives in Brooklyn, New York."
Output: "York. New Brooklyn, in lives Emma"

Plan:
1. Create a stack
2. Pass the string into the stack
3. For a reverse string by pushing from stack until it's empty

Time: O(N)
Space: O(N)
Data Structure / Graph Algorithm: Stack:) Last In Last Out
Time taken:  10 minutes
 */
public class Q7_ReverseWords {

    public static String reverse(String input) {
        StringBuilder reverse = new StringBuilder(); //just learned about StringBuilder! it's easier that using += to add to a string all the time
        String[] arr = input.split(" "); //separate all the words in the input at spaces
        Stack<String> stack = new Stack<>();
        for(String word : arr) { //add every word to the stack
            stack.push(word);
        }

        while(!stack.isEmpty()) { //push out every word and add spaces in between
            reverse.append(stack.pop());
            reverse.append(" ");
        }
        return reverse.toString();
    }

    public static void main (String args[]) {

        //Test 1
        System.out.println(reverse("Uber Career Prep")); //Prep Career Uber

        //Test 2
        System.out.println(reverse("Emma lives in Brooklyn, New York.")); //York. York. New Brooklyn, in lives Emma

        //Test 3: Empty
        System.out.println(reverse("")); //

    }

}
