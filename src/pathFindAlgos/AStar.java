package pathFindAlgos;

import enums.CellTypeAlgo;
import enums.CellTypeTerrain;
import settings.GlobalSettings;


import frameMapComponents.MapNode;
import frameMapComponents.MapPanel;

import java.util.ArrayList;

import static utils.utilityMix.logExecute;

public class AStar {

    public AStar (){

    }
    // --- --- --- A Star Algorithm Setup --- --- ---
    public static ArrayList<MapNode> focusList = new ArrayList<>();
    public static ArrayList<MapNode> checkedList = new ArrayList<>();
    public static int step = 0;
    public static boolean targetReached = false;

    // --- --- --- A Star Methods --- --- ---
    // Writes costs of the cell on the cell
    public static void setInitialCostOnNodes(MapNode[][] nodeMatrix){
        System.out.println("SET INITIAL COST ON NODES EXECUTED");
        int c = 0, r = 0;

        while (c < nodeMatrix.length && r < nodeMatrix[0].length) {
            getInitialCost(nodeMatrix[c][r], MapPanel.targetNode);
            c++;
            if (c == nodeMatrix.length) {
                c = 0;
                r++;
            }
        }
    }

    public static void getInitialCost(MapNode mapNode, MapNode targetNode){
        System.out.println("GET INITIAL COST EXECUTED");

        // Calculate H Cost
        int xDistance = Math.abs(mapNode.column - targetNode.column);
        int yDistance = Math.abs(mapNode.row - targetNode.row);
        mapNode.hCost = (int) (10 * (Math.max(xDistance, yDistance) + Math.min(xDistance, yDistance) * (Math.sqrt(2) - 1)));
        // Calculate G Cost
        mapNode.gCost = 0;
        // Calculate F Cost
        mapNode.fCost = Integer.MAX_VALUE;

        if (mapNode.cellTypeTerrain != CellTypeTerrain.START && mapNode.cellTypeTerrain != CellTypeTerrain.TARGET) {
            mapNode.setText("<html>F:" + mapNode.fCost + "<br>G:" + mapNode.gCost + "<br>H:" + mapNode.hCost + "</html>");
        }
    }


    // Change cell type to FOCUS (aka open) --- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---

    // focused on an empty cell open = FOCUS
    public static void focusNode (MapNode currentNode, MapNode newFocusNode, int possibleNewGCost) {
        if (newFocusNode.cellTypeAlgo != CellTypeAlgo.FOCUS && newFocusNode.cellTypeAlgo != CellTypeAlgo.CHECKED && newFocusNode.cellTypeTerrain != CellTypeTerrain.WALL) {

            newFocusNode.setAsFocus();

            //int newGCost = getNewGCost(newFocusNode);
            if (possibleNewGCost < newFocusNode.gCost || newFocusNode.gCost == 0) {
                newFocusNode.gCost = possibleNewGCost;
                newFocusNode.fCost = newFocusNode.gCost + newFocusNode.hCost;
                newFocusNode.setText("<html>F:" + newFocusNode.fCost + "<br>G:" + newFocusNode.gCost + "<br>H:" + newFocusNode.hCost + "</html>");
            }

            newFocusNode.parent = currentNode;
            focusList.add(newFocusNode);
        }
    }


    // Auto Search for A Star Algo --- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---
    public static void autoSearch(MapNode currentNode) {
        logExecute("autoSearch Method");

        while (!targetReached) {// && step < 10000
            int c = currentNode.column;
            int r = currentNode.row;

            currentNode.setAsChecked();
            //checkedList.add(currentNode);
            focusList.remove(currentNode);

            // 4 Direction Check
            // Focus the upper cell if there is one
            if (r - 1 > -1)
                focusNode(currentNode, MapPanel.nodeMatrix[c][r - 1], currentNode.gCost + 10);
            // Focus the left cell if there is one
            if (c - 1 > -1)
                focusNode(currentNode, MapPanel.nodeMatrix[c - 1][r], currentNode.gCost + 10);
            // Focus the down cell if there is one
            if (r + 1 < GlobalSettings.rows_count)
                focusNode(currentNode, MapPanel.nodeMatrix[c][r + 1], currentNode.gCost + 10);
            // Focus the right cell if there is one
            if (c + 1 < GlobalSettings.columns_count)
                focusNode(currentNode, MapPanel.nodeMatrix[c + 1][r], currentNode.gCost + 10);

//            // * Direction Check (+4)
//            // Focus the upper-left cell if there is one
//            if (r - 1 > -1 && c - 1 > -1 && !(nodeMatrix[c - 1][r].cellTypeTerrain == CellTypeTerrain.WALL && nodeMatrix[c][r - 1].cellTypeTerrain == CellTypeTerrain.WALL))
//                focusNode(currentNode, nodeMatrix[c - 1][r - 1], currentNode.gCost + 14);
//            // Focus the down-left cell if there is one
//            if (r + 1 < rows && c - 1 > -1 && !(nodeMatrix[c - 1][r].cellTypeTerrain == CellTypeTerrain.WALL && nodeMatrix[c][r + 1].cellTypeTerrain == CellTypeTerrain.WALL))
//                focusNode(currentNode, nodeMatrix[c - 1][r + 1], currentNode.gCost + 14);
//            // Focus the upper-right cell if there is one
//            if (r - 1 > -1 && c + 1 < columns && !(nodeMatrix[c + 1][r].cellTypeTerrain == CellTypeTerrain.WALL && nodeMatrix[c][r - 1].cellTypeTerrain == CellTypeTerrain.WALL))
//                focusNode(currentNode, nodeMatrix[c + 1][r - 1], currentNode.gCost + 14);
//            // Focus the down-right cell if there is one
//            if (r + 1 < rows && c + 1 < columns && !(nodeMatrix[c + 1][r].cellTypeTerrain == CellTypeTerrain.WALL && nodeMatrix[c][r + 1].cellTypeTerrain == CellTypeTerrain.WALL))
//                focusNode(currentNode, nodeMatrix[c + 1][r + 1], currentNode.gCost + 14);

            // Find the best cell
            int bestNodeIndex = 0;
            int bestNodeFCost = Integer.MAX_VALUE;

            for (int i = 0; i < focusList.size(); i++) {
                // Check if this node's F cost is lower
                if (focusList.get(i).fCost < bestNodeFCost) {
                    bestNodeIndex = i;
                    bestNodeFCost = focusList.get(i).fCost;
                } else if (focusList.get(i).fCost == bestNodeFCost) {
                    // IF F cost are same check min G cost
                    if (focusList.get(i).gCost < focusList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            System.out.println("Evaluated another node " + step++);
            // After the loop we have next step cell
            currentNode = focusList.get(bestNodeIndex);
            //currentNode.cellType == CellType.TARGET
            if (currentNode.cellTypeTerrain == CellTypeTerrain.TARGET) {
                targetReached = true;
                trackThePath();
            }
            //step++;
        }

        System.out.println("Searched and found a new path");


//        Robot robot = new Robot(startNode);
//
//        while (robot.currentCell.cellTypeTerrain != CellTypeTerrain.TARGET) {
//            robot.goNextOnPath();
//        }

    }


    public static void trackThePath () {
        logExecute("trackThePath Method");

        MapNode current = MapPanel.targetNode;

        while (current != MapPanel.startNode) {
            current = current.parent;
            if (current !=  MapPanel.startNode) {
                current.setAsPath();
            }
        }
    }
}
