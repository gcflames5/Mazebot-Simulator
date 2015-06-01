package pathfinding.util;

public class Node {

    public Point point;
    public double pathCost; //cost to get here
    public Node parent;
    public boolean visited = false;

    public Node(Point point, double cost){
        this.point = point;
        this.pathCost = cost;
    }

    public double calcCost(Point end){
        return pathCost + point.dist(end);
    }

}
