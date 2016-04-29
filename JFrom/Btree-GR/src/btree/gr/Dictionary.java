package btree.gr;
import java.util.*;

public abstract class Dictionary extends DataStructure
{

    public Dictionary(main main1)
    {
        super(main1);
    }

    public int getNumButtons()
    {
        return 3;
    }

    public abstract void insert(int i);

    public abstract void find(int i);

    public abstract void delete(int i);

    public Button getButton(int i)
    {
        Button button = null;
        switch(i)
        {
        case 0: // '\0'
            button = new Button(M.a, "insert");
            button.setMnemonic(73);
            break;

        case 1: // '\001'
            button = new Button(M.a, "find");
            button.setMnemonic(70);
            break;

        case 2: // '\002'
            button = new Button(M.a, "delete");
            button.setMnemonic(68);
            break;
        }
        return button;
    }

    public void action(int i, Vector vector)
    {
        switch(i)
        {
        default:
            break;

        case 0: // '\0'
            if(vector.size() == 0)
            {
                Random random = new Random(System.currentTimeMillis());
                insert(random.nextInt(100));
                break;
            }
            int j;
            for(Iterator iterator = vector.iterator(); iterator.hasNext(); insert(j))
            {
                j = ((Integer)iterator.next()).intValue();
            }

            break;

        case 1: // '\001'
            if(vector.size() > 0)
            {
                find(((Integer)vector.firstElement()).intValue());
            }
            break;

        case 2: // '\002'
            int k;
            for(Iterator iterator1 = vector.iterator(); iterator1.hasNext(); delete(k))
            {
                k = ((Integer)iterator1.next()).intValue();
            }

            break;
        }
        M.B.disableNext();
    }
}
