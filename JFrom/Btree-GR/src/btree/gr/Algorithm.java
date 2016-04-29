package btree.gr;
public class Algorithm extends Thread
{

    main M;
    boolean suspended;

    public Algorithm(main main1)
    {
        suspended = false;
        M = main1;
    }

    public void mysuspend()
    {
        if(M.pause)
        {
            suspended = true;
            synchronized(this)
            {
                try
                {
                    while(suspended) 
                    {
                        wait();
                    }
                }
                catch(InterruptedException interruptedexception) { }
            }
        }
    }

    public void myresume()
    {
        synchronized(this)
        {
            suspended = false;
            notifyAll();
        }
    }

    public void setHeader(String s)
    {
        M.C.setHeader(s);
    }

    public void setText(String s)
    {
        if(M.pause)
        {
            M.C.setText(s);
        }
    }

    public void setText(String s, String s1)
    {
        if(M.pause)
        {
            M.C.setText(s, s1);
        }
    }

    public void setText(String s, String s1, String s2)
    {
        if(M.pause)
        {
            M.C.setText(s, s1, s2);
        }
    }

    public void setText(String s, String s1, String s2, String s3)
    {
        if(M.pause)
        {
            M.C.setText(s, s1, s2, s3);
        }
    }

    public void setText(String s, String s1, String s2, String s3, String s4)
    {
        if(M.pause)
        {
            M.C.setText(s, s1, s2, s3, s4);
        }
    }

    public void setText(String s, int i)
    {
        if(M.pause)
        {
            M.C.setText(s, i);
        }
    }

    public void setText(String s, int i, int j)
    {
        if(M.pause)
        {
            M.C.setText(s, i, j);
        }
    }

    public void setText(String s, int i, int j, int k)
    {
        if(M.pause)
        {
            M.C.setText(s, i, j, k);
        }
    }

    public void setText(String s, int i, int j, int k, int l)
    {
        if(M.pause)
        {
            M.C.setText(s, i, j, k, l);
        }
    }
}
