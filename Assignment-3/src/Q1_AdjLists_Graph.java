import java.security.KeyStore;
import java.util.*;
//import javafx.util.Pair;

/*Given an array of pairs of values representing edges in an unweighted graph,
    create the equivalent adjacency list/set representation (a map from element to a list or set of elements).
    // Example
    Input: [(1, 2), (2, 3), (1, 3), (3, 2), (2, 0)]
    Output:
    {
        0: []
        1: [2, 3]
        2: [0, 3]
        3: [2]
    }

    Notes:
    1. Having trouble with Pair class. Consider using Tuple or Map.Entry
    --> javafx.util.Pair library wouldn't import properly. Used Array instead.
    Spec says to use Pair. This is a C++ feature. There is a Pair class in java,
    but it doesn't work as well because it is not a build-in data structure. After struggling for some time with trying to
    import and use Pair, I felt it would be more efficient to just write my own Pair class. I would definitely rather write my
    own helper class in an interview than struggle to import something that may or may not work on the interviewing site.
 */

public class Q1_AdjLists_Graph {

    //helper Pair class
    public static class Pair {
        private final int origin;
        private final int destination;
        public Pair(int origin, int destination) {
            this.origin = origin;
            this.destination = destination;
        }
    }

    HashMap<Integer, Set<Integer>> graph;

    public Q1_AdjLists_Graph() {
        graph = new HashMap<>();
    }

    //Note: tried to use Pair (like in the spec), but IntelliJ does not allow to import javafx.util.Pair;
    // HashMap<Integer, Set<Integer>> adjacencySet(Pair<Integer, Integer>[] edges)
    HashMap<Integer, Set<Integer>> adjacencySet(Pair[] edges) {
        for(Pair edge : edges) {
            int origin = edge.origin;
            int destination = edge.destination;
            //if the origin or the destination vertex does not exist yet, add it to the graph

            if (!graph.containsKey(origin)) { //if origin doesn't exist
                graph.put(origin, new HashSet<Integer>());
            }

            if (!graph.containsKey(destination)) { //if destination doesn't exist
                graph.put(destination, new HashSet<Integer>());
            }

            graph.get(origin).add(destination); //update the origin's set of destinations
        }
        return graph;
    }

    //breadth first search for a target value
    //(visit all adj. vertices before moving on to the next vertex --> queue)
    static boolean bfs(int target, int start, HashMap<Integer, Set<Integer>> graph) {

        //visit nodes level by level
        boolean visited[] = new boolean[graph.size()];

        //originally picked the first val in HashMap as the starting node, but decided to specify it in args
        //int start = graph.entrySet().iterator().next().getKey();

        //queue
        Queue<Integer> queue = new PriorityQueue<>();
        //start node is already visited
        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) { //while there are still nodes to be visited
            int vertex = queue.poll(); //visit the next vertex in queue
            if (vertex == target) { return true; } //end here if it's the target

            //else
            for (int node : graph.get(vertex)) { //for every adj. node to the current vertex
                if (!visited[node]) { //visit the node and add it to the queue IF it wasn't visited before
                    visited[node] = true;
                    queue.add(node);
                }
            }
        }
        return false; //if the target node is not in the graph
    }

    //depth first search for a target value
    // (proceed as far down as possible before moving on to the next branch --> stack)
    static boolean dfs(int target, int start, HashMap<Integer, Set<Integer>> graph) {

        //visit nodes as far as one branch will allow
        boolean visited[] = new boolean[graph.size()];
        //stack (LIFO)
        Stack<Integer> stack = new Stack<>();
        //mark the start node as visited
        visited[start] = true;
        stack.add(start);

        while(!stack.isEmpty()) { //while there are still nodes left to be visited
            int vertex = stack.pop(); //visit the next vertex in stack
            if (vertex == target) { return true; } //end here if it's the target

            //else
            for (int node : graph.get(vertex)) { //for every adj. node to the current vertex
                if (!visited[node]) { //visit the node and add it to the queue IF it wasn't visited before
                    visited[node] = true;
                    stack.add(node);
                }
            }
        }
        return false;
    }

    /* implement topological sort (using either DFS or Kahn's algorithmm)
    I used Kahn's :
    1. Using queue, add nodes only with indegree of 0
    2. Repeatedly remove the nodes without any dependencies (indegree=0) from the queue and add them to the topological ordering

     */

    static int[] topologicalSort(HashMap<Integer, Set<Integer>> graph) {

        //find the indegree of each node
        int[] indegree = new int[graph.size()];
        for(int source : graph.keySet()) { //for each Integer source node in graph
            for(int destination : graph.get(source)) { //for each destination node in the Set of destinations
                indegree[destination]++; //increment the indegree of each destination
            }
        }

        //add only nodes with indegree = 0
        Queue<Integer> queue = new PriorityQueue<>();

        //add all nodes with indegree = 0 to the queue
        for(int node = 0; node < graph.size(); node++){
            if (indegree[node] == 0) {
                queue.offer(node);
            }
        }

        //dequeued nodes will go here --> topologically ordered using Kahn's
        int[] kahnsOrder = new int[graph.size()];
        int index = 0; //to keep track of the last filled spot in kahnsOrder

        while(!queue.isEmpty()) { //while there are still nodes to visit
            int source = queue.poll(); //get the next node (indeg = 0) from the queue
            kahnsOrder[index] = source; //add the node to the topological order
            index++; //increment the index spot for next node

            //decrement the indegrees of all destination nodes of the source node that we just removed
            for (int destination : graph.get(source)) {
                indegree[destination]--;
                //if a node's new indegree is now 0 --> add it to the queue
                if (indegree[destination] == 0) {
                    queue.offer(destination);
                }
            }
        }
        return kahnsOrder;
    }



    public static void main(String[] args) {
        //Test 1: From the spec
        Q1_AdjLists_Graph graph1 = new Q1_AdjLists_Graph();
        Pair[] vertices1 = {
                new Pair(1, 2),
                new Pair(2, 3),
                new Pair(1, 3),
                new Pair(3, 2),
                new Pair(2, 0)
        };

        graph1.adjacencySet(vertices1);
        for (int vertex : graph1.graph.keySet()){
            System.out.println(vertex + ": " + graph1.graph.get(vertex));
        }
        /*
        0: []
        1: [2, 3]
        2: [0, 3]
        3: [2]
         */

        //bfs
        System.out.println(bfs(0, 1, graph1.graph)); //true --> 0 is in the graph
        //visiting order: 1, 2, 3, 0
        System.out.println(bfs(5, 1, graph1.graph)); //false --> 5 is not in the graph
        //visiting order: 1, 2, 3, 0

        //dfs
        System.out.println(dfs(0, 1, graph1.graph)); //true --> 0 is in the graph
        //visiting order: 1, 2, 0, 3
        System.out.println(dfs(6, 1, graph1.graph)); //false --> 6 is not in the graph
        //visiting order: 1, 2, 0, 3

        //topological using Kahn's
        System.out.println(Arrays.toString(topologicalSort(graph1.graph))); //[1 0 0 0]
        //IMPORTANT: Kahn's stops working after visitng Node 1 because this example has a cycle (Nodes 2 and 3).
        //Kahn's Algorithm works properly only on directed, acyclic graphs.
        //for a working example, see Test 2


        //Test 2: Bigger graph
        Q1_AdjLists_Graph graph2 = new Q1_AdjLists_Graph();
        Pair[] vertices2 = {
                new Pair(5, 2), new Pair(2, 3), new Pair(1, 8), new Pair(8, 0),
                new Pair(5, 8), new Pair(1, 6), new Pair(9, 0), new Pair(0, 4),
                new Pair(7, 10), new Pair(4, 7)
        };

        graph2.adjacencySet(vertices2);
        for (int vertex : graph2.graph.keySet()){
            System.out.println(vertex + ": " + graph2.graph.get(vertex));
        }
        /*
        0: [4]     4:[7]       8: [0]
        1: [6, 8]  5: [2, 8]   9: [0]
        2: [3]     6: []       10: []
        3: []      7: [10]
         */

        //bfs
        System.out.println(bfs(3, 5, graph2.graph)); //true --> 3 is in the graph
        //visiting order: 5, 2, 8, 3
        System.out.println(bfs(11, 5, graph2.graph)); //false --> 7 is not in the graph
        //visiting order: 5, 2, 8, 3, 0, 4, 7, 10

        //dfs
        System.out.println(dfs(10, 5, graph2.graph)); //true --> 45 is in the graph
        //visiting order: 5, 2, 3, 8, 0, 4, 7, 10
        System.out.println(dfs(12, 5, graph2.graph)); //false --> 20 is not in the graph
        //visiting order: 5, 2, 3, 8, 0, 4, 7, 10

        //topological using Kahn's
        System.out.println(Arrays.toString(topologicalSort(graph2.graph))); //[1, 5, 2, 3, 6, 8, 9, 0, 4, 7, 10]
        //This Kahn's works because the graph is acyclic :)

    }
}
