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
	
		do {
			selection = displayMenu(itemsMaster);
		} while (selection != 6);
		System.out.println("Goodbye!");
	}

	private static int displayMenu(List<Item> itemsMaster) throws Exception {
		System.out.println(
				"1. Print Book List\n2. Print DVD List\n3. Search Books by Author \n4. Search Books by Title Keyword\n5. Return Items\n6. Quit");
		int selection = Validator.getInt(scan, "Please make a selection (enter number): ", 1, 6);
		if (selection == 1) {
			displayBooks(getBooks(itemsMaster), itemsMaster);
		} else if (selection == 2) {
			displayDVDS(getDVDS(itemsMaster), itemsMaster);
		} else if (selection == 3) {
			searchAuthors(scan, itemsMaster);
		} else if (selection == 4) {
			searchTitles(scan, itemsMaster);
		} else if (selection == 5) {
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
	
	public static void displayBooks(List<Item> books, List<Item> itemsMaster) throws Exception {
		System.out.println("\nBibliotheca Catalog");
		System.out.println("================================================================\n");
		System.out.printf("%-50s %-50s\n", "Title", "Author");
		System.out.println("------------------------------------------------------------------");
		int i = 1;
		for (Item b : books) {
				System.out.printf("%-1s %-45s\n", df.format(i) + ".", b);
				i++;		
		}
		checkout(scan, books, itemsMaster);
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
		System.out.printf("%-50s\n", "Title");
		System.out.println("------------------------------------------------------------------");
		int i = 1;
		for (Item b : dvds) {
			System.out.printf("%-1s %-45s\n", df.format(i) + ".", b.getTitle());
			i++;
		}
		checkout(scan, dvds, itemsMaster);
	}
	
	public static void searchAuthors(Scanner scan, List<Item> itemsMaster) throws Exception {
		int j = 0;
		List<Item> search = new ArrayList<>();
		System.out.println("Please enter an author's name to search.");
		String userInput = scan.next();
		for(int i = 0; i < itemsMaster.size(); i++) {
			Item item = itemsMaster.get(i);
			if (item instanceof Book) {
			String author = ((Book) item).getAuthor(); 
			if(author.toLowerCase().contains(userInput.toLowerCase())) {
				search.add(itemsMaster.get(i));
				System.out.printf("%-15s %-15s\n", (j + 1) + ". ", search.get(j).getTitle());
							j++;
						}
			}
		}
		checkout(scan, search, itemsMaster);
	}

	public static void searchTitles(Scanner scan, List<Item> itemsMaster) throws Exception {
		int j = 0;
		List<Item> search = new ArrayList<>();
		System.out.println("Please enter a Title to search");
		String userInput = scan.next();
		for(int i = 0; i < itemsMaster.size(); i++) {
				if (itemsMaster.get(i).getTitle().toLowerCase().contains(userInput.toLowerCase())) {
					search.add(itemsMaster.get(i));
					System.out.printf("%-15s %-15s\n", (j + 1) + ". ", search.get(j));
					j++;
				}
		}
		checkout(scan, search, itemsMaster);
	}
	
	public static void checkout(Scanner scan, List<Item> typeItems, List<Item> masterList) throws Exception {
		String[] values = { "y", "n" };
		String answer = Validator.getStringMatching(scan, "Would you like to checkout? (y/n)", values);
		if (answer.equalsIgnoreCase("Y")) {
			int userInput = Validator.getInt(scan, "Please enter a number from the selection to checkout.", 1,
					typeItems.size());
			userInput -= 1;
			Item selectedItem = typeItems.get(userInput);
			if (selectedItem.getStatus().equals(Status.ONSHELF)) {
				selectedItem.setStatus(Status.CHECKEDOUT);
				selectedItem.setDueDate(selectedItem.generateDueDate());
				System.out.println(selectedItem.getTitle());
				System.out.println("Checkout successful! " + selectedItem.getDueDate());
				LibraryTextFile.writeFile(selectedItem, masterList);
			} else {
				System.out.println("Sorry, " + selectedItem.getTitle() + " is " + selectedItem.getStatus());
			}
		} 
	}
	
	public static void returnItems(Scanner scan, List<Item> itemsMaster) throws Exception {
		List<Item> checkouts = new ArrayList<>();
		for (Item each : itemsMaster) {
			if(each.getStatus().equals(Status.CHECKEDOUT)) {
				checkouts.add(each);
			}
		}
			int i = 1;
			for(Item returnItem : checkouts) {
			System.out.println(i + ". " + returnItem);
			i++;
			}
			int userInput = Validator.getInt(scan, "\n\nPlease enter a number from the selection to checkout.", 1,
					checkouts.size());
			userInput -= 1;
			Item selectedItem = checkouts.get(userInput);
			selectedItem.setStatus(Status.ONSHELF);
			selectedItem.setDueDate(null);
			LibraryTextFile.writeFile(selectedItem, itemsMaster);
			System.out.println(selectedItem.getTitle() + " returned successfully!\n");
	}
	
	public static void displayBanner() {
	}
}
