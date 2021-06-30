import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code NN}.
 *
 * @author Cameron Erdman
 *
 */
public final class XMLTreeNNExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeNNExpressionEvaluator() {
    }

    /**
     * Evaluate the given expression.
     *
     * @param exp
     *            the {@code XMLTree} representing the expression
     * @return the value of the expression
     * @requires <pre>
     * [exp is a subtree of a well-formed XML arithmetic expression]  and
     *  [the label of the root of exp is not "expression"]
     * </pre>
     * @ensures evaluate = [the value of the expression]
     */
    private static NaturalNumber evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        String operator = "";
        NaturalNumber returnValue = new NaturalNumber2();
        NaturalNumber zero = new NaturalNumber2(0);

        if (exp.hasAttribute("value")) { //if yes store number in leftChild
            //makes sure returnValue can be set from a string of value
            if (returnValue.canSetFromString(exp.attributeValue("value"))) {
                returnValue.setFromString(exp.attributeValue("value"));
            } else {
                components.utilities.Reporter
                        .fatalErrorToConsole("Error: Can not set from String");
            }

        } else { //else assign the operator to operator string
            operator = exp.label();
            //only need to take the left child
            if (exp.isTag() && exp.numberOfChildren() > 0) {
                returnValue = evaluate(exp.child(0));
            } else {
                components.utilities.Reporter.fatalErrorToConsole(
                        "Error: Child 0 is not valid in xml exp");
            }
            //Check if the right Child is avalable to be taken
            if (exp.isTag() && exp.numberOfChildren() > 1) {
                //the bueaty of natural number is only one variable is needed
                //find what the operator is and apply with right child
                if (operator.equals("plus")) {
                    returnValue.add(evaluate(exp.child(1)));
                } else if (operator.equals("minus")) {
                    //makes sure returnValue is less than exp.child(1)
                    if (evaluate(exp.child(1)).compareTo(returnValue) > 0) {
                        components.utilities.Reporter.fatalErrorToConsole(
                                "Error: Subtracting the right child from the left "
                                        + "child results in a value less than zero");
                    }
                    returnValue.subtract(evaluate(exp.child(1)));
                } else if (operator.equals("times")) {
                    returnValue.multiply(evaluate(exp.child(1)));
                } else {
                    //makes sure (exp.child(1) is greater than 0
                    if (evaluate(exp.child(1)).compareTo(zero) > 0) {
                        returnValue.divide(evaluate(exp.child(1)));
                    } else {
                        components.utilities.Reporter.fatalErrorToConsole(
                                "Error: The evaluate method "
                                        + "of exp.child(1) is less than 0");
                    }
                }
            } else { // else print and error and skip all math
                components.utilities.Reporter.fatalErrorToConsole(
                        "Error: Child 1 is not valid in xml exp");
            }
        }
        return returnValue;
    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();

        out.print("Enter the name of an expression XML file: ");
        String file = in.nextLine();
        while (!file.equals("")) {
            XMLTree exp = new XMLTree1(file);
            out.println(evaluate(exp.child(0)));
            out.print("Enter the name of an expression XML file: ");
            file = in.nextLine();
        }

        in.close();
        out.close();
    }

}
