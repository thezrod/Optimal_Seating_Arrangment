import javafx.util.Pair;
import java.util.ArrayList;


/*
* Calculate_Happiness handles all calculations for relations.
* Calculating the total score if to people is seated next to each other.
* Can also find the highest score for an individual.
* */
public class Calculate_Happiness {

    /*
    *  Given to names, there combined score is calculated.
    *  Score is 0 if the person does not exist,
    *  this will not be a problem when implemented because this code is just while looping the list of people from i/o.
    * */
    static protected int calcHappinessBetweenTwo(String personOne, String personTwo) {
        int totalHappiness = 0;
        Person p1 = People.getPerson(personOne);
        Person p2 = People.getPerson(personTwo);

        /*
        System.out.println("----------------------");
        System.out.println("Happiness Between:");
        System.out.println(p1.name + " & " + p2.name);
*/
        totalHappiness += p1.getHappinessScoreByName(p2.name);
        totalHappiness += p2.getHappinessScoreByName(p1.name);

       /* System.out.println("Total Happiness: " + totalHappiness);
        System.out.println("----------------------" + "\n");*/
        return totalHappiness;
    }

    /*
    * Takes a string representing a Persons name.
    * That person is found in the list of People.
    * Returns the highest total score for a person.
    * */
    static protected int findHighestTotal(String person) {
        int maximumHappiness = 0;
        ArrayList<Person> personsCopy = People.persons;
        ArrayList<Pair> totalHappinessWithPerson = new ArrayList<Pair>();

        for (Person p : personsCopy) {
            if (!p.name.equals(person)) {
                totalHappinessWithPerson.add(new Pair<>(p.name, calcHappinessBetweenTwo(person, p.name)));
            }
        }

        for (Pair p : totalHappinessWithPerson) {
            if ((int) p.getValue() > maximumHappiness) {
                maximumHappiness = (int) p.getValue();
            }
        }
            return maximumHappiness;
    }

    /*
    * Work similarly to findHighestTotal but returns the name of the highest scorer instead of the score.
    * Returns a String
    *
    * */
    static protected String getNameOfHighestScorer(String person){
        int maximumHappiness = 0;
        String highestScorer = "";
        ArrayList<Person> personsCopy = People.persons;
        ArrayList<Pair> totalHappinessWithPerson = new ArrayList<Pair>();

        for (Person p : personsCopy) {
            if (!p.name.equals(person)) {
                totalHappinessWithPerson.add(new Pair<>(p.name, calcHappinessBetweenTwo(person, p.name)));
            }
        }

        for (Pair p : totalHappinessWithPerson) {
            if ((int) p.getValue() > maximumHappiness) {
                maximumHappiness = (int) p.getValue();
                highestScorer = (String)p.getKey();
            }
        }
        return highestScorer;
    }
    static protected String getNameOfHighestScorer(String person, ArrayList<Person> otherPeople){
        int maximumHappiness = 0;
        String highestScorer = "";
        ArrayList<Pair> totalHappinessWithPerson = new ArrayList<Pair>();

        for (Person p : otherPeople) {
            if (!p.name.equals(person)) {
                totalHappinessWithPerson.add(new Pair<>(p.name, calcHappinessBetweenTwo(person, p.name)));
            }
        }

        for (Pair p : totalHappinessWithPerson) {
            if ((int) p.getValue() > maximumHappiness) {
                maximumHappiness = (int) p.getValue();
                highestScorer = (String)p.getKey();
            }
        }
        return highestScorer;
    }

    static protected ArrayList<String> getNameOfXHighestScorer(String person, int numberOfPeople, ArrayList<Person> otherPeople){
        ArrayList<String> highscoreList = new ArrayList<>();
        if(numberOfPeople > otherPeople.size()){
            highscoreList.add("To many people wanted");
            return highscoreList;
        }

        for(int i =0; i < numberOfPeople; i++) {
            System.out.println("Loop #" + (i+1));
            int maximumHappiness = 0;
            String highestScorer = "";
            ArrayList<Pair> totalHappinessWithPerson = new ArrayList<Pair>();

            for (Person p : otherPeople) {
                if (!p.name.equals(person)) {
                    totalHappinessWithPerson.add(new Pair<>(p.name, calcHappinessBetweenTwo(person, p.name)));
                }
            }

            Person tempPerson = new Person("");
            for (Pair p : totalHappinessWithPerson) {
                if ((int) p.getValue() > maximumHappiness) {
                    maximumHappiness = (int) p.getValue();
                    highestScorer = (String) p.getKey();
                    System.out.println("Highscorer found: " + highestScorer);
                    highscoreList.add(highestScorer);
                    System.out.println("highscoreList updated: " + highscoreList);
                    tempPerson.setPerson(People.getPerson((String)p.getKey()));
                }
            }
            otherPeople.remove(tempPerson);
            System.out.println("List of people updated " + otherPeople);
        }
        return highscoreList;
    }


    static protected int findHighestTotalFromRemaining(String person, ArrayList<Person> remaining) {
        int maximumHappiness = 0;
        ArrayList<Pair> totalHappinessWithPerson = new ArrayList<Pair>();

        for (Person p : remaining) {
            if (!p.name.equals(person)) {
                totalHappinessWithPerson.add(new Pair<>(p.name, calcHappinessBetweenTwo(person, p.name)));
            }
        }

        for (Pair p : totalHappinessWithPerson) {
            if ((int) p.getValue() > maximumHappiness) {
                maximumHappiness = (int) p.getValue();
            }
        }
        return maximumHappiness;
    }


    static protected String getHighestScorerFromRemaining(String person, ArrayList<Person> remaining){
        int maximumHappiness = 0;
        String highestScorer = "";
        ArrayList<Pair> totalHappinessWithPerson = new ArrayList<Pair>();

        for (Person p : remaining) {
            if (!p.name.equals(person)) {
                totalHappinessWithPerson.add(new Pair<>(p.name, calcHappinessBetweenTwo(person, p.name)));
            }
        }

        for (Pair p : totalHappinessWithPerson) {
            if ((int) p.getValue() > maximumHappiness) {
                maximumHappiness = (int) p.getValue();
                highestScorer = (String)p.getKey();
            }
        }
        return highestScorer;
    }

    static protected String getHighestScorerExcluding(String person, ArrayList<Person> exclusions){
        int maximumHappiness = 0;
        String highestScorer = "";
        ArrayList<Pair> totalHappinessWithPerson = new ArrayList<Pair>();

        for (Person p : People.persons) {
            if (!p.name.equals(person)) {
                for (Person p2:exclusions){
                    if(!p.equals(p2)){
                        totalHappinessWithPerson.add(new Pair<>(p.name, calcHappinessBetweenTwo(person, p.name)));
                    }

                }

            }
        }

        for (Pair p : totalHappinessWithPerson) {
            if ((int) p.getValue() > maximumHappiness) {
                maximumHappiness = (int) p.getValue();
                highestScorer = (String)p.getKey();
            }
        }
        return highestScorer;
    }
    static protected ArrayList<Person> getHighestScoringPair(){
        int bestScoreSoFar = 0;
        ArrayList<Person> highsocrePair = new ArrayList<>();
        for (Person p: People.persons) {
            int tempTotal = findHighestTotal(p.name);
            if(tempTotal > bestScoreSoFar){
                bestScoreSoFar = tempTotal;

                highsocrePair.clear();
                highsocrePair.add(p);
                highsocrePair.add(People.getPerson(getNameOfHighestScorer(p.name)));
                System.out.println("New Pair!: " + p.name
                        + " & " + getNameOfHighestScorer(p.name)
                        + " had a combined score of "
                        + bestScoreSoFar);
            }
        }
        return highsocrePair;
    }

    static int calculateTotalHappinessOfTable(String[] seating){
        int totalHappiness = 0;
        System.out.println("seating.length: " + seating.length);
        for (int i = 0; i < seating.length; i++){

            if(i == 0){
                totalHappiness += Calculate_Happiness.calcHappinessBetweenTwo(People.getPerson(seating[i]).name, People.getPerson(seating[seating.length-1]).name);
                totalHappiness += Calculate_Happiness.calcHappinessBetweenTwo(People.getPerson(seating[i]).name, People.getPerson(seating[i+1]).name);
            }
             else if(i == (seating.length-1)){
                totalHappiness += Calculate_Happiness.calcHappinessBetweenTwo(People.getPerson(seating[i]).name, People.getPerson(seating[i-1]).name);
                totalHappiness += Calculate_Happiness.calcHappinessBetweenTwo(People.getPerson(seating[i]).name, People.getPerson(seating[0]).name);
            }
            else{
                totalHappiness += Calculate_Happiness.calcHappinessBetweenTwo(People.getPerson(seating[i]).name, People.getPerson(seating[i-1]).name);
                totalHappiness += Calculate_Happiness.calcHappinessBetweenTwo(People.getPerson(seating[i]).name, People.getPerson(seating[i+1]).name);
            }

        }
        System.out.print("Total Score For Seating" + stringList(seating) + ": ");
        return totalHappiness;
    }

    static String stringList(String[] list){
        String output = " {";

        for (String s:list) {
            output += s+ ", ";
        }
        output += "}";
        return output;
    }
}
