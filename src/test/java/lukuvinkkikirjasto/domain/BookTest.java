package lukuvinkkikirjasto.domain;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"})
public class BookTest {

    public BookTest() {
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
    public void bookHasTitleAndAuthor() {
        Book book = new Book("A book about books", "Billy Book");
        assertEquals("A book about books", book.getTitle());
        assertEquals("Billy Book", book.getAuthor());
    }

    @Test
    public void bookHasAllInformation() {
        Book book = new Book("A book about books", "Billy Book", "This book has a lot of information about books", "ISBN1234");
        assertEquals("A book about books", book.getTitle());
        assertEquals("Billy Book", book.getAuthor());
        assertEquals("This book has a lot of information about books", book.getDescription());
        assertEquals("ISBN1234", book.getISBN());
    }
}
