package ru.les.dav.sandbox;

public class Task2Distance {
    public static void main(String[] args) {
        Point first = new Point(1.5,3.1);
        Point second = new Point(-5.7,7);
        //через функцию
        System.out.println("Через функицю:\nРасстояние от точки (" + first.x + "," + first.y + ") до точки (" + second.x + "," + second.y + ") равно "+ String.format("%.4g%n", distance(first,second)));
        //через метод
        System.out.println("Через метод:\nРасстояние от точки (" + first.x + "," + first.y + ") до точки (" + second.x + "," + second.y + ") равно "+ String.format("%.4g%n", first.distance(second)));
    }
    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow((p2.x-p1.x),2)+Math.pow((p2.y-p1.y),2));
    }
}