import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.math.BigDecimal;


public class PrepareDemo {
    public static void main(String[] args) throws Exception {
        String url ="jdbc:mysql://localhost:3306/bookshelf"
                + "?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";

        String sql = "INSERT INTO book (name,type,price,pages) VALUES (?, ?,?,?)";

        try (Connection conn = DriverManager.getConnection(url,"root","123456");
            PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, "围城");
                ps.setString(2, "PAPER");
                ps.setBigDecimal(3, new BigDecimal(10.00));
                ps.setInt(4, 300);

                int rows = ps.executeUpdate();
                System.out.println(rows + "行数据插入成功");
                }
        sql = "SELECT * FROM book";
        try (Connection conn = DriverManager.getConnection(url,"root","123456");
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            int count = 0;
            while (rs.next()) {
                System.out.println(rs.getInt("id") + "|"
                        + rs.getString("name") + "|"
                        + rs.getBigDecimal("price"));
                count++;
            }
            System.out.println("共查询到" + count + "条记录");
            }
        }
}
