package lukuvinkkikirjasto.domain;

public class Book implements Tip {

    private String name;
    private String author;
    private String description;
    private String ISBN;

    public Book(String name, String author) {
        this(name, author, "", "");
    }

    public Book(String name, String author, String description, String ISBN) {
        this.name = name;
        this.author = author;
        this.description = description;
        this.ISBN = ISBN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @Override
    public String toString() {
        return "Author: " + this.author + "\nName: " + this.name + "\nDescription: " + this.description + "\nISBN: " + this.ISBN;
    }
}
