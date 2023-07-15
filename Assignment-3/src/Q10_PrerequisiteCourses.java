/* Question 10: Prerequisite Courses
Given a list of courses that a student needs to take to complete their major and a map of courses to their prerequisites,
return a valid order for them to take their courses assuming they only take one course for their major at once.

Example:
Input: ["Intro to Programming", "Data Structures", "Advanced Algorithms", "Operating Systems", "Databases"],
{ "Data Structures": ["Intro to Programming"], "Advanced Algorithms": ["Data Structures"], "Operating Systems": ["Advanced Algorithms"], "Databases": ["Advanced Algorithms"] }

Output: ["Intro to Programming", "Data Structures", "Advanced Algorithms", "Operating Systems", "Databases"]
or
["Intro to Programming", "Data Structures", "Advanced Algorithms", "Databases", "Operating Systems"]


Plan:
1. Reuse topological sort from question 1?

Time: O(V + E)
Space: O(N)
Data Structure / Graph Algorithm: Graph - Topological Sorting
Time taken:  40+ minutes (Spend too long trying to rewrite the problem for strings. Went for a simpler approach that can be improved )
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class Q10_PrerequisiteCourses extends Q1_AdjLists_Graph{
    public String[] schedule(String[] courses){
        String[] schedule = new String[courses.length];
        for (int course : topologicalSort(this.graph)) {
            schedule[course] = courses[course];
        }
        return schedule;
    }

    public static void main (String args[]) {

        //Test 1: CS courses
        String[] csCourses = {"Intro to Programming", "Data Structures", "Advanced Algorithms", "Operating Systems", "Databases"};
        // Prerequisites: { "Data Structures": ["Intro to Programming"], "Advanced Algorithms": ["Data Structures"], "Operating Systems": ["Advanced Algorithms"], "Databases": ["Advanced Algorithms"] }
        Pair[] vertices1 = {
                new Pair(1, 0),
                new Pair(2, 1),
                new Pair(3, 2),
                new Pair(4, 2)
        };

        Q10_PrerequisiteCourses graph1 = new Q10_PrerequisiteCourses();
        graph1.adjacencySet(vertices1);
        System.out.println(Arrays.toString(graph1.schedule(csCourses))); //[Intro to Programming, Data Structures, Advanced Algorithms, Operating Systems, Databases]

        //Test 2: English Courses
        String[] englishCourses = {"Intro to Writing", "Contemporary Literature", "Ancient Literature", "Comparative Literature", "Plays & Screenplays"};
        // Prerequisites: { "Contemporary Literature": ["Intro to Writing"], "Ancient Literature": ["Intro to Writing"],
        // "Comparative Literature": ["Ancient Literature", "Contemporary Literature"], "Plays & Screenplays": ["Intro to Writing"] }
        Pair[] vertices2 = {
                new Pair(1, 0),
                new Pair(2, 0),
                new Pair(3, 2),
                new Pair(3, 1),
                new Pair(4, 0)
        };

        Q10_PrerequisiteCourses graph2 = new Q10_PrerequisiteCourses();
        graph2.adjacencySet(vertices2);
        System.out.println(Arrays.toString(graph2.schedule(englishCourses))); //[Intro to Writing, Contemporary Literature, Ancient Literature, Comparative Literature, Plays & Screenplays]


    }
}
