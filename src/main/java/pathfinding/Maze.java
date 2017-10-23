package pathfinding;

import pathfinding.util.Point;
import pathfinding.util.TileState;
import ui.DebugView;

import java.io.Serializable;

public class Maze implements Serializable{

    public MazeTile[][] tileMatrix;

    public Maze(){
        this.tileMatrix = new MazeTile[15][15];
        clearMaze(TileState.UNDISCOVERED);
    }

    public void setTile(Point p, MazeTile tile){
        this.tileMatrix[p.x][p.y] = tile;
    }

    public void setTileState(Point p, TileState state){
        this.tileMatrix[p.x][p.y].setState(state);
    }

    public MazeTile getTile(Point p){
        return this.tileMatrix[p.x][p.y];
    }

    public void clearMaze(TileState state){
        for (int x = 0; x < tileMatrix.length; x++)
            for (int y = 0; y < tileMatrix[x].length; y++)
                tileMatrix[x][y] = new MazeTile(state);
    }

    public Point getStart(){
        for (int x = 0; x < tileMatrix.length; x++)
            for (int y = 0; y < tileMatrix[x].length; y++) {
                if (tileMatrix[x][y].getState() == TileState.START){
                    DebugView.d("Found " + x + "  " + y);
                    return new Point(x, y);
                }
            }
        return null;
    }

    public Point getEnd(){
        for (int x = 0; x < tileMatrix.length; x++)
            for (int y = 0; y < tileMatrix[x].length; y++)
                if (tileMatrix[x][y].getState() == TileState.END)
                    return new Point(x, y);
        return null;
    }

}
