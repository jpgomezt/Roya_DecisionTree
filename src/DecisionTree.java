import java.util.ArrayList;

/**
 * DecisionTree --- class that builds the main tree
 */
public class DecisionTree{

    /**
     * The root node of the tree
     */
    public Node root;

    /**
     * The entrpy of the root node
     */
    private double entropy;

    /**
     * The data that is going to be compared in the true branch of the tree
     */
    private ArrayList<double[]> trueRow = new ArrayList<>();

    /**
     * The data that is going to be compared in the false branch of the tree
     */
    private ArrayList<double[]> falseRow = new ArrayList<>();

    /**
     * Constructor of the desicion tree
     * @param dataSet the data set that is being used to built this tree
     */
    public DecisionTree(ArrayList<double[]> dataSet) {
        this.root = builtTree(dataSet);
    }

    /**
     * This method evaluate some data and converts the result to a string
     * @param test a data to evaluate
     * @return The string string form of the prediction of data that is being evaluated
     */
    public String evaluateST(double[] test){
        return this.root.evaluateST(test, " ");
    }

    public String evaluate(double[] test){
        return this.root.evaluate(test, " ");
    }

    /**
     * This method evaluate some data and converts the result to a double
     * @param test a data to evaluate
     * @return The string double form of the prediction of data that is being evaluated
     */
    public double precision(double[] test){
        return this.root.precision(test);
    }

    /**
     * This method build the entire tree
     * @param dataSet the data set that is being used to built this tree
     * @return a Node that can either be a DecisionNode or a Leaf
     */
    private Node builtTree(ArrayList<double[]> dataSet) {
        this.entropy = this.getEntropy(dataSet);
        if(this.entropy == 0){
            return new Leaf(this.classCount(dataSet));
        }
        else{
            Question question = this.findQuestion(dataSet);
            if(question == null){
                return new Leaf(this.classCount(dataSet));
            }
            else{
                this.partition(dataSet, question);
                return new DecisionNode(question, new DecisionTree(trueRow).root, new DecisionTree(falseRow).root);
            }
        }
    }

    /**
     * This method receive the dataSet that contains the tree and evaluates its entropy
     * @param dataSet The data set that contains this tree
     * @return the entropy of the  root
     */
    private double getEntropy(ArrayList<double[]> dataSet) {
        double[] count = this.classCount(dataSet);
        if(count[0] == 0 || count[1] == 0)return 0;
        double total = count[0]+count[1];
        double impurity = -(count[0]/total) * (log2(count[0]/total)) -(count[1]/total) * (log2(count[1]/total));
        return impurity;
    }

    /**
     * This method calculates the log 2 of a number
     * @param num a number
     * @return a number evaluated in log base 2
     */
    private double log2(double num){
        return (Math.log(num)/Math.log(2));
    }

    /**
     * This method stores whether a data has a 'yes' label or a 'no' label in an data set
     * @param arr The data set that contains this tree
     * @return contains the amount of data of each kind of label, being classCount[0] = 'no', and classCount[1] = 'yes'
     */
    private double[] classCount(ArrayList<double[]> arr){
        double[] count = new double[2];
        for(double[] row: arr){
            if(row[row.length-1]==1){
                count[1] = count[1] + 1;
            }
            else{
                count[0] = count[0] + 1;
            }
        }
        return count;
    }

    /**
     * Method that find which is the best possible question in a dataSet using entropy
     * @param arr The data set that contains this tree
     * @return a Question
     */
    private Question findQuestion(ArrayList<double[]> arr){
        Question bestQuestion = null;
        double bestGain = 0;
        for(int i = 0; i < arr.size(); i++){
            for(int j = 0; j < arr.get(i).length-1; j++){
                Question currentQuestion = new Question(j, arr.get(i)[j]);
                this.partition(arr, currentQuestion);
                if(this.trueRow.size()!=0 && this.falseRow.size()!=0){
                    double trueTreeEntropy = this.getEntropy(this.trueRow);
                    double falseTreeEntropy = this.getEntropy(this.falseRow);
                    double totalData = trueRow.size() + falseRow.size();
                    double gain = this.entropy - (trueTreeEntropy * (trueRow.size()/totalData)) -
                                                                (falseTreeEntropy * (falseRow.size()/totalData));//ID3
                    if(gain >= bestGain){
                        bestQuestion = currentQuestion;
                        bestGain = gain;
                    }
                }
            }
        }
        return bestQuestion;
    }

    /**
     * This method creates the two branches of the root
     * @param arr The data set that contains this tree
     * @param question the Question to compare
     */
    private void partition(ArrayList<double[]> arr, Question question){
        ArrayList<double[]> True = new ArrayList<>();
        ArrayList<double[]> False = new ArrayList<>();
        for (double[] row : arr) {
            if (question.match(row)) {
                True.add(row);
            } else {
                False.add(row);
            }
        }
        this.trueRow = True;
        this.falseRow = False;
    }

    /**
     * This method transform the tree into a string
     * @return The string form of the tree
     */
    public String toString(){
        return root.toString(" ");
    }
}
