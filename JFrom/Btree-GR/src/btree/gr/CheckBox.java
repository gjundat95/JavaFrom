package btree.gr;
import javax.swing.JCheckBox;

public class CheckBox extends JCheckBox
{

    applet a;
    String t;

    public CheckBox(applet applet1, String s, boolean flag)
    {
        super(applet1.getString(s), flag);
        a = applet1;
        t = s;
    }

    public void refresh()
    {
        setText(a.getString(t));
    }
}