import java.util.HashMap;

public class HashmapDemo {
    public static void main(String[] args) {
        HashMap<String, Integer> priceMap = new HashMap<>();

        priceMap.put("三体", 10);
        priceMap.put("活着", 20);
        priceMap.put("平凡的世界", 30);

        System.out.println("Price of 三体: " + priceMap.get("三体"));
        System.out.println("不存在的书: " + priceMap.get("红楼梦"));
        System.out.println("有没有《平凡的世界》: " + priceMap.containsKey("平凡的世界"));
        System.out.println("一共" + priceMap.size() + "本书");

        priceMap.put("三体",200);
        System.out.println("改价后的: " + priceMap.get("三体"));

        priceMap.remove("活着");
        System.out.println("删除后的书本数量: " + priceMap.size());

        for (String name : priceMap.keySet()) {
            System.out.println(name + "= " + priceMap.get(name)+"元");
        }
    }
}
