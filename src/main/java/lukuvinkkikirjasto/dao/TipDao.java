package lukuvinkkikirjasto.dao;

import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {

    public List<Tip> getAllTips();

    public void addTip(Tip tip);

    public Tip getTip(String title);

    public int getPriority(Tip tip);

    public void setPriority(String title, int i);

    public void markTip(String id);

    public void removeTip(String id);

    public void editTipByTitle(String title, String element, String edit);

    public List<Tip> searchByKeyword(String keyword);
}
