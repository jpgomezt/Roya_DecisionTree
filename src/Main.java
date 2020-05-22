import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main --- main program
 */
public class Main {
    /**
     * This method creates a decision tree, calculates it's precision and lets the user input some data to get a
     * prediction based on the tree
     * @param args is not used
     */
    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();
        ArrayList<double[]> mtz = Reader.readFile(new File("data_set.csv"));
        DecisionTree myTree = new DecisionTree(mtz);
        long fin = System.currentTimeMillis();
        double tiempo = (double) ((fin - inicio)/1000.0);
        System.out.println("The execution time was " + tiempo + " seconds");
        System.out.println(myTree.toString());
        ArrayList<double[]> test = Reader.readFile(new File("test.csv"));
        System.out.println( "\nThe precision of the tree is "+precision(myTree, test) + "%");
        boolean exe = true;
        Scanner scanner = new Scanner(System.in);
        while(exe){
            System.out.println("\nInsert the data to evaluate (Q to exit)");
            String line = scanner.nextLine();
            if(line.equalsIgnoreCase("Q")){
                exe = false;
            }
            else{
                System.out.println(myTree.evaluateST(Reader.readLine(line)));
                System.out.println("\n" + myTree.evaluate(Reader.readLine(line)));
            }
        }
    }

    /**
     * This method calculates the precision of a tree based on a dataSet
     * @param tree the tree used to calculate it's precision
     * @param test the dataSet to compare with the tree
     * @return the precision of the tree
     */
    public static double precision(DecisionTree tree, ArrayList<double[]> test){
        double[] count = new double[2];
        for(double[] data: test){
            if(tree.precision(data) == data[data.length-1]){
                count[1] = count[1] + 1;
            }
            else {
                count[0] = count[0] + 1;
            }
        }
        return (int)(count[1]/(count[1]+count[0]) * 10000)/100.0;
    }
}
