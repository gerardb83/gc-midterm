public class Book extends Item {

	private String author;
	private int checkoutdays = 14;
	private int holddays = 3;
	
	public int getCheckoutdays() {
		super.getCheckoutdays();
		return checkoutdays;
	}

	public void setCheckoutdays(int checkoutdays) {
		this.checkoutdays = checkoutdays;
	}

	public int getHolddays() {
		super.getHolddays();
		return holddays;
	}

	public void setHolddays(int holddays) {
		this.holddays = holddays;
	}

	public String getAuthor() {
			super.getAuthor();
			return author;
		}
	
	public void setAuthor(String author) {
		super.setAuthor(author);
		this.author = author;
	}

	@Override
	public Type getItemType() {
		return Type.BOOK;
	}

	@Override
	public String toLine() {
		return String.format("%s,%s,%s,%s,%s", getTitle(), getAuthor(), getStatus(), getDueDate(), getType());
	}

	public String toString() {
		return String.format("%-45s %-30s", getTitle(), getAuthor());
	}
}