package pathfinding;

import pathfinding.util.Direction;
import pathfinding.util.Point;
import pathfinding.util.TileState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

    public class Explorer {

    public Maze maze, key;
    public Point currentPoint;
    Direction currentDirection;

    List<Point> possiblePoints;
    Stack<Point> missedOppertunities;

    Random rand;

    public Explorer(Maze maze, Maze mazeKey){
        this.maze = maze;
        this.currentPoint = mazeKey.getStart();
        this.currentDirection = Direction.UP;
        this.key = mazeKey;
        this.possiblePoints = new ArrayList<Point>();
        this.missedOppertunities = new Stack();

        rand = new Random();
    }

    public boolean step(){
        checkVisibleDirections();
        if (!move())
            return false;
        return true;
    }

    public void cleanup(){
        for (int x = 0; x < maze.tileMatrix.length; x++)
            for (int y = 0; y < maze.tileMatrix[x].length; y++)
                if (maze.tileMatrix[x][y].getState() == TileState.OPEN_AND_WAITING)
                    maze.setTileState(new Point(x, y), TileState.OPEN);
    }

    private void checkVisibleDirections(){
        for (Direction d : currentDirection.pseudo()){
            Point newPoint = currentPoint.inDirectionOf(d);
            if (newPoint.x < 0 || newPoint.x >= maze.tileMatrix.length
                    || newPoint.y < 0 || newPoint.y >= maze.tileMatrix.length)
                continue;
            maze.setTile(newPoint, key.getTile(newPoint));
            if (maze.getTile(newPoint).getState() == TileState.OPEN)
                possiblePoints.add(newPoint);
        }
    }

    private boolean move(){
        if (possiblePoints.size() <= 0){
            if (missedOppertunities.size() <= 0)
                return false;
            possiblePoints.add(missedOppertunities.pop());
        }

        //Choose a new Point to move to and take other possibilities and put them in missedOppertunities
        Point chosenPoint = possiblePoints.get(rand.nextInt(possiblePoints.size()));
        possiblePoints.remove(chosenPoint);
        for (Point p : possiblePoints){
            missedOppertunities.add(p);
            maze.setTileState(p, TileState.OPEN_AND_WAITING);
        }
        possiblePoints.clear();

        //Set the current tile (before moving) state to OPEN_AND_EXPLORED
        maze.setTileState(currentPoint, TileState.OPEN_AND_EXPLORED);

        //Turn the bot if need be and update the current point
        currentDirection = Direction.getDir(currentPoint, chosenPoint);
        currentPoint = chosenPoint;

        return true;
    }

}
