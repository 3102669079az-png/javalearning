public abstract class Book {
    String name;
    int price;
    int pages;

    Book(String name,int price,int pages) {
        this.name = name;
        this.price = price;
        this.pages = pages;
    }

    void print() {
        System.out.println(name+","+price+"元,"+pages+"页" );
    }
    int totalValue(int count) {
        return price*count;
    }

    abstract String getType();

}
