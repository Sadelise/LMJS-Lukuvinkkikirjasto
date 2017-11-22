package lukuvinkkikirjasto;

import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.domain.Book;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.io.ConsoleIO;
import lukuvinkkikirjasto.io.IO;
import lukuvinkkikirjasto.dao.TipDao;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

    private TipDao tipDao;
    private IO io;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);

        run(new ConsoleIO(), new BasicTipDao());
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
                        tipDao.markTip(i);
                        scanner.print("Book now marked as read");
                        scanner.print(tipDao.getAllTips().get(i).toString());
                    }
                }
            }
            if (command.equals("remove")) {
                if (tipDao.getAllTips().size() > 0) {
                    int i = Integer.parseInt(scanner.readLine("Enter book number")) - 1;
                    if (tipDao.testTipNumber(i)) {
                        scanner.print(tipDao.getAllTips().get(i).toString());
                        String s = scanner.readLine("Are you sure you want to delete this book? y/n");
                        if (s.equals("y")) {
                            tipDao.removeTip(i);
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
        }
    }
}
