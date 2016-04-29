package btree.gr;
import java.awt.*;
import java.io.PrintStream;
import javax.swing.JPanel;

public class Screen extends JPanel
    implements Runnable
{

    Thread t;
    boolean suspended;
    DataStructure D;
    Image I;
    Graphics G;
    Dimension size;

    public Screen()
    {
        t = null;
        D = null;
    }

    public void setDS(DataStructure datastructure)
    {
        D = datastructure;
    }

    void check_size()
    {
        Dimension dimension = getSize();
        if(I == null || dimension.width != size.width || dimension.height != size.height)
        {
            I = createImage(dimension.width, dimension.height);
            G = I.getGraphics();
            size = dimension;
            D.resize();
        }
    }

    void clear()
    {
        G.setColor(Color.white);
        G.fillRect(0, 0, size.width, size.height);
    }

    public void paint(Graphics g)
    {
        check_size();
        clear();
        if(D != null)
        {
            D.draw(G);
        } else
        {
            System.out.println("[DS null !]");
        }
        g.drawImage(I, 0, 0, null);
    }

    public void suspend()
    {
        suspended = true;
    }

    public void resume()
    {
        if(suspended)
        {
            suspended = false;
            synchronized(this)
            {
                notify();
            }
        }
    }

    public void start()
    {
        if(t == null)
        {
            t = new Thread(this);
            suspended = false;
            t.start();
        }
    }

    public void run()
    {
        do
        {
            try
            {
                if(suspended)
                {
                    synchronized(this)
                    {
                        while(suspended) 
                        {
                            wait();
                        }
                    }
                }
                repaint();
                Thread.sleep(50L);
            }
            catch(InterruptedException interruptedexception)
            {
                return;
            }
        } while(true);
    }
}
