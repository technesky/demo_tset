package com.sky.demo;

/**
 * 泛型的使用
 * Created by sunzhg on 2016/9/22.
 */

/**
 * 泛型接口
 */
interface IMessage{
    public <T> T getMessage(T t);
}
class Message implements IMessage{

    @Override
    public <T> T getMessage(T t) {
        return t;
    }
}

/**
 * 泛型属性和方法
 * @param <T>
 */
class Point<T>{
    private T x;
    private T y;

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
public class Demo2 {
    public static void main(String[] args) {
        IMessage message=new Message();
        System.out.println(message.getMessage("你好"));

        Point<Integer> point=new Point<Integer>();
        point.setX(10);
        point.setY(20);
        System.out.println(point);
        Point<String> point2=new Point<String>();
        point2.setX("横坐标：10");
        point2.setY("纵坐标：20");
        println(point2);
    }
    //问号代表通配符，匹配所有类型，不能写入，只能读取。
    public static void println(Point<?> point){
        System.out.println(point);
    }
}
