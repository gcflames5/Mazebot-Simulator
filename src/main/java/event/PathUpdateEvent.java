package event;

import pathfinding.util.Node;

import java.util.List;

public class PathUpdateEvent extends Event{

    List<Node> nodeList;

    public PathUpdateEvent(List<Node> nodeList){
        this.nodeList = nodeList;
    }

    public List<Node> getPath(){
        return this.nodeList;
    }

}
