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
    public void markTip(int tip){

        if (!testTipNumber(tip)) {
            return;
        }
        tips.get(tip).markRead();
    }
    
    @Override
    public void removeTip(int tip) {
        
        if (!testTipNumber(tip)) {
            return;
        }
        tips.remove(tip);
    }
    
    @Override
    public boolean testTipNumber(int tip) {
        
        if(tip<0||tip>=tips.size()){
            System.out.println("Not a valid book. \n Enter a number between 1 and " + tips.size());
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String string = "";
        int tipNumber = 1;
        for(Tip tip : tips) {
            string += "Tip " + tipNumber + ":\n";
            string += tip.toString() + "\n" + "\n";
            tipNumber++;
        }
        return string;
    }
}
