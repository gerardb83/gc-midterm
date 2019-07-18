import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryApp {

	static Scanner scan = new Scanner(System.in);
	public static DecimalFormat df = new DecimalFormat("00");

	public static void main(String[] args) throws Exception {
		Path path = Paths.get("items.txt");
		if (Files.notExists(path)) {
			Files.createFile(path);
		}
		int selection = 0;
		List<Item> itemsMaster = LibraryTextFile.readFile();
		System.out.println("\nWelcome to Grand Circus Bibliotheca\n");

		do {
			selection = displayMenu(itemsMaster);
		} while (selection != 7);
		System.out.println("Goodbye!");
	}

	private static int displayMenu(List<Item> itemsMaster) throws Exception {
		System.out.println(
				"1. Print Book List\n2. Print DVD List\n3. Search Books by Author \n4. Search Books by Title Keyword\n5. Pickup Hold\n6. Return Items\n7. Quit\n");
		int selection = Validator.getInt(scan, "Please make a selection (enter number): ", 1, 7);
		if (selection == 1) {
			displayBooks(getBooks(itemsMaster), itemsMaster);
		} else if (selection == 2) {
			displayDVDS(getDVDS(itemsMaster), itemsMaster);
		} else if (selection == 3) {
			searchAuthors(scan, itemsMaster);
		} else if (selection == 4) {
			searchTitles(scan, itemsMaster);
		} else if (selection == 5) {
			pickupHold(scan, itemsMaster);
		} else if (selection == 6) {
			returnItems(scan, itemsMaster);
		}
		return selection;
	}
	
	public static List<Item> getBooks(List<Item> itemsMaster) throws Exception {
		List<Item> books = new ArrayList<>();
		for (Item each : itemsMaster) {
			if (each.getType() == Type.BOOK) {
				books.add(each);
			}
		}
		return books;
	}

	public static void makeBanner(String author, String dueDate) {
		System.out.println("\nBibliotheca Catalog");
		System.out.println("============================================================================================================\n");
		System.out.printf("%-50s %-30s %-20s\n", "Title", author, dueDate);
		System.out.println("-----------------------------------------------------------------------------------------------------------");
	}
	
	public static void displayBooks(List<Item> books, List<Item> itemsMaster) throws Exception {
		makeBanner("Author", "Status");
		int i = 1;
		for (Item each : books) {
			System.out.printf("%-1s %-45s\n", df.format(i) + ". " + each, each.getStatus());
			i++;
		}
		getUserChoice(scan, books, itemsMaster);
	}

	public static List<Item> getDVDS(List<Item> itemsMaster) throws Exception {
		List<Item> dvds = new ArrayList<>();
		for (Item each : itemsMaster) {
			if (each.getType() == Type.DVD) {
				dvds.add(each);
			}
		}
		return dvds;
	}

	public static void displayDVDS(List<Item> dvds, List<Item> itemsMaster) throws Exception {
		makeBanner("Status", "");
		int i = 1;
		for (Item each : dvds) {
			System.out.printf("%-50s %-5s\n", df.format(i) + ". " + each.getTitle(), each.getStatus());
			i++;
		}
		getUserChoice(scan, dvds, itemsMaster);
	}

	public static void searchAuthors(Scanner scan, List<Item> itemsMaster) throws Exception {
		int j = 0;
		List<Item> search = new ArrayList<>();
		System.out.println("Please enter an author's name to search.");
		String userInput = scan.next();
		makeBanner("Author", "Status");
		for (int i = 0; i < itemsMaster.size(); i++) {
			Item item = itemsMaster.get(i);
			if (item instanceof Book) {
				String author = item.getAuthor();
				if (author.toLowerCase().contains(userInput.toLowerCase())) {
					search.add(itemsMaster.get(i));
					System.out.println((j + 1) + ". " + search.get(j) + "\t" + search.get(j).getStatus());
					j++;
				}
			}
		}
		getUserChoice(scan, search, itemsMaster);
	}

	public static void searchTitles(Scanner scan, List<Item> itemsMaster) throws Exception {
		int j = 0;
		List<Item> search = new ArrayList<>();
		System.out.println("Please enter a Title to search");
		String userInput = scan.next();
		makeBanner("Author", "Status");
		for (int i = 0; i < itemsMaster.size(); i++) {
			if (itemsMaster.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase())) {
				search.add(itemsMaster.get(i));
				System.out.println(df.format((j + 1)) + ". " + search.get(j) + search.get(j).getStatus());
				j++;
			}
		}
		getUserChoice(scan, search, itemsMaster);
	}

	public static void getUserChoice(Scanner scan, List<Item> typeItems, List<Item> itemsMaster) throws Exception {
		String[] values = { "C", "H", "M" };
		String answer = Validator.getStringMatching(scan,
				"\nEnter (C) to checkout or (H) to place hold or (M) to return to Main Menu?\n", values);
		if (answer.equalsIgnoreCase("C")) {
			checkout(scan, typeItems, itemsMaster);
		} else if (answer.equalsIgnoreCase("H")) {
			placeHold(scan, typeItems, itemsMaster);
		} else
			displayMenu(itemsMaster);
	}

	public static void checkout(Scanner scan, List<Item> typeItems, List<Item> masterList) throws Exception {
		int userInput = Validator.getInt(scan, "Please enter a number from the selection to checkout: \n", 1,
				typeItems.size());
		userInput -= 1;
		Item selectedItem = typeItems.get(userInput);
		if (selectedItem.getStatus().equals(Status.ONSHELF)) {
			selectedItem.setStatus(Status.CHECKEDOUT);
			selectedItem.setDueDate(selectedItem.generateDueDate(selectedItem.getCheckoutdays()));
			if (selectedItem.getType() == Type.BOOK) {
				System.out.println("\n" + selectedItem.getTitle() + " by " + selectedItem.getAuthor());
			} else {
				System.out.println("\n" + selectedItem.getTitle());
			}
			System.out.println(
					"Checkout successful! " + selectedItem.getTitle() + " will be due on " + selectedItem.getDueDate() + "\n");
			LibraryTextFile.writeFile(selectedItem, masterList);
		} else {
			System.out.println("Sorry, " + selectedItem.getTitle() + " is " + selectedItem.getStatus() + " until " + selectedItem.getDueDate() + "\n");
		}
	}

	public static void returnItems(Scanner scan, List<Item> itemsMaster) throws Exception {
		makeBanner("Author", "Due Date");
		List<Item> checkouts = new ArrayList<>();
		for (Item each : itemsMaster) {
			if (each.getStatus().equals(Status.CHECKEDOUT)) {
				checkouts.add(each);
			}
		}
		int i = 1;
		for (Item returnItem : checkouts) {
			System.out.println(df.format(i) + ". " + returnItem + " " + returnItem.getDueDate());
			i++;
		}
		int userInput = Validator.getInt(scan, "\n\nPlease enter a number from the selection to return: ", 1,
				checkouts.size());
		userInput -= 1;
		Item selectedItem = checkouts.get(userInput);
		selectedItem.setStatus(Status.ONSHELF);
		selectedItem.setDueDate(null);
		LibraryTextFile.writeFile(selectedItem, itemsMaster);
		if (selectedItem.getType() == Type.BOOK) {
			System.out
					.println(selectedItem.getTitle() + " by " + selectedItem.getAuthor() + " returned successfully!\n");
		} else {
			System.out.println(selectedItem.getTitle() + " returned successfully!\n");
		}
	}

	public static void placeHold(Scanner scan, List<Item> typeItems, List<Item> masterList) throws Exception {
		int userInput = Validator.getInt(scan, "Please enter a number from the selection to place hold: ", 1,
				typeItems.size());
		userInput -= 1;
		Item selectedItem = typeItems.get(userInput);
		if (selectedItem.getStatus().equals(Status.ONSHELF)) {
			selectedItem.setStatus(Status.HOLD);
			selectedItem.setDueDate(selectedItem.generateDueDate(selectedItem.getHolddays()));
			if (selectedItem.getType() == Type.BOOK) {
				System.out.println("Hold placed successfully! " + selectedItem.getTitle() + " by "
						+ selectedItem.getAuthor() + " will be held until " + selectedItem.getDueDate() + "\n");
			} else {
				System.out.println("Hold placed successfully! " + selectedItem.getTitle() + " will be held until "
						+ selectedItem.getDueDate() + "\n");
			}
			LibraryTextFile.writeFile(selectedItem, masterList);
		} else if (selectedItem.getStatus().equals(Status.CHECKEDOUT)) {
			System.out.println("Sorry, " + selectedItem.getTitle()
					+ " is already checked out. But it will be available after " + selectedItem.getDueDate());
		}
	}

	public static void pickupHold(Scanner scan, List<Item> itemsMaster) throws Exception {
		makeBanner("Author", "Will be Held Until");
		List<Item> holds = new ArrayList<>();
		for (Item each : itemsMaster) {
			if (each.getStatus().equals(Status.HOLD)) {
				holds.add(each);
			}
		}
		int i = 1;
		for (Item holdItem : holds) {
			System.out.println(i + ". " + holdItem + " " + holdItem.getDueDate());
			i++;
		}
		int userInput = Validator.getInt(scan,
				"\n\nPlease enter a number from the selection of Holds waiting for pickup: ", 1, holds.size());
		userInput -= 1;
		Item selectedItem = holds.get(userInput);
		selectedItem.setStatus(Status.CHECKEDOUT);
		selectedItem.setDueDate(selectedItem.generateDueDate(selectedItem.getCheckoutdays()));
		LibraryTextFile.writeFile(selectedItem, itemsMaster);
		if (selectedItem.getType() == Type.BOOK) {
			System.out.println("Hold checked out successfully! " + 
					selectedItem.getTitle() + " by " + selectedItem.getAuthor() + " will be due on " + selectedItem.getDueDate() + "\n");
		} else {
			System.out.println("Hold checked out successfully! " + selectedItem.getTitle() + " will be due on " + selectedItem.getDueDate() + "\n");
		}
	}
}