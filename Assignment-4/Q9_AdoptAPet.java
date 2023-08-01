
import java.util.*;

/* Question 9: Adopt a Pet
"An animal shelter that houses cats and dogs wants to ensure no pet has to wait too long for a forever home.
Therefore, anyone who comes to adopt a pet can pick the species (cat or dog) but not the specific animal;
they are assigned the animal of that species that has been in the shelter longest.
If there are no animals available of the desired species, they must take the other species.
You are given a list of pets in the shelter with their names, species, and time in the shelter at the start of a week.
You receive a sequence of incoming people (to adopt pets) and animals (new additions to the shelter) one at a time.
Print the names and species of the pets as they are adopted out."



Plan:
1. Older cats and dogs are adopted before the newer ones. First In First Out basis --> Queue
2. Declare an Animal class that makes an Animal object and takes in arguments of a String name,
String species, and int time they spent in the shelter
3. Have one Queue for cats and a second Queue for dogs. Whenever a new animal is requested, pull the top
animal from the appropriate Queue. If the requested animal queue is empty, pull from the other one.
4. If input is a person, output the name and type of the dequed animal that they requested
5. If the input is an animal, no output, but add that animal to the appropriate queue

Notes:
1. From initial scan looks like a Queue or a Heap problem.
2. Fairly straigtforward once a Queue is implemented

Time: O(1) ? Because offer() and poll() take constant time
Space: O(N) ? for N given animals
Data Structure: Queue
Time taken: 30 min
 */

abstract class Animal { //general Animal class
    //abstract classes cannot be instantiated,
    String name;
    int time;
    public Animal(String n){
        name = n;
    }
    public void setTime(int t){
        //set time in shelter separately, bc on the spec, there are cases where time in the shelter is given with the animal name,
        //and also cases where it's not provided
        time = t;
    }
    public int getTime(){
        return time; //returns how old animal has been in the shelter
    }
}

class Dog extends Animal { //for Dog objects
    public Dog(String n) {super(n);}
}

class Cat extends Animal { //for Cat objects
    public Cat(String n) {super(n);}
}

public class Q9_AdoptAPet {
    //implement the Queue operations here
    Queue<Animal> dogs = new PriorityQueue<Animal>(10, new Comparator<Dog>() {
        public int compare(Animal d1, Animal d2) {
            if (d1.getTime() < d2.getTime()) { //older dogs in front of younger
                return -1;
            } else if (d1.getTime() > d2.getTime()){
                return 1;
            }
            return 0;
        }
    });
    Queue<Animal> cats = new PriorityQueue<Animal>(10, new Comparator<Cat>() {
        public int compare(Animal c1, Animal c2) {
            if (c1.getTime() < c2.getTime()) { //older cats in front
                return -1;
            } else if (c1.getTime() > c2.getTime()){
                return 1;
            }
            return 0;
        }
    });
    private int time = 0; //to keep track of animals, we can say that the first animal was inserted on day 0, and the days can increment by
    // 1 with each next animal to keep track of their order

    public void insert(Animal a){
        a.setTime(time);
        time++; //time increments globally --> every Animal ages by 1, so every older animal still gets adopted first
        if (a instanceof Dog){
            dogs.offer(a);
        } else if (a instanceof Cat){
            cats.offer(a);
        }
    }

    public String adopt(String a){
        if (cats.isEmpty() && dogs.isEmpty()){
            return "Sorry! No pets available right now.";
        }
        else if (a == "dog"){
            if (!dogs.isEmpty()){
                return dogs.poll().name;
            } else {
                return cats.poll().name;
            }
        }
        else {
            if (!cats.isEmpty()){
                return cats.poll().name;
            } else {
                return dogs.poll().name;
            }
        }
    }


    public static void main(String[] args){
        //copied from the spec
        Dog Sadie = new Dog("Sadie");
        Sadie.setTime(4);
        Cat Woof = new Cat("Woof");
        Woof.setTime(7);
        Dog Chirpy = new Dog("Chirpy");
        Chirpy.setTime(2);
        Dog Lola = new Dog("Lola");
        Lola.setTime(1);

        Q9_AdoptAPet shelter = new Q9_AdoptAPet();
        shelter.insert(Sadie);
        shelter.insert(Woof);
        shelter.insert(Chirpy);
        shelter.insert(Lola);

        System.out.println(Arrays.toString(shelter.dogs.toArray())); //[Sadie, Chirpy, Lola]
        System.out.println(Arrays.toString(shelter.cats.toArray())); //[Woof]

        //Bob, person, dog
        System.out.println(shelter.adopt("dog")); //Sadie (dog)

        //Floofy, cat
        Cat Floofy = new Cat("Floofy");
        shelter.insert(Floofy);

        //Sally, person, cat
        System.out.println(shelter.adopt("cat")); //Woof (cat)

        //Ji, person, cat
        System.out.println(shelter.adopt("cat")); //Floofy (cat)

        //ALi, person, cat
        System.out.println(shelter.adopt("dog")); //Chirpy (dog bc we ran out of cats)

        System.out.println(Arrays.toString(shelter.dogs.toArray())); //[Lola]
        System.out.println(Arrays.toString(shelter.cats.toArray())); //[]

    }
}


