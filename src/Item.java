import java.util.Date;

public abstract class Item {

	private Status status = Status.ONSHELF;

	private String title;

	private String author;

	private Type type;

	private Date dueDate;

	public abstract Date generateDueDate();

	public Item() {

	}

	public String getAuthor() {

		return author;
	}

	public void setAuthor(String author) {

		this.author = author;
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

	public void setType(Type type) {

		this.type = type;
	}

	public Date getDueDate() {

		return dueDate;
	}

	public void setDueDate(Date dueDate) {

		this.dueDate = dueDate;
	}
}
