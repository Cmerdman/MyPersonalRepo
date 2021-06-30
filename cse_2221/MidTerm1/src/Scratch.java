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
     * Put a short phrase describing the static method myMethod here.
     *
     * @param x
     *
     * @param y
     *
     * @return answer
     */
    private static boolean m1(int x, int y) {
        int a = x;
        int b = y;
        boolean result = true;

        if (a > 0 && b > 0) {
            result = false;
        } else {
            result = true;
        }

        return result;
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     *
     * @param x
     *
     * @param y
     *
     * @return answer
     */
    private static int m(int x, int y) {
        int a = x;
        int b = y;
        a = a + b;
        b = a;
        return a + b;
    }

    /**
     * Put a short phrase describing the static method myMethod here.
     *
     * @param x
     *
     * @param y
     *
     * @return answer
     */
    private static boolean m2(int x, int y) {
        return !(x > 0 && y > 0);
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

        //m
        int x = 3, y = 4;
        out.println("m = " + m(x, y));
        //m1
        //x = ; y = ;
        out.println("m1 = " + m1(x, y));
        //m2
        //x = ; y = ;
        out.println("m2 = " + m2(x, y));
        //mc1a
        double dblX = 1 / 3;
        out.println("mc1a = " + dblX);
        //mc1b
        double dblY = (5 / 3);
        out.println("mc1b =  5/3 =" + dblY);
        //mc2a
        int i = 0, k = 0;
        while (i < 3) {
            i++;
            k = k + i;
        }
        out.println("mc2a = " + k);
        //mc2b
        int j = 3, m = 0;
        while (j < 0) {
            j--;
            m = m + j;
        }
        out.println("mc2b = " + m);
        //mc3a
        int num = 42;
        String s = "";
        if (num > 40) {
            s = "Yes";
        }
        if (num < 40) {
            s = "No";
        } else {
            s = "Maybe";
        }
        out.println("mc3a = " + s);
        //mc3b
        int nu = 42;
        String c = "";
        if (nu < 40) {
            c = "Yes";
        }
        if (nu > 40) {
            c = "No";
        } else {
            c = "Maybe";
        }
        out.println("mc3b = " + c);
        //mc4a
        char[] v = { 'a', 'e', 'i', 'o', 'u' };
        out.println("mc4a = " + "v:" + v[1] + v[v.length - 1]);
        //mc4b
        char[] w = { 'g', 'o', 'b', 'u', 'x' };
        out.println("mc4b = " + "w:" + w[1] + w[w.length - 1]);
        //mc6a
        int z = 12, count = 0;
        while ((z / 3) != 2) {
            z--;
            count++;
        }
        out.println("mc6a = " + "z=" + z + ", count=" + count);

        //mc7a
        int num1 = 4, num2 = 9;
        num2 = m(num1, num2);
        out.println("mc7a = " + "num1=" + num1 + ", num2=" + num2);
        //mc7b
        int n1 = 9, n2 = 4;
        n2 = m(n1, n2);
        out.println("mc7b = " + "n1=" + n1 + ", n2=" + n2);

        //tracing table
        int i1 = 4, j1 = 1, n = 0;
        while (i1 > j1) {
            if (n % 2 == 0) {
                i1--;
            } else {
                j1++;
            }
            n++;
        }
        out.println("tracing table: i = " + i1 + ", j = " + j1 + ", n = " + n);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
