public abstract class Book {
    String name;
    int price;
    int pages;

    Book(String name,int price,int pages) {
        if(name==null || name.isEmpty()) {
            throw new BookException("书名不能为空");
        }
        if(price<0) {
            throw new BookException("价格不能为负数:"+price);
        }
        if(pages<=0) {
            throw new BookException("页数必须为正数:"+pages);
        }
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
