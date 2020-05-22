import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Reader --- class that helps Reading files and Strings to convert them into a matrix or an array
 */
public class Reader {

    /**
     * This method converts a dataSet File into a dataSet matrix
     * @param file the File that contains a dataSet
     * @return a matrix of the data set
     */
    public static ArrayList<double[]> readFile(File file){
        ArrayList<double[]> coffeSet = new ArrayList<>();
        try{
            Scanner reader = new Scanner(file);
            reader.nextLine();
            while(reader.hasNextLine()){
                coffeSet.add(readLine(reader.nextLine()));
            }
            reader.close();
        }
        catch (Exception e){
            System.out.println(e);
        }
        return coffeSet;
    }

    /**
     * This method converts a line into an array
     * @param line the line to fill the matriz
     * @return the data into an array
     */
    public static double[] readLine(String line){
        Scanner row = new Scanner(line);
        row.useDelimiter(",");
        double[] variables = new double[7];
        int i = 0;
        while (row.hasNext()){
            if(row.hasNextDouble()){variables[i] = row.nextDouble();}
            else{
                if(row.next().equals("yes")){ variables[i] = 1;}
                else{variables[i] = 0;}
            }
            i++;
        }
        row.close();
        return variables;
    }
}