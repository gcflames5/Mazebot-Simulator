package pathfinding;

import pathfinding.util.TileState;

import java.io.Serializable;

public class MazeTile implements Serializable{

    private TileState state;

    public MazeTile(TileState state){
        this.state = state;
    }

    public void setState(TileState state){
        this.state = state;
    }

    public TileState getState(){
        return this.state;
    }

}
