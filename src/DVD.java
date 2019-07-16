import java.util.Calendar;
import java.util.Date;

public class DVD extends Item {

	private String title;

	private String author;

	private Date dueDate;

	public String getTitle() {

		super.getTitle();
		return title;
	}

	public void setTitle(String title) {

		super.setTitle(title);
		this.title = title;
	}

	public String getAuthor() {

		super.getAuthor();
		return author;
	}

	public void setAuthor(String author) {

		super.setAuthor(author);
		this.author = author;
	}

	public Date getDueDate() {

		return dueDate;
	}

	public void setDueDate(Date dueDate) {

		this.dueDate = dueDate;
	}

	@Override
	public String toString() {

		return "DVD [title=" + title + ", author=" + author + ", dueDate=" + dueDate + "]";
	}

	@Override
	public Date generateDueDate() {

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, 3);
		dt = c.getTime();
		return dt;
	}
}
