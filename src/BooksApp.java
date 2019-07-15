import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BooksApp {

	static Scanner scan = new Scanner(System.in);

	public static void main(String[] args) {

		int selection = 0;
		List<Books> book = new ArrayList<>();
		book.add(new Books("The Alchemist", "Paulo Coelho"));
		book.add(new Books("Three Musketeers", "Alexandre Dumas"));
		book.add(new Books("On the Road", "Jack Keuroac"));
		System.out.println("Welcome to the Grand Circus Bibliotheca!");
		displayMenu();
		do {
			selection = Validator.getInt(scan, "Please make a selection (enter number): ", 1, 4);
			if (selection == 1) {
				displayBooks(book);
			} else if (selection == 2) {
				searchAuthors(scan, book);
			} else if (selection == 3) {
				searchTitles(scan, book);
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

	public static void displayBooks(List<Books> books) {

		System.out.println("\nBibliotheca Catalog");
		System.out.println("===================================================\n");
		for (int i = 0; i < books.size(); i++) {
			System.out.printf("%-15s %-15s\n", (i + 1) + ". " + books.get(i).getTitle() + " by ",
					books.get(i).getAuthor());
		}
		System.out.println();
		bookCheckout(scan, books);
	}

	public static void searchAuthors(Scanner scan, List<Books> books) {

		String userInput = Validator.getStringMatching(scan, "Please enter the author's name.", books);
		for (int i = 0; i < books.size(); i++) {
			if (userInput.equalsIgnoreCase(books.get(i).getAuthor())) {
				System.out.printf("%-15s %-15s\n", (i + 1) + ". " + books.get(i).getTitle() + " by ",
						books.get(i).getAuthor());
			}
		}
	}

	public static void searchTitles(Scanner scan, List<Books> books) {

		String userInput = Validator.getStringMatchingTitle(scan, "Please enter the Title", books);
		for (int i = 0; i < books.size(); i++) {
			if (userInput.equalsIgnoreCase(books.get(i).getTitle())) {
				System.out.printf("%-15s %-15s\n", (i + 1) + ". " + books.get(i).getTitle() + " by ",
						books.get(i).getAuthor());
			}
		}
	}

	public static void bookCheckout(Scanner scan, List<Books> books) {

		String[] values = { "y", "n" };
		String answer = Validator.getStringMatching(scan, "Would you like to checckout? (y/n)", values);
		if (answer.equalsIgnoreCase("Y")) {
			int userInput = Validator.getInt(scan, "Please enter a number from the selection to checkout.", 1,
					books.size());
			userInput -= 1;
//			System.out.println(books.get(userInput).getStatus());
			if (books.get(userInput).getStatus().equals(Status.ONSHELF)) {
				books.get(userInput).setStatus(Status.CHECKEDOUT);
//				System.out.println(books.get(userInput).getStatus());
				books.get(userInput).setDueDate(calcDueDate());
				System.out.println("Checkout successful! " + books.get(userInput).getDueDate());
				displayMenu();
			} else {
				System.out.println(
						"Sorry, " + books.get(userInput).getTitle() + " is " + books.get(userInput).getStatus());
			}
		} else {
			displayMenu();
		}
	}
}
