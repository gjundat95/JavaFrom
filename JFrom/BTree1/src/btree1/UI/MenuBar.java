package btree1.UI;

import javax.swing.*;

// Referenced classes of package UI:
//            MainFrame

public class MenuBar extends JMenuBar
{

    private static final long serialVersionUID = 0x9f74266b54daabd1L;
    private MainFrame parent;

    public MenuBar(MainFrame frame)
    {
        parent = frame;
        String menuName = "";
        JMenuBar menuBar = new JMenuBar();
        menuName = "File";
        String fileFunctions[] = {
            "Exit"
        };
        JMenu mfile = buildMenu(menuName, fileFunctions);
        menuName = "Help";
        String helpFunctions[] = {
            "About"
        };
        JMenu mhelp = buildMenu(menuName, helpFunctions);
        menuBar.add(mfile);
        menuBar.add(mhelp);
        parent.setJMenuBar(menuBar);
    }

    public JMenu buildMenu(String menuName, String functions[])
    {
        JMenu menu = new JMenu(menuName);
        for(int i = 0; i < functions.length; i++)
        {
            JMenuItem item = new JMenuItem(functions[i]);
            item.addActionListener(parent.menuAL);
            item.setActionCommand(functions[i]);
            menu.add(item);
        }

        return menu;
    }
}
