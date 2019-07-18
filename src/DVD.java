public class DVD extends Item {

	private String author = null;
	private int checkoutdays = 3;
	private int holddays = 3;

	public int getCheckoutdays() {
		super.getCheckoutdays();
		return checkoutdays;
	}

	public void setCheckoutdays(int checkoutdays) {
		this.checkoutdays = checkoutdays;
	}

	public String getAuthor() {
		super.getAuthor();
		return author;
	}

	public int getHolddays() {
		super.getHolddays();
		return holddays;
	}

	public void setHolddays(int holddays) {
		this.holddays = holddays;
	}

	@Override
	public Type getItemType() {
		return Type.DVD;
	}

	@Override
	public String toLine() {
		return String.format("%s,%s,%s,%s,%s", getTitle(), null, getStatus(), getDueDate(), getType());
	}

	@Override
	public String toString() {
		return String.format("%-76s", getTitle());
	};
}