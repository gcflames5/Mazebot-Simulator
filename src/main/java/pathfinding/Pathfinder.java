package pathfinding;

import pathfinding.util.Direction;
import pathfinding.util.Node;
import pathfinding.util.Point;
import pathfinding.util.TileState;
import ui.MazeController;
import ui.MazeSolveView;

import javax.swing.*;
import java.util.*;

public class Pathfinder {

    Maze mazeToSolve;
    Node currentNode;
    Point endPoint;

    private List<Node> nodes, open, closed, path;

    JComboBox delay;

    public Pathfinder(JComboBox delay, Maze mazeToSolve){
        this.delay = delay;

        path = Collections.synchronizedList(new ArrayList<Node>());
        open = new ArrayList<Node>();
        closed = new ArrayList<Node>();
        nodes = new ArrayList<Node>();

        for (int x = 0; x < mazeToSolve.tileMatrix.length; x++){
            for (int y = 0; y < mazeToSolve.tileMatrix[x].length; y++){
                nodes.add(new Node(new Point(x, y), 0));
            }
        }

        this.mazeToSolve = mazeToSolve;

        this.endPoint = mazeToSolve.getEnd();
    }

    //Use Best-first search algorithm and pseudo priority queue to get best path
    public List<Node> getPath(MazeSolveView view){
        return makePath(find(view));
    }

    public List<Node> makePath(Node end){
        List<Node> nodes = new ArrayList<Node>();
        Node currentNode = end;
        while (currentNode.parent != null){
            nodes.add(currentNode);
            currentNode = currentNode.parent;
        }
        return nodes;
    }

    private List<Node> getPossibilities(Node from){
        List<Node> possibilities = new ArrayList<Node>();
        for (Direction d : Direction.values()){
            Point newPoint = from.point.inDirectionOf(d);
            if (newPoint.x < 0 || newPoint.x >= mazeToSolve.tileMatrix.length
                    || newPoint.y < 0 || newPoint.y >= mazeToSolve.tileMatrix.length)
                continue;
            if (mazeToSolve.getTile(newPoint).getState() == TileState.OPEN
                    || mazeToSolve.getTile(newPoint).getState() == TileState.OPEN_AND_EXPLORED
                    || mazeToSolve.getTile(newPoint).getState() == TileState.OPEN_AND_WAITING
                    || mazeToSolve.getTile(newPoint).getState() == TileState.END){
                Node node = pointToNode(nodes, newPoint);
                if (node != null && !node.visited)
                    possibilities.add(node);
            }
        }

        return possibilities;
    }

    /*public Node find(MazeSolveView view){
        this.currentNode = pointToNode(nodes, mazeToSolve.getStart());
        System.out.println(this.endPoint);
        open.add(currentNode);
        while(!open.isEmpty()){
            view.path = makePath(currentNode);
            try {
                Thread.sleep(Integer.valueOf(delay.getSelectedItem().toString()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Node node = getClosestToGoal(open);
            System.out.println("--");
            for (Node n : open)
                System.out.println(n.point + " "  + n.calcCost(endPoint));

            open.remove(currentNode);
            if (currentNode != node){
                node.parent = currentNode;
                node.pathCost = node.parent.pathCost + 1;
                node.visited = true;
            }
            currentNode = node;

            if (currentNode.point.equals(endPoint)){
                return currentNode;
            }

            for (Node n : getPossibilities(node)){
                if (!n.visited)
                    open.add(n);
            }
        }
        throw new RuntimeException("FAILED");
    }*/

    public Node find(MazeSolveView view) {
        System.out.println(this.endPoint);
        this.currentNode = pointToNode(nodes, mazeToSolve.getStart());
        open.add(this.currentNode);
        while (!open.isEmpty()){
            try {
                Thread.sleep(Integer.valueOf(delay.getSelectedItem().toString()));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Node node = getClosestToGoal(open);
            view.path = makePath(node);
            open.remove(node);
            closed.add(node);
            if (node.point.equals(endPoint)){
                return node;
            }
            for (Node child : getPossibilities(node)){
                if (!closed.contains(child) && !open.contains(child)){
                    open.add(child);
                    child.pathCost = node.pathCost + 1;
                    if (child.parent == null)
                        child.parent = node;
                }else if (child.calcCost(endPoint) < node.calcCost(endPoint)){
                    if (!open.contains(child)){
                        open.add(child);
                        child.pathCost = node.pathCost + 1;
                        if (child.parent == null)
                            child.parent = node;
                    }
                }
            }
        }
        throw new RuntimeException("FAILED");
    }

    private Node pointToNode(List<Node> nodes, Point p){
        for (Node n : nodes)
            if (n.point.equals(p))
                return n;
        return null;
    }

    private Node getClosestToGoal(List<Node> possibilities){
        Node best = null;
        double value = Double.MAX_VALUE;
        for (Node n : possibilities){
            double pval = n.calcCost(endPoint);
            if (pval <= value){
                best = n;
                value = pval;
            }
        }
        return best;
    }

     class PQsort implements Comparator<Node> {

         Point end;

         public PQsort(Point end){
             this.end = end;
         }

        public int compare(Node one, Node two) {
            return (int) (two.calcCost(end) - one.calcCost(end));
        }
    }

}