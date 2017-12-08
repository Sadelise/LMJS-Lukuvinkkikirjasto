
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
    
<<<<<<< HEAD
    String[] getTags();
    
    boolean getUrlpresent();
    
    String getReference();
=======
    String getReference();
    
    boolean isUrlpresent();
>>>>>>> 9d2eae68e8c6ab435cf3210f8a5bb660e542aec5

    boolean edit(String element, String edit);
    
    boolean contains(String keyword);
}
