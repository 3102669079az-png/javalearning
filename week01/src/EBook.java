public class EBook extends Book implements Downloadable {
    int fileSize; // in MB
    EBook(String name, int price, int pages, int fileSize) {
        super(name, price, pages);
        this.fileSize = fileSize;
    }
    @Override
    String getType() {
        return "电子书";
    }
    @Override
        public void print() {
            System.out.println(name + "," + price + "元," + pages + "页," + fileSize + "MB");
        }
    @Override
    public void download() {
        System.out.println("正在下载《" + name + "》，大小为" + fileSize + "MB");
    }

}
