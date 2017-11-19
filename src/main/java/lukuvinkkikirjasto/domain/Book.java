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

    public Book(String title, String author) {
        this(title, author, "", "");
    }

    @Override
    public String toString() {
        return "Author: " + this.author + "\nTitle: " + this.title + "\nDescription: " + this.description + "\nISBN: " + this.ISBN;
    }
}
