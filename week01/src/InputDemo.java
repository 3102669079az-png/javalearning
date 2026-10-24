import java.util.Scanner;

public class InputDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1>打招呼   0>退出  2>输入单价");
            System.out.print("请输入你的选择:");

            String choice = sc.nextLine().trim();
            if (choice.equals("0")) {
                System.out.println("再见");
                break;
            } else if (choice.equals("1")) {
                System.out.print("请输入你的名字:");
                String name = sc.nextLine();
                System.out.println("Hello," + name + "!");
            } else if (choice.equals("2")) {
                System.out.print("单价:");
                String s= sc.nextLine().trim();

                try{
                    int price = Integer.parseInt(s);
                    System.out.println("单价是"+price+"元");
                } catch (NumberFormatException e) {
                    System.out.println("不是有效的数字:"+s);
                }

            } else {
                System.out.println("输入有误，请重新输入"+choice);
            }
        }
        sc.close();
    }
}
