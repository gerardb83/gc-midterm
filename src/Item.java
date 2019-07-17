import java.util.Calendar;
import java.util.Date;

public abstract class Item {
	
	private Status status = Status.ONSHELF;
	private String title;
	private Type type;
	private Date dueDate;
	public abstract String toLine();
	public abstract Type getItemType();
	public abstract int getNumberOfCheckOutDays();

	public Item() {
		this.type = getItemType();
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

	public Date generateDueDate() {

		Date dt = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(dt);
		c.add(Calendar.DATE, getNumberOfCheckOutDays());
		dt = c.getTime();
		return dt;
	}
}