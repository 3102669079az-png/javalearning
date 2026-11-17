public class LazySingleton {
    private static volatile LazySingleton instance;

    static int created = 0;

    private LazySingleton() {
        created++;
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static LazySingleton getInstance() {
        if (instance == null) {
            synchronized (LazySingleton.class) {
                if (instance == null)
                    instance = new LazySingleton();
            }

        }
        return instance;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                LazySingleton.getInstance();
            }, "测试线程").start();
        }

        Thread.sleep(1000);
        System.out.println("写法1 懒汉（无锁） 次数：" + created);
    }
}
