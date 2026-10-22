public class Main {
    public static void main(String[] args) {
        Book b1 = new Book("活着", 45, 191);
        b1.print();
        Book b2 = new Book("三体", 23, 302);
        b2.print();
        System.out.println(b1.name + "买三本共" + b1.totalvalue(3) + "元");


        Book[] books = {
                new Book("活着", 45, 191),
                new Book("三体", 23, 302),
                new Book("平凡的世界", 68, 402)
        };
        int sum = 0;

        {
            for (Book book : books) {
                book.print();
                sum = sum + book.price;
            }
            System.out.println("总" + books.length + "本" + sum + "元");
        }
    }
}
