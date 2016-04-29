package btree.gr;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;

public class main extends JPanel
{

    Buttons B;
    Commentary C;
    DataStructure D;
    Screen S;
    String d;
    applet a;
    int STEPS;
    boolean pause;
    boolean small;

    public main(String s, applet applet1)
    {
        STEPS = 10;
        pause = true;
        small = false;
        d = s;
        a = applet1;
        init();
    }

    public void init()
    {
        JPanel jpanel = new JPanel();
        S = new Screen() {

            final main this$0;

            public Dimension getPreferredSize()
            {
                return new Dimension(900, 400);
            }

            public Dimension getMinimumSize()
            {
                return new Dimension(600, 300);
            }

            
            {
                this$0 = main.this;
                //super();
            }
        };
        jpanel.add(S);
        jpanel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(a.getString("display")), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        C = new Commentary(a) {

            final main this$0;

            public Dimension getPreferredSize()
            {
                return new Dimension(250, 530);
            }

            public Dimension getMinimumSize()
            {
                return new Dimension(200, 530);
            }

            
            {
                this$0 = main.this;
                //super(applet1);
            }
        };
        
        if(d.equals("b-tree"))
        {
            D = new BTree(this);
        } 
        B = new Buttons(this);
        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new BoxLayout(jpanel1, 3));
        jpanel1.add(jpanel);
        jpanel1.add(B);
        JPanel jpanel2 = new JPanel();
        jpanel2.add(C);
        jpanel2.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(a.getString("text")), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        setLayout(new FlowLayout());
        add(jpanel1);
        add(jpanel2);
        S.setDS(D);
        S.start();
    }

    public void refresh()
    {
        B.refresh();
        D.setStats();
    }
}
