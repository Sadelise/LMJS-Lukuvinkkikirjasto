package lukuvinkkikirjasto.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.dao.TipDao;

/**
 * Simple Dao implementation with no data persistence.
 *
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
        tips.put(tip.identify(), tip);
    }

    @Override
    public void markTip(String id) {
        Tip tip = tips.get(id);
        if (!tip.isRead()) {
            tip.markRead();
        } else {
            tip.markNotRead();
        }
    }

    @Override
    public void removeTip(String id) {
        tips.remove(id);
    }

    @Override
    public boolean testTipNumber(int tip) {

        if (tip < 0 || tip >= tips.size()) {
            System.out.println("Not a valid book. \n Enter a number between 1 and " + tips.size());
            return false;
        }
        return true;
    }

    @Override
    public void editTip(int tip, String element, String edit) {

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
        for (Tip tip : tips.values()) {
            string += "Tip " + tipNumber + ":\n";
            string += tip.toString() + "\n" + "\n";
            tipNumber++;
        }
        return string;
    }

    @Override
    public Tip getTip(String title) {
        return this.tips.get(title);
    }

    @Override
    public void editTipByTitle(String title, String element, String edit) {

    }

    @Override
    public List<Tip> searchByAttribute(String attribute) {
        List<Tip> filteredTips = new ArrayList<>();
        for (Tip tip : filteredTips) {
            if (tip.contains(attribute)) {
                filteredTips.add(tip);
            }
        }
        return filteredTips;
    }
}
