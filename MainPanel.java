import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class MainPanel extends JPanel {
	OrderClient oc;
	
	MainPanel(OrderClient oc){
		this.oc = oc;
		init();
	}

	void init(){
		setBounds(50, 100, 584, 761);
		setVisible(true);
		setLayout(new GridLayout(3, 3));
		for (int i=1; i<=9; i++){
			String text = "menu" + i;
			JButton btn = new JButton(text);
			btn.setPreferredSize(new Dimension(200, 100));
			add(btn);
		}
		setBackground(new Color(70, 70, 70));
	}

}
