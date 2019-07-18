import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;  

public class LibraryTextFile {
	private static FileLinesHelper linesHelper = new FileLinesHelper("items.txt");
	
	private static Item convertLineToItem(String line) throws Exception {
		String[] parts = line.split(",");
		String tempType = parts[4].trim();
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
			String tempDate = parts[3].trim();
			if(!tempDate.equals("null")) {
				SimpleDateFormat formDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy"); 
				Date newDate = formDate.parse(tempDate);
				books.setDueDate(newDate);
			}
			return books;
		} else if (tempType.equals("DVD")) {
			DVD dvds = new DVD();
			dvds.setTitle(parts[0]);
			String tempStatus = parts[2].trim();
			if (tempStatus.equals("ONSHELF")) {
				dvds.setStatus(Status.ONSHELF);
			} else if (tempStatus.equals("CHECKEDOUT")) {
				dvds.setStatus(Status.CHECKEDOUT);
			} else if (tempStatus.equals("HOLD")) {
				dvds.setStatus(Status.HOLD);
			}
			String tempDate = parts[3].trim();
			if(!tempDate.equals("null")) {
				SimpleDateFormat formDate = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy"); 
				Date newDate = formDate.parse(tempDate);
				dvds.setDueDate(newDate);
			}
			return dvds;
		}
		return null;
	}

	private static String convertItemToLine(Item items) {
		return items.toLine();

	}
	
	public static void writeFile(Item item, List<Item> itemsMaster) throws Exception{
		for (Item each : itemsMaster) {
			if(each.getTitle().equals(item.getTitle())){
				each.setStatus(item.getStatus());
				each.setDueDate(item.getDueDate());
			}
		} rewriteFile(itemsMaster);
	}

	public static List<Item> readFile() throws Exception {
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

	
//	public static void appendToFile(Item item) throws IOException {
//		String line = convertItemToLine(item);
//		linesHelper.appendToFile(line);
//	}

}