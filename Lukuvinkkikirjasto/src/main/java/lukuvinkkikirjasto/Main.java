package lukuvinkkikirjasto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lukuvinkkikirjasto.domain.Book;
import lukuvinkkikirjasto.domain.Tip;

public class Main {

    private static List<Tip> tips;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        tips = new ArrayList<>();

        while (true) {
            System.out.println("");
            System.out.println("To add a new book, type 'add' and press Enter. To exit, type 'quit' and press Enter.");
            String command = scanner.nextLine();
            if (command.equals("quit")) {
                break;
            }
            if (command.equals("add")) {
                System.out.println("Give the name of the book: ");
                String name = scanner.nextLine();
                System.out.println("And the name of the author: ");
                String author = scanner.nextLine();
                Book book = new Book(name, author);
                tips.add(book);
                System.out.println("\nKirja lisätty!\n" + book);
            }
        }
    }
}
