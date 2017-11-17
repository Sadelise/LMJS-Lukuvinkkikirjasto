
import java.util.Scanner;

/**
 *
 * @author
 */
public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

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
                addNewBook(name, author);
            }
        }
    }

    public static void addNewBook(String name, String author) {

        System.out.println("Kirja lisätty! (ei oikeesti)");
    }
}
