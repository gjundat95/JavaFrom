package btree.gr;
public class BDelete extends Algorithm
{

    BTree T;
    BNode v;
    int K;

    public BDelete(BTree btree, int i)
    {
        super(btree.M);
        T = btree;
        K = i;
        v = btree.v = new BNode(btree, i);
        v.bgColor(Node.DELETE);
        setHeader("deletion");
    }

    public void run()
    {
        if(T.root == null)
        {
            v.goToRoot();
            setText("empty");
            mysuspend();
            v.goDown();
            v.bgColor(Node.NOTFOUND);
            setText("notfound");
        } else
        {
            BNode bnode = T.root;
            v.goTo(bnode);
            setText("bstdeletestart");
            mysuspend();
            do
            {
                if(bnode.isIn(K))
                {
                    break;
                }
                int i = bnode.search(K);
                if(i == 0)
                {
                    setText("bfind0", K, bnode.key[0]);
                } else
                if(i == bnode.numKeys)
                {
                    setText("bfindn", bnode.key[bnode.numKeys - 1], K, bnode.numKeys + 1);
                } else
                {
                    setText("bfind", bnode.key[i - 1], K, bnode.key[i], i + 1);
                }
                bnode = bnode.c[i];
                if(bnode == null)
                {
                    break;
                }
                v.goTo(bnode);
                mysuspend();
            } while(true);
            if(bnode == null)
            {
                setText("notfound");
                v.goDown();
                return;
            }
            bnode.bgColor(Node.FOUND);
            mysuspend();
            bnode.bgColor(Node.NORMAL);
            if(bnode.isLeaf())
            {
                setText("bdelete1");
                if(bnode.isRoot() && bnode.numKeys == 1)
                {
                    T.v = bnode;
                    T.root = null;
                    T.v.goDown();
                } else
                {
                    T.v = bnode.del(K);
                    T.reposition();
                    T.v.goDown();
                    mysuspend();
                }
            } else
            {
                setText("bdelete2");
                BNode bnode1 = bnode.way(K + 1);
                v = T.v = new BNode(T, 0xfffe7961, bnode.x, bnode.y);
                v.goTo(bnode1);
                mysuspend();
                for(; !bnode1.isLeaf(); mysuspend())
                {
                    bnode1 = bnode1.c[0];
                    v.goTo(bnode1);
                }

                v = T.v = bnode1.delMin();
                v.goTo(bnode);
                mysuspend();
                bnode.replace(K, v.key[0]);
                T.v = null;
                mysuspend();
                bnode.bgColor(Node.NORMAL);
                bnode = bnode1;
            }
            do
            {
                if(bnode.isRoot() || bnode.numKeys >= (T.order - 1) / 2)
                {
                    break;
                }
                bnode.bgColor(Node.NOTFOUND);
                BNode bnode3 = null;
                BNode bnode4 = null;
                BNode bnode5 = bnode.parent;
                boolean flag = true;
                int j = bnode.order();
                int k = 0;
                int l = 0;
                if(j > 0)
                {
                    bnode3 = bnode5.c[j - 1];
                    k = bnode3.numKeys;
                }
                if(j + 1 < bnode5.numChildren)
                {
                    bnode4 = bnode5.c[j + 1];
                    l = bnode4.numKeys;
                }
                BNode bnode2;
                if(k >= l)
                {
                    bnode2 = bnode3;
                    j--;
                } else
                {
                    bnode2 = bnode4;
                    flag = false;
                }
                if(bnode2.numKeys > (T.order - 1) / 2)
                {
                    if(flag)
                    {
                        setText("bleft");
                    } else
                    {
                        setText("bright");
                    }
                    T.v = flag ? bnode2.delMax() : bnode2.delMin();
                    T.v.goTo(bnode5);
                    mysuspend();
                    int i1 = bnode5.key[j];
                    bnode5.key[j] = T.v.key[0];
                    T.v = new BNode(T, i1, bnode5.x, bnode5.y);
                    T.v.goTo(bnode);
                    mysuspend();
                    if(flag)
                    {
                        bnode.insMin(i1);
                        if(!bnode.isLeaf())
                        {
                            bnode.insMinCh(bnode2.delMaxCh());
                            bnode.c[0].parent = bnode;
                        }
                    } else
                    {
                        bnode.insMax(i1);
                        if(!bnode.isLeaf())
                        {
                            bnode.insMaxCh(bnode2.delMinCh());
                            bnode.c[bnode.numChildren - 1].parent = bnode;
                        }
                    }
                    bnode.bgColor(Node.NORMAL);
                    T.v = null;
                    break;
                }
                setText("bmerge");
                if(bnode5.isRoot() && bnode5.numKeys == 1)
                {
                    T.v = new BNode(T.root);
                    T.root.key[0] = -1;
                    T.v.goTo((bnode.tox + bnode2.tox) / 2, bnode.y);
                    mysuspend();
                    if(flag)
                    {
                        T.root = new BNode(bnode2, T.v, bnode);
                    } else
                    {
                        T.root = new BNode(bnode, T.v, bnode2);
                    }
                    break;
                }
                T.v = bnode5.del(bnode5.key[j]);
                T.v.goTo((bnode.tox + bnode2.tox) / 2, bnode.y);
                mysuspend();
                if(flag)
                {
                    bnode5.c[j] = new BNode(bnode2, T.v, bnode);
                } else
                {
                    bnode5.c[j] = new BNode(bnode, T.v, bnode2);
                }
                bnode5.c[j].parent = bnode5;
                bnode5.numChildren--;
                for(int j1 = j + 1; j1 < bnode5.numChildren; j1++)
                {
                    bnode5.c[j1] = bnode5.c[j1 + 1];
                }

                bnode = bnode5;
            } while(true);
            T.v = null;
            T.reposition();
        }
    }
}
