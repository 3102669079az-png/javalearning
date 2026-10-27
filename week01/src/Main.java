import java.util.HashMap;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        HashMap<String, Book> shelf = load();

        while (true) {
            System.out.println();
            System.out.println("==== 书架 ====");
            System.out.println("1 加书   2 列出全部   3 查找   4 删除   0 退出");
            System.out.print("> ");

            String cmd = sc.nextLine().trim();

            if (cmd.equals("0")) {
                System.out.println("再见");
                break;
            } else if (cmd.equals("1")) {
                System.out.print("书名: ");
                String name = sc.nextLine().trim();

                System.out.print("价格: ");
                int price;
                try {
                    price = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("价格必须是数字");
                    continue;
                }

                System.out.print("页数: ");
                int pages;
                try {
                    pages = Integer.parseInt(sc.nextLine().trim());
                } catch (NumberFormatException e) {
                    System.out.println("页数必须是数字");
                    continue;
                }

                try {
                    shelf.put(name, new PaperBook(name, price, pages));
                    save(shelf);
                    System.out.println("添加成功:" + name);
                } catch (BookException e) {
                    System.out.println("添加失败:" + e.getMessage());
                }

            } else if (cmd.equals("2")) {
                if (shelf.isEmpty()) {
                    System.out.println("书架为空");
                } else {
                    int i = 1;
                    for (Book book : shelf.values()) {
                        System.out.print(i++ + ". ");
                        book.print();
                    }
                }
                System.out.println("共" + shelf.size() + "本书");
            } else if (cmd.equals("3")) {
                System.out.print("要查找的书名: ");
                String key = sc.nextLine().trim();
                Book found = shelf.get(key);
                if (found == null) {
                    System.out.println("没有找到这本书:" + key);
                } else {
                    System.out.println("找到: ");
                    found.print();
                }
            } else if (cmd.equals("4")) {
                System.out.print("要删除的书名: ");
                String key = sc.nextLine().trim();
                Book removed = shelf.remove(key);
                if (removed == null) {
                    System.out.println("没有找到这本书:" + key);
                } else {
                    save(shelf);
                    System.out.println("删除成功:" + removed.name);
                }
            } else {
                System.out.println("没有这个命令");
            }
        }
        sc.close();
    }

    private static void save(HashMap<String, Book> shelf) throws IOException {
        List<String> lines = new ArrayList<>();
        for (Book book : shelf.values()) {
            lines.add(book.name + "," + book.price + "," + book.pages);
        }
        Files.write(Path.of("shelf.txt"), lines);
    }

    private static HashMap<String, Book> load() throws IOException {
        HashMap<String, Book> shelf = new HashMap<>();
        Path file = Path.of("shelf.txt");

        if (!Files.exists(file)) {
            return shelf;
        }
        for (String line : Files.readAllLines(file)) {
            if (line.isBlank()) continue;
            String[] p = line.split(",");
            try {
                Book b = new PaperBook(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]));
                shelf.put(b.name, b);
            } catch (BookException | NumberFormatException e) {
                System.out.println("跳过损坏的记录:" + line);
            }
        }
        return shelf;
    }
}