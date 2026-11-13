public class SyncDemo {
    static int count = 0;

    synchronized void add(){
        count++;
    }

    public static void main(String[] args) throws Exception{
        SyncDemo obj1 = new SyncDemo();
        SyncDemo obj2 = new SyncDemo();
        Runnable jobA =() ->{
            for(int i= 0; i < 100000; i++)obj1.add();
        };
        Runnable jobB =() ->{
            for(int i= 0; i < 100000; i++)obj2.add();
        };

        Thread a = new Thread(jobA,"线程A");
        Thread b = new Thread(jobB,"线程B");

        a.start();
        b.start();
        a.join();
        b.join();

        System.out.println("count = " + count + " 期望 200000 丢了 " + (200000 - count));
    }
}
