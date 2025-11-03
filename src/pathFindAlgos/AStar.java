package pathFindAlgos;

import enums.CellTypeAlgo;
import enums.CellTypeTerrain;
import settings.GlobalSettings;


import frameMapComponents.MapNode;
import frameMapComponents.MapPanel;

import javax.swing.*;
import java.util.Arrays;
import java.util.PriorityQueue;

import static utils.utilityMix.logExecute;

public class AStar {

    // --- --- --- A Star Algorithm Setup --- --- ---
    public static PriorityQueue<MapNode> openPrioQueue =
            new PriorityQueue<>((node1, node2) -> {
                if (node1.fCost != node2.fCost)
                    return Integer.compare(node1.fCost, node2.fCost);
                return Integer.compare(node1.hCost, node2.hCost);
            });
    //public static ArrayList<MapNode> checkedList = new ArrayList<>();
    public static int step = 0;
    public static boolean targetReached = false;

    // --- --- --- A Star Methods --- --- ---
    // Writes costs of the cell on the cell
    public static void setInitialCostOnNodes(MapNode[][] nodeMatrix){
        logExecute("SET INITIAL COST ON NODES EXECUTED");
        int c = 0, r = 0;

        while (c < nodeMatrix.length && r < nodeMatrix[0].length) {
            calculateInitialCost(nodeMatrix[c][r], MapPanel.targetNode);
            c++;
            if (c == nodeMatrix.length) {
                c = 0;
                r++;
            }
        }
    }

    // Calculated initial costs
    public static void calculateInitialCost(MapNode mapNode, MapNode targetNode){
        logExecute("GET INITIAL COST EXECUTED");

        // Calculate H Cost
        int xDistance = Math.abs(mapNode.column - targetNode.column);
        int yDistance = Math.abs(mapNode.row - targetNode.row);
        mapNode.hCost = (int) (10 * (Math.max(xDistance, yDistance) + Math.min(xDistance, yDistance) * (Math.sqrt(2) - 1)));
        // Calculate G Cost
        //mapNode.gCost = 0;
        mapNode.gCost = Integer.MAX_VALUE;
        // Calculate F Cost
        mapNode.fCost = Integer.MAX_VALUE;

        if (mapNode.cellTypeTerrain != CellTypeTerrain.START && mapNode.cellTypeTerrain != CellTypeTerrain.TARGET) {
            mapNode.setText("<html>F:" + mapNode.fCost + "<br>G:" + mapNode.gCost + "<br>H:" + mapNode.hCost + "</html>");
        }
    }

    // Change cell type to FOCUS (aka open) --- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---

    // focused on an empty cell open = FOCUS
    public static void focusNode (MapNode currentNode, MapNode newFocusNode, int possibleNewGCost) {

        if (newFocusNode.cellTypeTerrain == CellTypeTerrain.WALL || newFocusNode.cellTypeAlgo == CellTypeAlgo.CHECKED) {
            return; // skip walls or already closed
        }

        // found a shorter path?
        if (possibleNewGCost < newFocusNode.gCost) {
            newFocusNode.parent = currentNode;
            newFocusNode.gCost = possibleNewGCost;
            newFocusNode.fCost = newFocusNode.gCost + newFocusNode.hCost;

            // mark as open if not already
            if (newFocusNode.cellTypeAlgo != CellTypeAlgo.FOCUS) {
                newFocusNode.setAsFocus();
            }

            // refresh queue ordering
            openPrioQueue.remove(newFocusNode);
            openPrioQueue.add(newFocusNode);
        }
    }

    // Searching a path
    public static void autoSearchPath (MapNode currentNode){
        logExecute("autoSearchPath Method Initiated");

        // prepare start
        openPrioQueue.clear();
        targetReached = false;
        step = 0;

        // ensure costs were initialized previously
        currentNode.gCost = 0;
        currentNode.fCost = currentNode.hCost; // g + h = 0 + h
        currentNode.setAsFocus(); // Set as open node
        openPrioQueue.add(currentNode);

        while (!openPrioQueue.isEmpty() && !targetReached) {
            MapNode current = openPrioQueue.poll(); // best node by comparator

            if (current == null) break; // Null case break

            if (current.cellTypeAlgo == CellTypeAlgo.CHECKED) continue;

            current.setText("<html>F:" + current.fCost + "<br>G:" + current.gCost + "<br>H:" + current.hCost + "</html>");

            System.out.println("Now looking at node at row n" + current.row + " column n" + current.column);

            if (current.cellTypeTerrain == CellTypeTerrain.TARGET) {
                System.out.println("Searched and found a new path");
                targetReached = true;
                trackThePath();
                break;
            }

            // Set as checked
            current.setAsChecked();

            // Setup to find next nodes to check
            int c = current.column;
            int r = current.row;

            // neighbor offsets (4 straight + 4 diagonal) and costs
            int[][] dirs = {
                    {0, -1}, {-1, 0}, {0, 1}, {1, 0},
                    {-1, -1}, {-1, 1}, {1, -1}, {1, 1}
            };
            int[] costs = {10, 10, 10, 10, 14, 14, 14, 14};

            for (int i = 0; i < dirs.length; i++) {
                int nc = c + dirs[i][0];
                int nr = r + dirs[i][1];

                // bounds check if it is out of map (use global dimensions)
                if (nc < 0 || nr < 0 || nc >= GlobalSettings.columns_count || nr >= GlobalSettings.rows_count)
                    continue;

                // diagonal corner-cut prevention:
                if (Math.abs(dirs[i][0]) == 1 && Math.abs(dirs[i][1]) == 1) {
                    // if both adjacent orthogonal neighbors are walls, skip diagonal
                    if (MapPanel.nodeMatrix[nc][r].cellTypeTerrain == CellTypeTerrain.WALL && MapPanel.nodeMatrix[c][nr].cellTypeTerrain == CellTypeTerrain.WALL) {
                        continue;
                    }
                }

                MapNode neighbor = MapPanel.nodeMatrix[nc][nr];

                // skip walls or already closed nodes
                if (neighbor.cellTypeTerrain == CellTypeTerrain.WALL || neighbor.cellTypeAlgo == CellTypeAlgo.CHECKED)
                    continue;


                int tentativeG = current.gCost + costs[i];
                System.out.println("Calculated " + tentativeG + " for direction " + i + " which is " + Arrays.toString(dirs[i]) + " when curr g cost is " + current.gCost);

                // focusNode handles adding/updating the open set
                focusNode(current, neighbor, tentativeG);

            }



        }

    }
    public static void trackThePath() {
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


