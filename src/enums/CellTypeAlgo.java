package enums;

import java.awt.*;

public enum CellTypeAlgo {
    // Highlighting the cell after evaluating
    NOTHING(Color.white),
    FOCUS(Color.red),
    CHECKED(Color.cyan),
    PATH(Color.blue),
    PASSEDPATH(Color.orange),

    // Robot or sensors are on this cell
    ROBOT(Color.magenta),
    SENSORS(Color.yellow);

    final Color color;

    CellTypeAlgo(Color color){
        this.color = color;
    }
}
