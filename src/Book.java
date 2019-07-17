public class Book extends Item {

    private String author;
   
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int getNumberOfCheckOutDays() {
        return 14;
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
        return String.format("%-45s %-45s", getTitle(), getAuthor());
    }
}