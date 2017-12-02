
package lukuvinkkikirjasto.domain;

public interface Tip {
    boolean markRead();
    
    boolean markNotRead();
    
    boolean isRead();
    
    String identify();

    boolean edit(String element, String edit);
    
    boolean contains(String attribute);
}
