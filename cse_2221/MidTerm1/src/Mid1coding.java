import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * Put a short phrase describing the program here.
 *
 * @author Put your name here
 *
 */
public final class Mid1coding {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Mid1coding() {
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     */
    private static int length(String[] a) {
        int x = 0;
        String len = "";
        for (int i = 0; i < a.length; i++) {
            if (len.length() < a[i].length()) {
                len = a[i];
                x = i;
            }
        }

        return (x);

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
        String[] a = { "", "", "", "", "" };
        int next = 0;
        int exit = 0;

        while (exit != 1 && next < a.length) {
            out.print("Please enter a String: ");
            a[next] = in.nextLine();
            if (a[next].length() < 1) {
                exit = 1;
            }

            next++;
        }

        int x = length(a);
        out.println("The longest string was: " + a[x]);
        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
