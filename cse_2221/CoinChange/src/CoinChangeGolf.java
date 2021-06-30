import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A program to calculate the change with the least number of coins.
 *
 * @author Cameron Erdman
 *
 */
public final class CoinChangeGolf {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChangeGolf() {

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

        out.print("How much change in cents do you wish to calculate: ");
        int change = in.nextInteger(), remainder = 0;

        out.println("The change should be " + change / 100 + " dollars "
                + "with " + (remainder = change % 100)
                + " cents left over, then " + remainder / 50 + " half-dollars "
                + "with " + (remainder = remainder % 50)
                + " cents left over, next " + remainder / 25 + " quarters "
                + "with " + (remainder = remainder % 25)
                + " cents left over, and " + remainder / 10 + " dimes "
                + "with " + (remainder = remainder % 10)
                + " cents left over, then " + remainder / 5 + " nickels "
                + "with " + (remainder = remainder % 5)
                + " cents left over, and finally " + remainder / 1
                + " pennys.");

        in.close();
        out.close();
    }

}
