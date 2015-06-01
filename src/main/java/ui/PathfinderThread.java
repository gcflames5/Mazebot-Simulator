package ui;

import pathfinding.Maze;
import pathfinding.Explorer;

import javax.swing.*;

public class PathfinderThread {

    JComboBox delayList;
    MazeSolveView solveView;
    Thread thread;
    Maze keyMaze;
    long stepTime;

    public PathfinderThread(JComboBox delayList, MazeSolveView solveView, Maze keyMaze, long stepTime){
        this.delayList = delayList;
        this.solveView = solveView;
        this.keyMaze = keyMaze;
        this.stepTime = stepTime;

        thread = new Thread(new PathfinderRunnable());
    }

    class PathfinderRunnable implements Runnable {

        Explorer explorer;

        public PathfinderRunnable(){
            explorer = new Explorer(new Maze(), keyMaze);
        }

        public void run(){
            while(explorer.step()) {
                solveView.maze = explorer.maze;
                try {
                    Thread.sleep(Integer.valueOf(delayList.getSelectedItem().toString()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //explorer.cleanup();
            SolveThread sThread = new SolveThread(delayList, solveView, keyMaze, stepTime);
            sThread.thread.start();
        }

    }


}
