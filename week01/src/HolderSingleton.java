public class HolderSingleton {
    static int created = 0;
    private HolderSingleton(){
        created++;
        try {
            Thread.sleep(30);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static class Holder{
        private static final HolderSingleton INSTANCE = new HolderSingleton();
    }

    public static HolderSingleton getInstance(){
        return Holder.INSTANCE;
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                HolderSingleton.getInstance();
            }, "测试线程").start();
        }

        Thread.sleep(1000);
        System.out.println("写法 静态内部类 次数：" + created);
    }
}
