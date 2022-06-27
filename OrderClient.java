import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class OrderClient extends JFrame {
	final int WIDTH = 700;
	final int HEIGHT = 1000;

	Container cp;
	JPanel leftPanel, mainPanel;

	void init(){
		setTitle("KIOSK");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		setMain();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void setMain(){
		cp = getContentPane();
		cp.setLayout(null);
		leftPanel = new LeftPanel(this);
		mainPanel = new MainPanel(this);
		cp.setBackground(new Color(150, 150, 150));
		//cp.add(leftPanel);
		cp.add(mainPanel);
	}

	public static void main(String[] args) {
		new OrderClient().init();
	}
}
