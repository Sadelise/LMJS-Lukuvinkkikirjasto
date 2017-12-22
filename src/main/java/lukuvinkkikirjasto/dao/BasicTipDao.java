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
    public int getPriority(Tip tip) {
        return 0;
    }

    @Override
    public void setPriority(String title, int i) {
        tips.get(title).setPriority(i);
    }

    @Override
    public void editTipByTitle(String title, String element, String edit) {
        Tip tip = getTip(title);
        tip.edit(element, edit);
    }

    @Override
    public List<Tip> searchByKeyword(String keyword) {
        List<Tip> filteredTips = new ArrayList<>();
        for (Tip tip : tips.values()) {
            if (tip.contains(keyword)) {
                filteredTips.add(tip);
            }
        }
        return filteredTips;
    }
}
