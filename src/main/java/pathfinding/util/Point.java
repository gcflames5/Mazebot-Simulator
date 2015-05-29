package pathfinding.util;

public class Point {

    public int x, y;
    public int cost = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point inDirectionOf(Direction direction){
        return new Point(this.x + direction.dirx, this.y + direction.diry);
    }

    public double dist(Point p2){
        return Math.sqrt(Math.pow(this.x - p2.x, 2) + Math.pow(this.y - p2.y, 2));
    }

    @Override
    public boolean equals(Object obj){
        if (obj.getClass() != Point.class)
            return false;
        Point p = (Point) obj;
        return this.x == p.x && this.y == p.y;
    }

    @Override
    public String toString(){
        return " POINT: { x : " + this.x + ", y : " + this.y + " }";
    }

}
