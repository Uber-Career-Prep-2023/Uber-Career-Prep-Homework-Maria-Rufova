import edu.princeton.cs.algs4.In;

import java.util.*;

/* Question 8: Alternating Path
Given an origin and a destination in a directed graph in which edges can be blue or red,
determine the length of the shortest path from the origin to the destination in which the edges
traversed alternate in color. Return -1 if no such path exists.


Example:
[(A, B, "blue"), (A, C, "red"), (B, D, "blue"), (B, E, "blue"), (C, B, "red"), (D, C, "blue"), (A, D, "red"), (D, E, "red"), (E, C, "red")]

Input: origin = A, destination = E
Output: 4 (path: A→D (red), D→C (blue), C→B (red), B→E (blue))

Plan:
1. Represent all nodes numerically (A = 0, B =1, ...) and red edge = 0 and blue edge = 1
2. Create an adj list in form of a Hashmap that maps each node to a list with each entry being a destination + edge color btw them
3. Create an int[] answer to record the min path lengths between each node and the origin
4. Create a Queue that takes in triplet list of {curr node, steps taken to get there, prev edge color used}
5. While the queue is not empty, poll next elem, loop through all of elem's neighbors. If a neighbor has not been visited by
that color edge yet and prev color != curr color, visit it and enque it. If it's the node's first visit, update it's entry
in int[] answers with steps + 1.
6. Return answers[destination]

Time: O(V + E)
Space: O(V + E)
Data Structure / Graph Algorithm: BFS (finding a shortest path + taking account of alternating color paths)
Time taken: 40+ minutes (I gave up trying to re-use Q1)

Notes:
1. Major brain freeze. Like I understand that this is a BFS problem and I just have to find the
shortest path from A to B while also checking that color pathways alternate. I was trying to re-use
the Graph implementation from Q1, but I don't think that is the way to go. I switched to treating this
question not like a HW assignment but as an interview, where I would not have access to a previously
implemented Graph class.
2. How to format the input? A list of String lists containing origin, destination, and color?
    {{"A", "B", "red}, {"C", "B", blue}} ?
3. After struggling to do this with Strings, I converted all String inputs to Integers.
So node "A" is now node 0, node "B" is now node 1, etc.
Most of the problem was easy enough to adject for String inputs, but I got majorly stuck on how to update
the "visited" boolean array, as the indexes of items in the boolean array need to correspond to the value of the node.
So like, if I need to mark node "A" as visited, I can't use type visited["A"] = true. It forces me to do
visited[1] = true because the index needs to be a number. At this point I basically started giving every String Node a representation
in an Integer Node, things started to get covoluted, and I decided to redo this question from the start, now using only
Integers to represent Nodes and edge colors. This doesn't change the algorithm that I intended to use for the problem. It just
simplifies some logistics and allows me to have a cleaner solution.
4. red edge = 0
   blue edge  = 1
 */

public class Q8_AlternatingPath {
    public static int alternatingPaths(Integer[][] red, Integer[][] blue, int origin, int destination) {
        /* the graph is represented by a Hashmap.
        every Entry is matching origin Int to an ArrayList with a destination Int and the edge color between them
         */
        Map<Integer, List<List<Integer>>> graph = new HashMap<>();
        //save all the nodes + their destinations + path colors in the graph adjency list
        /*
        computeIfAbsent method: "method of HashMap class is used to compute value for a given key using the given mapping function,
        if key is not already associated with a value"
        @source: geeksforgeeks.org/hashmap-computeifabsent-method-in-java-with-examples/
         */
        for(Integer[] redEdge : red){ //for every red edge
            /*
            if the origin (redEdge[0]) has not yet been added to the HashMap,
            make redEdge[0] a new key + make an empty ArrayList of String ArrayLists to store destinations + edge colors
            save the destination (redEdge[1]) and the edge color to the origin's list of destinations
            Ex: { "A": {"B", "red"}, {"C", "blue} , "B": {"A", "red"}, {"D", "red"} , etc other nodes}
             */
            graph.computeIfAbsent(redEdge[0], k -> new ArrayList<List<Integer>>()).add(Arrays.asList(redEdge[1],0));
        }
        for(Integer[] blueEdge : blue){ //same procedure for blue edges, just changing the color
            graph.computeIfAbsent(blueEdge[0], k -> new ArrayList<List<Integer>>()).add(Arrays.asList(blueEdge[1],1));
        }

        //answer array where answer[node] is the distance from that node to the origin node
        int[] answer = new int[graph.size()];
        Arrays.fill(answer, -1); //initialize dist to every unvisited node as -1

        //2D visited array where visited[node][color] indicates whether a node has been visited using an edge of color
        boolean[][] visited = new boolean[graph.size()][2];

        //Queue of Triplets: { curr node, steps taken to visit the node, color of prev edge used}
        Queue<int[]> queue = new LinkedList<>();
        //start at the origin node
        //"origin" node has 0 steps and no specific color of visit (-1)
        queue.offer(new int[] {origin, 0, -1});
        answer[origin] = 0; //dist to origin is 0
        visited[origin][1] = visited[origin][0] = true; //mark origin node as visited (from both edge colors)

        while(!queue.isEmpty()) { //while there are still nodes to visit
            int[] element = queue.poll(); //remove the first queue elem to obtain [node, steps, prevColor]
            int node = element[0], steps = element[1], prevColor = element[2];

            if(!graph.containsKey(node)) { //if we accidentally got a node not in the graph, skip it and continue
                continue;
            }

            for(List<Integer> neighbor : graph.get(node)) { //loop through all neihgbor nodes of the curr node (and the edge colors btw them)
                int n = neighbor.get(0); //neighbor node
                int edgeColor = neighbor.get(1); //edge color btw them
                //if the neighbor has not yet been visited with a color edge and this edgeColor != prevColor
                if(!visited[n][edgeColor] && edgeColor != prevColor){

                    if (answer[n] == -1) { //if this is neighbor's first visit, update its distance in the answer array
                        answer[n] = steps + 1;
                    }

                    visited[n][edgeColor] = true; //visit this neighbor
                    queue.offer(new int[] {n, 1+steps, edgeColor}); //update its steps and offer it to queue

                }
            }
        }
        return answer[destination]; //return the min path length to destination
    }
    public static void main (String args[]) {
        /*
        [(A, B, "blue"), (A, C, "red"), (B, D, "blue"), (B, E, "blue"),
        (C, B, "red"), (D, C, "blue"), (A, D, "red"), (D, E, "red"), (E, C, "red")]
         */
        //Nodes will be represented numerically (A = 0, B = 1, ...)
        //edge colors: red = 0, blue = 1
        //Graph is passed in as two String lists of red and blue edges
        Integer[][]red = {{0, 2},{2, 1}, {0, 3}, {3, 4}, {4, 2}};
        Integer[][]blue = {{0, 1}, {1, 3}, {1, 4}, {3, 2}};

        System.out.println(alternatingPaths(red, blue, 0, 4)); //4
        // A --> D --> C --> B --> E
        //  red    blue  red   blue

        System.out.println(alternatingPaths(red, blue, 4, 3)); // -1
        //The only path is
        // E --> C --> B --> D
        //  red   red   blue

    }
}
