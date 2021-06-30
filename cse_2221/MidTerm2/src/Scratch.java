import components.queue.Queue;
import components.queue.Queue1L;
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
public final class Scratch {

    /**
     * Private constructor so this utility class cannot be instantiated.
     */
    private Scratch() {
    }

    /**
     * Reverses ("flips") {@code this}.
     *
     * @param q
     *            the queue to be flipped
     * @updates this
     * @ensures this = rev(#this)
     */
    public static void flip1(Queue<Integer> q) {
        int temp = q.dequeue();
        if (q.length() != 0) {
            flip1(q);
        }
        q.enqueue(temp);
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

        Queue<Integer> q = new Queue1L<>();
        q.enqueue(12);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(6);
        q.enqueue(1);
        q.enqueue(65);
        out.println(q);
        q.flip();
        out.println(q);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
