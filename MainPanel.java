import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class MainPanel extends JPanel implements Runnable {
	OrderClient oc;
	JPanel listPanel, sidePanel, cartPanel;
	JButton cartBtn;
	
	MainPanel(OrderClient oc){
		this.oc = oc;
		init();
		setCartPanel();
		setListPanel();
		setSidePanel();
	}
	
	void init() {
		setBounds(0, 100, 684, 860);
		setVisible(true);
		setLayout(null);
		setBackground(new Color(250, 250, 250));
	}
	
	void setCartPanel(){
		cartPanel = new JPanel();
		cartPanel.setBounds(0, 661, 684, 120);
		cartPanel.setVisible(true);
		cartPanel.setLayout(null);
		cartPanel.setBackground(new Color(70, 200, 70));
		cartBtn = new JButton("▲");
		cartBtn.addActionListener(e -> {
			if(cartPanel.getSize().equals(new Dimension(684, 120))) {
				Thread th = new Thread(this);
				th.start();
				listPanel.setVisible(false);
				sidePanel.setVisible(false);
				cartBtn.setText("▼");
			} else {
				cartPanel.setBounds(0, 661, 684, 120);
				listPanel.setVisible(true);
				sidePanel.setVisible(true);
				cartBtn.setText("▲");
				oc.repaint();
			}
		});
		cartBtn.setBounds(624, 0, 40, 40);
		cartPanel.add(cartBtn);
		add(cartPanel);
	}
	
	void setListPanel(){
		listPanel = new JPanel();
		listPanel.setBounds(120, 0, 564, 661);
		listPanel.setVisible(true);
		listPanel.setLayout(new GridLayout(3, 3, 20, 20));
		listPanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 , 20));
		for (int i=1; i<=9; i++){
			String text = "menu" + i;
			JButton btn = new JButton(text);
			btn.setPreferredSize(new Dimension(200, 100));
			listPanel.add(btn);
		}
		add(listPanel);
	}
	
	void setSidePanel(){
		sidePanel = new JPanel();
		sidePanel.setBounds(0, 0, 120, 661);
		sidePanel.setVisible(true);
		sidePanel.setLayout(null);
		sidePanel.setBackground(new Color(70, 70, 70));
		JButton menuBtn1 = new JButton("커피");
		JButton menuBtn2 = new JButton("음료");
		JButton menuBtn3 = new JButton("디저트");
		menuBtn1.setBounds(10, 20, 100, 50);
		menuBtn2.setBounds(10, 100, 100, 50);
		menuBtn3.setBounds(10, 180, 100, 50);		
		sidePanel.add(menuBtn1);
		sidePanel.add(menuBtn2);
		sidePanel.add(menuBtn3);
		add(sidePanel);
	}

	@Override
	public void run() {
		int i = 661;
		while(i > 0) {
			if(i==5) {
				i -= 5;
			} else {
				i -= 8;
			}
			cartPanel.setBounds(0, i, 684, 785);
			try {
				Thread.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			oc.repaint();
		}
	}

}
