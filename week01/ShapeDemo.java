abstract class Shape {
    protected final String name;
    protected Shape(String name) { this.name = name; }
    public abstract double area();
    public abstract double perimeter();
    public String describe() {
        return String.format("%s: area=%.2f, perimeter=%.2f", name, area(), perimeter());
    }
}
interface Resizable {
    void resize(double factor);
}
class Circle extends Shape {
    private double r;
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
    @Override public double area()      { return Math.PI * r * r; }
    @Override public double perimeter() { return 2 * Math.PI * r; }
}

class Rectangle extends Shape implements Resizable {
    private double width;
    private double height;
    public Rectangle(double width, double height) {
        super("Rectangle");
        setSize(width, height);
    }
    public double getWidth()  { return width; }
    public double getHeight() { return height; }

    public void setSize(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("宽/高必须 > 0");
        }
        this.width = width;
        this.height = height;
    }

    @Override public double area()      { return width * height; }
    @Override public double perimeter() { return 2 * (width + height); }

    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("缩放因子必须 > 0，实际为 " + factor);
        }
        setSize(width * factor, height * factor);  // 宽高同乘 factor
    }
}
class Triangle extends Shape {
    private final double a, b, c;
    public Triangle(double a, double b, double c) {
        super("Triangle");
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalArgumentException("边长必须 > 0");
        }
        // 三角不等式：任意两边之和 > 第三边
        if (a + b <= c || a + c <= b || b + c <= a) {
            throw new IllegalArgumentException(
                    "不满足三角不等式: " + a + ", " + b + ", " + c);
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double area() {
        double p = (a + b + c) / 2;
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));  // 海伦公式
    }

    @Override public double perimeter() { return a + b + c; }
}

public class ShapeDemo {
    public static void main(String[] args) {
        Shape[] shapes = { new Circle(2), new Rectangle(3, 4), new Triangle(3, 4, 5) };

        System.out.println("=== 缩放前 ===");
        for (Shape s : shapes) {
            System.out.println(s.describe());
        }

        System.out.println("=== resize(2) 后 ===");
        for (Shape s : shapes) {
            if (s instanceof Resizable) {
                ((Resizable) s).resize(2);
                System.out.println(s.describe());
            }
        }
    }
}

