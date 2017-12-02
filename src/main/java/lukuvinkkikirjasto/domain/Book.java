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

    public Book(String title, String author, String description, String ISBN) {
        this(title, author, description, ISBN, false);
    }

    @Override
    public boolean markRead() {
        if (this.read) {
            return false;
        }
        this.read = true;
        return true;
    }

    @Override
    public boolean markNotRead() {
        if (!this.read) {
            return false;
        }
        this.read = false;
        return true;
    }

    @Override
    public boolean edit(String element, String edit) {
        switch (element) {
            case "title":
                changeTitle(edit);
                break;
            case "author":
                changeAuthor(edit);
                break;
            case "description":
                changeDescription(edit);
                break;
            case "isbn":
                changeISBN(edit);
                break;
            default:
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String r;
        if (read) {
            r = "Read";
        } else {
            r = "Not read";
        }
        return "Author: " + this.author + "\nTitle: " + this.title + "\nDescription: " + this.description + "\nISBN: " + this.ISBN + "\n" + r;
    }

    @Override
    public String identify() {
        return title;
    }

    private void changeTitle(String title) {
        this.title = title;
    }

    private void changeAuthor(String author) {
        this.author = author;
    }

    private void changeDescription(String description) {
        this.description = description;
    }

    private void changeISBN(String isbn) {
        this.ISBN = isbn;
    }

    @Override
    public boolean contains(String keyword) {
        if (this.ISBN != null) {
            if (this.ISBN.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        if (this.author != null) {
            if (this.author.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        if (this.description != null) {
            if (this.description.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        if (this.title != null) {
            if (this.title.toLowerCase().contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
