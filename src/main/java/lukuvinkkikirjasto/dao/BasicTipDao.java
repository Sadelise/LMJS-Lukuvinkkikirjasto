package lukuvinkkikirjasto.dao;

import java.util.ArrayList;
import java.util.List;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.dao.TipDao;

/**
 * Simple Dao implementation with no data persistence.
 * @author lmantyla
 */
public class BasicTipDao implements TipDao {

    List<Tip> tips;
    
    public BasicTipDao() {
        tips = new ArrayList<Tip>();
    }
        
    @Override
    public List<Tip> getAllTips() {
        return tips;
    }

    @Override
    public void addTip(Tip tip) {
        tips.add(tip);
    }

    @Override
    public String toString() {
        String string = "";
        int tipNumber = 1;
        for(Tip tip : tips) {
            string += "Tip " + tipNumber + ":\n";
            string += tip.toString() + "\n";
            tipNumber++;
        }
        return string;
    }
}
