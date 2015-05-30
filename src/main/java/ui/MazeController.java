package ui;

import pathfinding.Maze;
import pathfinding.util.TileState;
import ui.util.PlaceMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MazeController extends JFrame implements ActionListener{

    MazeBuildView buildView;
    MazeSolveView solveView;
    PathfinderThread pfthreadHandler;

    public JComboBox stepDelayList;

    public MazeController(MazeBuildView buildView, MazeSolveView solveView){
        this.buildView = buildView;
        this.solveView = solveView;

        Container pane = getContentPane();
        pane.setLayout(new GridLayout(1, 4));

        JLabel modeLabel = new JLabel("   Modes");
        pane.add(modeLabel);

        for (PlaceMode placeMode : PlaceMode.values()){
            JButton button = new JButton(placeMode.name());
            button.addActionListener(this);
            button.putClientProperty("changeMode", placeMode);
            pane.add(button);
        }

        JButton allclear = new JButton("All Clear");
        allclear.addActionListener(this);
        allclear.putClientProperty("action", "all_clear");
        pane.add(allclear);

        JButton allblocked = new JButton("All Blocked");
        allblocked.addActionListener(this);
        allblocked.putClientProperty("action", "all_blocked");
        pane.add(allblocked);

        JLabel controlsLabel = new JLabel("   |   Controls");
        pane.add(controlsLabel);

        JButton begin = new JButton("Begin");
        begin.addActionListener(this);
        begin.putClientProperty("action", "begin");
        pane.add(begin);

        JButton stop = new JButton("Stop");
        stop.addActionListener(this);
        stop.putClientProperty("action", "stop");
        pane.add(stop);


        JLabel stepLabel = new JLabel("   |   Step Delay");
        pane.add(stepLabel);

        stepDelayList = new JComboBox(new String[] { "1", "5", "50", "100", "150", "250", "500", "1000" });
        stepDelayList.setSelectedIndex(3);
        pane.add(stepDelayList);

        JButton save = new JButton("Save");
        save.addActionListener(this);
        save.putClientProperty("action", "save");
        pane.add(save);

        JButton load = new JButton("Load");
        load.addActionListener(this);
        load.putClientProperty("action", "load");
        pane.add(load);
    }

    public void actionPerformed(ActionEvent e) {
        JButton source = ((JButton) e.getSource());
        if (source.getClientProperty("changeMode") != null) {
            try {
                buildView.mode = (PlaceMode) source.getClientProperty("changeMode");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else if (source.getClientProperty("action") != null){
            String action = (String) source.getClientProperty("action");
            if (action.equalsIgnoreCase("begin")){
                solveView.setVisible(true);
                buildView.setVisible(false);
                this.pfthreadHandler = new PathfinderThread(stepDelayList, solveView, buildView.maze, 250);
                this.pfthreadHandler.thread.start();
            }else if (action.equalsIgnoreCase("stop")){
                this.pfthreadHandler.thread.stop();
                buildView.setVisible(true);
                solveView.setVisible(false);
            }else if (action.equalsIgnoreCase("all_clear")){
                buildView.maze.clearMaze(TileState.OPEN);
            }else if (action.equalsIgnoreCase("all_blocked")){
                buildView.maze.clearMaze(TileState.OBSTRUCTED);
            }else if (action.equalsIgnoreCase("save")){
                JFileChooser chooser= new JFileChooser();
                int choice = chooser.showOpenDialog(getParent());
                if (choice != JFileChooser.APPROVE_OPTION) return;
                File chosenFile = chooser.getSelectedFile();
                try {
                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(chosenFile));
                    oos.writeObject(buildView.maze);
                    oos.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }else if (action.equalsIgnoreCase("load")){
                JFileChooser chooser= new JFileChooser();
                int choice = chooser.showOpenDialog(getParent());
                if (choice != JFileChooser.APPROVE_OPTION) return;
                File chosenFile = chooser.getSelectedFile();
                try {
                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chosenFile));
                    buildView.maze = (Maze) ois.readObject();
                    ois.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }

        }
    }
}
