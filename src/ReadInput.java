import javafx.util.Pair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/*
* Class that reads the .dat file.
* The class assumes that all lines of the file are as in the example;
*       - Starts with a name.
*       - Says "lose" or "gain" followed by a number.
*       - End with the other persons name.
*
* The class constructs a list of people in the class People.
* The People are all of the class Person.
* The Class creates integers based on the value and keywords "lose" & "gain" in the text.
*
*
* */
public class ReadInput {

   private static ArrayList<String> data = new ArrayList<>();
    /*
     * The method that handles io. A scanner that tries to read the .dat file and catches FileNotFoundExceptions.
     */
    public static void readFile() {

            try
        {
            File file = new File("data.dat");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(line.length() > 0) {
                    data.add(line);
                }
            }

            scanner.close();
        } catch(
        FileNotFoundException e)

        {
            System.out.println("File not found.");
        }
    }
    /*
    * Helper / Debugging function that prints data
    * */
    protected void printData() {
        for (String str : data) {
            System.out.println(str);
        }
    }
    private static ArrayList<String> getDataForPerson(String name){
        ArrayList<String> list = new ArrayList<>();
        for (String str: data) {

            int i = str.indexOf(' ');
            String word = str.substring(0, i);

            if(word.equals(name)){
                list.add(str);
            }
        }
        return list;
    }
    /*
     * Helper / Debugging function that prints data
     * */
    protected void printDataForPerson(String name){

        for (String str: data) {

            int i = str.indexOf(' ');
            String word = str.substring(0, i);

            if(word.equals(name)){
                System.out.println(str);
            }
        }
    }
    /*
    * setRelations takes a name of a person.
    * After reading the file to find all lines that starts with that persons name, it calculates all relations.
    * The relations are saved them as pairs containing the happiness-score and the name of the other person in the relation.
    *
    *
    * */
    public static ArrayList<Pair> setRelations(String name){
        readFile();
        ArrayList<String> personalData = getDataForPerson(name);
        ArrayList<Pair> relations = new ArrayList<>();
        for (String str:personalData) {
           String[] test =  str.split(" ");

            int happinessValue;
            String personWithRelation = test[test.length-1];
            personWithRelation = personWithRelation.substring(0,personWithRelation.length()-1);

            if(test[2].equals("lose")){
                happinessValue = Math.negateExact(Integer.parseInt(test[3]));
            }
            else{
                happinessValue = Integer.parseInt(test[3]);
            }

            relations.add(new Pair(personWithRelation, happinessValue));
        }
        return relations;
    }
    /*
    *This method reads the file and returns a list of all the names of the people found in the text.
    * The Set class is used to eliminate duplicates and Collections is used to easily sort the list.
    *
    * */
    protected static ArrayList<String> getAllPersons(){
        readFile();
        ArrayList<String> names = new ArrayList<>();

        for (String str: data) {

            int i = str.indexOf(' ');
            String word = str.substring(0, i);
            names.add(word);
        }

        Set<String> setOfNames = new HashSet<>();
        setOfNames.addAll(names);

        names.clear();
        names.addAll(setOfNames);
        Collections.sort(names);

        return names;

    }
}