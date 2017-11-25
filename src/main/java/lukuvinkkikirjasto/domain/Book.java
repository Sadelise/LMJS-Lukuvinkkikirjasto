package lukuvinkkikirjasto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book implements Tip {

    private String title;
    private String author;
    private String description;
    private String ISBN;
    private boolean read;

    public Book(String title, String author) {
        this(title, author, "", "", false);
    }

    public boolean markRead(){
        if(this.read)
            return false;
        this.read=true;
        return true;
    }

    @Override
    public String toString() {
        String r;
        if(read) r = "Read";
        else r = "Not read";
        return "Author: " + this.author + "\nTitle: " + this.title + "\nDescription: " + this.description + "\nISBN: " + this.ISBN + "\n"+r;
    }
    
    @Override
    public String identify() {
        return title;
    }
}
