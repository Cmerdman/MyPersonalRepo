import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber2;

/**
 * @author Cameron Erdman
 *
 */
public class CryptoUtilitiesTest {

    /*
     * Tests of reduceToGCD
     */

    @Test
    public void testReduceToGCD_0_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(0);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    @Test
    public void testReduceToGCD_30_21() {
        NaturalNumber n = new NaturalNumber2(30);
        NaturalNumber nExpected = new NaturalNumber2(3);
        NaturalNumber m = new NaturalNumber2(21);
        NaturalNumber mExpected = new NaturalNumber2(0);
        CryptoUtilities.reduceToGCD(n, m);
        assertEquals(nExpected, n);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isEven
     */

    @Test
    public void testIsEven_0() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(0);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_1() {
        NaturalNumber n = new NaturalNumber2(1);
        NaturalNumber nExpected = new NaturalNumber2(1);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    @Test
    public void testIsEven_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_123124135125143613461346() {
        NaturalNumber n = new NaturalNumber2("123124135125143613461346");
        NaturalNumber nExpected = new NaturalNumber2(
                "123124135125143613461346");
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(true, result);
    }

    @Test
    public void testIsEven_234803() {
        NaturalNumber n = new NaturalNumber2(234803);
        NaturalNumber nExpected = new NaturalNumber2(234803);
        boolean result = CryptoUtilities.isEven(n);
        assertEquals(nExpected, n);
        assertEquals(false, result);
    }

    /*
     * Tests of powerMod
     */

    @Test
    public void testPowerMod_0_0_2() {
        NaturalNumber n = new NaturalNumber2(0);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(0);
        NaturalNumber pExpected = new NaturalNumber2(0);
        NaturalNumber m = new NaturalNumber2(2);
        NaturalNumber mExpected = new NaturalNumber2(2);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_17_18_19() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(18);
        NaturalNumber pExpected = new NaturalNumber2(18);
        NaturalNumber m = new NaturalNumber2(19);
        NaturalNumber mExpected = new NaturalNumber2(19);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_20_25_5() {
        NaturalNumber n = new NaturalNumber2(20);
        NaturalNumber nExpected = new NaturalNumber2(0);
        NaturalNumber p = new NaturalNumber2(25);
        NaturalNumber pExpected = new NaturalNumber2(25);
        NaturalNumber m = new NaturalNumber2(5);
        NaturalNumber mExpected = new NaturalNumber2(5);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    @Test
    public void testPowerMod_200_150_7() {
        NaturalNumber n = new NaturalNumber2(200);
        NaturalNumber nExpected = new NaturalNumber2(1);
        NaturalNumber p = new NaturalNumber2(150);
        NaturalNumber pExpected = new NaturalNumber2(150);
        NaturalNumber m = new NaturalNumber2(7);
        NaturalNumber mExpected = new NaturalNumber2(7);
        CryptoUtilities.powerMod(n, p, m);
        assertEquals(nExpected, n);
        assertEquals(pExpected, p);
        assertEquals(mExpected, m);
    }

    /*
     * Tests of isWitnessToCompositeness
     */
    @Test
    public void testIsWitnessToCompositeness_2_4() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        NaturalNumber w = new NaturalNumber2(4);
        NaturalNumber wExpected = new NaturalNumber2(4);
        boolean check = CryptoUtilities.isWitnessToCompositeness(n, w);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, check);
    }

    @Test
    public void testIsWitnessToCompositeness_5_17() {
        NaturalNumber n = new NaturalNumber2(5);
        NaturalNumber nExpected = new NaturalNumber2(5);
        NaturalNumber w = new NaturalNumber2(17);
        NaturalNumber wExpected = new NaturalNumber2(17);
        boolean check = CryptoUtilities.isWitnessToCompositeness(n, w);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(false, check);
    }

    @Test
    public void testIsWitnessToCompositeness_12_18() {
        NaturalNumber n = new NaturalNumber2(12);
        NaturalNumber nExpected = new NaturalNumber2(12);
        NaturalNumber w = new NaturalNumber2(18);
        NaturalNumber wExpected = new NaturalNumber2(18);
        boolean check = CryptoUtilities.isWitnessToCompositeness(n, w);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, check);
    }

    @Test
    public void testIsWitnessToCompositeness_191248912587_19124891258735() {
        NaturalNumber n = new NaturalNumber2("191248912587");
        NaturalNumber nExpected = new NaturalNumber2("191248912587");
        NaturalNumber w = new NaturalNumber2("19124891258735");
        NaturalNumber wExpected = new NaturalNumber2("19124891258735");
        boolean check = CryptoUtilities.isWitnessToCompositeness(n, w);
        assertEquals(nExpected, n);
        assertEquals(wExpected, w);
        assertEquals(true, check);
    }

    /*
     * Tests of isPrime1
     */
    @Test
    public void testIsPrime1_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean check = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(true, check);
    }

    @Test
    public void testIsPrime1_6() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(6);
        boolean check = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(false, check);
    }

    @Test
    public void testIsPrime1_17() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(17);
        boolean check = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(true, check);
    }

    @Test
    public void testIsPrime1_12353142634267() {
        NaturalNumber n = new NaturalNumber2("12353142634267");
        NaturalNumber nExpected = new NaturalNumber2("12353142634267");
        boolean check = CryptoUtilities.isPrime1(n);
        assertEquals(nExpected, n);
        assertEquals(false, check);
    }

    /*
     * Tests of isPrime2
     */
    @Test
    public void testIsPrime2_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(2);
        boolean check = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, check);
    }

    @Test
    public void testIsPrime2_6() {
        NaturalNumber n = new NaturalNumber2(6);
        NaturalNumber nExpected = new NaturalNumber2(6);
        boolean check = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, check);
    }

    @Test
    public void testIsPrime2_17() {
        NaturalNumber n = new NaturalNumber2(17);
        NaturalNumber nExpected = new NaturalNumber2(17);
        boolean check = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(true, check);
    }

    @Test
    public void testIsPrime2_12353142634267() {
        NaturalNumber n = new NaturalNumber2("12353142634267");
        NaturalNumber nExpected = new NaturalNumber2("12353142634267");
        boolean check = CryptoUtilities.isPrime2(n);
        assertEquals(nExpected, n);
        assertEquals(false, check);
    }

    /*
     * Tests of generateNextLikelyPrime
     */
    @Test
    public void testGenerateNextLikelyPrime_2() {
        NaturalNumber n = new NaturalNumber2(2);
        NaturalNumber nExpected = new NaturalNumber2(3);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_134() {
        NaturalNumber n = new NaturalNumber2(134);
        NaturalNumber nExpected = new NaturalNumber2(137);
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_1125126134612342() {
        NaturalNumber n = new NaturalNumber2("1125126134612342");
        NaturalNumber nExpected = new NaturalNumber2("1125126134612357");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

    @Test
    public void testGenerateNextLikelyPrime_1125126134612357() {
        NaturalNumber n = new NaturalNumber2("1125126134612357");
        NaturalNumber nExpected = new NaturalNumber2("1125126134612393");
        CryptoUtilities.generateNextLikelyPrime(n);
        assertEquals(nExpected, n);
    }

}
