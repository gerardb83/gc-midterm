import java.util.Date;

public class Book extends Item{

	private String title;
	private String author;
	private Status status = Status.ONSHELF;
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
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Status getStatus() {
		super.getStatus();
		return status;
	}

	public void setStatus(Status status) {
		super.setStatus(status);
		this.status = status;
	}

	public Date getDueDate() {
		super.getDueDate();
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		super.setDueDate(dueDate);
		this.dueDate = dueDate;
	}

	public Book() {

		super();
	}

	public Book(String title, String author) {

		super();
		this.title = title;
		this.author = author;
	}


	@Override
	public String toString() {

		return "Books [title=" + title + ", author=" + author + ", status=" + status + ", dueDate=" + dueDate + "]";
	}
}
