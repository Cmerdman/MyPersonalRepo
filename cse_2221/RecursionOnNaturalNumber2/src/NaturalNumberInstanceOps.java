import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * Extension of {@code NaturalNumber2} with secondary operations implemented as
 * instance methods: add, subtract, and power.
 *
 * @author Put your name here
 *
 */
public final class NaturalNumberInstanceOps extends NaturalNumber2 {

    /**
     * No-argument constructor.
     */
    public NaturalNumberInstanceOps() {
    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumberInstanceOps(int i) {
        super(i);
    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumberInstanceOps(String s) {
        super(s);
    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumberInstanceOps(NaturalNumber n) {
        super(n);
    }

    @Override
    public void add(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        /**
         * @decreases n
         */
        int thisLowDigit = this.divideBy10();
        int nLowDigit = n.divideBy10();
        if (!n.isZero()) {
            this.add(n);
        }
        thisLowDigit += nLowDigit;
        if (thisLowDigit >= RADIX) {
            thisLowDigit -= RADIX;
            this.increment();
        }
        this.multiplyBy10(thisLowDigit);
        n.multiplyBy10(nLowDigit);
    }

    /**
     * Subtracts {@code n} from {@code this}.
     *
     * @param n
     *            {@code NaturalNumber} to subtract
     * @updates this
     * @requires this >= n
     * @ensures this = #this - n
     */
    @Override
    public void subtract(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";
        assert this.compareTo(n) >= 0 : "Violation of: this >= n";

        int thisLowDigit = this.divideBy10();
        int nLowDigit = n.divideBy10();
        if (!n.isZero()) {
            this.subtract(n);
        }
        if (thisLowDigit <= nLowDigit) {
            thisLowDigit += RADIX;
            this.decrement();
        }
        thisLowDigit -= nLowDigit;
        this.multiplyBy10(thisLowDigit);
        n.multiplyBy10(nLowDigit);
    }

    /**
     * Raises {@code this} to the power {@code p}.
     *
     * @param p
     *            power to raise to
     * @updates this
     * @requires p >= 0
     * @ensures this = #this ^ (p)
     */
    @Override
    public void power(int p) {
        assert p >= 0 : "Violation of: p >= 0";
        this.multiply(this);
        p -= 1;
        if (!(p == 0)) {
            this.power(p);
        }
    }

}