package btree.gr;
import java.awt.*;
import java.util.Vector;
import javax.swing.JPanel;

public abstract class DataStructure
{

    Algorithm A;
    main M;
    int radius;
    int xspan;
    int yspan;
    int fontsize;
    int rootx;
    int rooty;
    int sheight;
    int swidth;
    Font font;
    FontMetrics fm;

    public DataStructure(main main1)
    {
        M = main1;
    }

    public abstract int getNumButtons();

    public void next()
    {
        A.myresume();
    }

    public void setFS(int i)
    {
        fontsize = i;
        font = new Font("Helvetica", 0, fontsize);
        fm = M.S.G.getFontMetrics(font);
    }

    public abstract Button getButton(int i);

    public void otherButtons(JPanel jpanel)
    {
    }

    public abstract String stats();

    public abstract void action(int i, Vector vector);

    public abstract void clear();

    public abstract void clean();

    public abstract void draw(Graphics g);

    public abstract void resize();

    void start(Algorithm algorithm)
    {
        A = algorithm;
        A.start();
        try
        {
            A.join();
        }
        catch(InterruptedException interruptedexception) { }
        setStats();
    }

    void setStats()
    {
        M.B.setStats(stats());
    }

    public double lg(int i)
    {
        return Math.log(i) / Math.log(2D);
    }
}
