abstract class Shape {
    protected final String name;

    protected Shape(String name) { this.name = name; }

    public abstract double area();
    public abstract double perimeter();

    // 用 String.format 输出 "name: area=xx.xx, perimeter=xx.xx"
    public String describe() {
        return String.format("%s: area=%.2f, perimeter=%.2f", name, area(), perimeter());
    }
    //       注意这里调用的是抽象方法 —— 这就是模板方法的雏形
}

interface Resizable {
    void resize(double factor);
    // 想一想：为什么「可缩放」适合做接口，而不是放在 Shape 抽象类里？
}

class Circle extends Shape {
    private double r;
    // 构造器、getR()、setR()（setR 里校验 r > 0，否则抛 IllegalArgumentException）
    public Circle(double r) {
        super("Circle");
        setR(r);
    }
    public double getR() { return r; }
    public void setR(double r) {
        if (r <= 0) {
            throw new IllegalArgumentException("半径必须 > 0，实际为 " + r);
        }
        this.r = r;
    }
    public double area() { return Math.PI * r * r; }
    public double perimeter() { return 2 * Math.PI * r; }
}

class Rectangle extends Shape implements Resizable {
    // 宽高，area、perimeter、resize（宽高同乘 factor）
    private double width, height;
    public Rectangle(double width, double height) {
        super("Rectangle");
        setWidth(width);
        setHeight(height);
    }
    public double getWidth() { return width; }
    public void setWidth(double width) { this.width = width; }
    public double getHeight() { return height; }
    public void setHeight(double height) { this.height = height; }
    public double area() { return width * height; }
    public double perimeter() { return 2 * (width + height); }
    public void resize(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("缩放因子必须 > 0，实际为 " + factor);
        }
        setWidth(width * factor);
        setHeight(height * factor);
    }
}

class Triangle extends Shape {
    // 三条边；构造器里校验三角不等式（任意两边之和 > 第三边）
    private double a, b, c;
    public Triangle(double a, double b, double c) {
        super("Triangle");
        setA(a);
        setB(b);
        setC(c);
    }
    public double getA() { return a; }
    public void setA(double a) { this.a = a; }
    public double getB() { return b; }
    public void setB(double b) { this.b = b; }
    public double getC() { return c; }
    public void setC(double c) { this.c = c; }
    public double area() { return Math.sqrt(a * b * c); }
    public double perimeter() { return a + b + c; }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(2), new Rectangle(3, 4), new Triangle(3, 4, 5) };
        // 遍历输出 describe()
        for (Shape s : shapes) {
            System.out.println(s.describe());
        }
        //用 s instanceof Resizable 判断，是的话 resize(2) 后再输出一次
        for (Shape s : shapes) {
            if (s instanceof Resizable) {
                ((Resizable) s).resize(2);
                System.out.println(s.describe());
            }
        }
    }
}
