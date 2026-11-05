import java.math.BigDecimal;

public class PaperBook extends Book {
    PaperBook(String name, BigDecimal price, int pages) {
        super(name,price,pages);
    }

    @Override
    String getType() {
        return "纸质书";
    }
}
