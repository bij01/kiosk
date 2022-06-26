import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class OrderClient extends JFrame {
	final int WIDTH = 1000;
	final int HEIGHT = 700;

	void init(){
		setTitle("T����");
		setSize(WIDTH, HEIGHT);
		setPosition(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	void setPosition(int w_width, int w_height){  //������ �߾����� ����
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		int x_position = (gd.getDisplayMode().getWidth()/2);
		int y_position = (gd.getDisplayMode().getHeight()/2);
		x_position = x_position - (w_width/2);
		y_position = y_position - (w_height/2);
		setBounds(x_position, y_position, w_width, w_height);	
    }

	public static void main(String[] args) {
		new OrderClient().init();
	}
}
