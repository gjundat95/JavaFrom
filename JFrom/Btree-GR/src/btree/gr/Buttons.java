package btree.gr;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.*;

public class Buttons extends JPanel
    implements ActionListener
{

    main M;
    DataStructure D;
    int numButtons;
    int numInputs;
    JTextField I;
    Button B[];
    Button next;
    Button clear;
    Button random;
    CheckBox pause;
    CheckBox small;
    JLabel stats;
   // static final boolean $assertionsDisabled = !Buttons.desiredAssertionStatus();

    public Buttons(main main1)
    {
        M = main1;
        D = main1.D;
        if(D == null)
        {
            throw new AssertionError("data structure not initialized yet");
        }
        I = new JTextField(5);
        B = new Button[numButtons = D.getNumButtons()];
        JPanel jpanel = new JPanel();
        jpanel.setLayout(new FlowLayout());
        jpanel.add(I);
        I.addActionListener(this);
        for(int i = 0; i < numButtons; i++)
        {
            jpanel.add(B[i] = D.getButton(i));
            B[i].setEnabled(true);
            B[i].addActionListener(this);
        }

        jpanel.add(next = new Button(main1.a, "next"));
        next.setMnemonic(78);
        next.setEnabled(false);
        next.addActionListener(this);
        JPanel jpanel1 = new JPanel();
        jpanel1.setLayout(new FlowLayout());
        pause = new CheckBox(main1.a, "pause", true);
        pause.setMnemonic(80);
        pause.addActionListener(this);
        jpanel1.add(pause);
        small = new CheckBox(main1.a, "small", false);
        small.setMnemonic(83);
        small.addActionListener(this);
        jpanel1.add(small);
        D.otherButtons(jpanel1);
        clear = new Button(main1.a, "clear");
        clear.setMnemonic(67);
        clear.addActionListener(this);
        jpanel1.add(clear);
        random = new Button(main1.a, "random");
        random.setMnemonic(82);
        random.addActionListener(this);
        jpanel1.add(random);
        stats = new JLabel(D.stats());
        JPanel jpanel2 = new JPanel();
        jpanel2.setLayout(new FlowLayout());
        jpanel2.add(stats);
        setLayout(new BoxLayout(this, 3));
        add(jpanel);
        add(jpanel1);
        add(jpanel2);
        setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(main1.a.getString("control")), BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    }

    public void actionPerformed(ActionEvent actionevent)
    {
label0:
        {
label1:
            {
                int i;
label2:
                {
                    if(actionevent.getSource() == next)
                    {
                        D.next();
                        break label0;
                    }
                    if(actionevent.getSource() == clear)
                    {
                        D.clear();
                        break label0;
                    }
                    if(actionevent.getSource() != random)
                    {
                        break label1;
                    }
                    i = 10;
                    StringTokenizer stringtokenizer = new StringTokenizer(I.getText());
                    try
                    {
                        i = Integer.parseInt(stringtokenizer.nextToken());
                    }
                    catch(Exception exception)
                    {
                        if(i < 1)
                        {
                            i = 1;
                        }
                        if(i > 99)
                        {
                            i = 99;
                        }
                        break label2;
                    }
                    finally
                    {
                        if(i < 1)
                        {
                            i = 1;
                        }
                        if(i > 99)
                        {
                            i = 99;
                        }
                        //throw exception1;
                    }
                    if(i < 1)
                    {
                        i = 1;
                    }
                    if(i > 99)
                    {
                        i = 99;
                    }
                    break label2;
                }
                Vector vector1 = new Vector(i);
                Random random1 = new Random(System.currentTimeMillis());
                boolean flag = M.pause;
                M.pause = false;
                for(int l = 0; l < i; l++)
                {
                    vector1.add(Integer.valueOf(random1.nextInt(100)));
                }

                D.action(0, vector1);
                M.pause = flag;
                break label0;
            }
            if(actionevent.getSource() == pause)
            {
                M.pause = pause.isSelected();
            } else
            if(actionevent.getSource() == small)
            {
                M.small = small.isSelected();
                D.resize();
            } else
            {
                int j = 0;
                do
                {
                    if(j >= numButtons)
                    {
                        break;
                    }
                    if(actionevent.getSource() == B[j])
                    {
                        Vector vector = new Vector();
                        for(StringTokenizer stringtokenizer1 = new StringTokenizer(I.getText()); stringtokenizer1.hasMoreTokens();)
                        {
                            try
                            {
                                int k = Integer.parseInt(stringtokenizer1.nextToken());
                                if(k < 0)
                                {
                                    k = 0;
                                }
                                if(k > 99)
                                {
                                    k = 99;
                                }
                                vector.add(Integer.valueOf(k));
                            }
                            catch(NumberFormatException numberformatexception) { }
                        }

                        enableNext();
                        final int fi = j;
                        final Vector fargs = vector;
                        Thread thread = new Thread(new Runnable() {
                            int i;
                            final int fi;
                            final Vector fargs;
                            final Buttons this$0;

                            public void run()
                            {
                                D.action(fi, fargs);
                            }

            
            {
                this$0 = Buttons.this;
                fi = i;
                fargs = vector;
                //super();
            }
                        });
                        thread.start();
                        break;
                    }
                    j++;
                } while(true);
            }
        }
    }

    public void enableNext()
    {
        for(int i = 0; i < numButtons; i++)
        {
            B[i].setEnabled(false);
        }

        clear.setEnabled(false);
        random.setEnabled(false);
        next.setEnabled(true);
    }

    public void disableNext()
    {
        for(int i = 0; i < numButtons; i++)
        {
            B[i].setEnabled(true);
        }

        clear.setEnabled(true);
        random.setEnabled(true);
        next.setEnabled(false);
    }

    public void setStats(String s)
    {
        stats.setText(s);
    }

    public void refresh()
    {
        next.refresh();
        clear.refresh();
        random.refresh();
        pause.refresh();
        small.refresh();
        for(int i = 0; i < numButtons; i++)
        {
            B[i].refresh();
        }

    }

}
