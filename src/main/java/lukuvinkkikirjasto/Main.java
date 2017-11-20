package lukuvinkkikirjasto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lukuvinkkikirjasto.dao.BasicTipDao;
import lukuvinkkikirjasto.domain.Book;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.io.ConsoleIO;
import lukuvinkkikirjasto.io.IO;
import lukuvinkkikirjasto.dao.TipDao;

public class Main {

    private TipDao tipDao;
    private IO io;

    public static void main(String[] args) {
        run(new ConsoleIO(), new BasicTipDao());
    }

    public static void run(IO scanner, TipDao tipDao) {
        while (true) {
            System.out.println("");
            String command = scanner.readLine("To add a new book, type 'add' and press Enter. To view all tips, type 'view' and press Enter. To exit, type 'quit' and press Enter.");
            if (command.equals("quit")) {
                break;
            }
            if (command.equals("add")) {
                String name = scanner.readLine("Give the name of the book: ");
                String author = scanner.readLine("And the name of the author: ");
                Tip book = new Book(name, author);
                tipDao.addTip(book);
                System.out.println();
                scanner.print("Kirja lisatty!");
                scanner.print(book.toString());
            }
            if (command.equals("view")) {
                scanner.print(tipDao.toString());
            }
        }
    }
}
