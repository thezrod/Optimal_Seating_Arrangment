public class Main {

    public static void main(String args[]){


        People people = new People();
        people.initializePeople();
        people.printListOfPersons();


        //ReadInput ri = new ReadInput();

      //  ri.readFile();
      //  ri.printData();
       // ri.printDataForPerson("Alice");
      //  ri.setRelations("Alice");

       // Person alice = new Person("Alice", people);
       // alice.printPrefs();

       // ri.getAllPersons();



        /*No Crash with a non-existing person*/
        Person misingno = People.getPerson("misigno");

       /* Person bob = People.getPerson("Bob");
        bob.printPrefs();
        bob.getHappinessScoreByName("David");
        bob.getHappinessScoreByName("Martin");
        bob.getHappinessScoreByName("Bob");

        Calculate_Happiness.calcHappinessBetweenTwo("Bob", "David");
        System.out.println("Max Happiness: " + Calculate_Happiness.findHighestTotal("Bob"));
        */

       /*
        Person[] personArray = People.persons.toArray(new Person[0]);
        String[] stringArray = new String[personArray.length+1];
        stringArray[0] = " ";
        for (int i = 0; i < personArray.length;i++){
            stringArray[i+1] = personArray[i].name;
        }
        */

       // Printer.printTable(stringArray);


        Table table = new Table();
        /*
        table.printNumberOfSeats();
        System.out.println(Table.getSeatedAt(10));
        System.out.println(Table.getSeatedAt(0));
        //table.seatPerson(bob,0);
        table.printNumberOfSeats();
        Table.AlphabeticalHappinessSeating();
        */

        /*
        System.out.println("Neighbours to 0: " + Table.getNeighbourSeats(0));
        System.out.println("Neighbours to 7: " + Table.getNeighbourSeats(7));
        System.out.println("Neighbours to 2: " + Table.getNeighbourSeats(2));
        System.out.println("Neighbours to 3: " + Table.getNeighbourSeats(3));
        System.out.println("Neighbours to 5: " + Table.getNeighbourSeats(5));
        */

       // System.out.println(Calculate_Happiness.findHighestTotal("Alice"));
        //System.out.println("Alice's highest scorer: " + Calculate_Happiness.getNameOfHighestScorer("Alice"));
        //Table.seatAllPeople();
        //Table.AlphabeticalHappinessSeating();
        //Table.Alpha("Alice");
        //Table.SequentialSeating();
        //Table.MyNewList();
       // Table.printNumberOfSeats();
       // Table.permute(People.persons,1);
        //Calculate_Happiness.getNameOfXHighestScorer("Alice", 2, People.persons);
        //Calculate_Happiness.getHighestScoringPair();
        Table.seatPeople();
        String[] seating = {"Alice", "Bob","Carol", "David", "Eric", "George", "Mallory", "Frank"};
        System.out.println(Calculate_Happiness.calculateTotalHappinessOfTable(seating));

        String[] seating2 = {"Mallory", "Alice", "Bob", "David", "Eric","Carol", "George", "Frank"};
        System.out.println(Calculate_Happiness.calculateTotalHappinessOfTable(seating2));
    }
}
