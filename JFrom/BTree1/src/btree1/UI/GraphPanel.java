package btree1.UI;

import BTree1.BTNodeInterface;
import BTree1.BTreeInterface;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JDesktopPane;

// Referenced classes of package UI:
//            MainFrame

public class GraphPanel extends JDesktopPane
{

    private static final long serialVersionUID = 0x8e777ddd444dd939L;
    private BTreeInterface btree;
    private int xCenter;
    private int yCenter;
    private int d;
    private int r;

    public GraphPanel(MainFrame frame)
        throws Exception
    {
        d = 10;
        r = d / 2;
        btree = frame.btree;
        setBackground(Color.white);
    }

    public void paint(Graphics g)
    {
        super.paint(g);
        int x = xCenter = 100;
        int y = yCenter = 100;
        int rootId = btree.getRootBID();
        drawTree(g, rootId, x, y);
    }

    private void drawTree(Graphics g, int nodeId, int x, int y)
    {
        g.fillOval(xCenter, yCenter, d, d);
        g.drawString(Integer.toString(nodeId), xCenter + d, yCenter);
        if(yCenter > 100)
        {
            g.drawLine(x + r, y + d, xCenter + r, yCenter);
        }
        BTNodeInterface node = btree.readNode(nodeId);
        int tag = node.getTag();
        if(tag == 1)
        {
            xCenter += 50;
            return;
        }
        x = xCenter;
        y = yCenter;
        yCenter += 100;
        int keyNum = node.getLen();
        int child[] = node.getPointer();
        for(int i = 0; i <= keyNum; i++)
        {
            drawTree(g, child[i], x, y);
        }

        yCenter -= 100;
    }
}
