import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class OrderClient extends JFrame {
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	Container cp;
	JPanel leftPanel;

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
		JPanel leftPanel = new LeftPanel(this);
		cp.setBackground(new Color(150, 150, 150));
		cp.add(leftPanel);
	}

	public static void main(String[] args) {
		new OrderClient().init();
	}
}
