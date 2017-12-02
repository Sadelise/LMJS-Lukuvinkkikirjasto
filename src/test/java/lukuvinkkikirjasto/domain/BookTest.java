package lukuvinkkikirjasto.domain;

import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.dao.TipDao;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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
        Book book = new Book("A book about books", "Billy Book", "This book has a lot of information about books", "tag","ISBN1234");
        assertEquals("A book about books", book.getTitle());
        assertEquals("Billy Book", book.getAuthor());
        assertEquals("This book has a lot of information about books", book.getDescription());
        assertEquals("tag", book.getTagString());
        assertEquals("ISBN1234", book.getISBN());
    }

    @Test
    public void bookCanBeMarkedAsRead() {
        Book book = new Book("Name", "Author");
        TipDao dao = new BasicTipDao();
        assertEquals(false, book.isRead());
        book.markRead();
        assertEquals(true, book.isRead());
    }

    @Test
    public void bookCanBeSearchedByWord() {
        Book book = new Book("Name", "Author");
        book.setDescription("Testikuvaus");
        assertTrue(book.contains("testi"));
        assertTrue(book.contains("kuva"));
    }

}
