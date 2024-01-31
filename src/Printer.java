/*
* The printer class was a test to print tables of relations to visualize the relations easily.
* I dropped this when it took more time then expected to finish the task instead.
*
* */
public class Printer {


    static protected void printTable(String[] people){
        System.out.printf("------------------------------------------%n");
        System.out.printf("| Table of Happiness!                    |%n");
        System.out.printf("------------------------------------------%n");
        //System.out.printf("| %4s | %4s | %4s | %4s |%n", people);
        System.out.printf(createColumn(people.length),people);
        for(int i = 1; i < people.length; i++) {
            System.out.printf("| %8s |%n", people[i]);
        }
        System.out.printf("------------------------------------------%n");
    }

    static private String createColumn(int numberOfPeople){
        String column ="| ";
        for (int i = 0; i < numberOfPeople; i++){
            column = column + "%8s | ";
        }
        column = column + "%n";
        //System.out.println(column);
        return column;
    }
}
