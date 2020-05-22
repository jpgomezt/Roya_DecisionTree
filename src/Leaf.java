/**
 * Leaf --- Kind of node in a tree, that contains a prediction
 */
public class Leaf extends Node {
    /**
     * Prediction in the form of a String
     */
    private String prediction;

    /**
     * Prediction in the form of a double
     */
    private double predic;

    /**
     * Constructor method
     * @param classCount a double[]: contains the amount of data of each kind of label, being classCount[0] = 'no', and classCount[1] = 'yes'
     */
    public Leaf(double[] classCount) {
        double percentage = classCount[1]/(classCount[0]+classCount[1]);
        if(percentage == 1){
            this.predic = 1;
            this.prediction = "This coffe crop have Rust";
        }
        else if(percentage == 0){
            this.predic = 0;
            this.prediction = "This coffe crop does not have Rust";
        }
        else{
            if(percentage > 0.5)this.predic = 1;
            else this.predic = 0;
            this.prediction = "This coffe crop has a " + percentage*100 + "% of having Rust";
        }
    }

    /**
     * Method that returns the predition of the leaf
     * @param spacing Helper parammeter to build a readable tree
     * @return The predicition of the leaf
     */
    @Override
    public String toString(String spacing) {
        return spacing + this.prediction;
    }

    /**
     * This method evaluate some data to a prediction
     * @param test Is the data that is being evaluated
     * @return The string prediction of the leaf
     */
    @Override
    public String evaluateST(double[] test, String spacing) {
        return spacing + this.prediction;
    }

    public String evaluate(double[] test, String spacing) {
        return spacing + this.prediction;
    }

    /**
     * This method evaluate some data to a prediction
     * @param test Is the data that is being evaluated
     * @return The double prediction of the leaf
     */
    @Override
    public double precision(double[] test) {
        return this.predic;
    }
}
