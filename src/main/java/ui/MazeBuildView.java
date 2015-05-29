package ui;

import pathfinding.Maze;
import pathfinding.util.Point;
import pathfinding.util.TileState;
import ui.util.PlaceMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class MazeBuildView extends JFrame implements ActionListener{

    Maze maze;
    Map<JButton, Point> buttonMap;

    PlaceMode mode;

    public MazeBuildView(int rows, int cols) {
        Container pane = getContentPane();

        pane.setLayout(new GridLayout(rows, cols));

        buttonMap = new HashMap<JButton, Point>();
        maze = new Maze();

        maze.clearMaze(TileState.OPEN);

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                JButton button = new JButton(" ");
                button.addActionListener(this);
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

    public void update() {
        for (Map.Entry<JButton, Point> entry : buttonMap.entrySet()) {
            entry.getKey().setBackground(maze.getTile(entry.getValue()).getState().color);
        }
    }

    public void actionPerformed(ActionEvent e) {
        this.changeTile(e);
    }

    private void changeTile(ActionEvent e){
        Point point = buttonMap.get(e.getSource());
        if (point == null || mode == null)
            return;
        switch (mode){
            case CLEAR:
                maze.setTileState(point, TileState.OPEN);
                break;
            case START:
                maze.setTileState(point, TileState.START);
                break;
            case END:
                maze.setTileState(point, TileState.END);
                break;
            case BARRIER:
                maze.setTileState(point, TileState.OBSTRUCTED);
                break;
        }
    }
}
