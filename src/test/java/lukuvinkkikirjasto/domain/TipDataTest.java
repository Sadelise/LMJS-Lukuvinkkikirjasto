package lukuvinkkikirjasto.domain;

import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.dao.TipDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class TipDataTest {

    public TipDataTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void TipDataCanBeCreatedFromBook() {
        Book book = new Book("A book about books", "Billy Book");
        assertEquals("A book about books", book.getTitle());
        assertEquals("Billy Book", book.getAuthor());
    }

}
