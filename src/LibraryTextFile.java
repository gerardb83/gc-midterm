import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryTextFile {

	private static FileLinesHelper linesHelper = new FileLinesHelper("items.txt");

	private static Book convertLineToItem(String line) {

		String[] parts = line.split(",");
		Book books = new Book();
		books.setTitle(parts[0]);
		books.setAuthor(parts[1].trim());
		String tempStatus = parts[2].trim();
		if (tempStatus.equals("ONSHELF")) {
			books.setStatus(Status.ONSHELF);
		} else if (tempStatus.equals("CHECKEDOUT")) {
			books.setStatus(Status.CHECKEDOUT);
		} else if (tempStatus.equals("HOLD")) {
			books.setStatus(Status.HOLD);
		}
		return books;
	}

	private static String convertItemToLine(Item items) {

		return String.format("%s,%s,%s,%s", items.getTitle(), items.getAuthor(), items.getStatus(), items.getDueDate());
	}

	public static List<Item> readFile() {

		List<String> lines = linesHelper.readFile();
		List<Item> items = new ArrayList<>(lines.size());
		for (String line : lines) {
			items.add(convertLineToItem(line));
		}
		return items;
	}

	public static void rewriteFile(List<Item> items) throws IOException {

		List<String> lines = new ArrayList<>(items.size());
		for (Item item : items) {
			lines.add(convertItemToLine(item));
		}
		linesHelper.rewriteFile(lines);
	}
//	public static void appendToFile(Book item) throws IOException {
//		String line = convertItemToLine(item);
//		linesHelper.appendToFile(line);
//	}
}