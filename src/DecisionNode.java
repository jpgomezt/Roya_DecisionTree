/**
 * DecisionNode --- Node of the tree that contains a question
 */
public class DecisionNode extends Node {

    /**
     * The Question of the Node
     */
    private Question question;

    /**
     * The true branch of the Node
     */
    private Node trueBranch;

    /**
     * the false branch o the Node
     */
    private Node falseBranch;

    /**
     * Constructor method of the class
     * @param question the Question of the Node
     * @param trueBranch the true branch of the Node
     * @param falseBranch the false branch of the Node
     */
    public DecisionNode(Question question, Node trueBranch, Node falseBranch) {
        this.question = question;
        this.trueBranch = trueBranch;
        this.falseBranch = falseBranch;
    }

    /**
     * Method that returns the predition of the leaf
     * @param spacing Helper parammeter to build a readable tree
     * @return The String version of the Question and the branches of the tree
     */
    @Override
    public String toString(String spacing) {
        return spacing + question.toString() + "\n" +
                spacing + "-->True: " + "\n" + trueBranch.toString(spacing + "|" + " ") +
                "\n" + spacing + "-->False: " + "\n" + falseBranch.toString(spacing + " ");
    }

    /**
     * This method evaluate some data to a prediction
     * @param test Is the data that is being evaluated
     * @return The path it is taking and it's prediction
     */
    @Override
    public String evaluateST(double[] test, String spacing) {
        if(question.match(test)){
            return spacing + question.toString() + "\n" + spacing + "this way **** ->True: (" + test[question.questionLabel] + ")\n"+
                                                                this.trueBranch.evaluateST(test,spacing + "|" + " ")
                                    + "\n" + spacing + "not this way-->False: " + "\n" + falseBranch.toString(spacing + " ");
        }
        else{
            return spacing + question.toString() + "\n" + spacing + "not this way-->True: " +
                            "\n" + trueBranch.toString(spacing + "|" + " ") +
                                "\n" + spacing + "this way **** ->False: ("+ test[question.questionLabel] +
                                            ")\n"+ this.falseBranch.evaluateST(test, spacing + " ");
        }
    }

    public String evaluate(double[] test, String spacing){
        if(question.match(test)){
            return spacing + question.toString() + "\n" + spacing + "-->True: (" + test[question.questionLabel] + ")\n"+
                    this.trueBranch.evaluate(test,spacing + "|" + " ");
        }
        else{
            return spacing + question.toString() + "\n" + spacing + "-->False: ("+ test[question.questionLabel] +
                    ")\n"+ this.falseBranch.evaluate(test, spacing + " ");
        }
    }

    /**
     * This method evaluate some data to a prediction
     * @param test Is the data that is being evaluated
     * @return The double of the path it takes
     */
    @Override
    public double precision(double[] test) {
        if(question.match(test)){
            return this.trueBranch.precision(test);
        }
        else{
            return this.falseBranch.precision(test);
        }
    }
}
