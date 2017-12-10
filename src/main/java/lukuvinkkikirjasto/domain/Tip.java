
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
    
    String[] getTags();
    
    boolean getUrlpresent();

    int getPriority();

    void setPriority(int i);

    boolean edit(String element, String edit);
    
    boolean contains(String keyword);
}
