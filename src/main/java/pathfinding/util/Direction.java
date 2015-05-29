package pathfinding.util;

public enum Direction {

    LEFT(-1,0),
    RIGHT(1, 0),
    DOWN(0, -1),
    UP(0, 1);

    public int dirx, diry;

    Direction(int dirx, int diry){
        this.dirx = dirx;
        this.diry = diry;
    }

    //If facing in "this" direction, which 3 dirs can the bot see?
    public Direction[] pseudo(){
        if (this == LEFT)
            return new Direction[] { Direction.UP, Direction.LEFT, Direction.DOWN };
        else if (this == RIGHT)
            return new Direction[] {  Direction.UP, Direction.RIGHT, Direction.DOWN };
        else if (this == DOWN)
            return new Direction[] { Direction.LEFT, Direction.DOWN, Direction.RIGHT };
        else if (this == UP)
            return new Direction[] { Direction.LEFT, Direction.UP, Direction.RIGHT };
        return null;
    }

    public static Direction getDir(Point from, Point to){
        if (from.x > to.x)
            return Direction.LEFT;
        if (from.x < to.x)
            return Direction.RIGHT;
        if (from.y > to.y)
            return Direction.DOWN;
        if (from.y < to.y)
            return Direction.UP;
        return null;
    }

}
