package btree.gr;
import javax.swing.JEditorPane;

public class Commentary extends JEditorPane
{

    applet a;
    String H;

    public Commentary(applet applet1)
    {
        setContentType("text/html; charset=iso-8859-2");
        setEditable(false);
        a = applet1;
    }

    public void setHeader(String s)
    {
        H = a.getString(s);
    }

    public void setText(String s)
    {
        super.setText((new StringBuilder()).append("<h3>").append(H).append("</h3>").append(a.getString(s)).toString());
    }

    public void setText(String s, String s1)
    {
        super.setText((new StringBuilder()).append("<h3>").append(H).append("</h3>").append(a.getString(s).replaceAll("#1", s1)).toString());
    }

    public void setText(String s, String s1, String s2)
    {
        super.setText((new StringBuilder()).append("<h3>").append(H).append("</h3>").append(a.getString(s).replaceAll("#1", s1).replaceAll("#2", s2)).toString());
    }

    public void setText(String s, String s1, String s2, String s3)
    {
        super.setText((new StringBuilder()).append("<h3>").append(H).append("</h3>").append(a.getString(s).replaceAll("#1", s1).replaceAll("#2", s2).replaceAll("#3", s3)).toString());
    }

    public void setText(String s, String s1, String s2, String s3, String s4)
    {
        super.setText((new StringBuilder()).append("<h3>").append(H).append("</h3>").append(a.getString(s).replaceAll("#1", s1).replaceAll("#2", s2).replaceAll("#3", s3).replaceAll("#4", s4)).toString());
    }

    public void setText(String s, int i)
    {
        setText(s, Integer.toString(i));
    }

    public void setText(String s, int i, int j)
    {
        setText(s, Integer.toString(i), Integer.toString(j));
    }

    public void setText(String s, int i, int j, int k)
    {
        setText(s, Integer.toString(i), Integer.toString(j), Integer.toString(k));
    }

    public void setText(String s, int i, int j, int k, int l)
    {
        setText(s, Integer.toString(i), Integer.toString(j), Integer.toString(k), Integer.toString(l));
    }
}
