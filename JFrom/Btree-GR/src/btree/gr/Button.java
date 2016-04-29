package btree.gr;
import javax.swing.JButton;

public class Button extends JButton
{

    applet a;
    String t;

    public Button(applet applet1, String s)
    {
        super(applet1.getString(s));
        a = applet1;
        t = s;
    }

    public void refresh()
    {
        setText(a.getString(t));
    }
}

