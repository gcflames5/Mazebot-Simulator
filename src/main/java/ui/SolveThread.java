package ui;

import pathfinding.Maze;
import pathfinding.Pathfinder;
import pathfinding.util.Node;

import javax.swing.*;
import java.util.List;

public class SolveThread {

    JComboBox delayList;
    Thread thread;
    Maze keyMaze;

    public SolveThread(JComboBox delayList, Maze keyMaze){
        this.delayList = delayList;
        this.keyMaze = keyMaze;

        thread = new Thread(new SolveRunnable());
    }

    class SolveRunnable implements Runnable {

        Pathfinder finder;

        public SolveRunnable(){
            finder = new Pathfinder(keyMaze.getStart(), keyMaze.getEnd(), keyMaze, delayList);
        }

        public void run(){
            List<Node> finalPath = finder.getPath();
            DebugView.d("Path is " + finalPath.size() + " tiles long.");
        }

    }


}
