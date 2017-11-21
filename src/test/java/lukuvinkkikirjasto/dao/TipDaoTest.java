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

        public TestTip() {
            isRead = false;
        }

        public boolean markRead() {
            isRead = true;
            return isRead;
        }

        public boolean isRead() {
            return isRead;
        }
    }

    @Before
    public void setUp() {
        tipDao = new BasicTipDao();
        tip = new TestTip();
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
        tipDao.addTip(tip);
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
        tipDao.markTip(0);
        assertTrue(tip.isRead());
    }

    @Test
    public void bookCanBeRemoved() {
        tipDao.addTip(tip);
        tipDao.removeTip(0);
        assertEquals(0, tipDao.getAllTips().size());
    }

    @Test
    public void nonexistentBookCanNotBeRemoved() {
        tipDao.addTip(tip);
        tipDao.removeTip(1);
        assertEquals(1, tipDao.getAllTips().size());
    }
}
