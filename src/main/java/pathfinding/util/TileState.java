package pathfinding.util;

import java.awt.*;

public enum TileState {

    UNDISCOVERED(Color.GRAY),
    OPEN(Color.WHITE),
    OBSTRUCTED(Color.RED),
    START(Color.GREEN),
    END(Color.BLUE),
    OPEN_AND_EXPLORED(Color.WHITE),
    OPEN_AND_WAITING(Color.YELLOW);

    public Color color;

    TileState(Color color){
        this.color = color;
    }

}
