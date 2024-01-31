import java.util.ArrayList;

/*
* The class representing the table.
*
* Responsible for:
* Seating people at the table.
* Checking seats for people.
* Checking for neighbours.
* Printing the table.
*
* */
public class Table {

    /*List of seats at the table*/
    private static  ArrayList<String> seats = new ArrayList<>();

    public static int getNumSeats(){
        return seats.size();
    }

    /*getter for a seat.
    * if someone is seated the name is returned
    * If the string is empty, "Empty" is returned
    * If the input is out of bound an error message is returned.
    * */
    public static String getSeatedAt(int seat){
        if(seat > seats.size()){
            return  "-----------------------------------------\n"
                    + "ERROR! Not enough seats\n"
                    + "Number of seats: " + seats.size() +"\n"
                    + "Input: " + seat + "\n"
                    +"-----------------------------------------";
        }
        else if(seats.get(seat).equals("")){
            return "Empty";
        }
        return seats.get(seat);
    }

    private static int getNumOfEmptySeats(){
        int emptySeats = 0;
        for (String s: seats) {
            if(s.isEmpty()){
                emptySeats++;
            }
        }
        return emptySeats;
    }

    public static void printNumberOfSeats(){
        System.out.println("Number of seats: " + seats);
    }

    /*Constructor
    *
    * Adds seats equal to the number of people
    * */
    Table(){
        System.out.println("Persons Size: " + People.persons.size());
        for(int i = 0; i < People.persons.size(); i++){
            seats.add("");
        }
    }

    /*Seats a person at the table*/
    protected static boolean seatPerson(Person person, int seat){
        if(!seats.get(seat).equals("")){
            System.out.println("Seat is taken already taken!");
            return false;
        }
        seats.set(seat,person.name);
        return true;
    }

    /*"Stupid" seating that just seats the people around the table not using any algorithm to calculate happiness*/
    protected static void seatAllPeople(){
        int currentSeat = 0;
        for (Person p: People.persons) {
            seatPerson(p, currentSeat);
            currentSeat++;
        }
    }
    /*Seats all people, starting in alphabetical order and finding the best neighbours*/
    protected static void AlphabeticalHappinessSeating(){
        ArrayList<Integer> availableSeats = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++){
            availableSeats.add(i);
            System.out.println("availableSeats[" + i + "] added");
        }
        ArrayList<Person> nonSeatedPeople = People.persons;

        ArrayList<Person> seatedPeople = new ArrayList<>();
        String highestScorer;
        String secondHighestScorer;


        for (int i = 1; i < nonSeatedPeople.size()-1; i ++) {
            if(nonSeatedPeople.size() == 1 && availableSeats.size() == 1){
                seatPerson(nonSeatedPeople.get(0), availableSeats.get(0));
            }
            else if(nonSeatedPeople.size() == 2){

            }
            else {
                Person currentPerson = nonSeatedPeople.get(0);
                seatPerson(currentPerson, availableSeats.get(0));
                highestScorer = Calculate_Happiness.getNameOfHighestScorer(currentPerson.name);
                nonSeatedPeople.remove(highestScorer);
                secondHighestScorer = Calculate_Happiness.getHighestScorerFromRemaining(currentPerson.name, nonSeatedPeople);

                seatedPeople.add(People.getPerson(highestScorer));
                secondHighestScorer = Calculate_Happiness.getHighestScorerExcluding(currentPerson.name, seatedPeople);

                seatPerson(People.getPerson(highestScorer), getNeighbourSeats(availableSeats.get(0)).get(0));
                seatPerson(People.getPerson(secondHighestScorer), getNeighbourSeats(availableSeats.get(0)).get(1));
                nonSeatedPeople.remove(0);
            }
            if(availableSeats.size() > 3) {
                availableSeats.remove(0);
                availableSeats.remove(getNeighbourSeats(availableSeats.get(0)).get(0));
                availableSeats.remove(getNeighbourSeats(availableSeats.get(0)).get(1));
            }
        }
    }

    protected static void Alpha(String name){
        ArrayList<Integer> availableSeats = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++){
            availableSeats.add(i);
        }
        ArrayList<Person> seatedPeople = new ArrayList<>();



        String highestScorer;
        String secondHighestScorer;

            Person currentPerson = People.getPerson(name);
            seatPerson(currentPerson, availableSeats.get(0));
            highestScorer = Calculate_Happiness.getNameOfHighestScorer(currentPerson.name);
            seatedPeople.add(People.getPerson(highestScorer));
            secondHighestScorer = Calculate_Happiness.getHighestScorerExcluding(currentPerson.name, seatedPeople);

            System.out.println("highestScorer" +Calculate_Happiness.getHighestScorerFromRemaining(currentPerson.name, People.persons));
            System.out.println( "nonseatedPeople.remove: " + People.persons.remove(highestScorer));
            System.out.println( "secondHighestScorer: " + Calculate_Happiness.getHighestScorerFromRemaining(currentPerson.name, People.persons));

            System.out.println("Neighbours: ");
            System.out.println(getNeighbourSeats(availableSeats.get(0)).get(0));
            System.out.println(getNeighbourSeats(availableSeats.get(0)).get(1));

            seatPerson(People.getPerson(highestScorer), getNeighbourSeats(availableSeats.get(0)).get(0));
            seatPerson(People.getPerson(secondHighestScorer), getNeighbourSeats(availableSeats.get(0)).get(1));
    }

    protected static void SequentialSeating(){
        ArrayList<Integer> availableSeats = new ArrayList<>();
        for (int i = 0; i < seats.size(); i++){
            availableSeats.add(i);
            System.out.println("availableSeats[" + i + "] added");
        }
        ArrayList<Person> nonSeatedPeople = People.persons;
        ArrayList<Person> seatedPeople = new ArrayList<>();
        String highestScorer;


        for (int i = 1; i < nonSeatedPeople.size(); i ++) {
        Person currentPerson = nonSeatedPeople.get(0);
            for(int j = 0; j < availableSeats.size(); j++) {
                if(seatPerson(currentPerson, availableSeats.get(j))) {
                    seatedPeople.add(currentPerson);

                    highestScorer = Calculate_Happiness.getHighestScorerExcluding(currentPerson.name, seatedPeople);
                    boolean success = false;
                    for(int k = 0; k < 2; k++){
                        if(seatPerson(People.getPerson(highestScorer), getNeighbourSeats(availableSeats.get(j)).get(k))) {
                            seatedPeople.add(People.getPerson(highestScorer));
                            nonSeatedPeople.remove(highestScorer);

                            availableSeats.remove(0);
                            availableSeats.remove(getNeighbourSeats(availableSeats.get(0)).get(0));
                            success = true;
                            break;
                        }
                    }
                    if(!success){
                        System.out.println("No space!");
                    }

                    nonSeatedPeople.remove(0);
                break;
                }
            }
        }
        System.out.println("Seated People: ");
        for (Person p:seatedPeople) {
            System.out.print(p.name + ", ");
        }
        System.out.println("\n"+"NonSeated People: ");
        for (Person p:nonSeatedPeople) {
            System.out.print(p.name + ", ");
        }
        System.out.println("\n");
    }

    /*
    * Finds and returns the to neighbour-seats of a seat.
    * */
    protected static ArrayList<Integer> getNeighbourSeats(int seatNumber){
        ArrayList<Integer> neighbours = new ArrayList<>();
        if(seatNumber == 0){
            neighbours.add(getNumSeats()-1);
        }
        else{
            neighbours.add(seatNumber-1);
        }
        if(seatNumber != getNumSeats()-1){
            neighbours.add(seatNumber+1);
        }
        else{
            neighbours.add(0);
        }

        return neighbours;
    }

    protected static void MyNewList(){

        ArrayList<Integer> availableSeats = new ArrayList<>();

        for (int i = 0; i < seats.size(); i++){
            availableSeats.add(i);
            System.out.println("availableSeats[" + i + "] added");
        }
        int numbAvailSeats = availableSeats.size();
        ArrayList<Person> nonSeatedPeople = People.persons;
        ArrayList<Person> seatedPeople = new ArrayList<>();
        ArrayList<Person> calculatedPeople = new ArrayList<>();

        while(numbAvailSeats > 0) {
            if (seatedPeople.size() == 0) {
                seatPerson(nonSeatedPeople.get(0), availableSeats.get(0));
                seatedPeople.add(nonSeatedPeople.get(0));
                nonSeatedPeople.remove(0);
                calculatedPeople.add(nonSeatedPeople.get(0));
                numbAvailSeats--;
            }
            else {
                for (Person p : seatedPeople) {
                    for (Person p2 : calculatedPeople) {
                        if (!p.equals(p2)) {
                            calculatedPeople.add(p);
                            String nextPerson = Calculate_Happiness.getNameOfHighestScorer(p.name);
                            seatPerson(People.getPerson(nextPerson),availableSeats.get(0));
                            numbAvailSeats--;
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("Seated People: ");
        for (Person p:seatedPeople) {
            System.out.print(p.name + ", ");
        }
        System.out.println("\n"+"NonSeated People: ");
        for (Person p:nonSeatedPeople) {
            System.out.print(p.name + ", ");
        }
        System.out.println("\n");
    }

    /**
     * 1) Generate all combinations of seating.
     * 2) Calculate each combination
     * 3) Output the greatest Happiness Score ant the seating arrangement.
     *
     * */
    static void permute(ArrayList<Person> arr, int k){

        for(int i = k; i < arr.size(); i++){
            java.util.Collections.swap(arr, i, k);
            permute(arr, k+1);
            java.util.Collections.swap(arr, k, i);

        }
        if (k == arr.size() -1){
            System.out.println(java.util.Arrays.toString(arr.toArray()));
            
        }
    }
    static void seatPeople(){
        ArrayList<Person> unseatedPeople = People.persons;
        ArrayList<Person> seatedPeople = People.persons;
        int emptySeats = getNumOfEmptySeats();
        System.out.println("Number of empty seats: " + emptySeats);
        while(emptySeats > 0){
            if(emptySeats == People.getNumberOfPeople()) {
                ArrayList<Person> firstPair =Calculate_Happiness.getHighestScoringPair();
                for (Person p: firstPair) {
                    seatPerson(p, getFirstAvailableSeat());
                    seatedPeople.add(p);
                    unseatedPeople.remove(p);
                }
            }
            else{
                //Person tmp = new Person("");
                for(int i = 0; i < seatedPeople.size(); i++){
                    if(!seats.get(i).isEmpty()){
                        for (int position:getNeighbourSeats(i)) {
                            if(seats.get(position).isEmpty()){
                                seatPerson(People.getPerson(Calculate_Happiness.getNameOfHighestScorer(seats.get(i),unseatedPeople)), position);

                                // unseatedPeople.remove(People.getPerson(Calculate_Happiness.getNameOfHighestScorer(seats.get(i), unseatedPeople)));

                            }
                        }
                    }
                }
            }
            emptySeats = getNumOfEmptySeats();
            System.out.println("Number of empty seats: " + emptySeats);
        }
        System.out.println("Seated People: ");
        for (Person p:seatedPeople) {
            System.out.print(p.name + ", ");
        }
        System.out.println("\n"+"NonSeated People: ");
        for (Person p:unseatedPeople) {
            System.out.print(p.name + ", ");
        }
        System.out.println("\n");
    }

    private static int getFirstAvailableSeat() {
        for (int i = 0; i < getNumSeats(); i++){
            if (seats.get(i).isEmpty())
                return i;
        }
        return -1;
    }

}
