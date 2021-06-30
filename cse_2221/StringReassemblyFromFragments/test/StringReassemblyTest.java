import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

public class StringReassemblyTest {

    /*
     * Tests of Combination
     */
    @Test
    public void combination_hello_olay_1() {
        String str1 = "hello";
        String str2 = "olay";
        int overlap = 1;
        String ret = StringReassembly.combination(str1, str2, overlap);
        assertEquals(ret, "hellolay");
    }

    @Test
    public void combination_Goal_alape_2() {
        String str1 = "Goal";
        String str2 = "al_ape";
        int overlap = 2;
        String ret = StringReassembly.combination(str1, str2, overlap);
        assertEquals(ret, "Goal_ape");
    }

    @Test
    public void combination_loaf_oafl_3() {
        String str1 = "loaf";
        String str2 = "oafl";
        int overlap = 3;
        String ret = StringReassembly.combination(str1, str2, overlap);
        assertEquals(ret, "loafl");
    }

    @Test
    public void combination_you_fool_of_a___fool_of_a_took_3() {
        String str1 = "you fool of a";
        String str2 = "fool of a took";
        int overlap = 9;
        String ret = StringReassembly.combination(str1, str2, overlap);
        assertEquals(ret, "you fool of a took");
    }

    /*
     * Tests of addToSetAvoidingSubstrings
     */

    @Test
    public void addToSetAvoidingSubstrings_strSet_hello() {
        Set<String> strSet = new Set1L<>();
        strSet.add("blown");
        strSet.add("olay");
        strSet.add("felon");
        strSet.add("jim");
        strSet.add("words");
        Set<String> strSetCopys = strSet.newInstance();
        strSetCopys.add("blown");
        strSetCopys.add("olay");
        strSetCopys.add("felon");
        strSetCopys.add("jim");
        strSetCopys.add("words");
        strSetCopys.add("hello");
        String str = "hello";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetCopys);
    }

    @Test
    public void addToSetAvoidingSubstrings_strSet_olay() {
        Set<String> strSet = new Set1L<>();
        strSet.add("blown");
        strSet.add("olay");
        strSet.add("felon");
        strSet.add("jim");
        strSet.add("words");
        Set<String> strSetCopys = strSet.newInstance();
        strSetCopys.add("blown");
        strSetCopys.add("olay");
        strSetCopys.add("felon");
        strSetCopys.add("jim");
        strSetCopys.add("words");
        String str = "olay";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetCopys);
    }

    @Test
    public void addToSetAvoidingSubstrings_strSet_own() {
        Set<String> strSet = new Set1L<>();
        strSet.add("blown");
        strSet.add("olay");
        strSet.add("felon");
        strSet.add("jim");
        strSet.add("words");
        Set<String> strSetCopys = strSet.newInstance();
        strSetCopys.add("blown");
        strSetCopys.add("olay");
        strSetCopys.add("felon");
        strSetCopys.add("jim");
        strSetCopys.add("words");
        String str = "own";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetCopys);
    }

    @Test
    public void addToSetAvoidingSubstrings_strSet_felony() {
        Set<String> strSet = new Set1L<>();
        strSet.add("blown");
        strSet.add("olay");
        strSet.add("felon");
        strSet.add("jim");
        strSet.add("words");
        Set<String> strSetCopys = strSet.newInstance();
        strSetCopys.add("blown");
        strSetCopys.add("olay");
        strSetCopys.add("felony");
        strSetCopys.add("jim");
        strSetCopys.add("words");
        String str = "felony";
        StringReassembly.addToSetAvoidingSubstrings(strSet, str);
        assertEquals(strSet, strSetCopys);
    }

    /*
     * Tests of linesFromInput
     */
    @Test
    public void linesFromInput_cheer() {
        SimpleReader input = new SimpleReader1L("data/cheer-8-2.txt");
        Set<String> in = StringReassembly.linesFromInput(input);
        Set<String> expectedIn = new Set1L<>();
        expectedIn.add("Bucks -- Beat");
        expectedIn.add("Go Bucks");
        expectedIn.add("Beat Mich");
        expectedIn.add("Michigan~");
        expectedIn.add("o Bucks -- B");
        assertEquals(in, expectedIn);
    }

    /*
     * Tests of printWithLineSeparators
     */
    @Test
    public void printWithLineSeparators_hello_friend() {
        String text = "hello~friend";
        SimpleWriter out = new SimpleWriter1L("data/test.txt");
        StringReassembly.printWithLineSeparators(text, out);
        SimpleReader in = new SimpleReader1L("data/test.txt");
        Set<String> input = new Set1L<>();
        while (!(in.atEOS())) {
            input.add(in.nextLine());
        }
        Set<String> expectedInput = new Set1L<>();
        expectedInput.add("hello");
        expectedInput.add("friend");
        in.close();
        assertEquals(input, expectedInput);
    }

    @Test
    public void printWithLineSeparators_h20() {
        String text = "h~2~0";
        SimpleWriter out = new SimpleWriter1L("data/test.txt");
        StringReassembly.printWithLineSeparators(text, out);
        SimpleReader in = new SimpleReader1L("data/test.txt");
        Set<String> input = new Set1L<>();
        while (!(in.atEOS())) {
            input.add(in.nextLine());
        }
        Set<String> expectedInput = new Set1L<>();
        expectedInput.add("h");
        expectedInput.add("2");
        expectedInput.add("0");
        in.close();
        assertEquals(input, expectedInput);
    }

}
