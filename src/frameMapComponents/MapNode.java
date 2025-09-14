package frameMapComponents;

import enums.CellTypeAlgo;
import enums.CellTypeTerrain;
import settings.GlobalSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pathFindAlgos.AStar.setInitialCostOnNodes;
import static utils.utilityMix.textIsATerrainType;

public class MapNode extends JButton implements ActionListener {
    // Track previous node to trace path
    public MapNode parent;
    // Position of node in grid
    public int column, row;
    // Calculated variables for A Star algo
    public int gCost, hCost, fCost;
    // Terrain type independent of algo such as Empty, Wall, Unknown, Start, Target etc.
    public CellTypeTerrain cellTypeTerrain;
    // Algo related info Visited, Focus, Path, Nothing etc.
    public CellTypeAlgo cellTypeAlgo;

    public MapNode(int column, int row){
        this.column = column;
        this.row = row;
        this.cellTypeTerrain = CellTypeTerrain.EMPTY;
        this.cellTypeAlgo = CellTypeAlgo.NOTHING;

        setBackground(Color.white);
        setForeground(Color.black);

        setText("C-" + column + "| R-" + row);
        addActionListener(this);
    }

    // Setting node type --- --- --- /// --- --- --- /// --- --- --- /// --- --- ---

    // Set a node as Start node
    public void setAsStart(MapNode selectedNode){
        setBackground(CellTypeTerrain.START.color);
        setText("Start");
        cellTypeTerrain = CellTypeTerrain.START;
        gCost = 0;
        MapPanel.startNode = selectedNode;
        MapPanel.currentNode = MapPanel.startNode;
    }
    // Set a node as Target node
    public void setAsTarget(MapNode selectedNode){
        setBackground(CellTypeTerrain.TARGET.color);
        setText("Target");
        cellTypeTerrain = CellTypeTerrain.TARGET;
        MapPanel.targetNode = selectedNode;
    }

    public void setCellAs (String providedCellType, MapNode selectedNode){
        if (textIsATerrainType(providedCellType)){
            if(providedCellType.equals("START")){
                setAsStart(selectedNode);
            } else if (providedCellType.equals("TARGET")) {
                setAsTarget(selectedNode);
            } else {
                setBackground(CellTypeTerrain.valueOf(providedCellType).color);
                setText(providedCellType);
                cellTypeTerrain = CellTypeTerrain.valueOf(providedCellType);
            }
        }
    }
    // --- --- --- Setting CellTypeAlgo methods --- --- --- /// --- --- --- /// --- --- --- /// --- --- ---

    // open = FOCUS
    public void setAsFocus() {
        cellTypeAlgo = CellTypeAlgo.FOCUS;
        //setBackground(CellTypeAlgo.FOCUS.color);
    }
    public void setAsChecked () {
        if (cellTypeTerrain != CellTypeTerrain.START && cellTypeTerrain != CellTypeTerrain.TARGET) {
            cellTypeAlgo = CellTypeAlgo.CHECKED;
            setBackground(CellTypeAlgo.CHECKED.color);
        }
    }

    public void setAsPath () {
        setBackground(CellTypeAlgo.PATH.color);
        cellTypeAlgo = CellTypeAlgo.PATH;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String clickTypeAsStr = String.valueOf(MenuButton.clickType);
        MapNode clickedNodeButton = (MapNode)e.getSource();
        String buttonStrBeforeClick = clickedNodeButton.getText();

        switch (clickTypeAsStr) {
            case "CLOSE", "NORMAL":
                return;
            case "EMPTY":
                {
                    if (buttonStrBeforeClick.equals("START")) {
                        GlobalSettings.startCellCount--;
                        MapPanel.startNode = null;
                    }else if (buttonStrBeforeClick.equals("TARGET")) {
                        GlobalSettings.targetCellCount--;
                        MapPanel.targetNode = null;
                    }else if (buttonStrBeforeClick.equals("ROBOT")) {
                        GlobalSettings.robotCount--;
                    }
                }
                break;
            case "WALL":
                {
                    clickedNodeButton.cellTypeTerrain = CellTypeTerrain.WALL;
                    clickedNodeButton.setBackground(CellTypeTerrain.WALL.color);
                    System.out.println();
                }
            case "ROBOT":
                if (GlobalSettings.robotCount++ == 0) {
                    setCellAs(clickTypeAsStr, clickedNodeButton);
                } else {
                    System.out.println("Log for action #" + GlobalSettings.logCounter++ + "\nThere is already a robot cell\n--- --- --- /// --- --- --- /// --- --- --- /// --- --- ---");
                    return;
                }
                break;
            case "START":
                if (GlobalSettings.startCellCount++ == 0) {
                    setCellAs(clickTypeAsStr, clickedNodeButton);
//                    MapPanel.startNode = MapPanel.nodeMatrix[column][row];
//                    MapPanel.currentNode = MapPanel.startNode;
                } else {
                    System.out.println("Log for action #" + GlobalSettings.logCounter++ + "\nThere is already a start cell\n--- --- --- /// --- --- --- /// --- --- --- /// --- --- ---");
                    return;
                }
                break;
            case "TARGET":
                if (GlobalSettings.targetCellCount++ == 0) {
                    setCellAs(clickTypeAsStr, clickedNodeButton);
                    //MapPanel.targetNode = MapPanel.nodeMatrix[column][row];
                } else {
                    System.out.println("Log for action #" + GlobalSettings.logCounter++ + "\nThere is already a target cell\n--- --- --- /// --- --- --- /// --- --- --- /// --- --- ---");
                    return;
                }
                break;

        }

        // Setting Cells New Type
        setCellAs(clickTypeAsStr, clickedNodeButton);

        // Print the log of the action
        System.out.println("Log for action #" + GlobalSettings.logCounter++);
        System.out.println("Clicked on cell in column " + column + " row " + row);
        System.out.println("Tried to set cell value to " + cellTypeTerrain);
        System.out.println("Click type was " + MenuButton.clickType);
        System.out.println("--- --- --- /// --- --- --- /// --- --- --- /// --- --- ---");
    }
}
