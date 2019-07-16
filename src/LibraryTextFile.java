
//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LibraryTextFile {
	private static FileLinesHelper linesHelper = new FileLinesHelper("items.txt");

	private static Book convertLineToItem(String line) {
		String[] parts = line.split(",");
		Book books  = new Book();
		books.setTitle(parts[0]);
		books.setAuthor(parts[1].trim());
		return books;
	}

//	private static String convertItemToLine(Book books) {
//		return String.format("%s,%s", books.getTitle(), books.getAuthor());
//	}

	public static List<Book> readFile() {
		List<String> lines = linesHelper.readFile();
		List<Book> items = new ArrayList<>(lines.size());
		for (String line : lines) {
			items.add(convertLineToItem(line));
		}
		return items;
	}

//	public static void rewriteFile(List<Book> items) throws IOException {
//		List<String> lines = new ArrayList<>(items.size());
//		for (Book item : items) {
//			lines.add(convertItemToLine(item));
//		}
//		linesHelper.rewriteFile(lines);
//	}

//	public static void appendToFile(Book item) throws IOException {
//		String line = convertItemToLine(item);
//		linesHelper.appendToFile(line);
//	}
}