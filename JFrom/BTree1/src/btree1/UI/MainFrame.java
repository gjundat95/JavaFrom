package btree1.UI;

import BTree1.BTNodeInterface;
import BTree1.BTreeInterface;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

// Referenced classes of package UI:
//            MenuBar, InfoPanel, GraphPanel

public class MainFrame extends JFrame
{
    class MenuAL extends AbstractAction
        implements ActionListener
    {

        private static final long serialVersionUID = 0x59b5963d9a55a5cL;
        final MainFrame this$0;

        public void actionPerformed(ActionEvent e)
        {
            try
            {
                String command = e.getActionCommand();
                if(command.equals("Exit"))
                {
                    System.exit(0);
                }
                command.equals("About");
            }
            catch(Exception exception) { }
        }

        public MenuAL()
        {
            this$0 = MainFrame.this;
           // super();
        }

        public MenuAL(String name, Icon icon)
        {
            this$0 = MainFrame.this;
           //super(name, icon);
        }
    }

    class TreeSL
        implements TreeSelectionListener
    {

        final MainFrame this$0;

        public void valueChanged(TreeSelectionEvent e)
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)e.getPath().getLastPathComponent();
            String NodeId = node.toString();
            try
            {
                infoPanel.showInfo(NodeId);
            }
            catch(Exception exception) { }
        }

        TreeSL()
        {
            this$0 = MainFrame.this;
            //super();
        }
    }


    private static final long serialVersionUID = 0x23614f043e70f73L;
    private JPanel barPanel;
    private JPanel leftCards;
    private JPanel leftPanel;
    private JTabbedPane rightCards;
    private CardLayout leftCl;
    public MenuAL menuAL;
    private TreeSL treeSL;
    private InfoPanel infoPanel;
    private GraphPanel graphPanel;
    private JScrollPane scInfoPanel;
    private JScrollPane scGraphPanel;
    public BTreeInterface btree;
    private JTree BTree;
    private JScrollPane scBTree;

    public MainFrame(BTreeInterface tree)
    {
        barPanel = new JPanel();
        leftCards = new JPanel();
        leftPanel = new JPanel();
        rightCards = new JTabbedPane(1, 1);
        leftCl = new CardLayout();
        menuAL = new MenuAL();
        treeSL = new TreeSL();
        try
        {
            setTitle("User Interface");
            setDefaultCloseOperation(3);
            setIconImage((new ImageIcon("src/Icons/default.gif")).getImage());
            setBounds(100, 100, 1000, 600);
            barPanel.setLayout(new BoxLayout(barPanel, 0));
            barPanel.add(new MenuBar(this));
            getContentPane().add(barPanel, "North");
            JSplitPane spPane = new JSplitPane(1, true, leftPanel, rightCards);
            spPane.setDividerLocation(200);
            btree = tree;
            BTree = new JTree(new DefaultMutableTreeNode(Integer.valueOf(btree.getRootBID())));
            BTree.addTreeSelectionListener(treeSL);
            scBTree = new JScrollPane(BTree);
            browseBTree(BTree);
            leftCards.setLayout(leftCl);
            leftCards.add(Integer.toString(btree.getRootBID()), scBTree);
            leftPanel.setLayout(new BorderLayout());
            leftPanel.add(leftCards);
            infoPanel = new InfoPanel(this, "");
            scInfoPanel = new JScrollPane(infoPanel, 20, 30);
            scInfoPanel.getViewport().setBackground(Color.white);
            rightCards.add("\u57FA\u672C\u4FE1\u606F", scInfoPanel);
            graphPanel = new GraphPanel(this);
            scGraphPanel = new JScrollPane(graphPanel, 20, 30);
            scGraphPanel.getViewport().setBackground(Color.white);
            rightCards.add("\u7ED3\u6784\u4FE1\u606F", scGraphPanel);
            getContentPane().add(spPane, "Center");
            setVisible(true);
        }
        catch(Exception exception) { }
    }

    public void browseBTree(JTree tree)
        throws Exception
    {
        Stack nodeStack = new Stack();
        DefaultTreeModel dtmModel = (DefaultTreeModel)tree.getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode)dtmModel.getRoot();
        nodeStack.push(root);
        while(!nodeStack.empty()) 
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)nodeStack.pop();
            BTNodeInterface btNode = btree.readNode(Integer.parseInt(node.toString()));
            int tag = btNode.getTag();
            if(tag == 0)
            {
                int ChildNumber = btNode.getLen();
                int ChildNode[] = btNode.getPointer();
                for(int i = 0; i <= ChildNumber; i++)
                {
                    DefaultMutableTreeNode child = new DefaultMutableTreeNode(Integer.valueOf(ChildNode[i]));
                    node.add(child);
                    nodeStack.push(child);
                }

            }
        }
        tree.expandRow(0);
    }

}
