package lukuvinkkikirjasto.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.dao.TipDao;

/**
 * Simple Dao implementation with no data persistence.
 * @author lmantyla
 */
public class BasicTipDao implements TipDao {

    private Map<String, Tip> tips;
    
    public BasicTipDao() {
        tips = new HashMap<String, Tip>();
    }
        
    @Override
    public List<Tip> getAllTips() {
        return new ArrayList<>(tips.values());
    }

    @Override
    public void addTip(Tip tip) {
        tips.put(tip.getId(), tip);
    }

    @Override
    public void markTip(String id){
        Tip tip = tips.get(id);
        tip.markRead();
    }
    
    @Override
    public void removeTip(String id) {
        tips.remove(id);
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
    public Tip getTipByNumber(int tipNumber) {
        if (testTipNumber(tipNumber)) {
            return getAllTips().get(tipNumber);
        }
        return null;
    }


    @Override
    public String toString() {
        String string = "";
        int tipNumber = 1;
        for(Tip tip : tips.values()) {
            string += "Tip " + tipNumber + ":\n";
            string += tip.toString() + "\n" + "\n";
            tipNumber++;
        }
        return string;
    }
}
