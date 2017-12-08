package lukuvinkkikirjasto.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import lukuvinkkikirjasto.domain.Tip;

/**
 *
 * @author lmantyla
 */
public class TipDaoTest {

    BasicTipDao tipDao;
    Tip tip;

    class TestTip implements Tip {

        public boolean isRead;
        String id;
        public String description;

        public TestTip(String id) {
            isRead = false;
            this.id = id;
        }

        public boolean markRead() {
            isRead = true;
            return isRead;
        }

        public boolean isRead() {
            return isRead;
        }

        @Override
        public String getUrl() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public String getTitle() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean isUrlpresent() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public String identify() {
            return id;
        }

//        @Override
        public boolean edit(String element, String edit) {
            if (!element.equals("description")) {
                return false;
            }
            description = edit;
            return true;
        }

        @Override
        public boolean markNotRead() {
            isRead = false;
            return isRead;
        }

        @Override
        public boolean contains(String keyword) {
            keyword = keyword.toLowerCase().trim();
            if (description != null) {
                if (description.toLowerCase().contains(keyword)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String getType() {
            return "Book";
        }

        @Override
        public String getReference() {
            return "Suosittelija";
        }
    }

    @Before
    public void setUp() {
        tipDao = new BasicTipDao();
        tip = new TestTip("1");
        tip.edit("description", "Testikuvaus.");
    }

    @Test
    public void tipCanBeAddedAndRead() {
        assertEquals(tipDao.getAllTips().size(), 0);
        tipDao.addTip(tip);
        assertEquals(tipDao.getAllTips().size(), 1);
    }

    @Test
    public void multipleTipsCanBeAddedAndRead() {
        assertEquals(tipDao.getAllTips().size(), 0);
        tipDao.addTip(tip);
        Tip tip2 = new TestTip("2");
        tipDao.addTip(tip2);
        assertEquals(tipDao.getAllTips().size(), 2);
    }

    @Test
    public void nonexistentBookCanNotFound() {
        TipDao dao = new BasicTipDao();
        assertEquals(false, dao.testTipNumber(0));
    }

    @Test
    public void bookCanBeMarkedAsRead() {
        assertFalse(tip.isRead());
        tipDao.addTip(tip);
        tipDao.markTip(tip.identify());
        assertTrue(tip.isRead());
    }

    @Test
    public void bookCanBeRemoved() {
        tipDao.addTip(tip);
        tipDao.removeTip(tip.identify());
        assertEquals(0, tipDao.getAllTips().size());
    }

    @Test
    public void nonexistentBookCanNotBeRemoved() {
        tipDao.addTip(tip);
        tipDao.removeTip("Wrong");
        assertEquals(1, tipDao.getAllTips().size());
    }

    @Test
    public void existingAttributeReturnsTrueWhenSearching() {
        assertTrue(tip.contains("testikuvaus"));
        assertTrue(tip.contains("testi"));
    }

    @Test
    public void nonExistingAttributeReturnsFalseWhenSearching() {
        assertTrue(!tip.contains("nakki"));
    }
}
