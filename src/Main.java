public class Main {

    public static void main(String args[]){


        People people = new People();
        people.initializePeople();
        people.printListOfPersons();


        /*No Crash with a non-existing person*/
        Person misingno = People.getPerson("misigno");

        Table table = new Table();
        table.seatAndScorePeople();
    }

}
