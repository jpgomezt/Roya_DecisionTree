/**
 * Question --- class that built a question for de DecisionNode
 */
public class Question {

    /**
     * Is the label that is going than be evaluated int the question (0-5)
     */
    public int questionLabel;

    /**
     * Is the measure of the label that is going than be compared
     */
    public double questionVal;

    /**
     * Constructhanr
     * @param questionLabel The label than be evaluated
     * @param questionVal The value of the label than be compared
     */
    public Question(int questionLabel, double questionVal) {
        this.questionLabel = questionLabel;
        this.questionVal = questionVal;
    }

    /**
     * This method evaluates if the data value is greather than the question label
     * @param row Is the data thta is being compared than the question 
     * @return If the data value label is greather than the question value label
     */
    public boolean match(double[] row){
        return this.questionVal < row[this.questionLabel];
    }

    /**
     * This method transfrom the question into a string
     * @return The string form of the question
     */
    public String toString(){
        switch (questionLabel){
            case 0:
                return "Is pH > than " + questionVal;
            case 1:
                return "Is soil temperature > than " + questionVal;
            case 2:
                return "Is soil moisture > than " + questionVal;
            case 3:
               return "Is illuminance > than " + questionVal;
            case 4:
                return "Is env_temperature > than " + questionVal;
            case 5:
                return "Is env_humidity > than " + questionVal;
        }
        return "";
    }

}
