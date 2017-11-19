package lukuvinkkikirjasto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lukuvinkkikirjasto.domain.Book;
import lukuvinkkikirjasto.domain.Tip;
import lukuvinkkikirjasto.io.ConsoleIO;
import lukuvinkkikirjasto.io.IO;

public class Main {

    private static List<Tip> tips;
    private IO io;

    public static void main(String[] args) {
        run(new ConsoleIO());
    }

    public static void run(IO scanner) {
        tips = new ArrayList<>();

        while (true) {
            System.out.println("");
            String command = scanner.readLine("To add a new book, type 'add' and press Enter. To exit, type 'quit' and press Enter.");
            if (command.equals("quit")) {
                break;
            }
            if (command.equals("add")) {
                String name = scanner.readLine("Give the name of the book: ");
                String author = scanner.readLine("And the name of the author: ");
                Tip book = new Book(name, author);
                tips.add(book);
                System.out.println();
                scanner.print("Kirja lisatty!");
                scanner.print(book.toString());
            }
        }
    }
}
