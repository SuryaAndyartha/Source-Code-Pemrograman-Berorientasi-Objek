

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SalesItemTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SalesItemTest
{
    /**
     * Default constructor for test class SalesItemTest
     */
    public SalesItemTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void testRateBound()
    {
        SalesItem salesIte1 = new SalesItem("lababa", 3000);
        assertEquals(false, salesIte1.addComment("Amba", "SOAMAZING", 7));
    }

    @Test
    public void testCommentNum()
    {
        SalesItem salesIte1 = new SalesItem("Agumon", 1000);
        assertEquals(0, salesIte1.getNumberOfComments());
    }

    @Test
    public void testDupe()
    {
        SalesItem salesIte1 = new SalesItem("Gabumon", 10000);
        assertEquals(true, salesIte1.addComment("kaya", "amba", 1));
        assertEquals(false, salesIte1.addComment("kaya", "domba", 3));
    }

    @Test
    public void test()
    {
        SalesItem salesIte1 = new SalesItem("ambalabu", 5000);
        assertEquals(false, salesIte1.addComment("Amba", "SOAMAZING", 7));
    }
}
