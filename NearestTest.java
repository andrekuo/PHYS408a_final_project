/*
 * NearestTest.java
 * JUnit based test
 *
 * @author Barry (barry@coilgun.info)
 * Created on October 15, 2003, 10:07 PM
 * Updated Sept 21, 2005 for CoilSim9
 * Updated Oct 19, 2007 for InductorSim9
 */

import junit.framework.*;

public class NearestTest extends TestCase {
    
    public NearestTest(java.lang.String testName) {
        super(testName);
    }
    
    public static Test suite() {
        return new TestSuite(NearestTest.class);
    }
    
    /** Test of Unit method, of class nearest */
    public void testUnit() {
        System.out.println("testUnit");
        // Note: when String's don't match, it only prints the chars that are different
        Nearest nearest = new Nearest();
        System.out.println("   nearest.Unit(0.499) = " + nearest.Unit(0.499));
        System.out.println("   nearest.Unit(0.500) = " + nearest.Unit(0.500));
        System.out.println("   nearest.Unit(0.501) = " + nearest.Unit(0.501));
        assertEquals("0", nearest.Unit(0.1));
        assertEquals("0", nearest.Unit(0.499));
        assertEquals("0", nearest.Unit(0.500)); // round down to even number
        assertEquals("2", nearest.Unit(1.5));   // round up to even number
        assertEquals("1,000", nearest.Unit(1000.)); // must show comma for thousands
    }
    
    /** Test of Tenth method, of class nearest */
    public void testTenth() {
        System.out.println("testTenth");
        Nearest nearest = new Nearest();
        System.out.println("   nearest.Tenth(0.01) = " + nearest.Tenth(0.01));
        assertEquals("0.0", nearest.Tenth(0));
        assertEquals("0.0", nearest.Tenth(0.01));
        assertEquals("0.0", nearest.Tenth(0.049));
        assertEquals("0.0", nearest.Tenth(0.05));
        assertEquals("0.1", nearest.Tenth(0.10));
        assertEquals("0.1", nearest.Tenth(0.14));
        assertEquals("0.2", nearest.Tenth(0.15));
    }
    
    /** Test of Hundredth method, of class nearest */
    public void testHundredth() {
        System.out.println("testHundredth");
        Nearest nearest = new Nearest();
        System.out.println("   nearest.Hundredth(0.001) = " + nearest.Hundredth(0.001));
        assertEquals("0.00", nearest.Hundredth(0));
        assertEquals("0.00", nearest.Hundredth(0.001));
        assertEquals("0.00", nearest.Hundredth(0.0049));
        assertEquals("0.00", nearest.Hundredth(0.005));
        assertEquals("0.01", nearest.Hundredth(0.010));
        assertEquals("0.01", nearest.Hundredth(0.014));
        assertEquals("0.02", nearest.Hundredth(0.015));
    }
    
    /** Test of Thousandth method, of class nearest */
    public void testThousandth() {
        Nearest nearest = new Nearest();
        System.out.println("   nearest.Thousandth(0.0001) = " + nearest.Thousandth(0.0001));
        assertEquals("0.000", nearest.Thousandth(0));
        assertEquals("0.000", nearest.Thousandth(0.0001));
        assertEquals("0.000", nearest.Thousandth(0.00049));
        assertEquals("0.000", nearest.Thousandth(0.0005));
        assertEquals("0.001", nearest.Thousandth(0.0010));
        assertEquals("0.001", nearest.Thousandth(0.0014));
        assertEquals("0.002", nearest.Thousandth(0.0015));
    }
    
    /** Test of toStringSigFig method, of class nearest
     * For example: 0.012 - 0.12 - 1.2 - 12 - 120 - 1,200 - 12,000
     */
    public void testToStringSigFig2() {
        System.out.println("testToStringSigFig2");
        Nearest nearest = new Nearest();
        
        // test TWO significant figures
        System.out.println("   nearest.toStringSigFig(0.01234, 2) = " + nearest.toStringSigFig(0.01234, 2));
        System.out.println("   nearest.toStringSigFig(0.12345, 2) = " + nearest.toStringSigFig(0.12345, 2));
        System.out.println("   nearest.toStringSigFig(0.99999, 2) = " + nearest.toStringSigFig(0.99999, 2));
        System.out.println("   nearest.toStringSigFig(1.0, 2)     = " + nearest.toStringSigFig(1., 2));
        System.out.println("   nearest.toStringSigFig(1.00001, 2) = " + nearest.toStringSigFig(1.00001, 2));
        System.out.println("   nearest.toStringSigFig(1.23456, 2) = " + nearest.toStringSigFig(1.23456, 2));
        System.out.println("   nearest.toStringSigFig(12.3456, 2) = " + nearest.toStringSigFig(12.3456, 2));
        System.out.println("   nearest.toStringSigFig(123.456, 2) = " + nearest.toStringSigFig(123.456, 2));
        System.out.println("   nearest.toStringSigFig(1234.56, 2) = " + nearest.toStringSigFig(1234.56, 2));
        System.out.println("   nearest.toStringSigFig(12345.6, 2) = " + nearest.toStringSigFig(12345.6, 2));
        System.out.println("   nearest.toStringSigFig(123456., 2) = " + nearest.toStringSigFig(123456., 2));
        assertEquals("120,000",nearest.toStringSigFig(123456., 2));
        assertEquals("12,000", nearest.toStringSigFig(12345.6, 2));
        assertEquals("1,200",  nearest.toStringSigFig(1234.56, 2));
        assertEquals("120",   nearest.toStringSigFig(123.456, 2));
        assertEquals("12",    nearest.toStringSigFig(12.3456, 2));
        assertEquals("1.2",   nearest.toStringSigFig(1.23456, 2));
        assertEquals("1.0",   nearest.toStringSigFig(1.0, 2));
        assertEquals("1.0",   nearest.toStringSigFig(0.99999, 2));
        assertEquals("0.12",  nearest.toStringSigFig(0.12345, 2));
        assertEquals("0.012", nearest.toStringSigFig(0.01234, 2));
    }
    
    /** Test of toStringSigFig method, of class nearest
     * For example: 0.0123 - 0.123 - 1.23 - 12.3 - 123 - 1,230 - 12,300
     */
    public void testToStringSigFig3() {
        System.out.println("testToStringSigFig3");
        Nearest nearest = new Nearest();
        
        // test THREE significant figures
        System.out.println("   nearest.toStringSigFig(0.01234, 3) = " + nearest.toStringSigFig(0.01234, 3));
        System.out.println("   nearest.toStringSigFig(0.12345, 3) = " + nearest.toStringSigFig(0.12345, 3));
        System.out.println("   nearest.toStringSigFig(1.23456, 3) = " + nearest.toStringSigFig(1.23456, 3));
        System.out.println("   nearest.toStringSigFig(12.3456, 3) = " + nearest.toStringSigFig(12.3456, 3));
        System.out.println("   nearest.toStringSigFig(123.456, 3) = " + nearest.toStringSigFig(123.456, 3));
        System.out.println("   nearest.toStringSigFig(1234.56, 3) = " + nearest.toStringSigFig(1234.56, 3));
        System.out.println("   nearest.toStringSigFig(12345.6, 3) = " + nearest.toStringSigFig(12345.6, 3));
        System.out.println("   nearest.toStringSigFig(123456., 3) = " + nearest.toStringSigFig(123456., 3));
        assertEquals("0.0123", nearest.toStringSigFig(0.01234, 3));
        assertEquals("0.123",  nearest.toStringSigFig(0.12345, 3));
        assertEquals("1.23",   nearest.toStringSigFig(1.23456, 3));
        assertEquals("12.3",   nearest.toStringSigFig(12.3456, 3));
        assertEquals("123",    nearest.toStringSigFig(123.456, 3));
        assertEquals("1,230",  nearest.toStringSigFig(1234.56, 3));
        assertEquals("12,300", nearest.toStringSigFig(12345.6, 3));
        assertEquals("123,000",nearest.toStringSigFig(123456., 3));
    }
    
    /** Test of toStringEng method, of class TimeSim1. 
     */
    public void testToStringEng() {
        System.out.println("testToStringEng");
        Nearest nearest = new Nearest();
        System.out.println("    toStringEng(1E-1, 2, \"A\") = '"+ nearest.toStringEng(1E-1, 2, "A") + "'");
        System.out.println("    toStringEng( 90, 2, \"A\") = '" + nearest.toStringEng( 90, 2, "A") + "'");
        System.out.println("    toStringEng( 99, 2, \"A\") = '" + nearest.toStringEng( 99, 2, "A") + "'");
        System.out.println("    toStringEng(100, 2, \"A\") = '" + nearest.toStringEng(100, 2, "A") + "'");
        System.out.println("    toStringEng(110, 2, \"A\") = '" + nearest.toStringEng(110, 2, "A") + "'");
        System.out.println("    ");
        System.out.println("    toStringEng(1E4, 2, \"Hz\") = '" + nearest.toStringEng(1E4, 2, "Hz") + "'");
        System.out.println("    toStringEng(1E3, 2, \"Hz\") = '" + nearest.toStringEng(1E3, 2, "Hz") + "'");
        System.out.println("    toStringEng(1E2, 2, \"Hz\") = '" + nearest.toStringEng(1E2, 2, "Hz") + "'");
        
        assertEquals("1.0 TA", nearest.toStringEng(1E12, 2, "A")); 
        assertEquals("100 GA", nearest.toStringEng(1E11, 2, "A")); 
        assertEquals("10 GA",  nearest.toStringEng(1E10, 2, "A")); 
        assertEquals("1.0 GA", nearest.toStringEng(1E9, 2, "A")); 
        assertEquals("100 MA", nearest.toStringEng(1E8, 2, "A")); 
        assertEquals("10 MA",  nearest.toStringEng(1E7, 2, "A")); 
        assertEquals("1.0 MA", nearest.toStringEng(1E6, 2, "A")); 
        assertEquals("100 KA", nearest.toStringEng(1E5, 2, "A")); 
        assertEquals("10 KA",  nearest.toStringEng(1E4, 2, "A")); 
        assertEquals("1.0 KA", nearest.toStringEng(1E3, 2, "A")); 
        assertEquals("100 A",  nearest.toStringEng(100, 2, "A")); 
        assertEquals("10 A",   nearest.toStringEng(10, 2, "A"));
        assertEquals("1.0 A",  nearest.toStringEng(1, 2, "A"));
        assertEquals("100 mA", nearest.toStringEng(1E-1, 2, "A"));
        assertEquals("10 mA",  nearest.toStringEng(1E-2, 2, "A"));
        assertEquals("1.0 mA", nearest.toStringEng(1E-3, 2, "A"));
        assertEquals("100 uA", nearest.toStringEng(1E-4, 2, "A"));
        assertEquals("10 uA",  nearest.toStringEng(1E-5, 2, "A"));
        assertEquals("1.0 uA", nearest.toStringEng(1E-6, 2, "A"));
        assertEquals("100 nA", nearest.toStringEng(1E-7, 2, "A"));
        assertEquals("10 nA",  nearest.toStringEng(1E-8, 2, "A"));
        assertEquals("1.0 nA", nearest.toStringEng(1E-9, 2, "A"));

        // zero is special case: it has no units
        // this makes it easier to label graphs
        assertEquals("0", nearest.toStringEng(0, 2, "mom"));
        assertEquals("0", nearest.toStringEng(0, 3, "dad"));
    }
    
    /** Test of RoundTo method, of class Nearest. 
     */
    public void testRoundTo() {
        System.out.println("testRoundTo");
        Nearest nearest = new Nearest();
        
        System.out.println("    RoundTo(0.002, 10) = " + nearest.RoundTo(0.002, 10));
        System.out.println("    RoundTo(200.0, 10) = " + nearest.RoundTo(200.0, 10));
        assertEquals("0",      nearest.RoundTo(0.002, 10));
        assertEquals("0",      nearest.RoundTo(0.02, 10)); 
        assertEquals("0",      nearest.RoundTo(0.2, 10)); 
        assertEquals("0",      nearest.RoundTo(2.0, 10)); 
        assertEquals("20",     nearest.RoundTo(20.0, 10)); 
        assertEquals("200",    nearest.RoundTo(200.0, 10)); 
        assertEquals("2,000",  nearest.RoundTo(2000.0, 10)); 
        assertEquals("20,000", nearest.RoundTo(20000.0, 10)); 
    }
    
    /** Test of Decade method, of class nearest */
    public void testDecade() {
        System.out.println("testDecade");
        Nearest nearest = new Nearest();
        System.out.println("   nearest.Decade(0.01) = " + Nearest.Decade(0.01));
        String units = "a";
        // test within one decade
        assertEquals("1.0 a", nearest.toStringEng( Nearest.Decade(1.0) , 2, units));
        assertEquals("10 a", nearest.toStringEng( Nearest.Decade(1.1) , 2, units));
        assertEquals("10 a", nearest.toStringEng( Nearest.Decade(2.0) , 2, units));
        assertEquals("10 a", nearest.toStringEng( Nearest.Decade(4.0) , 2, units));
        assertEquals("10 a", nearest.toStringEng( Nearest.Decade(8.0) , 2, units));
        assertEquals("10 a", nearest.toStringEng( Nearest.Decade(9.9) , 2, units));
        // test a range of decades
        assertEquals("10 ma",  nearest.toStringEng( Nearest.Decade(1E-3) , 2, units));
        assertEquals("100 ma", nearest.toStringEng( Nearest.Decade(1E-2) , 2, units));
        assertEquals("1.0 a",  nearest.toStringEng( Nearest.Decade(1E-1) , 2, units));
        assertEquals("1.0 a",  nearest.toStringEng( Nearest.Decade(1E0) , 2, units));
        assertEquals("10 a",   nearest.toStringEng( Nearest.Decade(1E1) , 2, units));
        assertEquals("100 a",  nearest.toStringEng( Nearest.Decade(1E2) , 2, units));
        assertEquals("1.0 Ka", nearest.toStringEng( Nearest.Decade(1E3) , 2, units));
        assertEquals("10 Ka",  nearest.toStringEng( Nearest.Decade(1E4) , 2, units));
        assertEquals("100 Ka", nearest.toStringEng( Nearest.Decade(1E5) , 2, units));
    }

    /** Test of Decade method, of class nearest */
    public void testHalfDecade() {
        System.out.println("testHalfDecade");
        Nearest nearest = new Nearest();
        String units = "a";
        // test within one decade
        assertEquals("4.0 a", nearest.toStringEng( Nearest.HalfDecade(1.001),2, units));
        assertEquals("4.0 a", nearest.toStringEng( Nearest.HalfDecade(1.01), 2, units));
        assertEquals("4.0 a", nearest.toStringEng( Nearest.HalfDecade(1.1) , 2, units));
        assertEquals("4.0 a", nearest.toStringEng( Nearest.HalfDecade(2.0) , 2, units));
        assertEquals("4.0 a", nearest.toStringEng( Nearest.HalfDecade(4.0) , 2, units));
        assertEquals("10 a", nearest.toStringEng( Nearest.HalfDecade(8.0) , 2, units));
        assertEquals("10 a", nearest.toStringEng( Nearest.HalfDecade(9.9) , 2, units));
        assertEquals("10 a", nearest.toStringEng( Nearest.HalfDecade(10.) , 2, units));
        // test a range of decades
        assertEquals("1.0 ma", nearest.toStringEng( Nearest.HalfDecade(1E-3) , 2, units));
        assertEquals("10 ma",  nearest.toStringEng( Nearest.HalfDecade(1E-2) , 2, units));
        assertEquals("100 ma",  nearest.toStringEng( Nearest.HalfDecade(1E-1) , 2, units));
        assertEquals("1.0 a",  nearest.toStringEng( Nearest.HalfDecade(1E0) , 2, units));
        assertEquals("10 a",   nearest.toStringEng( Nearest.HalfDecade(1E1) , 2, units));
        assertEquals("100 a",  nearest.toStringEng( Nearest.HalfDecade(1E2) , 2, units));
        assertEquals("1.0 Ka", nearest.toStringEng( Nearest.HalfDecade(1E3) , 2, units));
        assertEquals("10 Ka",  nearest.toStringEng( Nearest.HalfDecade(1E4) , 2, units));
        assertEquals("100 Ka", nearest.toStringEng( Nearest.HalfDecade(1E5) , 2, units));
    }

    /** Test of ThirdDecade method, of class nearest */
    public void testThirdDecade() {
        System.out.println("testThirdDecade");
        Nearest nearest = new Nearest();
        String units = "b";
        // test within one decade
        assertEquals("2.0 b", nearest.toStringEng( Nearest.ThirdDecade(1.001),2, units));
        assertEquals("2.0 b", nearest.toStringEng( Nearest.ThirdDecade(1.01), 2, units));
        assertEquals("2.0 b", nearest.toStringEng( Nearest.ThirdDecade(1.10) , 2, units));
        assertEquals("2.0 b", nearest.toStringEng( Nearest.ThirdDecade(2.00) , 2, units));

        assertEquals("4.0 b", nearest.toStringEng( Nearest.ThirdDecade(2.01) , 2, units));
        assertEquals("4.0 b", nearest.toStringEng( Nearest.ThirdDecade(4.00) , 2, units));

        assertEquals("10 b", nearest.toStringEng( Nearest.ThirdDecade(4.01) , 2, units));
        assertEquals("10 b", nearest.toStringEng( Nearest.ThirdDecade(10.) , 2, units));
        assertEquals("20 b", nearest.toStringEng( Nearest.ThirdDecade(10.01), 2, units));
        // test a range of decades
        assertEquals("1.0 mb", nearest.toStringEng( Nearest.ThirdDecade(1E-3) , 2, units));
        assertEquals("10 mb",  nearest.toStringEng( Nearest.ThirdDecade(1E-2) , 2, units));
        assertEquals("100 mb",  nearest.toStringEng( Nearest.ThirdDecade(1E-1) , 2, units));
        assertEquals("1.0 b",  nearest.toStringEng( Nearest.ThirdDecade(1E0) , 2, units));
        assertEquals("10 b",   nearest.toStringEng( Nearest.ThirdDecade(1E1) , 2, units));
        assertEquals("100 b",  nearest.toStringEng( Nearest.ThirdDecade(1E2) , 2, units));
        assertEquals("1.0 Kb", nearest.toStringEng( Nearest.ThirdDecade(1E3) , 2, units));
        assertEquals("10 Kb",  nearest.toStringEng( Nearest.ThirdDecade(1E4) , 2, units));
        assertEquals("100 Kb", nearest.toStringEng( Nearest.ThirdDecade(1E5) , 2, units));
    }

    // Add test methods here, they have to start with 'test' name.
    // for example:
    // public void testHello() {}
    
    
}
