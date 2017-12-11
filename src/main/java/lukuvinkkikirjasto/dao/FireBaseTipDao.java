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
                    String type = (String) snapshot.child("type").getValue();
                    if (type.equals("Book")) {
                        System.out.println("Kirja tunnistettu!");
                        TipData tipData = snapshot.getValue(TipData.class);
                        Book book = new Book(tipData);
                        tips.put(book.identify(), book);
                    }
                    type = (String) snapshot.child("type").getValue();
                    if (type.equals("YouTubeVideo")) {
                        System.out.println("Youtubevideo tunnistettu!");
                        TipData tipData = snapshot.getValue(TipData.class);
                        YouTubeVideo video = new YouTubeVideo(tipData);
                        //Tip tip = snapshot.getValue(YouTubeVideo.class);
                        tips.put(video.identify(), video);
                    }
                }
            }

            /*for (String key : tips.keySet()) {
                 System.out.println(tips.get(key));
                 }*/
            
            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    @Override

    public List<Tip> getAllTips() {
        return new ArrayList<>(tips.values());
    }

    @Override
    public void addTip(Tip tip) {
        new Firebase(this.url).child(tip.identify()).setValue(new TipData(tip));
        this.tips.put(tip.identify(), tip);
    }

    @Override
    public Tip getTip(String title) {
        return tips.get(title);
    }

    @Override
    public int getPriority(Tip tip) {
        return tip.getPriority();
    }

    @Override
    public void setPriority(String title, int i) {
        tips.get(title).setPriority(i);
    }

    @Override
    public void markTip(String id) {
        Tip tip = tips.get(id);

        if (tip == null) {
            return;
        }
        if (!tip.isRead()) {
            tip.markRead();
        } else {
            tip.markNotRead();
        }

        new Firebase(this.url).child(tip.identify()).setValue(new TipData(tip));
        this.tips.put(tip.identify(), tip);
    }

    @Override
    public void removeTip(String id) {
        new Firebase(this.url).child(id).removeValue();
        this.tips.remove(id);
    }

    @Override
    public void editTipByTitle(String title, String element, String edit) {
        Tip tip = getTip(title);
        tip.edit(element, edit);

        new Firebase(this.url).child(tip.identify()).setValue(new TipData(tip));
        this.tips.put(tip.identify(), tip);
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
