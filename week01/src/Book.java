public class Book {
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
    int totalvalue(int count) {
        return price*count;
    }

}
