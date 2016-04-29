package btree.gr;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class BNode extends Node
{

    int leftw;
    int rightw;
    BNode parent;
    int numKeys;
    int numChildren;
    int key[];
    BNode c[];
    int nkeys;
    int nnodes;
    int height;

    public BNode(DataStructure datastructure, int i, int j, int k)
    {
        parent = null;
        numKeys = 1;
        numChildren = 0;
        nkeys = 1;
        nnodes = 1;
        height = 1;
        key = new int[((BTree)datastructure).order + 5];
        c = new BNode[((BTree)datastructure).order + 5];
        key[0] = i;
        numKeys = 1;
        D = datastructure;
        x = tox = j;
        y = toy = k;
        steps = 0;
        setColor(Color.black, NORMAL);
    }

    public BNode(DataStructure datastructure, int i)
    {
        this(datastructure, i, -10, -10);
    }

    public BNode(BNode bnode)
    {
        this(bnode.D, bnode.key[0], bnode.x, bnode.y);
    }

    public BNode(BNode bnode, BNode bnode1, BNode bnode2)
    {
        this(bnode.D, -1, bnode1.x, bnode1.y);
        int i = bnode.numKeys;
        int j = bnode2.numKeys;
        numKeys = i + 1 + j;
        for(int k = 0; k < i; k++)
        {
            key[k] = bnode.key[k];
        }

        key[i] = bnode1.key[0];
        for(int l = 0; l < j; l++)
        {
            key[i + 1 + l] = bnode2.key[l];
        }

        i = bnode.numChildren;
        j = bnode2.numChildren;
        numChildren = i + j;
        for(int i1 = 0; i1 < i; i1++)
        {
            c[i1] = bnode.c[i1];
        }

        for(int j1 = 0; j1 < j; j1++)
        {
            c[i + j1] = bnode2.c[j1];
        }

        for(int k1 = 0; k1 < numChildren; k1++)
        {
            c[k1].parent = this;
        }

    }

    public boolean isRoot()
    {
        return parent == null;
    }

    public boolean isLeaf()
    {
        return numChildren == 0;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("[").append(key[0]).toString();
        for(int i = 1; i < numKeys; i++)
        {
            s = (new StringBuilder()).append(s).append("|").append(key[i]).toString();
        }

        return (new StringBuilder()).append(s).append("]").toString();
    }

    public void calcTree()
    {
        nkeys = numKeys;
        nnodes = 1;
        for(int i = 0; i < numChildren; i++)
        {
            c[i].calcTree();
            nkeys += c[i].nkeys;
            nnodes += c[i].nnodes;
        }

        height = 1 + (isLeaf() ? 0 : c[0].height);
    }

    public void addLeaf(int i)
    {
        key[numKeys++] = i;
        for(int j = numKeys - 1; j > 0; j--)
        {
            if(key[j] < key[j - 1])
            {
                int k = key[j];
                key[j] = key[j - 1];
                key[j - 1] = k;
            }
        }

    }

    public int order()
    {
        for(int i = 0; i < parent.numChildren; i++)
        {
            if(parent.c[i] == this)
            {
                return i;
            }
        }

        return -5;
    }

    public void add(int i, BNode bnode)
    {
        for(int j = numKeys; j > i; j--)
        {
            key[j] = key[j - 1];
            c[j + 1] = c[j];
        }

        numKeys++;
        numChildren++;
        key[i] = bnode.key[0];
        c[i] = bnode.c[0];
        c[i].parent = this;
        c[i + 1] = bnode.c[1];
        c[i + 1].parent = this;
    }

    public boolean isIn(int i)
    {
        for(int j = 0; j < numKeys; j++)
        {
            if(key[j] == i)
            {
                return true;
            }
        }

        return false;
    }

    public BNode way(int i)
    {
        if(i < key[0])
        {
            return c[0];
        }
        for(int j = 1; j < numKeys; j++)
        {
            if(i < key[j])
            {
                return c[j];
            }
        }

        return c[numKeys];
    }

    public int search(int i)
    {
        if(i < key[0])
        {
            return 0;
        }
        for(int j = 1; j < numKeys; j++)
        {
            if(i < key[j])
            {
                return j;
            }
        }

        return numKeys;
    }

    public BNode split()
    {
        int i = width() / 2;
        int j = numKeys;
        int k = numKeys / 2;
        int l = numKeys - k - 1;
        BNode bnode = new BNode(D, key[0], (x - i) + k * D.radius, y);
        BNode bnode1 = new BNode(D, key[numKeys / 2], (x - i) + (k + 3) * D.radius, y);
        BNode bnode2 = new BNode(D, key[numKeys - 1], (x + i) - l * D.radius, y);
        for(int i1 = 1; i1 < numKeys / 2; i1++)
        {
            bnode.addLeaf(key[i1]);
        }

        for(int j1 = numKeys / 2 + 1; j1 < numKeys - 1; j1++)
        {
            bnode2.addLeaf(key[j1]);
        }

        if(isLeaf())
        {
            bnode.numChildren = bnode2.numChildren = 0;
        } else
        {
            bnode.numChildren = (numChildren + 1) / 2;
            bnode2.numChildren = numChildren / 2;
            for(int k1 = 0; k1 < bnode.numChildren; k1++)
            {
                bnode.c[k1] = c[k1];
                bnode.c[k1].parent = bnode;
            }

            for(int l1 = 0; l1 < bnode2.numChildren; l1++)
            {
                bnode2.c[l1] = c[bnode.numChildren + l1];
                bnode2.c[l1].parent = bnode2;
            }

        }
        bnode.parent = bnode2.parent = bnode1;
        bnode1.numChildren = 2;
        bnode1.parent = parent;
        bnode1.c[0] = bnode;
        bnode1.c[1] = bnode2;
        return bnode1;
    }

    public BNode del(int i)
    {
        int j;
        for(j = -1; key[++j] != i;) { }
        int k = j;
        for(numKeys--; j < numKeys; j++)
        {
            key[j] = key[j + 1];
        }

        return new BNode(D, i, x - ((numKeys + 1) - 2 * k) * D.radius, y);
    }

    public BNode delMin()
    {
        int i = key[0];
        numKeys--;
        for(int j = 0; j < numKeys; j++)
        {
            key[j] = key[j + 1];
        }

        return new BNode(D, i, x - (numKeys - 1) * D.radius, y);
    }

    public BNode delMinCh()
    {
        BNode bnode = c[0];
        numChildren--;
        for(int i = 0; i < numChildren; i++)
        {
            c[i] = c[i + 1];
        }

        return bnode;
    }

    public BNode delMax()
    {
        return new BNode(D, key[--numKeys], x + (numKeys - 1) * D.radius, y);
    }

    public BNode delMaxCh()
    {
        return c[--numChildren];
    }

    public void insMin(int i)
    {
        for(int j = numKeys++; j > 0; j--)
        {
            key[j] = key[j - 1];
        }

        key[0] = i;
    }

    public void insMinCh(BNode bnode)
    {
        for(int i = numChildren++; i > 0; i--)
        {
            c[i] = c[i - 1];
        }

        c[0] = bnode;
    }

    public void insMax(int i)
    {
        key[numKeys++] = i;
    }

    public void insMaxCh(BNode bnode)
    {
        c[numChildren++] = bnode;
    }

    public void replace(int i, int j)
    {
        int k;
        for(k = -1; key[++k] != i;) { }
        key[k] = j;
    }

    public int width()
    {
        if(key[0] != -1 && numKeys > 0)
        {
            String s = (new StringBuilder()).append("").append(key[0]).toString();
            if(key[0] == 0x1869f)
            {
                s = "\u221E";
            }
            if(key[0] == 0xfffe7961)
            {
                s = "-\u221E";
            }
            for(int i = 1; i < numKeys; i++)
            {
                s = (new StringBuilder()).append(s).append("  ").append(key[i]).toString();
            }

            return Math.max(D.fm.stringWidth(s) + 4, 2 * D.radius);
        } else
        {
            return D.radius;
        }
    }

    public void drawBg(Graphics g)
    {
        int i = width();
        int j = i / 2;
        g.setColor(bgcolor);
        java.awt.geom.RoundRectangle2D.Float float1 = new java.awt.geom.RoundRectangle2D.Float(x - j, y - D.radius, i, 2 * D.radius, D.radius * 2, D.radius * 2);
        ((Graphics2D)g).fill(float1);
        g.setColor(fgcolor);
        ((Graphics2D)g).draw(float1);
    }

    public void drawKey(Graphics g)
    {
        if(key[0] != -1 && numKeys > 0)
        {
            String s = (new StringBuilder()).append("").append(key[0]).toString();
            if(key[0] == 0x1869f)
            {
                s = "\u221E";
            }
            if(key[0] == 0xfffe7961)
            {
                s = "-\u221E";
            }
            for(int i = 1; i < numKeys; i++)
            {
                s = (new StringBuilder()).append(s).append("  ").append(key[i]).toString();
            }

            g.setFont(D.font);
            g.drawString(s, x - D.fm.stringWidth(s) / 2, (y - D.fm.getHeight() / 2) + D.fm.getAscent());
        }
    }

    public void drawTree(Graphics g)
    {
        for(int i = 0; i < numChildren; i++)
        {
            g.setColor(Color.black);
            g.drawLine(x, y, c[i].x, c[i].y - D.radius);
            c[i].drawTree(g);
        }

        draw(g);
    }

    public void moveTree()
    {
        for(int i = 0; i < numChildren; i++)
        {
            c[i].moveTree();
        }

        move();
    }

    public void rebox()
    {
        if(numChildren == 0)
        {
            leftw = rightw = width() / 2 + D.xspan;
        } else
        {
            if(numChildren % 2 == 0)
            {
                leftw = rightw = 0;
            } else
            {
                leftw = c[numChildren / 2].leftw;
                rightw = c[numChildren / 2].rightw;
            }
            for(int i = 0; i < numChildren / 2; i++)
            {
                leftw += c[i].leftw + c[i].rightw;
            }

            for(int j = (numChildren + 1) / 2; j < numChildren; j++)
            {
                rightw += c[j].leftw + c[j].rightw;
            }

        }
    }

    public void reboxTree()
    {
        for(int i = 0; i < numChildren; i++)
        {
            c[i].reboxTree();
        }

        rebox();
    }

    private void repos()
    {
        if(isRoot())
        {
            goToRoot();
        }
        int i = tox;
        int j = tox;
        int k = toy + 2 * D.radius + D.yspan;
        if(numChildren == 0)
        {
            return;
        }
        if(numChildren % 2 == 0)
        {
            int l = numChildren / 2 - 1;
            c[l].goTo(i -= c[l].rightw, k);
            c[l].repos();
            for(int j1 = l - 1; j1 >= 0; j1--)
            {
                c[j1].goTo(i -= c[j1 + 1].leftw + c[j1].rightw, k);
                c[j1].repos();
            }

            c[++l].goTo(j += c[l].leftw, k);
            c[l].repos();
            for(int k1 = l + 1; k1 < numChildren; k1++)
            {
                c[k1].goTo(j += c[k1 - 1].rightw + c[k1].leftw, k);
                c[k1].repos();
            }

        } else
        {
            int i1 = numChildren / 2;
            c[i1].goTo(i, k);
            c[i1].repos();
            for(int l1 = 1; l1 <= i1; l1++)
            {
                c[i1 - l1].goTo(i -= c[i1 - l1].rightw + c[(i1 - l1) + 1].leftw, k);
                c[i1 - l1].repos();
                c[i1 + l1].goTo(j += c[i1 + l1].leftw + c[(i1 + l1) - 1].rightw, k);
                c[i1 + l1].repos();
            }

        }
    }

    public void _reposition()
    {
        reboxTree();
        repos();
    }

    public void goTo(BNode bnode)
    {
        int i = key[0];
        int j = bnode.numKeys;
        for(int k = 0; k < j; k++)
        {
            if(i <= bnode.key[k])
            {
                j = k;
            }
        }

        int l = bnode.width() / 2;
        goTo((bnode.tox - l) + 2 * D.radius * j, bnode.toy);
    }
}
