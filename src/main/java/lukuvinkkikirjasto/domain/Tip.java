
package lukuvinkkikirjasto.domain;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public interface Tip {
    boolean markRead();

    boolean markNotRead();

    boolean isRead();

    String identify();
    
    String getType();
        
    String getDescription();
    
    String getTitle();
    
    String getUrl();
    
    boolean isUrlpresent();

    boolean edit(String element, String edit);
    
    boolean contains(String keyword);
}
