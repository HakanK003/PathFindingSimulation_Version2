package settings;

import enums.ClickType;

public class GlobalSettings {
    // Log info
    public static int logCounter = 1;

    // Cell general information
    public static final int columns_count = 13;
    public static final int rows_count = 9;
    public static final int nodeSize = 100;

    // Cell limitations info
    public static int robotCount = 0;
    public static int startCellCount = 0;
    public static int targetCellCount = 0;

    // Click Type info
    public static ClickType clickType = ClickType.NORMAL;

    // Menu information
    public static final int menuWidth = 300;
    public static int buttonHeight = 300;
    public static int buttonWidth = 250;
    public static int buttonCount = 15;

    public static String [] buttonTexts = new String[] {

            // Map settings (3)
            "SaveMap", "UseSavedMap","NewMap",
            // Choose cell types (click types) (6)
            "Close", "Wall", "Empty","Start", "Target", "Unknown",
            // Simulation Settings (3)
            "Save", "FindPath", "Reset",
            //Robot Settings (2)
            "RobotStart", "SensorSet",
            // Algo Show Options (1)
            "AlgoSet"
    };
    // Show cell info
    // Show cell costs
    // Robot Display On/Off
    // Sensor count
    // Auto pilot with time frame

    public static int robotStepSpeed = 1; // 1 second
    public static int robotSize = nodeSize / 2;
}
