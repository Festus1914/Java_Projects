import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibrarySystem {
    private static Map<String, Book> library = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            displayMenu();
            int choice = getIntInput(scanner, "Enter your choice: ");

            switch (choice) {
                case 1:
                    addBook(scanner);
                    break;
                case 2:
                    borrowBook(scanner);
                    break;
                case 3:
                    returnBook(scanner);
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting the library system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("\nLibrary System Menu:");
        System.out.println("1. Add Books");
        System.out.println("2. Borrow Books");
        System.out.println("3. Return Books");
        System.out.println("4. Exit");
    }

    private static void addBook(Scanner scanner) {
        System.out.println("\nAdd Books:");
        String title = getStringInput(scanner, "Enter book title: ");
        String author = getStringInput(scanner, "Enter book author: ");
        int quantity = getIntInput(scanner, "Enter book quantity: ");

        if (library.containsKey(title)) {
            Book book = library.get(title);
            book.setQuantity(book.getQuantity() + quantity);
            System.out.println("Book quantity updated.");
        } else {
            Book book = new Book(title, author, quantity);
            library.put(title, book);
            System.out.println("Book added to the library.");
        }
    }

    private static void borrowBook(Scanner scanner) {
        System.out.println("\nBorrow Books:");
        String title = getStringInput(scanner, "Enter book title: ");
        int quantity = getIntInput(scanner, "Enter number of books to borrow: ");

        if (library.containsKey(title)) {
            Book book = library.get(title);
            if (book.getQuantity() >= quantity) {
                book.setQuantity(book.getQuantity() - quantity);
                System.out.println("You have borrowed " + quantity + " books.");
            } else {
                System.out.println("Sorry, the requested number of books is not available.");
            }
        } else {
            System.out.println("The book you requested is not available in the library.");
        }
    }

    private static void returnBook(Scanner scanner) {
        System.out.println("\nReturn Books:");
        String title = getStringInput(scanner, "Enter book title: ");
        int quantity = getIntInput(scanner, "Enter number of books to return: ");

        if (library.containsKey(title)) {
            Book book = library.get(title);
            book.setQuantity(book.getQuantity() + quantity);
            System.out.println("You have returned " + quantity + " books.");
        } else {
            System.out.println("The book you are trying to return is not in the library.");
        }
    }

    private static String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    private static class Book {
        private String title;
        private String author;
        private int quantity;

        public Book(String title, String author, int quantity) {
            this.title = title;
            this.author = author;
            this.quantity = quantity;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}