public class HashDemo {
    public static void main(String[] args) {
        String[] keys={"三体","活着","平凡的世界","红楼梦"};

        for (String k:keys) {
            int h=k.hashCode();
            int index=(16-1)&h;
            System.out.println(k+"的hashCode=:"+h+"，下标是:"+index);
        }
    }
}
