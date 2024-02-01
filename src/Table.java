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

    protected int seatAndScorePeople(){
        int totalScore = 0;
        ArrayList<Person> copyPersons = People.persons;
        ArrayList<Person> startingPair = Calculate_Happiness.getHighestScoringPair();

        int seatsLeft = getNumSeats();

        int nextSeat = 0;

        for (Person p: startingPair) {
            seatPerson(p, nextSeat);
            nextSeat++;
            seatsLeft--;
            System.out.println("Setting seats");
        }

        totalScore += Calculate_Happiness.calcHappinessBetweenTwo(startingPair.get(0).name, startingPair.get(1).name);
        copyPersons.remove(startingPair.get(0));


        while (seatsLeft > 0){
            Person currentPerson = People.getPerson(getSeatedAt(nextSeat-1));
            Person newNeighbor = People.getPerson(Calculate_Happiness.getNameOfHighestScorer(currentPerson.name, copyPersons));
            seatPerson(newNeighbor, nextSeat);
            totalScore += (Calculate_Happiness.findHighestTotal(currentPerson.name, copyPersons));
            copyPersons.remove(currentPerson);
            nextSeat++;
            seatsLeft--;
        }
        System.out.println("Final totalScore: " + totalScore +"\n");

        System.out.print("Final table: \n" + "{ ");
        for (String seat: seats) {
            System.out.print(seat + ", ");
        }
        System.out.println("}");
        return totalScore;
    }
}
