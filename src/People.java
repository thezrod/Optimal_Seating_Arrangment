import java.util.ArrayList;
/*
* People handles the list of Persons.
* Remembers the number of people and lets us get Person by name.
*
*
* */
public class People {

    static ArrayList<Person> persons = new ArrayList<>();

    /*
     *Increments the number of Persons.
     * */
    protected static void increment(){
        System.out.println("NUMBER OF PEOPLE UPDATED!" + "\n" + "Number of people: " + getNumberOfPeople() + "\n");
    }
    /*
    *
    * Adds a new Person to the list
    *
    * */
    protected static void addPerson(Person p){
        persons.add(p);
    }
    /*
    *
    * Returns a Person from the list with the given name.
    * */
    protected static Person getPerson(String name){
        for (Person p:persons) {
            if(p.name.equals(name)){
                return p;
            }
        }
        return null;
    }
    /*
    * Prints the list of Persons
    * */
    protected void printListOfPersons(){
        System.out.println("List of People: ");
        for (Person p: persons) {
            System.out.println(p.name);
        }
        System.out.println();
    }

    /*
    * Returns the number of Persons in the list.
    * */
    public static int getNumberOfPeople(){

        return persons.size();
    }
    /*
    * Initializes the list by using ReadInput to read the file.
    * */
    protected void initializePeople(){
        ArrayList<String> people = ReadInput.getAllPersons();
        for (String s: people) {
            Person p = new Person(s,this);
        }
    }
}
