/* Question 6: Road Networks
Given a list of towns and a list of pairs representing roads between towns, return the number of road networks.

Example:
Input:
["Kona", "Hilo", "Volcano", "Lahaina", "Hana", "Haiku", "Kahului", "Princeville", "Lihue", "Waimea"],
[("Kona", "Volcano"), ("Volcano", "Hilo") ("Lahaina", "Hana"), ("Kahului", "Haiku"), ("Hana", "Haiku"), ("Kahului", Lahaina"), ("Princeville", "Lihue"), ("Lihue", "Waimea")]

Output: 2 (Networks are Kona-Hilo-Volcano, Haiku-Kahului-Lahaina-Hana, and Lihue-Waimea-Princeville)

Plan:
Can mostly reuse Q1 but adjust it for strings
1. Construct a graph using all the cities and roads
2. Use dfs to count all networks

Time: O(V + E) where V = # of vertices, E = # of edges
Space: O(V)
Data Structure / Graph Algorithm: Graph - DFS
Time taken:  40+ mins (it was confusing modifying Q1 for string inputs, and I think I should redo this)
 */

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

public class Q6_RoadNetworks{
    HashMap<String, Set<String>> graph; //adjacency list
    static String[] towns; //list of towns ["Kona", "Hilo", ...]
    static Pair[] roads; //list of roads [("Kona", "Volcano"), ("Volcano", "Hilo"), ... ]

    public Q6_RoadNetworks() {
        graph = new HashMap<>();
    }

    HashMap<String, Set<String>> adjacencySet(Pair[] edges) { //creates the graph (same as Q1, but with strings)
        for(Pair edge : edges) {
            String origin = edge.origin;
            String destination = edge.destination;
            //if the origin or the destination vertex does not exist yet, add it to the graph

            if (!graph.containsKey(origin)) { //if origin doesn't exist
                graph.put(origin, new HashSet<>());
            }

            if (!graph.containsKey(destination)) { //if destination doesn't exist
                graph.put(destination, new HashSet<>());
            }

            graph.get(origin).add(destination); //update the origin's set of destinations
        }
        return graph;
    }

    public int roadNetworks(String[] towns, Pair[] roads) {
        this.towns = towns;
        this.roads = roads;
        int networks = 0;

        boolean visited[] = new boolean[graph.size()]; //keep track of visited towns
        for (int townIndex = 0; townIndex < towns.length; townIndex++) { //for every town in the towns list
            if (!visited[townIndex]) { //if it has not been visited yet
                dfs(townIndex, visited, graph); //perform dfs
                networks++; //once dfs went as far as it could reach, increment networks counter
            }
        }
        return networks;
    }

    static void dfs(int start, boolean[] visited, HashMap<String, Set<String>> graph) {
        //visit nodes as far as one branch will allow;
        //stack (LIFO)
        Stack<Integer> stack = new Stack<>();
        //mark the start node as visited
        visited[start] = true;
        stack.add(start);

        while(!stack.isEmpty()) { //while there are still nodes left to be visited
            int vertex = stack.pop(); //visit the next vertex in stack

            for (String node : graph.get(towns[vertex])) { //for every adj. node to the current vertex
                if (!visited[ArrayUtils.indexOf(towns, node)]) { //visit the node and add it to the queue IF it wasn't visited before
                    visited[ArrayUtils.indexOf(towns, node)] = true;
                    stack.add(ArrayUtils.indexOf(towns, node));
                }
            }
        }
    }

    public static class Pair { //helper class to make pairs of cities (overrides Q1 Pair class that used ints)
        final String origin;
        final String destination;
        public Pair(String origin, String destination) {
            this.origin = origin;
            this.destination = destination;
        }

    }
    public static void main (String args[]) {
        //Test 1: Alaska
        Q6_RoadNetworks alaska = new Q6_RoadNetworks();
        String[] aTowns = {"Gustavus", "Homer", "Glacier Bay", "Fairbanks", "McCarthy", "Copper Center", "Healy"};
        Pair[] aRoads = {
                new Pair("Anchorage", "Homer"),
                new Pair("Glacier Bay", "Gustavus"),
                new Pair("Copper Center", "McCarthy"),
                new Pair("Anchorage", "Copper Center"),
                new Pair("Copper Center", "Fairbanks"),
                new Pair("Healy", "Fairbanks"),
                new Pair("Healy", "Anchorage")
        };

        alaska.adjacencySet(aRoads);
        System.out.println(alaska.roadNetworks(aTowns, aRoads)); // 2 --> [Homer, Glacier Bay] and [Anchorage, Fairbanks, McCarthy, Copper, Center, Homer, Healy]

        //Test 2: Hawaii
        Q6_RoadNetworks hawaii = new Q6_RoadNetworks();
        String[] hTowns = {"Kona", "Hilo", "Volcano", "Lahaina", "Hana", "Haiku", "Kahului", "Princeville", "Lihue", "Waimea"};
        Pair[] hRoads = {
                new Pair("Kona", "Volcano"),
                new Pair("Volcano", "Hilo"),
                new Pair("Lahaina", "Hana"),
                new Pair("Kahului", "Haiku"),
                new Pair("Hana", "Haiku"),
                new Pair("Kahului", "Lahaina"),
                new Pair("Princeville", "Lihue"),
                new Pair("Lihue", "Waimea")
        };

        hawaii.adjacencySet(hRoads);
        System.out.println(hawaii.roadNetworks(hTowns, hRoads)); // 2 --> [Kona, Hilo, Volcano] and [Haiku, Kahului, Lahaina] and [Lihue, Waimea, Princeville]

    }
}
