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

        totalHappiness += p1.getHappinessScoreByName(p2.name);
        totalHappiness += p2.getHappinessScoreByName(p1.name);

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
     * Takes a string representing a Persons name and a list of Persons.
     * Returns the highest total score for a person from the list
     *
     * */
    static protected int findHighestTotal(String person, ArrayList<Person> otherPeople) {
        int maximumHappiness = 0;
        ArrayList<Pair> totalHappinessWithPerson = new ArrayList<Pair>();

        for (Person p : otherPeople) {
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
}
