package btree.gr;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;

public class applet extends JPanel
{

    String lang;
   // main BSTm;
    main Bm;
   // JButton b;
    JTabbedPane tabs;
    //ResourceBundle all_msgs[];
    //int current_lang;
   // ResourceBundle msg;
   // Locale all_locales[];

    public applet()
    {
        tabs = new JTabbedPane();
        //all_msgs = new ResourceBundle[2];
    }

    public String getString(String s)
    {
        return "ble";
       // return all_msgs[current_lang].getString(s);
    }

    public void init()
    {
//        all_locales[0] = new Locale("en");
//        all_msgs[0] = ResourceBundle.getBundle("Messages_en", all_locales[0]);
//        all_locales[1] = new Locale("vi");
//        all_msgs[1] = ResourceBundle.getBundle("Messages_vi", all_locales[1]);
//        lang = "en";
//        if(lang.equals("vi"))
//        {
//            current_lang = 1;
//            b = new JButton("english");
//        } else
//        {
//            current_lang = 0;
//            b = new JButton("viet nam");
//        }
//        //locale = all_locales[current_lang];
//        msg = all_msgs[current_lang];
        Bm = new main("b-tree", this);
        tabs.addTab("Btree", Bm);
        add(tabs);

    }

    public void refresh()
    {
        tabs.setTitleAt(0, getString("btree"));
        Bm.refresh();
    }


    public static void main(String args[])
    {
        Frame frame = new Frame("Gnarled trees");
        frame.addWindowListener(new WindowAdapter() {

            public void windowClosing(WindowEvent windowevent)
            {
                System.exit(0);
            }

        });
        applet applet1 = new applet();
        applet1.setSize(1230, 670);
        frame.add(applet1);
        frame.pack();
        applet1.init();
        frame.setSize(1230, 690);
        frame.show();
    }
}
