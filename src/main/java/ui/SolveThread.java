package ui;

import pathfinding.Maze;
import pathfinding.Pathfinder;

import javax.swing.*;

public class SolveThread {

    JComboBox delayList;
    MazeSolveView solveView;
    Thread thread;
    Maze keyMaze;
    long stepTime;

    public SolveThread(JComboBox delayList, MazeSolveView solveView, Maze keyMaze, long stepTime){
        this.delayList = delayList;
        this.solveView = solveView;
        this.keyMaze = keyMaze;
        this.stepTime = stepTime;

        thread = new Thread(new SolveRunnable());
    }

    class SolveRunnable implements Runnable {

        Pathfinder finder;

        public SolveRunnable(){
            finder = new Pathfinder(delayList, keyMaze);
        }

        public void run(){
            solveView.path = finder.getPath(solveView);
            DebugView.d("Path is " + solveView.path.size() + " tiles long.");
        }

    }


}
