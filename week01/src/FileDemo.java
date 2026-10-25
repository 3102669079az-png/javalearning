import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileDemo {
    public static void main(String[] args) throws Exception {
        Path path = Path.of("demo.txt");

        Files.write(path, List.of("第一行", "第二行", "第三行"));
        System.out.println("写入文件成功");

        List<String> lines = Files.readAllLines(path);
        for (String line : lines) {
            System.out.println("读取文件内容:"+line);
        }
        System.out.println("文件位置："+path.toAbsolutePath());
    }
}
