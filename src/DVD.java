public class DVD extends Item {

    @Override
    public int getNumberOfCheckOutDays() {
        return 3;
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
        return String.format("%-45s", getTitle());
    };
}