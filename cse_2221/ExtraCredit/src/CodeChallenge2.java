import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree2;

/**
 *
 * Put a short phrase describing the program here.
 *
 *
 *
 * @author Cameron Erdman
 *
 *
 *
 */

public final class CodeChallenge2 {

    /**
     *
     * Private constructor so this utility class cannot be instantiated.
     *
     */

    private CodeChallenge2() {

    }

    /**
     *
     * Reports the value of the second-largest entry in an array.
     *
     *
     * @param a
     *            integer array
     *
     * @return the second largest integer in the array
     *
     * @requires [a has at least 2 entries]
     *
     * @ensures secondLargest = [largest value not including max entry]
     *
     */

    private static int secondLargest(int[] a) {
        int largest = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;
        int loc = 0;

        for (int i = 0; i < a.length; i++) {
            if (a[i] > largest) {
                largest = a[i];
                loc = i;
            } else if (a[i] > second && a[i] < largest) {
                second = a[i];
            }
            if (loc != i && a[i] == largest) {
                second = a[i];
            }
        }
        return second;
    }

    /**
     *
     * returns a copy of src.
     *
     *
     *
     * @param src
     *
     *            the source of the copied value
     *
     * @return the copied value
     *
     * @ensures copy = src
     *
     */

    public static NaturalNumber copy(NaturalNumber src) {

        NaturalNumber result = new NaturalNumber1L();

        int k = 0;
        if (!src.isZero()) {
            k = src.divideBy10();
            result = copy(src);
        }

        result.multiplyBy10(k);
        return result;

    }

    /*
     *
     * Convert the given Expression Tree to an String …
     *
     *
     *
     * @param exp
     *
     * XML formatted expression tree
     *
     * @return fully parenthesized expression
     *
     * @requires [exp is a subtree of a well-formed XML arithmetic expression]
     *
     * and [the label of the root of exp is not "expression"]
     *
     * @ensures stringTree = the encoded expression, fully parenthesized
     *
     */

    private static String stringTree(XMLTree exp) {

        String sign = exp.label();
        String number = "";
        if (exp.numberOfChildren() == 2) {

            /*
             * using recursive method to find the nested tree
             */
            String left = stringTree(exp.child(0));
            String right = stringTree(exp.child(1));
            /*
             * recognize the operator
             */
            if (sign.equals("plus")) {
                number = "(" + left + "+" + right + ")";

            }
            if (sign.equals("minus")) {
                number = "(" + left + "-" + right + ")";

            }
            if (sign.equals("times")) {
                number = "(" + left + "*" + right + ")";

            }
            if (sign.equals("divide")) {
                number = "(" + left + "/" + right + ")";

            }
        }
        /*
         * extract the value of tree
         */

        if (exp.numberOfChildren() == 0) {
            number = exp.attributeValue("value");

        }

        return number;

    }

    /**
     *
     * Main method.
     *
     *
     *
     * @param args
     *
     *            the command line arguments
     *
     */

    public static void main(String[] args) {

        SimpleReader in = new SimpleReader1L();

        SimpleWriter out = new SimpleWriter1L();

        /* test private static int secondLargest(int[] a) */

        out.println(" ** TESTING SECONDLARGEST ** ");

        int[] a = { 1, 5, -7, 4, 1 };

        int[] b = { 1, 5, -7, 4, 5 };

        int[] c = { -2, -3, -7, -4, -5 };

        int[] d = { 1, 0, -7, 0, -5 };

        out.println(

                "Second Largest of {1, 5, -7, 4, 1} is " + secondLargest(a));

        out.println(

                "Second Largest of {1, 5, -7, 4, 5} is " + secondLargest(b));

        out.println("Second Largest of {-2, -3, -7, -4, -5} is "

                + secondLargest(c));

        out.println(

                "Second Largest of {1, 0, -7, 0, -5} is " + secondLargest(d));

        /* test NaturalNumber copy(NaturalNumber src)) */

        out.println("\n ** TESTING COPY ** ");

        out.println("Copy of 123 is " + copy(new NaturalNumber1L("123")));

        out.println("Copy of 1001 is " + copy(new NaturalNumber1L("1001")));

        out.println("Copy of 1 is " + copy(new NaturalNumber1L("1")));

        out.println("Copy of 123123123123 is "

                + copy(new NaturalNumber1L("123123123123")));

        /* test stringTree(XMLTree exp) */

        out.println("\n ** TESTING STRINGTREE ** ");

        XMLTree cc2 = new XMLTree2("data/cc2.xml");

        out.println("data/cc2.xml becomes this string: "

                + stringTree(cc2.child(0)));

        out.println("\n ** TESTING DONE ** ");

        in.close();

        out.close();

    }

}
