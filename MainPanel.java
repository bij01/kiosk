import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class MainPanel extends JPanel implements Runnable {
	OrderClient oc;
	JPanel listPanel, sidePanel, cartPanel;
	JButton cartBtn;
	JLabel cartLabel1, cartLabel2;
	
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
		Image imgDown = returnImg("src/down.png", 20, 20);
		Image imgUp = returnImg("src/up.png", 20, 20);
		Image coffee1 = returnImg("src/coffee1.png", 50, 60);
		Font font = new Font("맑은 고딕", Font.BOLD, 15);
		
		cartPanel = new JPanel();
		cartPanel.setBounds(0, 621, 684, 160);
		cartPanel.setVisible(true);
		cartPanel.setLayout(null);
		cartPanel.setBackground(new Color(70, 200, 70));
		cartLabel1 = new JLabel("최근선택상품");
		cartLabel1.setFont(font);
		cartLabel2 = new JLabel("내역 자세히보기");
		cartLabel2.setFont(font);
		cartBtn = new JButton();
		cartBtn.addActionListener(e -> {
			if(cartPanel.getSize().equals(new Dimension(684, 160))) {
				Thread th = new Thread(this);
				th.start();
			    cartBtn.setIcon(new ImageIcon(imgDown));
				listPanel.setVisible(false);
				sidePanel.setVisible(false);
				repaint();
			} else {
			    cartBtn.setIcon(new ImageIcon(imgUp));
				cartPanel.setBounds(0, 621, 684, 160);
				listPanel.setVisible(true);
				sidePanel.setVisible(true);
				repaint();
				oc.repaint();
			}
		});
		
	    cartBtn.setIcon(new ImageIcon(imgUp));
	    cartBtn.setHorizontalAlignment(SwingConstants.CENTER);
	    cartLabel1.setBounds(10, 0, 100, 35);
	    cartLabel2.setBounds(514, 0, 120, 35);
	    cartBtn.setBounds(634, 5, 25, 25);
		cartPanel.add(cartLabel1);
		cartPanel.add(cartLabel2);
		cartPanel.add(cartBtn);
		
		JPanel cartSubPanel1 = new JPanel();
		cartSubPanel1.setBounds(0, 35, 684, 661);
		cartSubPanel1.setVisible(true);
		cartSubPanel1.setLayout(null);
		cartSubPanel1.setBackground(new Color(70, 70, 200));
		// 상품이 선택된 갯수만큼 추가 -> 다른 메소드로 빼야함
		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon(coffee1));
		imageLabel.setBounds(30, 10, 50, 70);
		JLabel nameLabel = new JLabel("에그 베이컨 과카몰리 샌드위치");
		nameLabel.setBounds(90, 10, 230, 70);
		nameLabel.setFont(font);
		nameLabel.setBorder(new EtchedBorder());
		nameLabel.setOpaque(true);
		nameLabel.setBackground(new Color(250, 250, 250));
		
		JLabel optionLabel1 = new JLabel("HOT");
		JLabel optionLabel2 = new JLabel("MEDIUM");
		JLabel optionLabel3 = new JLabel("추가안함");
		JLabel optionLabel4 = new JLabel("얼음많이");
		
		optionLabel1.setBounds(230+100, 10, 80, 30);
		optionLabel2.setBounds(330+90, 10, 80, 30);
		optionLabel3.setBounds(90, 10, 80, 30);
		optionLabel4.setBounds(90, 10, 80, 30);
		
		optionLabel1.setHorizontalAlignment(JLabel.CENTER);
		optionLabel2.setHorizontalAlignment(JLabel.CENTER);
		optionLabel3.setHorizontalAlignment(JLabel.CENTER);
		optionLabel4.setHorizontalAlignment(JLabel.CENTER);
		
		optionLabel1.setFont(font);
		optionLabel2.setFont(font);
		optionLabel3.setFont(font);
		optionLabel4.setFont(font);
		
		optionLabel1.setOpaque(true);
		optionLabel2.setOpaque(true);
		optionLabel3.setOpaque(true);
		optionLabel4.setOpaque(true);
		
		optionLabel1.setBackground(new Color(250, 250, 250));
		optionLabel2.setBackground(new Color(250, 250, 250));
		optionLabel3.setBackground(new Color(250, 250, 250));
		optionLabel4.setBackground(new Color(250, 250, 250));
		
		cartSubPanel1.add(imageLabel);
		cartSubPanel1.add(nameLabel);
		cartSubPanel1.add(optionLabel1);
		cartSubPanel1.add(optionLabel2);
		cartSubPanel1.add(optionLabel3);
		cartSubPanel1.add(optionLabel4);
	
		cartPanel.add(cartSubPanel1);
		//
		
		JPanel cartSubPanel2 = new JPanel();
		cartSubPanel2.setBounds(0, 750, 684, 35);
		cartSubPanel2.setVisible(true);
		cartSubPanel2.setLayout(null);
		cartSubPanel2.setBackground(new Color(250, 250, 250));
		JLabel orderLabel1 = new JLabel("주문수량:");
		JLabel orderLabel2 = new JLabel("1");
		JLabel orderLabel3 = new JLabel("주문금액:");
		JLabel orderLabel4 = new JLabel("4500");
		orderLabel1.setBounds(10, 0, 100, 30);
		orderLabel2.setBounds(110, 0, 100, 30);
		orderLabel3.setBounds(220, 0, 100, 30);
		orderLabel4.setBounds(330, 0, 100, 30);
		orderLabel1.setFont(font);
		orderLabel2.setFont(font);
		orderLabel3.setFont(font);
		orderLabel4.setFont(font);
		cartSubPanel2.add(orderLabel1);
		cartSubPanel2.add(orderLabel2);
		cartSubPanel2.add(orderLabel3);
		cartSubPanel2.add(orderLabel4);
		add(cartSubPanel2);
		add(cartPanel);
	}
	
	Image returnImg(String path, int size1, int size2){
		Image img = null;
		try {
			BufferedImage bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(new File(path));
			} catch (Exception e){
				System.out.println(e);
			}
			img = bufferedImage.getScaledInstance(size1, size2, Image.SCALE_DEFAULT);
		  } catch (Exception ex) {
		    System.out.println(ex);
		  }
		return img;
	}
	
	void setListPanel(){
		listPanel = new JPanel();
		listPanel.setBounds(120, 0, 564, 621);
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
		sidePanel.setBounds(0, 0, 120, 621);
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
		int i = 621;
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
