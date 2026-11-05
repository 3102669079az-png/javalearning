import java.util.List;

public class DaoTest {
    public static void main(String[] args) {
        BookDao dao = new BookDao();

        System.out.println("=== 全部 ===");
        List<Book> books = dao.findAll();
        for (Book b : books) {
            b.print();
        }
        System.out.println("共 " + books.size() + " 本");

        System.out.println("\n=== 按名查 ===");
        Book found = dao.findbyName("活着");
        System.out.println(found == null ? "没找到" : "找到: " + found.name);

        System.out.println("\n=== 插一本 ===");
        dao.insert(new PaperBook("DAO测试书", new java.math.BigDecimal("12.30"), 100));
        System.out.println("现在总共 " + dao.count() + " 本");

        System.out.println("\n=== 删掉它 ===");
        int n = dao.delete("DAO测试书");
        System.out.println("删除了 " + n + " 行，现在总共 " + dao.count() + " 本");
    }
}