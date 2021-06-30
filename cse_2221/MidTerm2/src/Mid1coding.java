import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;
import components.naturalnumber.NaturalNumber2;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.xmltree.XMLTree;
import components.xmltree.XMLTree1;

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
     * Reports whether the given digit appears in the given NaturalNumber.
     *
     * @param n
     * @param digit
     * @requires n > 0
     * @ensures countfigit = count (<n>, digit)
     * @return count
     */
    private static int countDigit(NaturalNumber n, int digit) {
        int count = 0;
        int r = n.divideBy10();
        if (r == digit) {
            count++;
        }
        if (!(n.isZero())) {
            return count + countDigit(n, digit);
        }
        return count;
    }

    /**
     * Reports whether the given text node appears in the given XMLTree.
     *
     * @param t
     * @param label
     * @requires n > 0
     * @ensures countfigit = count (<t>, label) > 1
     * @return count
     */
    private static int countLabel(XMLTree t, String label) {
        int count = 0;
        if (t.isTag()) {
            if (t.numberOfChildren() > 0) {
                for (int i = 0; i < t.numberOfChildren(); i++) {
                    if (label.equals(t.child(i).label())) {
                        count += countLabel(t.child(i), label);
                    }
                }
            }
        }
        return count;
    }

    /**
     * Reports whether the given digit appears in the given NaturalNumber.
     *
     * @requires n > 0
     * @ensures hasDigit = count (<n>, digit) > 1
     *
     */
    private static boolean hasDigit(NaturalNumber n, int digit) {
        int d = n.divideBy10();
        boolean has = (d == digit);
        if (!(n.isZero())) {
            has = has || hasDigit(n, digit);
        }
        n.multiplyBy10(d);
        return has;
    }

    //coding 2
    /**
     * Reports whether the given text node appears in the given XMLTree.
     *
     * @requires n > 0
     * @ensures countfigit = count (<t>, label) > 1
     *
     */
    private static boolean hasLabel(XMLTree t, String label) {
        boolean ret = false;
        if (t.numberOfChildren() > 0) {
            for (int i = (t.numberOfChildren() - 1); i >= 0; i--) {
                if (label.equals(t.label())) {
                    ret = true;
                }
                countLabel(t.child(i), label);
            }
        }
        return ret;
    }

    /*
     * mystery1
     */
    private static String mystery1(String s1) {
        s1 = s1.substring(0, 2);
        return s1;
    }

    /*
     * mystery2
     */
    private static void mystery2(NaturalNumber n1) {
        n1.decrement();
        //   n1 = new NaturalNumber1L(5);
    }

    /*
     * mystery3
     */
    private static void mystery3(NaturalNumber[] a, NaturalNumber n) {
        n = a[0];
        a[0] = a[1];
        a[1] = n;
        n.decrement();
    }

    /*
     * pp1
     */
    private static void pp1(int a, int b) {
        int tmp = a;
        //a = b;
        // b = tmp;
    }

    /*
     * pp2
     */
    private static void pp2(String a, String b) {
        String tmp = a;
        //a = b;
        // b = tmp;
    }

    /*
     * pp3
     */
    private static void pp3(int[] arr, int num) {
        arr[num] = 17;
        //num = arr.length;
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

        //methods
        NaturalNumber n = new NaturalNumber2(32353);
        int digit = 3;
        int returnCountDigit = countDigit(n, digit);
        out.println("countDigit = " + returnCountDigit + ", n= " + n);
        NaturalNumber nhas = new NaturalNumber2(32353);
        boolean returnHasDigit = hasDigit(nhas, digit);
        out.println("hasDigit = " + returnHasDigit);
        XMLTree t = new XMLTree1("https://news.yahoo.com/rss/");
        String label = "title";
        int returnCountLabel = countLabel(t, label);
        out.println("countLabel = " + returnCountLabel);
        boolean returnHasLabel = hasLabel(t, label);
        out.println("hasLabel = " + returnHasLabel);

        //non-methods

        //mc1a
        NaturalNumber n1 = new NaturalNumber2(7);
        NaturalNumber n2 = new NaturalNumber2(2);
        NaturalNumber n3 = new NaturalNumber2(3);
        NaturalNumber r = n1.divide(n2);
        if (!r.isZero()) {
            n1.multiply(n2);
            n1.add(r);
            n1.multiply(n3);
            n1.increment();
        }
        out.println("mc1a: n1 = " + n1 + ", r = " + r);

        //mc1b
        NaturalNumber n1b = new NaturalNumber2(5);
        NaturalNumber n2b = new NaturalNumber2(4);
        NaturalNumber n3b = new NaturalNumber2(3);
        NaturalNumber rb = n1b.divide(n2b);
        if (!rb.isZero()) {
            n1b.multiply(n2b);
            n1b.add(rb);
            n1b.multiply(n3b);
            n1b.increment();
        }
        out.println("mc1b: n1 = " + n1b + ", r = " + rb);

        //mc2a
        NaturalNumber n4 = new NaturalNumber2(25);
        NaturalNumber hund = new NaturalNumber2(100);
        NaturalNumber num = n4;
        while (n4.compareTo(hund) < 0) {
            n4.multiply(new NaturalNumber2(2));
            // if (n4.compareTo(num) > 0) {
            num = n4;
            //}
        }
        out.println("mc2a: num = " + num);

        //mc2b
        NaturalNumber n4b = new NaturalNumber2(5);
        NaturalNumber tenb = new NaturalNumber2(10);
        NaturalNumber numb = n4b;
        while (n4b.compareTo(tenb) < 0) {
            n4b.multiply(new NaturalNumber2(2));
            //   if (n4b.compareTo(numb) > 0) {
            numb = n4b;
            // }
        }
        out.println("mc2b: num = " + numb);

        //mc3a
        int i = 123, j = 345;
        pp1(i, j);
        out.println("mc3a: i= " + i + ", j= " + j);

        //mc3b
        int ib = 111, jb = 222;
        pp1(ib, jb);
        out.println("mc3b: i= " + ib + ", j= " + jb);

        //mc4a
        String x = "lag", y = "led";
        pp2(x, y);
        out.println("mc4a: x= " + x + ", y= " + y);

        //mc4b
        String xb = "aaa", yb = "bbb";
        pp2(x, y);
        out.println("mc4b: x= " + xb + ", y= " + yb);

        //mc5a
        int[] a = { 1, 2, 3 };
        int k = 0;

        pp3(a, k);
        out.println("mc5a: a= <" + a[0] + ", " + a[1] + ", " + a[2] + ">");
        out.println("mc5a: k= " + k);

        //mc5b
        int[] ab = { 9, 8, 7 };
        int kb = 1;

        pp3(ab, kb);
        out.println("mc5b: a= <" + ab[0] + ", " + ab[1] + ", " + ab[2] + ">");
        out.println("mc5b: k= " + kb);

        //mc6a
        NaturalNumber[] h = new NaturalNumber2[4];
        NaturalNumber c = new NaturalNumber2(1);
        for (int m = 0; m < h.length; m++) {
            h[m] = c.newInstance();
            c.increment();
        }
        out.println("mc6a: h= <" + h[0] + ", " + h[1] + ", " + h[2] + ", "
                + h[3] + ">");

        //mc6b
        NaturalNumber[] hb = new NaturalNumber2[4];
        NaturalNumber cb = new NaturalNumber2(0);
        for (int mb = 0; mb < hb.length; mb++) {
            cb.increment();
            hb[mb] = cb.newInstance();
        }
        out.println("mc6b: h= <" + hb[0] + ", " + hb[1] + ", " + hb[2] + ", "
                + hb[3] + ">");

        //sa1a
        NaturalNumber nn1 = new NaturalNumber1L(2);
        NaturalNumber nn2 = new NaturalNumber1L(6);
        NaturalNumber nn3 = new NaturalNumber1L(4);
        String s = "GOBucks";
        s = mystery1(s) + s;
        mystery2(nn1);
        nn2.add(nn2.divide(nn3));

        out.println("sa1a: s= " + s + ", nn1= " + nn1 + ", nn2= " + nn2);

        //sa1b
        NaturalNumber nn1b = new NaturalNumber1L(4);
        NaturalNumber nn2b = new NaturalNumber1L(10);
        NaturalNumber nn3b = new NaturalNumber1L(2);
        String sb = "OH-IO";
        sb = mystery1(sb) + sb;
        mystery2(nn1b);
        nn2b.add(nn2b.divide(nn3b));

        out.println("sa1b: s= " + sb + ", nn1= " + nn1b + ", nn2= " + nn2b);

        //sa2a
        NaturalNumber[] hh = new NaturalNumber[2];
        hh[0] = new NaturalNumber1L(4);
        hh[1] = new NaturalNumber1L(6);
        NaturalNumber nn4 = new NaturalNumber1L(8);
        mystery3(hh, nn4);
        out.println("sa2a: hh[0]= " + hh[0] + ", hh[1]= " + hh[1] + ", nn4= "
                + nn4);
        //sa2b
        NaturalNumber[] hhb = new NaturalNumber[2];
        hhb[0] = new NaturalNumber1L(6);
        hhb[1] = new NaturalNumber1L(4);
        NaturalNumber nn4b = new NaturalNumber1L(8);
        mystery3(hhb, nn4b);
        out.println("sa2b: hh[0]= " + hhb[0] + ", hh[1]= " + hhb[1] + ", nn4= "
                + nn4b);

        /*
         * Close input and output streams
         */
        in.close();
        out.close();
    }

}
