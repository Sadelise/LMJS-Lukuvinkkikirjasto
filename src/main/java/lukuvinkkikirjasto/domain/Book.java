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
    private String url;
    private String ISBN;
    private String tagString;
    //private String[] tags;
    private String type;
    private boolean read;
    //private boolean urlpresent;

    public Book(TipData tipData) {
        this.title = tipData.getTitle();
        this.author = tipData.getCreator();
        this.description = tipData.getDescription();
        this.tagString = tipData.getTagString();
        this.url = tipData.getUrl();
        this.type = tipData.getType();
        this.read = tipData.isRead();
    }

    public Book(String title, String author) {
        this(title, author, "", "", "", "", "Book", false);
    }

    public Book(String title, String author, String description, String tag, String ISBN) {
        this(title, author, description, "", ISBN, tag, "Book", false);
    }

    public Book(String title, String author, String description, boolean read, String type, String ISBN) {
        this(title, author, description, "", ISBN, "", "Book", false);
    }

    @Override
    public boolean markRead() {
        if (!this.read) {
            this.read = true;
            return true;
        }
        return false;
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
        if (read) {
            r = "Read";
        } else {
            r = "Not read";
        }

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

    @Override
    public boolean contains(String keyword) {
        if (this.tagString != null) {
            if (this.tagString.toLowerCase().contains(keyword)) {
                return true;
            }
        }
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

    private void addTags(String tags) {
        this.tagString = tags;
        /*if (tags != null) {
            this.tags = tags.split(";(\\s)*");
        } else {
            this.tags = new String[0];
        }*/
    }

    public String[] getTags() {
        if (tagString != null && tagString.length() > 0) {
            return tagString.split(";(\\s)*");
        } else {
            return new String[0];
        }
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String getUrl() {
        return url;
    }

    @Override
    public boolean getUrlpresent() {
        if (url == null || url.length() == 0) {
            return false;
        }
        return true;
    }

}
