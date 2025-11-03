package enums;

import java.awt.*;

public enum CellTypeAlgo {
    // Highlighting the cell after evaluating
    NOTHING(Color.white),
    FOCUS(Color.red), // Focus means it is in open priority queue
    CHECKED(Color.cyan), // Checked means it is in closed list and this is node is invalid
    PATH(Color.blue), // Path means it is correct node
    PASSEDPATH(Color.orange), // After the robot starts moving cells passed are updated to this

    // Robot or sensors are on this cell
    ROBOT(Color.magenta),
    SENSORS(Color.yellow);

    public final Color color;

    CellTypeAlgo(Color color){
        this.color = color;
    }
}
