import java.math.BigDecimal;

public class EBook extends Book implements Downloadable {
    int fileSize; // in MB
    EBook(String name, BigDecimal price, int pages, int fileSize) {
        super(name, price, pages);
        this.fileSize = fileSize;
    }
    @Override
    String getType() {
        return "电子书";
    }
    @Override
    public String toString() {
        return super.toString() + "," + fileSize + "MB";
    }
    @Override
    public void download() {
        System.out.println("正在下载《" + name + "》，大小为" + fileSize + "MB");
    }

}
