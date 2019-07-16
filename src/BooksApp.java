import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BooksApp {

	static Scanner scan = new Scanner(System.in);

	public static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {

		Path path = Paths.get("items.txt");
		if (Files.notExists(path)) {
			Files.createFile(path);
		}
		
		List<DVD> dvds = new ArrayList<>();
		
		dvds.add(new DVD ("It's a Wonderful Life (1946)"));
		dvds.add(new DVD ("Miracle on 34th Street (1947)"));
		dvds.add(new DVD ("Saving Private Ryan (1998)"));
		dvds.add(new DVD ("E.T. The Extra-Terrestrial (1982)"));
		dvds.add(new DVD ("The Bridge on the River Kwai (1957)"));


		LibraryTextFile.appendToFile(dvds);
		
		int selection = 0;
		List<Item> books = LibraryTextFile.readFile();
		LibraryTextFile.rewriteFile(books);
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

	public static void displayBooks(List<Item> books) throws Exception {

		System.out.println("\nBibliotheca Catalog");
		System.out.println("================================================================\n");
		System.out.printf("%-50s %-50s\n", "Title", "Author");
		System.out.println("------------------------------------------------------------------");
		int i = 1;
		for (Item b : books) {
			System.out.printf("%-1s %-45s %-45s\n", df.format(i) + ".", b.getTitle(), b.getAuthor());
			i++;
		}
		System.out.println();
		checkout(scan, books);
	}

	public static void searchAuthors(Scanner scan, List<Item> books) throws Exception {

		System.out.println("Please enter an author's name to search.");
		String userInput = scan.next();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getAuthor().toLowerCase().contains(userInput.toLowerCase())) {
//			if (userInput.equalsIgnoreCase(books.get(i).getAuthor())) {
				System.out.printf("%-15s %-15s\n", (i + 1) + ". " + books.get(i).getTitle() + " by ",
						books.get(i).getAuthor());
			}
		}
		checkout(scan, books);
	}

	public static void searchTitles(Scanner scan, List<Item> books) throws Exception {

		System.out.println("Please enter a Title to search");
		String userInput = scan.nextLine();
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase())) {
//			if (userInput.equalsIgnoreCase(books.get(i).getTitle())) {
				System.out.printf("%-15s %-15s\n", (i + 1) + ". " + books.get(i).getTitle() + " by ",
						books.get(i).getAuthor());
			}
		}
		checkout(scan, books);
	}

	public static void checkout(Scanner scan, List<Item> items) throws Exception {

		String[] values = { "y", "n" };
		String answer = Validator.getStringMatching(scan, "Would you like to checkout? (y/n)", values);
		if (answer.equalsIgnoreCase("Y")) {
			int userInput = Validator.getInt(scan, "Please enter a number from the selection to checkout.", 1,
					items.size());
			userInput -= 1;
//			System.out.println(books.get(userInput).getStatus());
			Item selectedItem = items.get(userInput);
			if (selectedItem.getStatus().equals(Status.ONSHELF)) {
				selectedItem.setStatus(Status.CHECKEDOUT);
//				System.out.println(books.get(userInput).getStatus());
				selectedItem.setDueDate(selectedItem.generateDueDate());
				System.out.println(selectedItem.getTitle());
				System.out.println("Checkout successful! " + selectedItem.getDueDate());
				LibraryTextFile.rewriteFile(items);
				displayMenu();
			} else {
				System.out.println("Sorry, " + selectedItem.getTitle() + " is " + selectedItem.getStatus());
			}
		} else {
			displayMenu();
		}
	}

	public static void displayBanner() {

	}
}
