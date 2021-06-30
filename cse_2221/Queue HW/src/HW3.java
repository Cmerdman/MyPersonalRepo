import components.sequence.Sequence;
import components.sequence.Sequence1L;
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
public final class HW3 {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private HW3() {
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @updates this
     * @ensures this = rev(#this)
     */

    public static void fliper(Sequence<Integer> st) {
        int x = st.length() - 1;
        for (int i = st.length() - 1; i > 0; i--) {
            int temp = st.remove(x);
            st.add(x - i, temp);
        }
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
        Sequence<Integer> st = new Sequence1L<>();
        st.add(0, 12);
        st.add(1, 6);
        st.add(2, 37);
        st.add(3, 54);
        st.add(4, 2);
        out.println(st);
        fliper(st);
        out.println(st);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
