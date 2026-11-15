public class VisDemoVolatile {
    static volatile boolean flag = false;

    public static void main(String[] args) throws Exception{
        Thread worker = new Thread(() ->{
            System.out.println("[工作线程] 开始等 flag 变成 true ...");
            long loops = 0;
            while (!flag) {
                loops++;
            }
            System.out.println("[工作线程] 看到 flag = true，退出循环，转了 " + loops + " 圈");
        },"工作线程");

        worker.start();
        Thread.sleep(1000);
        System.out.println("[主线程] 现在把 flag 改成 true");
        flag = true;
        System.out.println("[主线程] 已经改了，等 3 秒看工作线程有没有退出");
        Thread.sleep(3000);
        System.out.println("[主线程] worker.isAlive() = " + worker.isAlive());

    }
}
