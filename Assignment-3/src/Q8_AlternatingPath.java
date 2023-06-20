import java.util.Set;

/* Question 8: Alternating Path
Given an origin and a destination in a directed graph in which edges can be blue or red,
determine the length of the shortest path from the origin to the destination in which the edges
traversed alternate in color. Return -1 if no such path exists.


Example:
[(A, B, "blue"), (A, C, "red"), (B, D, "blue"), (B, E, "blue"), (C, B, "red"), (D, C, "blue"), (A, D, "red"), (D, E, "red"), (E, C, "red")]

Input: origin = A, destination = E
Output: 4 (path: A→D (red), D→C (blue), C→B (red), B→E (blue))

Plan:
1.

Time:
Space:
Data Structure / Graph Algorithm: Graph -
Time taken: 40+ minutes (I gave up trying to re-use Q1 and reimplemented graph class...)
 */
public class Q8_AlternatingPath {
    public static int alternatingPaths() {
        int paths = 0;

        return paths;
    }
    public static void main (String args[]) {
        String[][] graph = {
                {"A", "B", "blue"},
                {"A", "C", "red"},
                {"B", "D", "blue"},
                {"B", "E", "blue"},
                {"C", "B", "red"},
                {"D", "C", "blue"},
                {"A", "D", "red"},
                {"D", "E", "red"},
                {"E", "C", "red"}
        };

    }
}
