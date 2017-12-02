package lukuvinkkikirjasto.dao;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {

    public List<Tip> getAllTips();

    public void addTip(Tip tip);

    public Tip getTipByNumber(int tipNumber);

    public Tip getTip(String title);

    public void markTip(String id);

    public void removeTip(String id);

    public boolean testTipNumber(int tip);

    public void editTip(int tip, String element, String edit);

    public void editTipByTitle(String title, String element, String edit);

    public List<Tip> searchByKeyword(String keyword);
}
