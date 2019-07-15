import java.util.Date;

public class Books {

	private String title;

	private String author;

	private Status status;

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

	public String getStatus() {

		return status;
	}

	public void setStatus(String status) {

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

	public Books(String title, String author, String status, Date dueDate) {

		super();
		this.title = title;
		this.author = author;
		this.status = status;
		this.dueDate = dueDate;
	}

	@Override
	public String toString() {

		return "Books [title=" + title + ", author=" + author + ", status=" + status + ", dueDate=" + dueDate + "]";
	}
}
