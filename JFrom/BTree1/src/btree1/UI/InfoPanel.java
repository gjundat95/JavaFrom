package btree1.UI;

import BTree1.BTNodeInterface;
import BTree1.BTreeInterface;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

// Referenced classes of package UI:
//            MainFrame

public class InfoPanel extends JPanel
{

    private static final long serialVersionUID = 0xfdd030fccbfd7383L;
    private int iPanelWidth;
    private BTreeInterface btree;
    private DefaultTableModel tv;
    private JTextField tfId;
    private JTextField tfKeyNum;
    private JLabel labelId;
    private JLabel labelKeyNum;
    private JLabel labelSeperator;
    private JTable decKeyTable;
    private JScrollPane decKeyScrollPane;

    public InfoPanel(MainFrame frame, String NodeId)
        throws Exception
    {
        iPanelWidth = 600;
        btree = frame.btree;
        setLayout(new BoxLayout(this, 1));
        setBackground(Color.white);
        labelSeperator = new JLabel();
        labelSeperator.setMaximumSize(new Dimension(iPanelWidth, 60));
        Box SeperatorBox = Box.createVerticalBox();
        SeperatorBox.add(labelSeperator);
        add(SeperatorBox, "center");
        Box labelBox = Box.createVerticalBox();
        int iLabelWidth = iPanelWidth / 6;
        labelId = new JLabel("Node Id");
        labelId.setMaximumSize(new Dimension(iLabelWidth, 30));
        labelKeyNum = new JLabel("Number Of Key");
        labelKeyNum.setMaximumSize(new Dimension(iLabelWidth, 30));
        labelBox.add(labelId);
        labelBox.add(labelKeyNum);
        Box tfBox = Box.createVerticalBox();
        int iTfWidth = (iPanelWidth * 5) / 6;
        tfId = new JTextField();
        tfId.setBackground(Color.white);
        tfId.setEditable(false);
        tfId.setMaximumSize(new Dimension(iTfWidth, 30));
        tfKeyNum = new JTextField();
        tfKeyNum.setBackground(Color.white);
        tfKeyNum.setEditable(false);
        tfKeyNum.setMaximumSize(new Dimension(iTfWidth, 30));
        tfBox.add(tfId);
        tfBox.add(tfKeyNum);
        Box topBox = Box.createHorizontalBox();
        topBox.add(labelBox);
        topBox.add(tfBox);
        add(topBox, "center");
        labelSeperator = new JLabel();
        labelSeperator.setMaximumSize(new Dimension(iPanelWidth, 10));
        SeperatorBox = Box.createVerticalBox();
        SeperatorBox.add(labelSeperator);
        add(SeperatorBox, "center");
        tv = GetTableValues(NodeId);
        decKeyTable = new JTable(tv);
        decKeyScrollPane = new JScrollPane(decKeyTable, 20, 30);
        decKeyScrollPane.setMaximumSize(new Dimension(iPanelWidth, 600));
        decKeyScrollPane.getViewport().setBackground(decKeyTable.getBackground());
        Box bottomBox = Box.createHorizontalBox();
        bottomBox.add(decKeyScrollPane);
        add(bottomBox, "center");
    }

    public void showInfo(String NodeId)
        throws Exception
    {
        tfId.setText(NodeId);
        int nodeId = Integer.parseInt(NodeId);
        BTNodeInterface node = btree.readNode(nodeId);
        tfKeyNum.setText(Integer.toString(node.getLen()));
        tv = GetTableValues(NodeId);
        decKeyTable.setModel(tv);
    }

    public DefaultTableModel GetTableValues(String NodeId)
        throws Exception
    {
        DefaultTableModel tv = null;
        if(!NodeId.equals(""))
        {
            String Description[] = new String[1];
            Description[0] = "Key";
            int nodeId = Integer.parseInt(NodeId);
            BTNodeInterface node = btree.readNode(nodeId);
            int keyNum = node.getLen();
            long key[] = node.getKey();
            String Key[][] = new String[keyNum][1];
            for(int i = 0; i < keyNum; i++)
            {
                Key[i][0] = Long.toString(key[i]);
            }

            tv = new DefaultTableModel(Key, Description);
        }
        return tv;
    }
}