package DemoLayout;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;

import javax.swing.*;
import javax.swing.JFrame;

public class DemoFlowLayout extends JFrame {

	public static void main(String[] args) {
		new DemoFlowLayout();
	}

	public DemoFlowLayout(){
		
		this.setTitle("Jundat95");
		this.setBounds(20, 20, 300, 200);
		this.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
		this.applyComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		JButton btn1 = new JButton("Button 1");
		JButton btn2 = new JButton("Button 2");
		JButton btn3 = new JButton("Button 3");
		JButton btn4 = new JButton("Button 3");
		JButton btn5 = new JButton("Button 3");
		
		this.add(btn1);
		this.add(btn2);
		this.add(btn3);
		this.add(btn4);
		this.add(btn5);
		
		this.show();
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}

	
	
}
