package frameMapComponents;

import settings.GlobalSettings;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MapPanel extends JPanel {

    // Global Configuration --- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---
    public final int columns = GlobalSettings.columns_count;
    public final int rows = GlobalSettings.rows_count;
    public final int nodeSize = GlobalSettings.nodeSize;
    public final int screenWidth = nodeSize * columns;
    public final int screenHeight = nodeSize * rows;

    // Map Configuration --- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---
    // Whole Map
    public static MapNode [][] nodeMatrix = new MapNode[GlobalSettings.columns_count][GlobalSettings.rows_count];

    // Node Configuration --- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---
    /// Delete When Ready
    // Start, Target, Current, Robot Node
    public static MapNode startNode, targetNode, currentNode, robotNode;

    // Algorithm Configuration --- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---
    // A Star Algorithm Setup
    static ArrayList<MapNode> focusList = new ArrayList<>();
    ArrayList<MapNode> checkedList = new ArrayList<>();
    int step = 0;
    static boolean targetReached = false;



    // components.MapPanel Constructor --- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---
    public MapPanel(){

        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.blue);
        this.setLayout(new GridLayout(rows,columns));

        // Set Key Listener
        //this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < columns; c++) {
                nodeMatrix[c][r] = new MapNode (c,r);
                this.add(nodeMatrix[c][r]);
            }
        }

        // set start and end node manually
//        setStartNode(44,36);
//        setTargetNode(2, 2);
//
        //setInitialCostOnNodes(nodeMatrix);

    }

}
