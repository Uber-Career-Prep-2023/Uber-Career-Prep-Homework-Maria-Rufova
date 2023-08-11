/* Question 11: Vacation Destination
Given an origin city, a maximum travel time k, and pairs of destinations that can be reached directly from each other
with corresponding travel times in hours, return the number of destinations within k hours of the origin.
 Assume that having a stopover in a city adds an hour of travel time.

Example:
Input: [("Boston", "New York", 4), ("New York", "Philadelphia.", 2), ("Boston", "Newport", 1.5),
("Washington, D.C.", "Harper's Ferry", 1), ("Boston", "Portland", 2.5), ("Philadelphia", "Washington, D.C.", 2.5)]

Origin = "New York", k=7
Output: 2 (["Boston", "Philadelphia", "Washington, D.C", "Newport"])

Plan:
1. Graph is represented by a Hashmap where:
    Key: String of a city name
    Value: Destination instance that contains a Key city's String destination + Double travel time
2. An ArrayList to store all possible destination (changed to array from an int to check for accidentally added duplicates)
3. Add Destinations to a stack. Traverse while DFS.
4. Add origin Destination to a stack. While stack is not Empty, pop the next city.
5. If that popped city's destination is not origin, that means it is connected to other cities besides a direct connection to the origin
So we found a stopover, add +1 to travel time. Add the city to list of destinations.
6. If city.destination has other further destinations AND they are reachable within k travel time, add the next destinatin to the stack.
7. After visiting every city, return the size of answer Arraylist

Time: O(V + E)
Space: O(V + E)
Data Structure / Graph Algorithm: Graph - DFS Something weighted
Time taken:  40+ minutes

Notes:
1. Advice from Wonee:
    (a) About the approach to the problem:
    "This requires you to build a graph from the matrix parameter and use DFS.
    This problem is equivalent to count nodes within k distance of source in a weighted graph with
    one additional step: incrementing the total path weight by one for each node after the sources.
    Branches can be trimmed once the total path weight exceeds k (k being the travel time)

    (b) How do I trim branches once they exceed travel time?
    "after you construct your graph, you will transverse your graph using DFS.
    As you keep track of the current travel time compare it to the max travel time (k),
    if it exceeds it, stop exploring further branches (terminate dfs). That’s trimming"

    (c) How do I format the input of the function? On the spec it looks like an array of arrays, but the inner arrays don't have a unique type of variable stored inside them?
        like [("Boston", "New York", 4), ("New York", "Philadelphia.", 2), ("Boston", "Newport", 1.5)]
        what data structure would allow me to contain ("Boston", "New York", 4) ?
        The only thing I can think of is like a hashmap with 4 being the key and Boston and New York being the values mapped to 4
    "A hash map where the travel time is the key wouldn’t work since other pairs could have the same travel times.
     You could construct a map where origin city is the key and it maps to an array of a class that holds a destination
     city and a travel time or you could make a matrix of type object. But when you access the value, you should cast it."

 */

import java.util.*;

public class Q11_VacationDestinations {

    //stores a destination and the time it takes to get to them from a certain origin
    //(origin is not stored here; rather, it is a key in a Hashmap mapped to a value of class Destination)
    static class Destination {
        private final String destination;
        private final double time;
        public Destination(String destination, double time) {
            this.destination = destination;
            this.time = time;
        }
    }


    public static int vacationDestinations(String origin, double k, Map<String, ArrayList<Destination>> map){
        ArrayList<String> answer = new ArrayList<>(); //destinations you can reach from origin in max k time

        Stack<Destination> stack = new Stack<>(); //push cities to be visited into a stack
        stack.add(new Destination(origin, 0)); //add origin city to the stack
        int stopover = 0; //if we stop in a layover city, stopover time will be 1

        while(!stack.isEmpty()) { //while there are still cities to be visited
            Destination city = stack.pop(); //get the next city from the stack
            //if city's destination != origin city (aka city is connected to other destinations besides a direct connection to origin) AND it is NOt already recorded in the answer list
            if(city.destination != origin && !answer.contains(city.destination)) {
                stopover = 1; //we found a layover city --> increase stopover time
                answer.add(city.destination); //and add it to the list of destinations
            }
            if (map.get(city.destination) != null) { //if there are further destinations beyond this curr destination -> aka is dfs can continue down this branch

                ArrayList<Destination> further = map.get(city.destination); //get a list of further cities on this branch
                for (Destination d : further) { //for every further destination

                    if(city.time + d.time + stopover <= k){ //if adding this further destination (+ stopover) doesn't exceed our total travel time
                        //add this next destination and the total travel time it takes to get there to the stack
                        stack.add(new Destination(d.destination, city.time + d.time + stopover));
                    }
                }
            }
        }
        return answer.size();
    }
    public static void main (String args[]) {
        //hashmap where key is origin city, value is Destination with a destination city and travel time from the origin
        Map<String, ArrayList<Destination>> map = new HashMap<>();
        /*
        Input: [("Boston", "New York", 4), ("New York", "Philadelphia.", 2), ("Boston", "Newport", 1.5),
        ("Washington, D.C.", "Harper's Ferry", 1), ("Boston", "Portland", 2.5), ("Philadelphia", "Washington, D.C.", 2.5)]
         */
        //Insert all city connections. The graph is UNDIRECTED, so every origin is also a destination (ergo you can go Boston -> NY and NY -> Boston).
        map.put("Boston", new ArrayList<>());
        map.get("Boston").add(new Destination("New York", 4));
        map.get("Boston").add(new Destination("Newport", 1.5));
        map.get("Boston").add(new Destination("Portland", 2.5));

        map.put("New York", new ArrayList<>());
        map.get("New York").add(new Destination("Boston", 4));
        map.get("New York").add(new Destination("Philadelphia", 2));

        map.put("Philadelphia", new ArrayList<>());
        map.get("Philadelphia").add(new Destination("New York", 2));
        map.get("Philadelphia").add(new Destination("Washington D.C.", 2.5));

        map.put("Newport", new ArrayList<>());
        map.get("Newport").add(new Destination("Boston", 1.5));

        map.put("Washington D.C.", new ArrayList<>());
        map.get("Washington D.C.").add(new Destination("Harper's Ferry", 1));
        map.get("Washington D.C.").add(new Destination("Philadelphia", 2.5));

        map.put("Harper's Ferry", new ArrayList<>());
        map.get("Harper's Ferry").add(new Destination("Washington D.C.", 1));

        map.put("Portland", new ArrayList<>());
        map.get("Portland").add(new Destination("Boston", 2.5));

        /* Tests from the Spec:
        Test 1
        Origin = "New York", k = 5
        Output: 2  [Boston (4 hours, NY -> Boston) and Philadelphia (2 hours, NY -> Phil)]
         */
        System.out.println(vacationDestinations("New York", 5, map));

        /*Test 2
        Origin = "New York", k=7
        Output: 4  [Boston (4 hours, NY -> Boston) , Philadelphia (2 hours, NY -> Phil)] , Wash D.C. (5.5 hours, NY-> Philly-> DC), Newport (6.5 hours, NY-> Boston-> Newport)]
         */
        System.out.println(vacationDestinations("New York", 7, map));

        /*Test 3
        Origin = "New York", k=8
        Output: 6  [Boston (4 hours, NY -> Boston) , Philadelphia (2 hours, NY -> Phil)] , Wash D.C. (5.5 hours, NY-> Philly-> DC), Newport (6.5 hours, NY-> Boston-> Newport),
                    Harper's Ferry (7.5 hours, NY-> Philly-> DC-> Harper's Ferry), Portland (6.5, NY-> Boston-> Portland)]
         */
        System.out.println(vacationDestinations("New York", 8, map));

        /*
        More tests:
        Test 4
        Origin = Newport, k = 2
        Output: 1 (Boston)
         */
        System.out.println(vacationDestinations("Newport", 2, map));

       /* Test 5
        Origin = Washington D.C., k = 6
        Output: 3 (harper's Ferry, Philadelphia, New York)
         */
        System.out.println(vacationDestinations("Washington D.C.", 6, map));

        /* Test 6 (Empty)
        Origin = "", k = 0
        Output: 0
         */
        System.out.println(vacationDestinations("", 0, map));


    }
}
