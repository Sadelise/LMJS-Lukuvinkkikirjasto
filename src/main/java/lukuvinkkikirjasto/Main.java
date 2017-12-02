package lukuvinkkikirjasto;

import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.dao.FireBaseTipDao;
import lukuvinkkikirjasto.domain.Book;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.io.ConsoleIO;
import lukuvinkkikirjasto.io.IO;
import lukuvinkkikirjasto.dao.TipDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    private TipDao tipDao;
    private IO io;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        //run(new ConsoleIO(), new FireBaseTipDao());
/*        String[] s = new String[]{"first", "second", "third"};
        Book b = new Book("book", "auth", "descr", s, "123");
        System.out.println(b+"\n");
        String[] s2 = new String[6];
        b.addTags(s2);
        System.out.println(b+"\n");
        for(int i = 0; i<s.length; i++)
            s2[i] = s[i];
        s2[3] = "fourth";
        s2[4] = "fifth";
        s2[5] = "sixth";
        b.addTags(s2);
        System.out.println(b);*/
    }

    public static void run(IO scanner, TipDao tipDao) {
        while (true) {
            System.out.println("");
            String command = scanner.readLine("Type a command and press Enter "
                    + "(to see the list of all commands, type 'commands' and press Enter)");
            if (command.equals("quit")) {
                break;
            }
            if (command.equals("add")) {
                String name = scanner.readLine("Give the name of the book: ");
                String author = scanner.readLine("And the name of the author: ");
                Tip book = new Book(name, author);
                tipDao.addTip(book);
                System.out.println();
                scanner.print("Book added!");
                scanner.print(book.toString());
            }
            if (command.equals("view")) {
                scanner.print(tipDao.toString());

                if (tipDao.getAllTips().size() > 0) {
                    String s = scanner.readLine("Do you want to mark a book as read? y/n");
                    if (s.equals("y")) {
                        int i = Integer.parseInt(scanner.readLine("Enter book number")) - 1;
                        Tip tip = tipDao.getTipByNumber(i);
                        if (tip != null) {
                            tipDao.markTip(tip.identify());
                        }
                        scanner.print("Book now marked as read");
                        scanner.print(tipDao.getAllTips().get(i).toString());
                    }
                }
            }
            if (command.equals("remove")) {
                if (tipDao.getAllTips().size() > 0) {
                    int i = Integer.parseInt(scanner.readLine("Enter book number")) - 1;

                    Tip tip = tipDao.getTipByNumber(i);
                    if (tip != null) {
                        scanner.print(tipDao.getAllTips().get(i).toString());
                        String s = scanner.readLine("Are you sure you want to delete this book? y/n");
                        if (s.equals("y")) {
                            tipDao.removeTip(tip.identify());
                            scanner.print("The book was removed");
                        }
                    }
                } else {
                    scanner.print("No books to remove");
                }
            }
            if (command.equals("commands")) {
                scanner.print("To add a new book, type 'add' and press Enter.\n"
                        + "To view all tips, type 'view' and press Enter.\n"
                        + "To remove a book, type 'remove' and press Enter.\n"
                        + "To exit, type 'quit' and press Enter.");
            }
            if (command.equals("edit")) {
                String[] responses = {"author", "title", "description", "isbn"};
                int id = Integer.parseInt(scanner.readLine("Enter book number:")) - 1;

                String element;
                while (true) {
                    element = scanner.readLine("What would you like to change?").toLowerCase();

                    if (Arrays.asList(responses).contains(element))
                        break;
                    scanner.print("Accepted responses: author, title, description, isbn");
                }
                String edit = scanner.readLine("What do you want to change " + element.toUpperCase() + " to?");
                tipDao.editTip(id, element, edit);
                scanner.print("Changes saved");
            }
            if (command.equals("tags")){
                String title = scanner.readLine("Give title");
                String tags = scanner.readLine("Give tags");
                tipDao.editTipByTitle(title, "tags",tags);
            }
        }
    }
}
