package DemoLayout;

import java.awt.Component;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class SpringLayout {

	public static void main(String[] args) {
		JFrame frame = new JFrame("SpringLayout");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    Container contentPane = frame.getContentPane();

	    javax.swing.SpringLayout layout = new javax.swing.SpringLayout();
	    contentPane.setLayout(layout);

	    Component left = new JLabel("Left");
	    Component right = new JTextField(15);

	    contentPane.add(left);
	    contentPane.add(right);
	    //layout.putConstraint(javax.swing.SpringLayout.WEST, left, 10, javax.swing.SpringLayout.WEST, contentPane);
	    //layout.putConstraint(javax.swing.SpringLayout.NORTH, left, 25, javax.swing.SpringLayout.NORTH, contentPane);
	    //layout.putConstraint(javax.swing.SpringLayout.NORTH, right, 25, javax.swing.SpringLayout.NORTH, contentPane);
	    layout.putConstraint(javax.swing.SpringLayout.WEST, right, 20, javax.swing.SpringLayout.EAST, left);

	    frame.setSize(300, 100);
	    frame.setVisible(true);

	}

}
