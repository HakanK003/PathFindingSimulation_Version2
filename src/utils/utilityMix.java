package utils;

import settings.GlobalSettings;

public class utilityMix {

    private utilityMix(){

    }

    public static boolean textIsATerrainType(String buttonText){
        String[] clickTypes = new String[] {"CLOSE","EMPTY","WALL","UNKNOWN","START","TARGET","ROBOT","NORMAL"};
        for (String str : clickTypes) {
            if(str.equals(buttonText)) return true;
        }
        return false;
    }

    public static void logExecute(String logText){
        System.out.println("--- --- --- Log #" + GlobalSettings.logCounter++ + " --- --- ---");
        System.out.println("Executed " + logText);
        System.out.println("--- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---");
    }
    public static void logExecute(String logText, String expectedThing){
        System.out.println("--- --- --- Log #" + GlobalSettings.logCounter++ + " --- --- ---");
        System.out.println("Executed " + logText);
        System.out.println("Expected " + expectedThing);
        System.out.println("--- --- --- ||| --- --- --- ||| --- --- --- ||| --- --- ---");
    }
}
