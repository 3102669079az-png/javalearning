public class RaceDemo {
    static int count =0;

    public static void main(String[] args) throws Exception{
        Runnable job =() ->{
            for(int i= 0; i < 100000; i++){
                count++;

            }
        };

        Thread a = new Thread(job,"线程A");
        Thread b = new Thread(job,"线程B");

        a.start();
        b.start();
        a.join();
        b.join();

        System.out.println("count = " + count + " 期望 200000 丢了 " + (200000 - count));
    }
}
