import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    private static final String SELECT_ALL="SELECT id,name,type,price,pages,file_size";

    private static final String URL=
                      "jdbc:mysql://localhost:3306/bookshelf"
                    + "?useSSL=false&serverTimezone=Asia/Shanghai&characterEncoding=utf8";
    private static final String USER="root";
    private static final String PASSWORD="123456";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // ============ 查全部 ============
    public List<Book> findAll() {
        String sql =SELECT_ALL+" FROM book ORDER BY id";
        List<Book> result = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                result.add(toBook(rs));
            }
        }catch (SQLException e) {
            throw new RuntimeException("查询失败", e);
        }
        return result;
    }

    // ============ 查单个 ============
    public Book findByName(String name) {
        String sql =SELECT_ALL+" FROM book WHERE name = ? LIMIT 1";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return toBook(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查询失败", e);
        }
        return null;
    }

    // ============ 加 ============
    public long insert(Book book) {
        String sql = "INSERT INTO book (name, type, price, pages, file_size) VALUES (?, ?, ?, ?, ?)";

        String type;
        Integer fileSize=null;
        if(book instanceof EBook ebook){
            type="EBOOK";
            fileSize=ebook.fileSize;
        }else{
            type="PAPER";
        }
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, book.name);
            ps.setString(2, type);
            ps.setBigDecimal(3, book.price);
            ps.setInt(4, book.pages);
            if(fileSize!=null){
                ps.setInt(5, fileSize);
            }else{
                ps.setNull(5, Types.INTEGER);
            }
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    book.id = keys.getLong(1);
                }
            }
            return book.id;

        } catch (SQLException e) {
            throw new RuntimeException("插入失败", e);
        }
    }
    // ============ 删 ============
    public int delete(String name) {
        String sql = "DELETE FROM book WHERE name = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("删除失败", e);
        }
    }
    // ============ 数 ============
    public int count() {
        String sql = "SELECT COUNT(*) FROM book";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            rs.next();
            return rs.getInt(1);

        }catch (SQLException e) {
            throw new RuntimeException("计数失败", e);
        }
    }
    // ============ 变成对象============
    private Book toBook(ResultSet rs) throws SQLException {
        long id = rs.getLong("id");
        String name = rs.getString("name");
        String type = rs.getString("type");
        BigDecimal price = rs.getBigDecimal("price");
        int pages = rs.getInt("pages");

        Book book;
        if (type.equals("EBOOK")) {
            int filesize = rs.getInt("file_size");
            book = new EBook(name, price, pages, filesize);
        } else {
            book = new PaperBook(name, price, pages);
        }
        book.id=id;
        return book;
    }
    // ============ 改 ============
    public int update(Book book) {
        String sql = "UPDATE book SET name = ?, type=?,price = ?, pages = ?,file_size= ? WHERE id = ?";

        String type;
        Integer fileSize=null;
        if(book instanceof EBook ebook){
            type="EBOOK";
            fileSize=ebook.fileSize;
        }else{
            type="PAPER";
        }
        try(Connection coon=getConnection();
            PreparedStatement ps=coon.prepareStatement(sql)){

            ps.setString(1,book.name);
            ps.setString(2,type);
            ps.setBigDecimal(3,book.price);
            ps.setInt(4,book.pages);
            if(fileSize!=null){
                ps.setInt(5,fileSize);
            }else{
                ps.setNull(5,Types.INTEGER);
            }
            ps.setLong(6,book.id);

            return ps.executeUpdate();

        }catch(SQLException e){
            throw new RuntimeException("更新失败",e);
        }
    }

    public Book findById(long id) {
        String sql =SELECT_ALL+" FROM book WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return toBook(rs);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("查找图书时出错", e);
        }
        return null;
    }
    public int deleteById(long id) {
        String sql = "DELETE FROM book WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("删除图书时出错", e);
        }
    }
}
