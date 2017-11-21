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
        Book book = new Book("A book about books", "Billy Book", "This book has a lot of information about books", "ISBN1234", false);
        assertEquals("A book about books", book.getTitle());
        assertEquals("Billy Book", book.getAuthor());
        assertEquals("This book has a lot of information about books", book.getDescription());
        assertEquals("ISBN1234", book.getISBN());
    }

    @Test
    public void bookCanBeMarkedAsRead(){
        Book book = new Book("Name", "Author");
        TipDao dao = new BasicTipDao();
        assertEquals(false, book.isRead());
        book.markRead();
        Book book2 = new Book("A book about books", "Billy Book");
        dao.addTip(book2);
        dao.markTip(0);
        assertEquals(true, book.isRead());
        assertEquals(true, book2.isRead());
    }

    @Test
    public void nonexistentBookCanNotFound(){
        TipDao dao = new BasicTipDao();
        assertEquals(false, dao.testTipNumber(0));
    }

    @Test
    public void bookCanBeRemoved(){
        TipDao dao = new BasicTipDao();
        Tip book = new Book("Name", "Author");
        dao.addTip(book);
        dao.removeTip(0);
        assertEquals(0, dao.getAllTips().size());
    }

    @Test
    public void nonexistentBookCanNotBeRemoved(){
        TipDao dao = new BasicTipDao();
        Tip book = new Book("Author", "Book");
        dao.addTip(book);
        dao.removeTip(1);
        assertEquals(1, dao.getAllTips().size());
    }
}
