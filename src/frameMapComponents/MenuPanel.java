package frameMapComponents;

import enums.ClickType;
import settings.GlobalSettings;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel{
    public final int menuWidth = GlobalSettings.menuWidth;
//    public final int buttonHeight = GlobalSettings.buttonHeight;
//    public final int buttonWidth = GlobalSettings.buttonWidth;

    JButton[] MenuButtonBarArray = new JButton[GlobalSettings.buttonCount];

    //static ClickType clickType = ClickType.NORMAL;
    public MenuPanel(){

        this.setPreferredSize(new Dimension(menuWidth, GlobalSettings.rows_count * GlobalSettings.nodeSize));
        this.setBackground(Color.green);
        //this.setPreferredSize(new Dimension(settings.GlobalSettings.menuWidth, settings.GlobalSettings.));

        JPanel buttonPanel = new JPanel(new GridLayout(GlobalSettings.buttonCount/3, GlobalSettings.buttonCount));

        for (int i = 0; i < GlobalSettings.buttonCount; i++) {
            MenuButtonBarArray[i] = new MenuButton();
            MenuButtonBarArray[i].setSize(GlobalSettings.buttonWidth/5, GlobalSettings.buttonHeight/5);
            MenuButtonBarArray[i].setText(GlobalSettings.buttonTexts[i]);
            buttonPanel.add(MenuButtonBarArray[i]);
        }

        this.add(buttonPanel);

    }
}
