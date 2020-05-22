/**
 *
 */
public abstract class Node {
    public abstract String toString(String spacing);
    public abstract String evaluateST(double[] test, String spacing);
    public abstract String evaluate(double[] test, String spacing);
    public abstract double precision(double[] test);
}