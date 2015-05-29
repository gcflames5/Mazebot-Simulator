package pathfinding;

import pathfinding.util.TileState;

public class MazeTile {

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
