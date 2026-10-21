abstract class Shape {
    protected final String name;

    protected Shape(String name) { this.name = name; }

    public abstract double area();
    public abstract double perimeter();

    // TODO: 用 String.format 输出 "name: area=xx.xx, perimeter=xx.xx"
    //       注意这里调用的是抽象方法 —— 这就是模板方法的雏形
    public String describe() { return ""; }
}

interface Resizable {
    void resize(double factor);
    // 想一想：为什么「可缩放」适合做接口，而不是放在 Shape 抽象类里？
}

class Circle extends Shape {
    private double r;
    // TODO: 构造器、getR()、setR()（setR 里校验 r > 0，否则抛 IllegalArgumentException）
    // TODO: area()、perimeter()
}

class Rectangle extends Shape implements Resizable {
    // TODO: 宽高，area、perimeter、resize（宽高同乘 factor）
}

class Triangle extends Shape {
    // TODO: 三条边；构造器里校验三角不等式（任意两边之和 > 第三边）
    // TODO: area 用海伦公式：p = (a+b+c)/2; s = sqrt(p*(p-a)*(p-b)*(p-c))
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(2), new Rectangle(3, 4), new Triangle(3, 4, 5) };
        // TODO: 遍历输出 describe()
        // TODO: 用 s instanceof Resizable 判断，是的话 resize(2) 后再输出一次
    }
}
