package btree.gr;
public class BInsert extends Algorithm
{

    BTree T;
    BNode v;
    int K;

    public BInsert(BTree btree, int i)
    {
        super(btree.M);
        T = btree;
        v = btree.v = new BNode(btree, K = i);
        v.bgColor(Node.INSERT);
        setHeader("insertion");
    }

    public void run()
    {
        if(T.root == null)
        {
            T.root = v;
            v.goToRoot();
            setText("newroot");
            mysuspend();
            v.bgColor(Node.NORMAL);
        } else
        {
            BNode bnode = T.root;
            v.goTo(bnode);
            setText("bstinsertstart");
            mysuspend();
            do
            {
                if(bnode.isIn(K))
                {
                    setText("alreadythere");
                    v.goDown();
                    return;
                }
                if(bnode.isLeaf())
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
                v.goTo(bnode);
                mysuspend();
            } while(true);
            setText("binsertleaf");
            bnode.addLeaf(K);
            if(bnode.numKeys >= T.order)
            {
                bnode.bgColor(Node.NOTFOUND);
            }
            T.v = null;
            mysuspend();
            while(bnode.numKeys >= T.order) 
            {
                setText("bsplit");
                int j = bnode.parent == null ? -1 : bnode.order();
                bnode = bnode.split();
                if(bnode.parent == null)
                {
                    break;
                }
                bnode.parent.c[j] = bnode;
                mysuspend();
                bnode.goTo(bnode.parent);
                mysuspend();
                bnode.parent.add(j, bnode);
                bnode = bnode.parent;
                if(bnode.numKeys >= T.order)
                {
                    bnode.bgColor(Node.NOTFOUND);
                }
                T.reposition();
                mysuspend();
            }
            if(bnode.isRoot())
            {
                T.root = bnode;
            }
            T.reposition();
        }
    }
}
