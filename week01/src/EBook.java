public class EBook extends Book {
    int fileSize; // in MB
    EBook(String name, int price, int pages, int fileSize) {
        super(name, price, pages);
        this.fileSize = fileSize;
    }
    @Override
        public void print() {
            System.out.println(name + "," + price + "元," + pages + "页," + fileSize + "MB");
        }

}
