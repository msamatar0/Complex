import java.util.*;

/**
 *
 * @author msamatar0
 */
public class ContestGO{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        ArrayList<Shape> ar = new ArrayList<>();
        while(in.hasNext())
            ar.add(getShape(in));
        Collections.sort(ar);
        for(Shape i : ar)
            System.out.println(i);
    }
    private static Shape getShape(Scanner in){
        Shape shape = null;
        String s = in.next();
        switch(s){
            case "Circle":
                shape = new Circle(in);
                break;
            case "Ellipse":
                shape = new Ellipse(in);
                break;
            case "Hexagon":
                shape = new Hexagon(in);
                break;
        }
        return shape;
    }
}

abstract class Shape implements Comparable<Shape>{
    protected String fill;
    protected boolean isFilled;
    protected Date date;
    protected abstract double area();
    @Override
    public String toString(){
        return String.format("%s\t%,.2f\t%s\t%b\t%s", getClass().getSimpleName(), area(), fill, isFilled, date);
    }
    @Override
    public int compareTo(Shape o){
        int areaDiff = (int)(area() - o.area());
        return (areaDiff == 0? fill.compareTo(o.fill) : areaDiff);
    }
}

class Circle extends Shape{
    private double radius = 0;
    public Circle(Scanner in){
        radius = in.nextDouble();
        fill = in.next();
        isFilled = in.nextBoolean();
        date = new Date(in.nextLong());
    }
    @Override
    protected double area(){
        return radius * radius * Math.PI;
    }
}

class Ellipse extends Shape{
    private double minor = 0,
            major = 0;
    public Ellipse(Scanner in){
        minor = in.nextDouble();
        major = in.nextDouble();
        fill = in.next();
        isFilled = in.nextBoolean();
        date = new Date(in.nextLong());
    }
    @Override
    protected double area(){
        return minor * major * Math.PI;
    }
}

class Hexagon extends Shape{
    private double side = 0;
    public Hexagon(Scanner in){
        side = in.nextDouble();
        fill = in.next();
        isFilled = in.nextBoolean();
        date = new Date(in.nextLong());
    }
    @Override
    protected double area(){
        return (6 * side * side) / (4 * Math.tan(Math.PI / 6));
    }
}