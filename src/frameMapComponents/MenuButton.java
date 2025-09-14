package frameMapComponents;

import enums.ClickType;
import settings.GlobalSettings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static pathFindAlgos.AStar.*;
import static utils.utilityMix.textIsATerrainType;
import static utils.utilityMix.textIsATerrainType;

public class MenuButton  extends JButton implements ActionListener {
    static ClickType clickType = ClickType.NORMAL;
    MenuButton(){
        addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the button that clicked
        JButton clickedButton = (JButton) e.getSource();

        // Get the text of that button
        String buttonText = clickedButton.getText().toUpperCase();
        if (textIsATerrainType(buttonText)){
            clickType = ClickType.valueOf(clickedButton.getText().toUpperCase());
        } else if (buttonText.equals("ALGOSET")){
            setInitialCostOnNodes(MapPanel.nodeMatrix);
        } else if (buttonText.equals("ROBOTSTART")){
            /// Implement Robot Start Progress
        }else if (buttonText.equals("FINDPATH")){
            autoSearch(MapPanel.currentNode);
        }
        else {
            System.out.println("USER INPUT BOX REQUEST");
            ///  Implement a user input box system
        }


        // Print the log of the action
        System.out.println("Log for action #" + GlobalSettings.logCounter++);
        System.out.println("Clicked to " + buttonText);
        System.out.println("Click type set to " + clickType);
        System.out.println("--- --- --- /// --- --- --- /// --- --- --- /// --- --- ---");
    }

}
