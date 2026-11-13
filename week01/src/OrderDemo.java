public class OrderDemo {
    public static  void main(String[] args) throws Exception{
        Thread a =new Thread(() ->{
            for(int i=1;i<=12;i++) System.out.print("A"+ i +" ");
        },"线程A");

        Thread b =new Thread(() ->{
            for(int i=1;i<=12;i++) System.out.print("B"+ i +" ");
        },"线程B");

        a.start();
        b.start();
        a.join();
        b.join();
        System.out.println();
    }
}
