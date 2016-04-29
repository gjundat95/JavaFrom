package btree.gr;
import java.awt.*;

public class Node
{

    DataStructure D;
    int key;
    static final int INF = 0x1869f;
    static final int NOKEY = -1;
    static final int NULL = 0x186a0;
    int x;
    int y;
    int tox;
    int toy;
    int steps;
    boolean marked;
    Color fgcolor;
    Color bgcolor;
    static final Color NORMAL;
    static final Color INSERT = new Color(0x2b65ec);
    static final Color FIND;
    static final Color FOUND;
    static final Color NOTFOUND;
    static final Color DELETE;

    public Node()
    {
        marked = false;
    }

    public Node(DataStructure datastructure, int i, int j, int k)
    {
        marked = false;
        D = datastructure;
        key = i;
        x = tox = j;
        y = toy = k;
        steps = 0;
        setColor(Color.black, NORMAL);
    }

    public Node(DataStructure datastructure, int i)
    {
        this(datastructure, i, -10, -10);
    }

    public Node(Node node)
    {
        this(node.D, node.key, node.x, node.y);
    }

    public void setColor(Color color, Color color1)
    {
        fgcolor = color;
        bgcolor = color1;
    }

    public void fgColor(Color color)
    {
        fgcolor = color;
    }

    public void bgColor(Color color)
    {
        bgcolor = color;
    }

    public void mark()
    {
        marked = true;
    }

    public void unmark()
    {
        marked = false;
    }

    public void drawBg(Graphics g)
    {
        g.setColor(bgcolor);
        g.fillOval(x - D.radius, y - D.radius, 2 * D.radius, 2 * D.radius);
        g.setColor(fgcolor);
        g.drawOval(x - D.radius, y - D.radius, 2 * D.radius, 2 * D.radius);
        if(marked)
        {
            g.drawOval(x - D.radius - 2, y - D.radius - 2, 2 * D.radius + 4, 2 * D.radius + 4);
        }
    }

    public void drawKey(Graphics g)
    {
        g.setColor(fgcolor);
        if(key != -1)
        {
            String s = (new StringBuilder()).append("").append(key).toString();
            if(key == 0x1869f)
            {
                s = "\u221E";
            }
            if(key == 0xfffe7961)
            {
                s = "-\u221E";
            }
            g.setFont(D.font);
            g.drawString(s, x - D.fm.stringWidth(s) / 2, (y - D.fm.getHeight() / 2) + D.fm.getAscent());
        }
    }

    public void draw(Graphics g)
    {
        if(key != 0x186a0)
        {
            drawBg(g);
            drawKey(g);
        }
    }

    public void goTo(int i, int j)
    {
        tox = i;
        toy = j;
        steps = D.M.STEPS;
    }

    public void goTo(Node node)
    {
        goTo(node.tox, node.toy);
    }

    public void goAbove(Node node)
    {
        goTo(node.tox, node.toy - 2 * D.radius - D.yspan);
    }

    public void goNextTo(Node node)
    {
        goTo(node.tox + 2 * D.radius + D.xspan, node.toy);
    }

    public void goToRoot()
    {
        goTo(D.rootx, D.rooty);
    }

    public void goAboveRoot()
    {
        goTo(D.rootx, D.rooty - 2 * D.radius - D.yspan);
    }

    public void goDown()
    {
        goTo(tox, D.sheight + 3 * D.radius);
    }

    public void goLeft()
    {
        goTo(-D.radius, D.sheight + 3 * D.radius);
    }

    public void goRight()
    {
        goTo(D.swidth + D.radius, D.sheight + 3 * D.radius);
    }

    public void move()
    {
        if(steps > 0)
        {
            x += (tox - x) / steps;
            y += (toy - y) / steps;
            steps--;
        }
    }

    static 
    {
        NORMAL = Color.yellow;
        FIND = Color.lightGray;
        FOUND = Color.green;
        NOTFOUND = Color.red;
        DELETE = Color.red;
    }
}
