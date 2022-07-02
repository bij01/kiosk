package com.team2.kiosk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class MainPanel extends JPanel implements Runnable, ActionListener  {
	OrderClient oc;
	OrderServerImpl os;
	
	JPanel listPanel, sidePanel, cartPanel;
	JPanel cartSubPanel1, cartSubPanel2;
	JPanel optionPanel, staffPanel;
	JButton cartBtn, listBtn, cartDropBtn;
	JLabel cartLabel1, cartLabel2, orderLabel2, orderLabel4;
	
	Font font;
	JScrollPane listScroll;
	HashMap<Integer, JLabel> labelMap = new HashMap<Integer, JLabel>();
	String pname="", pno="", cop1="", cop2="", cop3="", cop4="", cop5="";
	
	Image imgDown = returnImg("src/down.png", 20, 20);
	Image imgUp = returnImg("src/up.png", 20, 20);
	
	int cartCount = 0;
	int cartPrice = 0;
	
	MainPanel(OrderClient oc){
		
		this.oc = oc;
		init();
		setOrderServer();
		setCartPanel();
		setListPanel();
		setSidePanel();
		setOptionPanel();
		setStaffPanel();
		//onStaffPanel();
		cop1 = "매장";
		os.deleteCart();
	}
	
	void setOrderServer() {
		os = new OrderServerImpl();
		os.connectDB();
	}
	
	void setOptionPanel() {
		optionPanel = oc.op;
		add(optionPanel);
	}
	
	void setStaffPanel() {
		staffPanel = new StaffPanel(this);
		add(staffPanel);
	}
	
	void onStaffPanel() {
		listPanel.setVisible(false);
		sidePanel.setVisible(false);
		cartPanel.setVisible(false);
		cartSubPanel1.setVisible(false);
		cartSubPanel2.setVisible(false);
		listScroll.setVisible(false);
		oc.mainBtn1.setVisible(false);
		oc.mainBtn2.setVisible(false);
		oc.bottomPanel.setVisible(false);
		staffPanel.setVisible(true);
	}
	
	void offStaffPanel() {
		listPanel.setVisible(true);
		sidePanel.setVisible(true);
		cartPanel.setVisible(true);
		cartSubPanel1.setVisible(true);
		cartSubPanel2.setVisible(true);
		listScroll.setVisible(true);
		oc.mainBtn1.setVisible(true);
		oc.mainBtn2.setVisible(true);
		oc.bottomPanel.setVisible(true);
		staffPanel.setVisible(false);
	}

	void onOptionPanel() {
		listPanel.setVisible(false);
		sidePanel.setVisible(false);
		cartPanel.setVisible(false);
		cartSubPanel1.setVisible(false);
		cartSubPanel2.setVisible(false);
		listScroll.setVisible(false);
		optionPanel.setVisible(true);
		oc.mainBtn2.setText("확인");
	}
	
	void offOptionPanel() {
		listPanel.setVisible(true);
		sidePanel.setVisible(true);
		cartPanel.setVisible(true);
		cartSubPanel1.setVisible(true);
		cartSubPanel2.setVisible(true);
		listScroll.setVisible(true);
		optionPanel.setVisible(false);
		oc.mainBtn2.setText("주문하기");
	}

	void init() {
		setBounds(0, 100, 684, 860);
		setVisible(true);
		setLayout(null);
		setBackground(new Color(250, 250, 250));
		Font font = new Font("HYPOST",Font.BOLD,15);
	}
	
	int optionNum(String optionName) {
		int num = 0;
		if(optionName.equals("매장")) num = 11;
		else if(optionName.equals("포장")) num = 12;
		else if(optionName.equals("ICE")) num = 21;
		else if(optionName.equals("HOT")) num = 22;
		else if(optionName.equals("MEDIUM")) num = 31;
		else if(optionName.equals("LARGE")) num = 32;
		else if(optionName.equals("샷추가")) num = 41;
		else if(optionName.equals("추가안함")) num = 42;
		else if(optionName.equals("얼음많이")) num = 51;
		else if(optionName.equals("얼음조금")) num = 52;
		else if(optionName.equals("선택안함")) num = 53;
		else if(optionName.equals("디저트")) num = 0;
		return num;
	}

	void addProductOnCart(String name, String op1, String op2, String op3, String op4) {
		cartCount += 1;
		os.productVector.clear();
		os.selectProduct(2, Integer.parseInt(pno));
		
		JLabel subLabel = new JLabel();
		subLabel.setVisible(true);
		subLabel.setLayout(null);
		subLabel.setOpaque(true);
		subLabel.setBackground(new Color(255, 255, 255));
		subLabel.setBorder(BorderFactory.createEtchedBorder());
		String path[] = os.returnFileInfo(Integer.parseInt(pno));
		ImageIcon listimage = new ImageIcon(returnImg("./src/noimage.png", 50, 55));
        try {
        	listimage = new ImageIcon(returnImg("./src/img/" + path[0] + "." + path[1], 70, 70));
        } catch (NullPointerException npe) {
        	
        }
		
		JLabel imageLabel = new JLabel("");
		
		String labelNo = Integer.toString(cartCount);
		JLabel numLabel = new JLabel(labelNo);
		numLabel.setBounds(10, 30, 30, 30);
		//numLabel.setBorder(BorderFactory.createEtchedBorder());
		numLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setIcon(listimage);
		imageLabel.setBounds(50, 10, 70, 70);
		imageLabel.setBorder(BorderFactory.createEtchedBorder());
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel nameLabel = new JLabel(name);
		nameLabel.setBounds(130, 10, 230, 70);
		nameLabel.setFont(new Font("HYPOST",Font.BOLD,13));
		nameLabel.setBorder(new EtchedBorder());
		nameLabel.setOpaque(true);
		nameLabel.setBackground(new Color(250, 250, 250));
		
		Object obj = os.productVector.get(2);
		String price = obj.toString();
		int priceInt = Integer.parseInt(price);
		cartPrice += priceInt;
		
		JLabel priceLabel = new JLabel(price);
		priceLabel.setBounds(550, 10, 50, 70);
		priceLabel.setFont(new Font("HYPOST",Font.BOLD,13));
		priceLabel.setBorder(new EtchedBorder());
		priceLabel.setOpaque(true);
		priceLabel.setBackground(new Color(250, 250, 250));
		priceLabel.setHorizontalAlignment(priceLabel.RIGHT);
		
		JLabel wonLabel = new JLabel("원");
		wonLabel.setBounds(600, 10, 30, 70);
		wonLabel.setFont(new Font("HYPOST",Font.BOLD,13));
		wonLabel.setBorder(new EtchedBorder());
		wonLabel.setOpaque(true);
		wonLabel.setBackground(new Color(250, 250, 250));
		wonLabel.setHorizontalAlignment(wonLabel.LEFT);
		
		if (pno.startsWith("3")) { // 디저트 메뉴일 경우 옵션 라벨 추가안함
		} else {
			JLabel optionLabel1 = new JLabel(op1);
			JLabel optionLabel2 = new JLabel(op2);
			JLabel optionLabel3 = new JLabel(op3);
			JLabel optionLabel4 = new JLabel(op4);

			optionLabel1.setBounds(370, 10, 80, 30);
			optionLabel2.setBounds(460, 10, 80, 30);
			optionLabel3.setBounds(370, 50, 80, 30);
			optionLabel4.setBounds(460, 50, 80, 30);

			optionLabel1.setHorizontalAlignment(JLabel.CENTER);
			optionLabel2.setHorizontalAlignment(JLabel.CENTER);
			optionLabel3.setHorizontalAlignment(JLabel.CENTER);
			optionLabel4.setHorizontalAlignment(JLabel.CENTER);

			optionLabel1.setFont(new Font("HYPOST",Font.BOLD,13));
			optionLabel2.setFont(new Font("HYPOST",Font.BOLD,13));
			optionLabel3.setFont(new Font("HYPOST",Font.BOLD,13));
			optionLabel4.setFont(new Font("HYPOST",Font.BOLD,13));

			optionLabel1.setBorder(BorderFactory.createEtchedBorder());
			optionLabel2.setBorder(BorderFactory.createEtchedBorder());
			optionLabel3.setBorder(BorderFactory.createEtchedBorder());
			optionLabel4.setBorder(BorderFactory.createEtchedBorder());

			optionLabel1.setBackground(new Color(250, 250, 250));
			optionLabel2.setBackground(new Color(250, 250, 250));
			optionLabel3.setBackground(new Color(250, 250, 250));
			optionLabel4.setBackground(new Color(250, 250, 250));
			subLabel.add(optionLabel1);
			subLabel.add(optionLabel2);
			subLabel.add(optionLabel3);
			subLabel.add(optionLabel4);
		}
		subLabel.add(numLabel);
		subLabel.add(imageLabel);
		subLabel.add(nameLabel);
		subLabel.add(priceLabel);
		subLabel.add(wonLabel);
		
		cartSubPanel1.add(subLabel);
		labelMap.put(cartCount, subLabel);
		orderLabel2.setText(Integer.toString(cartCount));
		orderLabel4.setText(Integer.toString(cartPrice));
		
		cop2=op1; cop3=op2; cop4=op3; cop5=op4;
		
		int pno1 = Integer.parseInt(pno);
		int pop1 = optionNum(cop1);
		int pop2 = optionNum(cop2);
		int pop3 = optionNum(cop3); 
		int pop4 = optionNum(cop4);
		int pop5 = optionNum(cop5);
		/*
		System.out.println(cartCount + "\t" + pno + "\t" + cop1 + "\t" + cop2 +"\t"
				+ cop3 + "\t" + cop4 + "\t" + cop5);
		System.out.println(cartCount + "," +  pno1 +", "+ pop1 +","+ pop2+","+pop3+","+pop4+","+pop5);
		*/
		os.insertCart(cartCount, pno1, pop1, pop2, pop3, pop4, pop5);
		setLabel();
	}
	
	void setLabel() {
		for(int i=1; i<=labelMap.size(); i++){
			cartSubPanel1.add(labelMap.get(i));
			cartSubPanel1.repaint();
			cartSubPanel2.repaint();
			cartPanel.repaint();
			oc.repaint();
		}
	}
	
	void initCart() {
		labelMap.clear();
		cartCount = 0;
		cartPrice = 0;
		orderLabel2.setText("0");
		orderLabel4.setText("0");
		cartSubPanel1.removeAll();
		cartSubPanel1.repaint();
		oc.repaint();
		os.deleteCart();
	}

	void setCartPanel() {
		
		
		Color bgColor = new Color(230, 230, 230);

		cartPanel = new JPanel();
		cartPanel.setBounds(0, 621, 684, 160);
		cartPanel.setVisible(true);
		cartPanel.setLayout(null);
		cartPanel.setBackground(bgColor);
		cartPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		JPanel cartTopPanel = new JPanel();
		cartTopPanel.setBounds(0, 0, 684, 35);
		cartTopPanel.setVisible(true);
		cartTopPanel.setLayout(null);
		cartTopPanel.setBackground(new Color(255, 255, 255));
		cartTopPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		cartLabel1 = new JLabel("장바구니");
		cartLabel1.setFont(new Font("HYPOST",Font.PLAIN,12));
		cartLabel2 = new JLabel("자세히보기");
		cartLabel2.setFont(new Font("HYPOST",Font.PLAIN,12));
		cartBtn = new JButton();
		cartBtn.addActionListener(e -> {
			if (cartPanel.getSize().equals(new Dimension(684, 160))) {
				Thread th = new Thread(this);
				th.start();
				cartBtn.setIcon(new ImageIcon(imgDown));
			    cartBtn.setIcon(new ImageIcon(imgDown));
			    listScroll.setVisible(false);
				listPanel.setVisible(false);
				sidePanel.setVisible(false);
				repaint();
			} else {
				cartBtn.setIcon(new ImageIcon(imgUp));
				cartPanel.setBounds(0, 621, 684, 160);
				listScroll.setVisible(true);
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
		
		cartTopPanel.add(cartLabel1);
		cartTopPanel.add(cartLabel2);
		cartTopPanel.add(cartBtn);
		cartSubPanel1 = new JPanel();
		cartSubPanel1.setBounds(0, 35, 684, 681);
		cartSubPanel1.setVisible(true);
		cartSubPanel1.setLayout(new GridLayout(7, 1, 10, 0));
		cartSubPanel1.setBackground(bgColor);
		
		cartSubPanel2 = new JPanel();
		cartSubPanel2.setBounds(0, 750, 684, 35);
		cartSubPanel2.setVisible(true);
		cartSubPanel2.setLayout(null);
		cartSubPanel2.setBackground(new Color(255,255,255));
		JLabel orderLabel1 = new JLabel("수량:");
		orderLabel2 = new JLabel("0");
		JLabel orderLabel3 = new JLabel("합계:");
		orderLabel4 = new JLabel("0");
		cartDropBtn = new JButton("장바구니 비우기");
		cartDropBtn.addActionListener(e ->{
			initCart();
		});
		orderLabel1.setBounds(10, 0, 100, 30);
		orderLabel2.setBounds(110, 0, 100, 30);
		orderLabel3.setBounds(220, 0, 100, 30);
		orderLabel4.setBounds(330, 0, 100, 30);
		cartDropBtn.setBounds(530, 1, 120, 28);
		
		orderLabel1.setFont(font);
		orderLabel2.setFont(font);
		orderLabel3.setFont(font);
		orderLabel4.setFont(font);
		
		cartSubPanel2.add(orderLabel1);
		cartSubPanel2.add(orderLabel2);
		cartSubPanel2.add(orderLabel3);
		cartSubPanel2.add(orderLabel4);
		cartSubPanel2.add(cartDropBtn);
		cartSubPanel2.setBorder(new BevelBorder(BevelBorder.RAISED));
		
		cartPanel.add(cartTopPanel);
		cartPanel.add(cartSubPanel1);
		
		add(cartSubPanel2);
		add(cartPanel);
	}

	Image returnImg(String path, int size1, int size2) {
		Image img = null;
		try {
			BufferedImage bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(new File(path));
			} catch (Exception e) {
				System.out.println(e);
			}
			img = bufferedImage.getScaledInstance(size1, size2, Image.SCALE_DEFAULT);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return img;
	}

	void setListPanel() {
		listPanel = new JPanel();
		listPanel.setBounds(120, 0, 564, 621);
		//listPanel.setBounds(120, 0, 464, 521);
		listPanel.setVisible(true);
		listPanel.setLayout(new GridLayout(0, 3, 20, 20));
		listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		addlistButton(11);
		listScroll = new JScrollPane(listPanel);
		listScroll.setBounds(120, 0, 564, 621);
		listScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScroll.getVerticalScrollBar().setUnitIncrement(18);
		listScroll.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
		add(listScroll);
	}	
	void addlistButton(int cno) {
		os.productsVector.clear();
		os.selectProduct(1, cno);
		for (int i=0; i<os.productsVector.size(); i++){
			Vector productList = os.productsVector.get(i);
			String path[] = os.returnFileInfo((Integer)productList.get(0));
			ImageIcon listimage = new ImageIcon(returnImg("./src/noimage.png", 120, 125));
	        try {
	        	listimage = new ImageIcon(returnImg("./src/img/" + path[0] + "." + path[1], 120, 125));
	        } catch (NullPointerException npe) {
	        	
	        }
	        
			String name = (String)productList.get(1);
			pno = Integer.toString((Integer)productList.get(0));
			JLabel pnoLabel = new JLabel(pno);
			pnoLabel.setVisible(false);
			listBtn = new JButton(name, listimage);
			listBtn.setLayout(null);
			listBtn.setPreferredSize(new Dimension(100, 150));
			listBtn.setBackground(Color.WHITE);
			listBtn.setFont(new Font("HYPOST", Font.BOLD, 12));
			listBtn.setVerticalTextPosition(listBtn.BOTTOM); // 텍스트 아래로
			listBtn.setHorizontalTextPosition(listBtn.CENTER);
			listBtn.addActionListener(e ->{
				if (cartCount == 7) {
					JOptionPane.showMessageDialog(null, 
					    "장바구니에는 최대 7개의 상품만 담을 수 있습니다.",
					    "안내메시지",
					    JOptionPane.WARNING_MESSAGE);
				} else {
					JButton selfBtn = (JButton)e.getSource();
					JLabel selfLabel = (JLabel)selfBtn.getComponent(0);
					pname = selfBtn.getText();
					pno = selfLabel.getText();
					if (pno.startsWith("3")) {
						addProductOnCart(pname, "디저트", "디저트", "디저트", "디저트");
					} else {
						onOptionPanel();
					}
				}
			});
			listBtn.add(pnoLabel);
			listPanel.add(listBtn);
		}
	}

	
	void setSidePanel() {
		sidePanel = new JPanel();
		sidePanel.setBounds(0, 0, 120, 621);
		sidePanel.setVisible(true);
		sidePanel.setLayout(null);
		sidePanel.setBackground(new Color(30, 30, 30));
	
	
		JLabel menuLetterLabel = new JLabel("메뉴");
		menuLetterLabel.setBounds(14, 3, 100, 50);
		menuLetterLabel.setForeground(Color.WHITE);
		menuLetterLabel.setFont(new Font("HYPOST", Font.BOLD, 20));
		sidePanel.add(menuLetterLabel);

		/*
		JSeparator menutopSolidline = new JSeparator();
		menutopSolidline.setBounds(17, 22, 50, 50);
		menutopSolidline.setForeground(Color.WHITE); // top line color
		menutopSolidline.setBackground(Color.black.brighter());
		add(menutopSolidline);
		

		//메뉴종류-커피,버튼
		sidePanel.add(menutopSolidline);
		*/
		String text =  "커피";
		ImageIcon sidemenuimg1 = new ImageIcon(returnImg("./src/a.png", 120, 60));
		
		JButton menuBtn1 = new JButton(text, sidemenuimg1);
		menuBtn1.setFont(new Font("HYPOST", Font.BOLD, 15));
		menuBtn1.setLayout(null);
		menuBtn1.setPreferredSize(new Dimension(145, 80));
		menuBtn1.setForeground(Color.WHITE);
		menuBtn1.setBounds(-16, 50, 150, 50);
		menuBtn1.setBorderPainted(false);
		menuBtn1.setBackground(new Color(255,0,0,0));
		menuBtn1.setVerticalTextPosition(menuBtn1.CENTER); // 텍스트 아래로
		menuBtn1.setHorizontalTextPosition(menuBtn1.CENTER);
		//menuBtn1.setContentAreaFilled(false);
		//menuBtn1.setFocusPainted(false);
		//menuBtn1.setOpaque(false);
		
		String text2 =  "음료";	
		JButton menuBtn2 = new JButton(text2, sidemenuimg1);
		menuBtn2.setFont(new Font("HYPOST", Font.BOLD, 15));
		menuBtn2.setLayout(null);
		menuBtn2.setPreferredSize(new Dimension(145, 80));
		menuBtn2.setForeground(Color.WHITE);
		menuBtn2.setBounds(-16, 110, 150, 50);
		menuBtn2.setBorderPainted(false);
		menuBtn2.setBackground(new Color(255,0,0,0));
		menuBtn2.setVerticalTextPosition(menuBtn2.CENTER); // 텍스트 아래로
		menuBtn2.setHorizontalTextPosition(menuBtn2.CENTER);
		
		String text3 =  "디저트";	
		JButton menuBtn3 = new JButton(text3, sidemenuimg1);
		menuBtn3.setFont(new Font("HYPOST", Font.BOLD, 15));
		menuBtn3.setLayout(null);
		menuBtn3.setPreferredSize(new Dimension(145, 80));
		menuBtn3.setForeground(Color.WHITE);
		menuBtn3.setBounds(-16, 170, 150, 50);
		menuBtn3.setBorderPainted(false);
		menuBtn3.setBackground(new Color(255,0,0,0));
		menuBtn3.setVerticalTextPosition(menuBtn3.CENTER); // 텍스트 아래로
		menuBtn3.setHorizontalTextPosition(menuBtn3.CENTER);
		
		String text4 =  "관리자";	
		JButton menuBtn4 = new JButton(text4, sidemenuimg1);
		menuBtn4.setFont(new Font("HYPOST", Font.BOLD, 15));
		menuBtn4.setLayout(null);
		menuBtn4.setPreferredSize(new Dimension(145, 80));
		menuBtn4.setForeground(Color.WHITE);
		menuBtn4.setBounds(-16, 550, 150, 50);
		menuBtn4.setBorderPainted(false);
		menuBtn4.setBackground(new Color(255,0,0,0));
		menuBtn4.setVerticalTextPosition(menuBtn4.CENTER); // 텍스트 아래로
		menuBtn4.setHorizontalTextPosition(menuBtn4.CENTER);
		
		menuBtn1.addActionListener(new ChangeList());
		menuBtn2.addActionListener(new ChangeList());
		menuBtn3.addActionListener(new ChangeList());
		menuBtn4.addActionListener(e -> {
			onStaffPanel();
		});

		sidePanel.add(menuBtn1);
		sidePanel.add(menuBtn2);
		sidePanel.add(menuBtn3);
		sidePanel.add(menuBtn4);
		add(sidePanel);	
	}
	
	@Override
	public void run() {
		int i = 621;
		while (i > 0) {
			if (i == 5) {
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
	
	class ChangeList implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selfBtn = (JButton)e.getSource();
			String text = selfBtn.getText();
			if(text.equals("커피")) {
				listPanel.removeAll();
				addlistButton(11);
				listPanel.revalidate();
				listPanel.repaint();
			} else if(text.equals("음료")) {
				listPanel.removeAll();
				addlistButton(22);
				listPanel.revalidate();
				listPanel.repaint();
			} else if(text.equals("디저트")) {
				//listScroll.removeAll();
				//listScroll.repaint();
				listPanel.removeAll();
				addlistButton(33);
				listPanel.repaint();
				listPanel.revalidate();
			}
			
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton Btn = (JButton) e.getSource();
		String btnText = Btn.getText();
		
	}
}
