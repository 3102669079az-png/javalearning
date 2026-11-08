import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookDao dao = new BookDao();

        while (true) {
            System.out.println();
            System.out.println("===书架====");
            System.out.println("1. 添加图书 2. 列出全部 3. 查找图书 4. 删除图书 0. 退出");
            System.out.print("请输入你的选择: ");

            String cmd = sc.nextLine().trim();

            if (cmd.equals("0")) {
                System.out.println("退出程序...");
                break;
            } else if (cmd.equals("1")) {
                addBook(sc, dao);
            } else if (cmd.equals("2")) {
                listBooks(dao);
            } else if (cmd.equals("3")) {
                findBook(sc, dao);
            } else if (cmd.equals("4")) {
                deleteBook(sc, dao);
            } else {
                System.out.println("无效的选择，请重新输入。");
            }
        }
        sc.close();
    }

    private static void addBook(Scanner sc, BookDao dao) {
        System.out.print("请输入图书名称: ");
        String name = sc.nextLine().trim();

        System.out.print("请输入图书价格: ");
        BigDecimal price;
        try {
            price = new BigDecimal(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("无效的价格，请重新输入。");
            return;
        }

        System.out.print("请输入图书页数: ");
        int pages;
        try {
            pages = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("无效的页数，请重新输入。");
            return;
        }


        try {
            dao.insert(new PaperBook(name, price, pages));
            System.out.println("图书添加成功！" + name);
        } catch (BookException e) {
            System.out.println("添加图书时出错: " + e.getMessage());
        }
    }

    private static void listBooks(BookDao dao) {
        List<Book> books = dao.findAll();
        if (books.isEmpty()) {
            System.out.println("没有图书。");
        } else {
            int i = 1;
            for (Book book : books) {
                System.out.println(i++ + ". ");
                System.out.print(book);
            }
        }
        System.out.println("共" + books.size() + "本");
    }

    private static void findBook(Scanner sc, BookDao dao) {
        System.out.print("请输入要查找的图书名称: ");
        String key = sc.nextLine().trim();

        Book book = dao.findbyName(key);
        if (book == null) {
            System.out.println("没有找到名为 " + key + " 的图书。");
        } else {
            System.out.println("找到图书: " + book);
        }
    }
    private static void deleteBook(Scanner sc, BookDao dao) {
        System.out.print("请输入要删除的图书名称: ");
        String key = sc.nextLine().trim();

        int result = dao.delete(key);
        if (result == 0) {
            System.out.println("没有找到名为 " + key + " 的图书。");
        } else {
            System.out.println("删除 " + result + " 本名为 " + key + " 的图书。");
        }
    }
}
