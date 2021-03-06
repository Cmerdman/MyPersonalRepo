import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.utilities.FormatChecker;

/**
 * A program to test the legitimacy of the de Jager theory.
 *
 * @author Cameron Erdman
 *
 */
public final class ABCDGuesser1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private ABCDGuesser1() {
    }

    /**
     * Repeatedly asks the user for a positive real number until the user enters
     * one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @return a positive real number entered by the user
     */
    private static double getPositiveDouble(SimpleReader in, SimpleWriter out) {
        int x = 1;
        double a = 0;
        while (x != 0) {
            out.print("Input a number to be your μ: ");
            String input = in.nextLine();
            if (FormatChecker.canParseDouble(input) //if num can be parsed
                    && Double.parseDouble(input) > 0) { //if num is greater than 0
                a = Double.parseDouble(input); //then parse num
                x = 0; //set x = 0 to leave loop
            } else {
                out.println("Try again  :("); //else redo
            }
        }
        return a; //return input
    }

    /**
     * Repeatedly asks the user for a positive real number not equal to 1.0
     * until the user enters one. Returns the positive real number.
     *
     * @param in
     *            the input stream
     * @param out
     *            the output stream
     * @param num
     *            the variable being returned to
     * @return a positive real number not equal to 1.0 entered by the user
     */
    private static double getPositiveDoubleNotOne(SimpleReader in,
            SimpleWriter out, String num) {
        int x = 1;
        double a = 0;
        while (x != 0) {
            out.print("Input a number to be your " + num + ": ");
            String input = in.nextLine();
            if (FormatChecker.canParseDouble(input) //checks can be parsed
                    && Double.parseDouble(input) > 0 //checks greated than 0
                    && Double.parseDouble(input) != 1) { //checks not 1
                a = Double.parseDouble(input); //parses num
                x = 0; //set x = 0 to leave loop
            } else {
                out.println("Try again  :("); //else ask again
            }
        }
        return a; //returns inputted num
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
        /*
         * Put your main program code here; it may call myMethod as shown
         */
        double u = getPositiveDouble(in, out); //gets the μ number
        double w = getPositiveDoubleNotOne(in, out, "w"); //gets the other 4 nums
        double x = getPositiveDoubleNotOne(in, out, "x"); // ^
        double y = getPositiveDoubleNotOne(in, out, "y"); // ^
        double z = getPositiveDoubleNotOne(in, out, "z"); // ^
        final double[] raised = { -5, -4, -3, -2, -1, -0.5, -(1.0 / 3.0), -0.25,
                0, 0.25, 1.0 / 3.0, 0.5, 1, 2, 3, 4, 5 }; //17 nums, index 16

        double guess = 0.0; //the approximated number
        double closestGuess = u * 2; // the closest approximated number
        int i = 0;
        int j = 0;
        int k = 0;
        int l = 0;
        double wTemp = w;
        double xTemp = x;
        double yTemp = y;
        double zTemp = z;
        final double pctError = 0.01;
        String bestVals = " "; // to store the numbers we raise to

        while (i < raised.length) { //a while loop for each of w, x, y, z
            wTemp = Math.pow(w, raised[i]); //each one loops through the raised array
            while (j < raised.length) {
                xTemp = Math.pow(x, raised[j]);
                while (k < raised.length) {
                    yTemp = Math.pow(y, raised[k]);
                    while (l < raised.length) {
                        zTemp = Math.pow(z, raised[l]);
                        guess = wTemp * xTemp * yTemp * zTemp;
                        if (Math.abs(guess - u) / u < pctError) { //if within pct error
                            if (Math.abs(u - guess) < Math
                                    .abs(u - closestGuess)) { //if less than previous best
                                closestGuess = guess;
                                bestVals = "w as: " + w + " raised to the "
                                        + raised[i] + ", x as: " + x
                                        + " raised to the " + raised[j]
                                        + ", y as: " + y + " raised to the "
                                        + raised[k] + ", and z as: " + z
                                        + " raised to the " + raised[l];
                            }
                        }
                        l++;

                    }
                    k++;
                    l = 0; //resets the indices after each loop
                }
                j++;
                k = 0;
            }
            i++;
            j = 0;
        }
        final int hundred = 100;
        double error = (Math.abs((closestGuess - u) / u) * hundred); //pct error
        out.println("The best guess to " + u + " was " + closestGuess + " with "
                + bestVals);
        out.print("Cornelis de Jager's formula was accurate to ");
        out.print(error, 2, false); // to printf using osu library
        out.println("%");
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
