public class PaperBook extends Book {
    PaperBook(String name,int price,int pages) {
        super(name,price,pages);
    }

    @Override
    String getType() {
        return "纸质书";
    }
}
