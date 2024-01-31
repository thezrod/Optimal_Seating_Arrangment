import javafx.util.Pair;

import java.util.ArrayList;

public class Person {

    protected String name;
    private ArrayList<Pair> preferences;

    protected void printPrefs(){
        System.out.println("----------------------------------");
        System.out.println("Relation_Pairs for " + name + ": ");
        for (Pair p: preferences) {
            System.out.println(p.getKey() + " " + p.getValue());
        }
        System.out.println();
    }

    protected int getHappinessScoreByName(String otherPerson){
        if(otherPerson.equals(name)){
            System.out.println("A person has no happiness_score with themselves!");
            return 0;
        }

        for (Pair p: preferences) {
            if(p.getKey().equals(otherPerson)){
               // System.out.println("Happiness_score with " + otherPerson + ": " + p.getValue());
                return (int)p.getValue();
            }
        }
        return 0;
    }
    protected void setPerson(Person p){
        this.name = p.name;
        this.preferences = p.preferences;
    }

    Person(String name, People people){
        this.name = name;
        preferences = ReadInput.setRelations(name);
        people.increment();
        people.addPerson(this);
    }

    Person(String name){
        this.name = name;
        preferences = ReadInput.setRelations(name);
        People.increment();
        People.addPerson(this);
    }
}
