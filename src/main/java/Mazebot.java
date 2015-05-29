import pathfinding.util.Point;
import ui.MazeBuildView;
import ui.MazeController;
import ui.MazeSolveView;

import javax.swing.*;

public class Mazebot {

    public static void main(String[] args){

        MazeBuildView gt = new MazeBuildView(15, 15);
        gt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gt.pack();
        gt.setLocationRelativeTo(null);
        gt.setVisible(true);

        MazeSolveView sv = new MazeSolveView(15, 15);
        sv.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sv.pack();
        sv.setLocationRelativeTo(null);
        sv.setVisible(false);


        MazeController mc = new MazeController(gt, sv);
        mc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mc.pack();
        mc.setVisible(true);
    }

}
