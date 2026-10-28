import java.util.HashMap;

public class EqualsDemo {
    public static void main(String[] args) {
            HashMap<BookKey, String>map=new HashMap<>();

            map.put(new BookKey("三体"), "刘慈欣");

            BookKey probe=new BookKey("三体");
            System.out.println("用新对象查:"+map.get(probe));
            System.out.println("；两个对象相等吗？"+probe.equals(new BookKey("三体")));
    }
}
class BookKey {
    String name;

    BookKey(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookKey other = (BookKey) o;
        return this.name.equals(other.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

