import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JdbcDemo {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/bookshelf"
                + "?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";

        try(Connection conn = DriverManager.getConnection(url, "root", "123456");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT id,name,price,pages FROM book ORDER BY id")) {

            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal("price");
                int pages = rs.getInt("pages");
                System.out.println(id + "|" + name + "|" + price + "|" + pages);
            }
        }

    }
}
