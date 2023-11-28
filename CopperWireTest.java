import junit.framework.TestCase;
public class CopperWireTest extends TestCase {
    // array of wire sizes that we know about (AWG 6...28)
    CopperWire[] wire = new CopperWire[CopperWire.NumberOfSizes];

    public static void main(final String[] args) {
        junit.textui.TestRunner.run(CopperWireTest.class);
    }

    protected void setUp() {
        wire = CopperWire.initialize();
    }

    // Verify the wire values are already sorted
    // Repeat test three times, once for each gauge (AWG, SWG, metric)
    public void testWireSize() {
        for (int gauge=0; gauge<CopperWire.NumberOfGauges; gauge++) {
            for (int ii=0; ii<CopperWire.NumberOfSizes-1; ii++) {
                assertTrue("current array has incorrect sort order",   wire[ii].SafeCurrent()  > wire[ii+1].SafeCurrent() );
                assertTrue("area array has incorrect sort order",      wire[ii].CircularMils() > wire[ii+1].CircularMils() );
                assertTrue("diameter array has incorrect sort order",  wire[ii].Diameter()     > wire[ii+1].Diameter() );
                assertTrue("resistance array has incorrect sort order",wire[ii].Resistance(1)  < wire[ii+1].Resistance(1) );
                assertTrue("weight array has incorrect sort order",    wire[ii].Weight(1)      > wire[ii+1].Weight(1) );
            }
            wire = CopperWire.nextGaugeType();
        }
    }

    // verify GetSize() function
    public void testGetSize() {
        final String s = wire[0].Name();
        assertTrue("wire size string is too short", s.length() > 2);
    }

    // verify it can cycle through AWG -> SWG -> metric -> AWG
    public void testNextGaugeType() {
        String sSize;
        sSize = wire[0].Name();
        assertEquals("expected wire[0] to be 4 AWG", "4  AWG", sSize);
        assertEquals("missing elements in wire array", CopperWire.NumberOfSizes, 29);

        wire = CopperWire.nextGaugeType();
        sSize = wire[0].Name();
        assertEquals("expected wire[0] to be 4 SWG", "4  SWG", sSize);
        assertEquals(CopperWire.NumberOfSizes, 29);

        wire = CopperWire.nextGaugeType();
        sSize = wire[0].Name();
        assertEquals("expected wire[0] to be 3.35 mm", sSize, "3.35 mm");
        assertEquals(CopperWire.NumberOfSizes, 37);

        wire = CopperWire.nextGaugeType();
        sSize = wire[0].Name();
        assertEquals("expected wire[0] to be 4 AWG", sSize, "4  AWG");
        assertEquals(CopperWire.NumberOfSizes, 29);
    }

    // test a helper function
//    public void testNearestTenth() {
//        String res = CopperWire.NearestTenth(1.45F);
//        assertEquals("NearestTenth failed to round up", res, "1.5");
//
//        res = CopperWire.NearestTenth(1.4999F);
//        assertEquals("NearestTenth failed to round up", res, "1.5");
//
//        res = CopperWire.NearestTenth(2.5001F);
//        assertEquals(res, "2.5");
//    }
}