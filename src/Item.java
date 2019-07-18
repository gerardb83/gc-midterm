import java.util.Calendar;
import java.util.Date;

public abstract class Item {

	private Status status = Status.ONSHELF;
	private String title;
	private String author;
	private Type type;
	private Date dueDate;
	private int checkoutdays;
	private int holddays;
	public abstract String toLine();
	public abstract Type getItemType();

	public Item() {
		this.type = getItemType();
	}

	public int getCheckoutdays() {
		return checkoutdays;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getHolddays() {
		return holddays;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date generateDueDate(int numcheckoutdays) {
		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, numcheckoutdays);
		dt = c.getTime();
		return dt;
	}
}