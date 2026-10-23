import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Book b1 = new PaperBook("活着", 45, 191);
        b1.print();
        Book b2 = new PaperBook("三体", 23, 302);
        b2.print();
        System.out.println(b1.name + "买三本共" + b1.totalValue(3) + "元");


        ArrayList<Book> shelf = new ArrayList<Book>();
        shelf.add(new PaperBook("活着", 45, 191));
        shelf.add(new EBook("三体", 23, 302, 5));
        shelf.add(new PaperBook("平凡的世界", 68, 402));
        shelf.remove(0);
        System.out.println("书架上有" + shelf.size() + "本书。");
        int sum = 0;

        {
            for (Book book : shelf) {
                System.out.println("【" + book.getType() + "】");
                book.print();
                sum = sum + book.price;
            }
            System.out.println("总" + shelf.size() + "本" + sum + "元");
        }


        EBook e1 = new EBook("三体电子版", 19, 302, 5);
        e1.print();
        System.out.println("书名:" + e1.name);

        Book b3 = new EBook("三体电子版", 19, 302, 5);
        b3.print();

        EBook e2 = new EBook("平凡的世界电子版", 12, 402, 3);
        e2.download();
    }
}
