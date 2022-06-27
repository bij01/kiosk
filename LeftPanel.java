import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class LeftPanel extends JPanel {
	OrderClient oc;
	
	LeftPanel(OrderClient oc){
		this.oc = oc;
		init();
	}

	void init(){
		
		setSize(300, oc.HEIGHT);
		setVisible(true);
		setLayout(null);
		setBackground(new Color(70, 70, 70));
	}
	void test(){
	}
}
