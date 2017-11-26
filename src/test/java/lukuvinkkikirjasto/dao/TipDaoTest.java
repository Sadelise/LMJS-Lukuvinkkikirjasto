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
        public String descrption;

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
        
        public String identify() {
            return id;
        }

//        @Override
        public boolean edit(String element, String edit) {
            if (!element.equals("description"))
                return false;
            descrption = edit;
            return true;
        }
    }

    @Before
    public void setUp() {
        tipDao = new BasicTipDao();
        tip = new TestTip("1");
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

}
