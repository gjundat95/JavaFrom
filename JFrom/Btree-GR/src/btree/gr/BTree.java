package btree.gr;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class BTree extends Dictionary
    implements ChangeListener
{

    int order;
    BNode root;
    BNode v;
    JSpinner OS;

    public BTree(main main1)
    {
        super(main1);
        order = 5;
        root = null;
        v = null;
        OS = new JSpinner(new SpinnerNumberModel(5, 3, 20, 1));
    }

    public void insert(int i)
    {
        start(new BInsert(this, i));
    }

    public void find(int i)
    {
        start(new BFind(this, i));
    }

    public void delete(int i)
    {
        start(new BDelete(this, i));
    }

    public void clear()
    {
        root = null;
    }

    public void clean()
    {
        v = null;
    }

    public String stats()
    {
        if(root == null)
        {
            return (new StringBuilder()).append("#").append(M.a.getString("nodes")).append(": 0;   #").append(M.a.getString("keys")).append(": 0 = 0% ").append(M.a.getString("full")).append(";   ").append(M.a.getString("height")).append(": 0").toString();
        } else
        {
            root.calcTree();
            return (new StringBuilder()).append("#").append(M.a.getString("nodes")).append(": ").append(root.nnodes).append(";   ").append("#").append(M.a.getString("keys")).append(": ").append(root.nkeys).append(" = ").append((100 * root.nkeys) / (root.nnodes * (order - 1))).append("% ").append(M.a.getString("full")).append(";   ").append(M.a.getString("height")).append(": ").append(root.height).toString();
        }
    }

    public void draw(Graphics g)
    {
        if(root != null)
        {
            root.moveTree();
            root.drawTree(g);
        }
        if(v != null)
        {
            v.move();
            v.draw(g);
        }
    }

    public void resize()
    {
        if(M.small)
        {
            radius = 5;
            xspan = 2;
            yspan = 2;
            setFS(5);
        } else
        {
            radius = 10;
            xspan = 5;
            yspan = 5;
            setFS(11);
        }
        swidth = M.S.size.width;
        sheight = M.S.size.height;
        rootx = swidth / 2;
        rooty = 5 * radius + yspan;
        if(root != null)
        {
            reposition();
        }
    }

    public void reposition()
    {
        if(root != null)
        {
            root._reposition();
        }
    }

    public void otherButtons(JPanel jpanel)
    {
        OS.addChangeListener(this);
        jpanel.add(OS);
    }

    public void stateChanged(ChangeEvent changeevent)
    {
        if(changeevent.getSource() == OS)
        {
            order = ((Integer)OS.getValue()).intValue();
            clear();
            reposition();
        }
    }
}
