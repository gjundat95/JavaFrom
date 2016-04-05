package DemoLayout;

import java.awt.Container;
import java.awt.Label;
import java.awt.TextField;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Jundat95");
		frame.setBounds(200, 10, 500, 400);
		
		Container contentPane = frame.getContentPane();
		GroupLayout layout = new GroupLayout(contentPane);
		contentPane.setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()
				.addComponent(new Label("Hii"))
				.addComponent(new TextField())
				);
		
		frame.add(contentPane);
		
		
		frame.setVisible(true);

	}

}
