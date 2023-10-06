package functiondemo;

import java.util.Objects;
import java.util.function.IntFunction;

/**
 * @author Hi Yen Wong
 * @date 2023/7/27 21:56
 */

class Position implements BiIn {
    private Integer x;
    private Integer y;
    private Integer z;

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }


    public Integer getZ() {
        return z;
    }


    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position position)) return false;
        return Objects.equals(getX(), position.getX()) && Objects.equals(getY(), position.getY()) && Objects.equals(getZ(), position.getZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    public Position(Integer x, Integer y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}

class Cube {
    private String x;
    private String y;
    private String z;

    @Override
    public String toString() {
        return "Cube{" +
                "x='" + x + '\'' +
                ", y='" + y + '\'' +
                ", z='" + z + '\'' +
                '}';
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getZ() {
        return z;
    }

    public void setZ(String z) {
        this.z = z;
    }

    public Cube(String x, String y, String z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public boolean equals(Position o) {
        return o.getX().toString().equals(this.x);
    }


}

interface BiIn {
    void setX(Integer x);

    Integer getX();

    Integer getY();

    Integer getZ();
}


interface BiOut {
    String getX(BiIn x);
}


class FunctionDemo {
    public Position getPosition() {

        return new Position(3, 4, 5);
    }

    static Position getPosition(Integer b) {
        return new Position(3 + b, 2 + b, 4 + b);
    }

    public Cube push(Integer stamp, IntFunction< Position> salt) {
        int i = 10 + stamp;
        int c = 20 + stamp;
        int d = 30 + stamp;
        Position apply = salt.apply(i + c + d);
        return new Cube(apply.getX().toString() + "_mark",
                apply.getY().toString() + "_mark",
                apply.getZ().toString() + "_mark");
    }


    public static void main(String[] args) {
        FunctionDemo functionDemo = new FunctionDemo();
        Cube patterns = functionDemo.push(3,
                FunctionDemo::getPosition);
        Cube patterns2 = functionDemo.push(3,
                x -> {
                    System.out.println("x:" + x);
                    return new Position(5 + x, 1 + x, 8 + x);
                });
        System.out.println(patterns.toString());
        System.out.println(patterns2.toString());
        BiOut biOut = functionDemo.functionDemo();
        String x = biOut.getX(new Position(1, 3, 10));
        System.out.println(x);
    }

    public BiOut functionDemo() {
        return resp ->
                resp.getZ().toString();


    }
}
