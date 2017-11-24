package lukuvinkkikirjasto.dao;

import java.util.ArrayList;
import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {
    public List<Tip> getAllTips();
    
    public void addTip(Tip tip);

    public Tip getTipByNumber(int tipNumber);
    
    public void markTip(String id);
    
    public void removeTip(String id);
    
    public boolean testTipNumber(int tip);
}
