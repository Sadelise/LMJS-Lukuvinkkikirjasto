package lukuvinkkikirjasto.dao;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lukuvinkkikirjasto.domain.*;

/**
 *
 * @author lmantyla
 */
public class FireBaseTipDao implements TipDao {

    private Map<String, Tip> tips;
    private final String url;

    public FireBaseTipDao() {
        this.url = "https://lukuvinkkikirjasto.firebaseio.com/";
        this.tips = new HashMap<>();

        new Firebase(this.url).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot data) {
                for (DataSnapshot snapshot : data.getChildren()) {
                    Tip tip = snapshot.getValue(Book.class);
                    tips.put(tip.getId(), tip);
                }
                /*for (String key : tips.keySet()) {
                    System.out.println(tips.get(key));
                }*/
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    /*public void delete(String key) {
        new Firebase(this.url).child(key).removeValue();
        this.items.remove(key);
    }

    public FirebaseItem get(String key) {
        return this.items.get(key);
    }

    public List<FirebaseItem> list() {
        return this.items.values().stream()
                .filter(o -> o.getName() != null)
                .sorted((o1, o2) -> o1.getName().compareTo(o2.getName()))
                .collect(Collectors.toList());
    }
}*/
    @Override
    public List<Tip> getAllTips() {
        return new ArrayList<>(tips.values());
    }

    @Override
    public void addTip(Tip tip) {
        new Firebase(this.url).child(tip.getId()).setValue(tip);
        this.tips.put(tip.getId(), tip);
    }

    @Override
    public Tip getTipByNumber(int tipNumber) {
        ArrayList<Tip> values = new ArrayList<>(tips.values());
        String key = values.get(tipNumber).getId();

        if (testTipNumber(tipNumber)) {
            return values.get(tipNumber);
        }
        return null;
    }

    @Override
    public void markTip(String id) {
        Tip tip = tips.get(id);
        
        if (tip == null) {
            return;
        }
        tip.markRead();
        
        new Firebase(this.url).child(tip.getId()).setValue(tip);
        this.tips.put(tip.getId(), tip);
    }

    @Override
    public void removeTip(String id) {
        new Firebase(this.url).child(id).removeValue();
        this.tips.remove(id);
    }

    @Override
    public boolean testTipNumber(int tip) {
        if (tip < 0 || tip >= tips.size()) {
            //System.out.println("Not a valid book. \n Enter a number between 1 and " + tips.size());
            return false;
        }
        return true;
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
}
