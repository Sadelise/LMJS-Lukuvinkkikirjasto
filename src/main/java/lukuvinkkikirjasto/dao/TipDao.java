package lukuvinkkikirjasto.dao;

import java.util.ArrayList;
import java.util.List;
import lukuvinkkikirjasto.domain.Tip;

public interface TipDao {
    public List<Tip> getAllTips();
    
    public void addTip(Tip tip);

    public void markTip(int tip);
}
