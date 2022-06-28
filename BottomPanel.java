import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class BottomPanel extends JPanel {
	OrderClient oc;
	JPanel listPanel;
	
	BottomPanel(OrderClient oc){
		this.oc = oc;
		init();
		setListPanel();
	}
	
	void setListPanel() {
		listPanel = new JPanel();
		listPanel.setBounds(0, 0, 684, 150);
		listPanel.setBackground(new Color(200, 200, 200));
		add(listPanel);
	}

	void init(){
		setBounds(0, 761, 684, 200);
		setVisible(true);
		setLayout(null);
		setBackground(new Color(100, 100, 100));
	}
}
