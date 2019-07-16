import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryTextFile {

	private static FileLinesHelper linesHelper = new FileLinesHelper("items.txt");

	private static Item convertLineToItem(String line) {

		String[] parts = line.split(",");
		String tempType = parts[3].trim();
		if (tempType.equals("BOOK")) {
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
		} else if (tempType.equals("DVD")){
			DVD dvds = new DVD();
			dvds.setTitle(parts[0]);
			dvds.setAuthor(null);
			String tempStatus = parts[2].trim();
			if (tempStatus.equals("ONSHELF")) {
				dvds.setStatus(Status.ONSHELF);
			} else if (tempStatus.equals("CHECKEDOUT")) {
				dvds.setStatus(Status.CHECKEDOUT);
			} else if (tempStatus.equals("HOLD")) {
				dvds.setStatus(Status.HOLD);
			}
		return dvds;
		}
		return null;
		}

	private static String convertItemToLine(Item items) {

		return String.format("%s,%s,%s,%s,%s", items.getTitle(), items.getAuthor(), items.getStatus(), items.getDueDate(), items.getType());
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
	public static void appendToFile(List<DVD> item) throws IOException {
		for (DVD each : item) {
		String line = convertItemToLine(each);
		linesHelper.appendToFile(line);
		}
	}
}