
package lukuvinkkikirjasto.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.List;

public interface Tip {
    boolean markRead();

    boolean markNotRead();

    boolean isRead();

    String identify();
    
    String getType();
        
    String getDescription();
    
    String getTitle();
    
    String getUrl();
    
<<<<<<< 47b2f8d34d4a4a76de640853862ad708a7b5568c
    String[] getTags();
    
    boolean getUrlpresent();
=======
    String getReference();
    
    boolean isUrlpresent();
>>>>>>> suosittelijan luominen vinkeille

    boolean edit(String element, String edit);
    
    boolean contains(String keyword);
}
