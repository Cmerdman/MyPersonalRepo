import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

/**
 * Program to evaluate XMLTree expressions of {@code int}.
 *
 * @author Cameron Erdman
 *
 */
public final class XMLTreeIntExpressionEvaluator {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private XMLTreeIntExpressionEvaluator() {
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
    private static int evaluate(XMLTree exp) {
        assert exp != null : "Violation of: exp is not null";
        int total = 0;
        String operator = "";
        int leftChild = 0;
        int rightChild = 0;

        if (exp.hasAttribute("value")) { //if yes is number and stores in total
            total = Integer.parseInt(exp.attributeValue("value"));
        } else { //if not, stores in operator and takes left and right child
            operator = exp.label();
            //knows this works because every operator has 2 children
            leftChild = evaluate(exp.child(0));
            rightChild = evaluate(exp.child(1));
            //if operator is a plus add and so on
            if (operator.equals("plus")) {
                total = leftChild + rightChild;
            } else if (operator.equals("minus")) {
                total = leftChild - rightChild;
            } else if (operator.equals("times")) {
                total = leftChild * rightChild;
            } else {
                total = leftChild / rightChild;
            }

        }
        return total;

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
