import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BooksApp {

	static Scanner scan = new Scanner(System.in);
	public static DecimalFormat df = new DecimalFormat("00");
	
	public static void main(String[] args) throws IOException {

		Path path = Paths.get("items.txt");
	    if (Files.notExists(path)) {
	        Files.createFile(path);
	    }
	        
		int selection = 0;
		List<Book> books = LibraryTextFile.readFile();
	
//		List<Book> books = new ArrayList<>();
//		books.add(new Book("The Alchemist", "Paulo Coelho"));
//		books.add(new Book("Three Musketeers", "Alexandre Dumas"));
//		books.add(new Book("On the Road", "Jack Keuroac"));
//		System.out.println("Welcome to the Grand Circus Bibliotheca!");
		displayMenu();
		do {
			selection = Validator.getInt(scan, "Please make a selection (enter number): ", 1, 4);
			if (selection == 1) {
				displayBooks(books);
			} else if (selection == 2) {
				searchAuthors(scan, books);
			} else if (selection == 3) {
				searchTitles(scan, books);
			}
		} while (selection != 4);
		System.out.println("Goodbye!");
	}

	private static void displayMenu() {

		System.out.println("1. Print List\n2. Search Books by Author \n3. Search Books by Title Keyword\n4. Quit");
	}

	private static Date calcDueDate() {

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 14);
		dt = c.getTime();
		return dt;
	}

	public static void displayBooks(List<Book> books) {
		System.out.println("\nBibliotheca Catalog");
		System.out.println("================================================================\n");
		System.out.printf("%-50s %-50s\n", "Title", "Author");
		System.out.println("------------------------------------------------------------------");
		int i = 1;
        for (Book b : books) {
            System.out.printf("%-1s %-45s %-45s\n", df.format(i) + ".", b.getTitle(), b.getAuthor());
            i++;
        }
		System.out.println();
		bookCheckout(scan, books);
	}

	public static void searchAuthors(Scanner scan, List<Book> books) {

		System.out.println("Please enter an author's name to search.");
		String userInput = scan.next();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
//			if (userInput.equalsIgnoreCase(books.get(i).getAuthor())) {
				System.out.printf("%-15s %-15s\n", (i + 1) + ". " + books.get(i).getTitle() + " by ",
						books.get(i).getAuthor());
			}
		}
	}

	public static void searchTitles(Scanner scan, List<Book> books) {

		System.out.println("Please enter a Title to search");
		String userInput = scan.nextLine();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase())) {
//			if (userInput.equalsIgnoreCase(books.get(i).getTitle())) {
				System.out.printf("%-15s %-15s\n", (i + 1) + ". " + books.get(i).getTitle() + " by ",
						books.get(i).getAuthor());
			}
		}
	}

	public static void bookCheckout(Scanner scan, List<Book> books) {

		String[] values = { "y", "n" };
		String answer = Validator.getStringMatching(scan, "Would you like to checckout? (y/n)", values);
		if (answer.equalsIgnoreCase("Y")) {
			int userInput = Validator.getInt(scan, "Please enter a number from the selection to checkout.", 1,
					books.size());
			userInput -= 1;
//			System.out.println(books.get(userInput).getStatus());
			Book selectedBook = books.get(userInput);
			if (selectedBook.getStatus().equals(Status.ONSHELF)) {
				selectedBook.setStatus(Status.CHECKEDOUT);
//				System.out.println(books.get(userInput).getStatus());
				selectedBook.setDueDate(calcDueDate());
				System.out.println(selectedBook.getTitle());
				System.out.println("Checkout successful! " + selectedBook.getDueDate());
				displayMenu();
			} else {
				System.out.println("Sorry, " + selectedBook.getTitle() + " is " + selectedBook.getStatus());
			}
		} else {
			displayMenu();
		}
	}

	public static void displayBanner() {

	}
}
