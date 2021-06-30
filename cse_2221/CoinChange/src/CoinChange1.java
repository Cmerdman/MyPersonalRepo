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
public final class CoinChange1 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private CoinChange1() {

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

        final String[] coins = { " dollars ", " half-dollars ", " quarters ",
                " dimes ", " nickels ", " pennys " };
        final int[] values = { 100, 50, 25, 10, 5, 1 };

        out.print("How much change in cents do you wish to calculate: ");
        int change = in.nextInteger(), remainder = 0;

        out.println("The change should be " + change / values[0] + coins[0]
                + "with " + (remainder = change % values[0])
                + " cents left over, then " + remainder / values[1] + coins[1]
                + "with " + (remainder = remainder % values[1])
                + " cents left over, then " + remainder / values[2] + coins[2]
                + "with " + (remainder = remainder % values[2])
                + " cents left over, then " + remainder / values[3] + coins[3]
                + "with " + (remainder = remainder % values[3])
                + " cents left over, then " + remainder / values[4] + coins[4]
                + "with " + (remainder = remainder % values[4])
                + " cents left over, then " + remainder / values[5] + coins[5]
                + "with " + (remainder = remainder % values[5])
                + " cents left over");

        in.close();
        out.close();
    }

}
