package btree.gr;
public class BFind extends Algorithm
{

    BTree T;
    BNode v;

    public BFind(BTree btree, int i)
    {
        super(btree.M);
        T = btree;
        v = btree.v = new BNode(btree, i);
        v.bgColor(Node.FIND);
        setHeader("search");
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
            setText("bstfindstart");
            mysuspend();
            do
            {
                if(bnode.isIn(v.key[0]))
                {
                    setText("found");
                    v.goDown();
                    v.bgColor(Node.FOUND);
                    break;
                }
                if(bnode.isLeaf())
                {
                    setText("notfound");
                    v.bgColor(Node.NOTFOUND);
                    v.goDown();
                    break;
                }
                bnode = bnode.way(v.key[0]);
                v.goTo(bnode);
                mysuspend();
            } while(true);
        }
    }
}
