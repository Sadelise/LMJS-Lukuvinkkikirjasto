package lukuvinkkikirjasto.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book implements Tip {

    private String title;
    private String author;
    private String description;
    private String ISBN;
    private String tagString;
    private String[] tags;

    private boolean read;


    public Book(String title, String author) {
        this(title, author, "", "", "",new String[0], false);
    }

    public Book(String title, String author, String description, String tag, String ISBN) {
        this(title, author, description, ISBN, "", new String[0], false);
        addTags(tag);
    }

    @Override
    public boolean markRead() {
        if (this.read)
            return false;
        this.read = true;
        return true;
    }

    @Override
    public boolean markNotRead() {
        if (!this.read)
            return false;
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
            case "tags":
                addTags(edit);
                break;
            default:
                return false;
        }
        return true;
    }


    @Override
    public String toString() {
        String r;
        if (read) r = "Read";
        else r = "Not read";

        return "Author: " + this.author + "\nTitle: " + this.title + "\nDescription: " + this.description + "\nISBN: " + this.ISBN + "\nTags: " + tagString + "\n" + r;
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

    private void addTags(String tags) {
        this.tagString = tags;
        if(tags!=null)
            this.tags = tags.split(";(\\s)*");
        else this.tags = new String[0];
    }

}
