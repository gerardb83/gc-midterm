import java.util.Date;

public class Books {

	private String title;

	private String author;

	private Status status = Status.ONSHELF;

	private Date dueDate;

	public String getTitle() {

		return title;
	}

	public void setTitle(String title) {

		this.title = title;
	}

	public String getAuthor() {

		return author;
	}

	public void setAuthor(String author) {

		this.author = author;
	}

	public Status getStatus() {

		return status;
	}

	public void setStatus(Status status) {

		this.status = status;
	}

	public Date getDueDate() {

		return dueDate;
	}

	public void setDueDate(Date dueDate) {

		this.dueDate = dueDate;
	}

	public Books() {

		super();
	}

	public Books(String title, String author) {

		super();
		this.title = title;
		this.author = author;
	}
	


	@Override
	public String toString() {

		return "Books [title=" + title + ", author=" + author + ", status=" + status + ", dueDate=" + dueDate + "]";
	}
}
