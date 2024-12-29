package Librairies;

public class Point {

    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return " (x=" + x + ", y=" + y + ")";
    }

    
    public boolean equals(Point B){
        return this.x==B.getX()&&this.y==B.getY();
    }

    public int distance(Point position) {
        return (int) Math.sqrt(Math.pow(this.x - position.getX(), 2) + Math.pow(this.y - position.getY(), 2));        
    }

}
