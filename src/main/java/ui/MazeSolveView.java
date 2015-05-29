package ui;

import pathfinding.Maze;
import pathfinding.util.Node;
import pathfinding.util.Point;
import ui.util.PlaceMode;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MazeSolveView extends JFrame{

    Maze maze;
    Map<JButton, Point> buttonMap;

    PlaceMode mode;
    public List<Node> path;

    public MazeSolveView(int rows, int cols) {
        Container pane = getContentPane();

        pane.setLayout(new GridLayout(rows, cols));

        buttonMap = new HashMap<JButton, Point>();
        maze = new Maze();

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                JButton button = new JButton(" ");
                pane.add(button);
                buttonMap.put(button, new Point(x, y));
            }
        }

        Thread t = new Thread(new Runnable() {
            public void run() {
                while (true){
                    update();
                    try { Thread.sleep(50); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        });
        t.start();
    }

    public void update(){
        for (Map.Entry<JButton, Point> entry : buttonMap.entrySet()) {
            entry.getKey().setBackground(maze.getTile(entry.getValue()).getState().color);
            if (path != null){
                /*for (int i = 0; i < path.size(); i++){
                    if (entry.getValue().equals(path.get(i).point))
                        entry.getKey().setBackground(Color.MAGENTA);
                }*/
                for (Node n : path){
                    if (entry.getValue().equals(n.point))
                        entry.getKey().setBackground(Color.MAGENTA);
                }
            }
        }
    }

}
