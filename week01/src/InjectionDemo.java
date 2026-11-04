import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InjectionDemo {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:mysql://localhost:3306/bookshelf"
                + "?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";

        String input = "' OR '1'='1";
        String sql ="SELECT id,name,price FROM book WHERE id='"+input+"'";
        System.out.println("实际执行的SQL:"+sql);
        try(Connection conn = DriverManager.getConnection(url, "root", "123456");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                BigDecimal price = rs.getBigDecimal ("price");
                System.out.println(id + "|" + name + "|" + price);
            }
        }
    }
}
