package com.team2.kiosk;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;


class MainPanel extends JPanel implements Runnable, MouseListener {
	private static final long serialVersionUID = -239742803172507112L;
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
	String pname = "", pno = "", cop1 = "", cop2 = "", cop3 = "", cop4 = "", cop5 = "";

	int cartCount = 0;
	int cartPrice = 0;
	
	Boolean iceOnly = false;
	
	Image imgUp;

	MainPanel(OrderClient oc) {
		this.oc = oc;
		init();
		setOrderServer();
		setCartPanel();
		setListPanel();
		setSidePanel();
		setOptionPanel();
		setStaffPanel();
		//onStaffPanel();
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
		oc.topPanel.setSize(684, 30);
		oc.setSize(700, 880);
		setLocation(0, 30);
		setSize(684, 780);
		oc.bottomPanel.setLocation(0, 811);
		oc.pageNameLabel.setText("");
		staffPanel.setVisible(true);
		staffPanel.setSize(684, 785);
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
		oc.topPanel.setSize(684, 100);
		oc.setSize(700, 1000);
		setLocation(0, 100);
		setSize(684, 860);
		oc.bottomPanel.setLocation(0, 881);
		oc.pageNameLabel.setText("주문화면");
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
		oc.mainBtn2.setText("확인>");
		oc.pageNameLabel.setText("옵션선택");
	}

	void offOptionPanel() {
		listPanel.setVisible(true);
		sidePanel.setVisible(true);
		cartPanel.setVisible(true);
		cartSubPanel1.setVisible(true);
		cartSubPanel2.setVisible(true);
		listScroll.setVisible(true);
		optionPanel.setVisible(false);
		oc.mainBtn2.setText("주문하기>");
		oc.pageNameLabel.setText("주문화면");
	}

	void init() {
		setBounds(0, 100, 684, 860);
		setVisible(true);
		setLayout(null);
		setBackground(new Color(250, 250, 250));
	}

	int optionNum(String optionName) {
		int num = 0;
		if (optionName.equals("먹고가기>"))
			num = 11;
		else if (optionName.equals("포장하기>"))
			num = 12;
		else if (optionName.equals("ICE"))
			num = 21;
		else if (optionName.equals("HOT"))
			num = 22;
		else if (optionName.equals("MEDIUM"))
			num = 31;
		else if (optionName.equals("LARGE"))
			num = 32;
		else if (optionName.equals("샷추가"))
			num = 41;
		else if (optionName.equals("추가안함"))
			num = 42;
		else if (optionName.equals("얼음많이"))
			num = 51;
		else if (optionName.equals("얼음조금"))
			num = 52;
		else if (optionName.equals("선택안함"))
			num = 53;
		else if (optionName.equals(""))
			num = 0;
		//System.out.println(num);
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
		subLabel.setBackground(new Color(240, 240, 240));
		subLabel.setBorder(BorderFactory.createEtchedBorder());
		String path[] = os.returnFileInfo(Integer.parseInt(pno));
		ImageIcon listimage = new ImageIcon(oc.returnImg("/src/noimage.png", 50, 55));
		try {
			listimage = new ImageIcon(oc.returnImg("/src/img/" + path[0] + "." + path[1], 70, 70));
		} catch (NullPointerException npe) {

		}

		JLabel imageLabel = new JLabel("");

		String labelNo = Integer.toString(cartCount);
		JLabel numLabel = new JLabel(labelNo);
		numLabel.setBounds(30, 15, 70, 70);
		//numLabel.setBorder(new EtchedBorder());
		//numLabel.setOpaque(true);
		//numLabel.setBackground(new Color(250, 250, 250));
		numLabel.setHorizontalAlignment(JLabel.CENTER);
		imageLabel.setIcon(listimage);
		imageLabel.setBounds(110, 15, 70, 70);
		imageLabel.setBorder(new EtchedBorder());
		imageLabel.setBackground(new Color(250, 250, 250));
		imageLabel.setOpaque(true);
		imageLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel nameLabel = new JLabel(name);
		nameLabel.setBounds(150, 15, 230, 70);
		nameLabel.setFont(new Font("HYPOST",Font.BOLD,13));
		nameLabel.setBorder(new EtchedBorder());
		nameLabel.setOpaque(true);
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setBackground(new Color(250, 250, 250));

		Object obj = os.productVector.get(2);
		String price = obj.toString();
		int priceInt = Integer.parseInt(price);
		cartPrice += priceInt;

		JLabel priceLabel = new JLabel();
		priceLabel.setBounds(570, 15, 90, 70);
		priceLabel.setBorder(new EtchedBorder());
		priceLabel.setBackground(new Color(255, 255, 255));
		priceLabel.setOpaque(true);
		priceLabel.setHorizontalAlignment(JLabel.CENTER);
		JLabel priceL = new JLabel(price);
		priceL.setBounds(0, 0, 50, 70);
		priceL.setFont(new Font("HYPOST",Font.BOLD,13));
		//priceL.setForeground(Color.WHITE);
		priceL.setHorizontalAlignment(JLabel.RIGHT);
		
		JLabel wonL = new JLabel("원");
		wonL.setBounds(50, 0, 30, 70);
		wonL.setFont(new Font("HYPOST",Font.BOLD,13));
		//wonL.setForeground(Color.WHITE);
		priceLabel.add(priceL);
		priceLabel.add(wonL);

		if (pno.startsWith("3")) { // 디저트 메뉴일 경우 옵션 라벨 추가안함
		} else {
			JLabel optionLabel1 = new JLabel(op1);
			JLabel optionLabel2 = new JLabel(op2);
			JLabel optionLabel3 = new JLabel(op3);
			JLabel optionLabel4 = new JLabel(op4);

			optionLabel1.setBounds(395, 15, 80, 35);
			optionLabel2.setBounds(475, 15, 80, 35);
			optionLabel3.setBounds(395, 50, 80, 35);
			optionLabel4.setBounds(475, 50, 80, 35);

			optionLabel1.setHorizontalAlignment(JLabel.CENTER);
			optionLabel2.setHorizontalAlignment(JLabel.CENTER);
			optionLabel3.setHorizontalAlignment(JLabel.CENTER);
			optionLabel4.setHorizontalAlignment(JLabel.CENTER);

			optionLabel1.setFont(new Font("HYPOST", Font.BOLD, 13));
			optionLabel2.setFont(new Font("HYPOST", Font.BOLD, 13));
			optionLabel3.setFont(new Font("HYPOST", Font.BOLD, 13));
			optionLabel4.setFont(new Font("HYPOST", Font.BOLD, 13));

			optionLabel1.setBorder(new EtchedBorder());
			optionLabel2.setBorder(new EtchedBorder());
			optionLabel3.setBorder(new EtchedBorder());
			optionLabel4.setBorder(new EtchedBorder());
			
			optionLabel1.setOpaque(true);
			optionLabel2.setOpaque(true);
			optionLabel3.setOpaque(true);
			optionLabel4.setOpaque(true);
			
			optionLabel1.setBackground(new Color(250, 250, 250));
			optionLabel2.setBackground(new Color(250, 250, 250));
			optionLabel3.setBackground(new Color(250, 250, 250));
			optionLabel4.setBackground(new Color(250, 250, 250));
			
			if(op1.equals("ICE")) {
				optionLabel1.setForeground(new Color(50, 50, 230));
			} else if (op1.equals("HOT")) {	
				optionLabel1.setForeground(new Color(230, 50, 50));
			}
			subLabel.add(optionLabel1);
			subLabel.add(optionLabel2);
			subLabel.add(optionLabel3);
			subLabel.add(optionLabel4);
		}
		subLabel.add(numLabel);
		subLabel.add(imageLabel);
		subLabel.add(nameLabel);
		subLabel.add(priceLabel);
		
		cartSubPanel1.add(subLabel);
		labelMap.put(cartCount, subLabel);
		orderLabel2.setText(Integer.toString(cartCount));
		orderLabel4.setText(Integer.toString(cartPrice));

		cop2 = op1;
		cop3 = op2;
		cop4 = op3;
		cop5 = op4;

		int pno1 = Integer.parseInt(pno);
		int pop1 = optionNum(cop1);
		int pop2 = optionNum(cop2);
		int pop3 = optionNum(cop3);
		int pop4 = optionNum(cop4);
		int pop5 = optionNum(cop5);
		/*
		 * System.out.println(cartCount + "\t" + pno + "\t" + cop1 + "\t" + cop2 +"\t" +
		 * cop3 + "\t" + cop4 + "\t" + cop5); System.out.println(cartCount + "," + pno1
		 * +", "+ pop1 +","+ pop2+","+pop3+","+pop4+","+pop5);
		 */
		os.insertCart(cartCount, pno1, pop1, pop2, pop3, pop4, pop5);
		setLabel();
	}

	void setLabel() {
		for (int i = 1; i <= labelMap.size(); i++) {
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
		cartLabel1.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		cartLabel2 = new JLabel("자세히보기");
		cartLabel2.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		
		Image imgDown = oc.returnImg("/src/down.png", 20, 20);
		imgUp = oc.returnImg("/src/up.png", 20, 20);
		
		cartBtn = new JButton();
		cartBtn.addActionListener(e -> {
			if (cartPanel.getSize().equals(new Dimension(684, 160))) {
				Thread th = new Thread(this);
				th.start();
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
		cartLabel1.setBounds(30, 0, 100, 35);
		cartLabel2.setBounds(550, 0, 120, 35);
		cartBtn.setBounds(634, 5, 25, 25);

		cartTopPanel.add(cartLabel1);
		cartTopPanel.add(cartLabel2);
		cartTopPanel.add(cartBtn);
		
		cartSubPanel1 = new JPanel();
		cartSubPanel1.setBounds(0, 35, 684, 721);
		cartSubPanel1.setVisible(true);
		cartSubPanel1.setLayout(new GridLayout(7, 1, 10, 0));

		cartSubPanel1.setBackground(new Color(250, 250, 250)); //카트 내부 컬러
		cartSubPanel2 = new JPanel();
		cartSubPanel2.setBounds(0, 750, 684, 35);
		cartSubPanel2.setVisible(true);
		cartSubPanel2.setLayout(null);
		cartSubPanel2.setBackground(new Color(255, 255, 255));
		JLabel orderLabel1 = new JLabel("수량:");
		orderLabel2 = new JLabel("0");
		JLabel orderLabel3 = new JLabel("합계:");
		orderLabel4 = new JLabel("0");
		cartDropBtn = new JButton("장바구니 비우기");
		cartDropBtn.setFont(new Font("배달의민족 주아", Font.PLAIN, 15));
		cartDropBtn.addActionListener(e ->{
			initCart(); // 카트초기화 및 원위치
			cartBtn.setIcon(new ImageIcon(imgUp));
			cartPanel.setBounds(0, 621, 684, 160);
			listScroll.setVisible(true);
			listPanel.setVisible(true);
			sidePanel.setVisible(true);
			repaint();
			oc.repaint();
		});
		orderLabel1.setBounds(30, 0, 80, 30);
		orderLabel2.setBounds(110, 0, 100, 30);
		orderLabel3.setBounds(220, 0, 100, 30);
		orderLabel4.setBounds(320, 0, 100, 30);
		cartDropBtn.setBounds(540, 1, 120, 28);

		orderLabel1.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		orderLabel2.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		orderLabel3.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		orderLabel4.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));

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

	void setListPanel() {
		listPanel = new JPanel();
		listPanel.setBounds(120, 0, 564, 621);
		// listPanel.setBounds(120, 0, 464, 521);
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
		try {
			os.selectProduct(1, cno);
		} catch (NullPointerException npe) {
			JOptionPane.showMessageDialog(null, "DB오류", "DB연결에 실패하였습니다.", JOptionPane.ERROR_MESSAGE);
		}
		for (int i = 0; i < os.productsVector.size(); i++) {
			Vector productList = os.productsVector.get(i);
			String path[] = os.returnFileInfo((Integer) productList.get(0));
			ImageIcon listimage = new ImageIcon(oc.returnImg("/src/noimage.png", 120, 125));

	        try {
	        	listimage = new ImageIcon(oc.returnImg("/src/img/" + path[0] + "." + path[1], 120, 125));
	        } catch (NullPointerException npe) {}
	        
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
			listBtn.addActionListener(e -> {
				if (cartCount == 7) {
					JOptionPane.showMessageDialog(null, "장바구니에는 최대 7개의 상품만 담을 수 있습니다.", "안내메시지",
							JOptionPane.WARNING_MESSAGE);
				} else {
					JButton selfBtn = (JButton) e.getSource();
					JLabel selfLabel = (JLabel) selfBtn.getComponent(0);
					pname = selfBtn.getText();
					pno = selfLabel.getText();
					if (pno.startsWith("3")) {
						addProductOnCart(pname, "HOT", "MEDIUM", "추가안함", "추가안함");
					} else if (pno.startsWith("2")){
						iceOnly = true;
						oc.op.optionBtn1.setVisible(false);
						oc.op.optionBtn2.setText("ICE ONLY");
						oc.op.optionBtn2.setBounds(240, 85, 200, 70);
						oc.op.optionimageLabel1.setText("#ICE");
						onOptionPanel();
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
		/*
		 * JLabel menuLetterLabel = new JLabel(new ImageIcon("메뉴"));
		 * menuLetterLabel.setBounds(0, 0, 200, 100);
		 * menuLetterLabel.setForeground(Color.WHITE); menuLetterLabel.setFont(new
		 * Font("HYPOST", Font.BOLD, 20)); sidePanel.add(menuLetterLabel);
		 */

		/*
		 * JSeparator menutopSolidline = new JSeparator();
		 * menutopSolidline.setBounds(17, 22, 50, 50);
		 * menutopSolidline.setForeground(Color.WHITE); // top line color
		 * menutopSolidline.setBackground(Color.black.brighter());
		 * sidePanel.add(menutopSolidline); //메뉴종류-커피,버튼
		 * sidePanel.add(menutopSolidline);
		 */
		ImageIcon sideBackgroundImg = new ImageIcon(oc.returnImg("/src/leftsideimg.png", 120, 621));
		JLabel sidebgLabel = new JLabel(sideBackgroundImg);
		sidebgLabel.setBounds(0, 0, 120, 621);
		
		
		ImageIcon sidemenuimg1 = new ImageIcon(oc.returnImg("/src/menu.png", 110, 40));
		String text1 = "커피";
		JButton menuBtn1 = new JButton(text1, sidemenuimg1);

		String text2 = "음료";
		JButton menuBtn2 = new JButton(text2, sidemenuimg1);

		String text3 = "디저트";
		JButton menuBtn3 = new JButton(text3, sidemenuimg1);

		String text4 =  "관리자";	
		JButton menuBtn4 = new JButton(text4, sidemenuimg1);
		menuBtn4.addActionListener(e -> { //관리자 버튼 액션
			try {
				JPanel panel = new JPanel(new BorderLayout());
				JLabel label = new JLabel("관리자 암호를 입력해주세요.");
				label.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				JTextField tf = new JTextField();
				panel.add(label, BorderLayout.NORTH);
				panel.add(label, BorderLayout.SOUTH);
				String answer = JOptionPane.showInputDialog(null, panel, "관리자 확인", JOptionPane.QUESTION_MESSAGE);
				if (answer.equals("java")) {
					onStaffPanel();
				}
			} catch(NullPointerException npe) {}
		});
		menuBtn1.setBounds(5, 0, 110, 50);
		menuBtn2.setBounds(5, 60, 110, 50);
		menuBtn3.setBounds(5, 120, 110, 50);
		menuBtn4.setBounds(5, 565, 110, 50);

		menuBtn1.addMouseListener(this);
		menuBtn2.addMouseListener(this);
		menuBtn3.addMouseListener(this);
		menuBtn4.addMouseListener(this);
	
		menuBtn1.addActionListener(new ChangeList());
		menuBtn2.addActionListener(new ChangeList());
		menuBtn3.addActionListener(new ChangeList());
		
		menuBtn1.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		menuBtn2.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		menuBtn3.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		menuBtn4.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		
		menuBtn1.setForeground(Color.WHITE);
		menuBtn2.setForeground(Color.WHITE);
		menuBtn3.setForeground(Color.WHITE);
		menuBtn4.setForeground(Color.WHITE);
		
		menuBtn1.setBackground(new Color(30, 30, 30, 250));
		menuBtn2.setBackground(new Color(30, 30, 30, 250));
		menuBtn3.setBackground(new Color(30, 30, 30, 250));
		menuBtn4.setBackground(new Color(30, 30, 30, 250));
		
		menuBtn1.setBorder(new BevelBorder(BevelBorder.RAISED, Color.white, Color.black, Color.white, Color.black));
		menuBtn2.setBorder(new BevelBorder(BevelBorder.RAISED, Color.white, Color.black, Color.white, Color.black));
		menuBtn3.setBorder(new BevelBorder(BevelBorder.RAISED, Color.white, Color.black, Color.white, Color.black));
		menuBtn4.setBorder(new BevelBorder(BevelBorder.RAISED, Color.white, Color.black, Color.white, Color.black));
		
		menuBtn1.setBorderPainted(false);
		menuBtn2.setBorderPainted(false);
		menuBtn3.setBorderPainted(false);
		menuBtn4.setBorderPainted(false);
		
		
		menuBtn1.setVerticalTextPosition(JButton.CENTER);
		menuBtn2.setVerticalTextPosition(JButton.CENTER);
		menuBtn3.setVerticalTextPosition(JButton.CENTER);
		menuBtn4.setVerticalTextPosition(JButton.CENTER);
		menuBtn1.setHorizontalTextPosition(JButton.CENTER);
		menuBtn2.setHorizontalTextPosition(JButton.CENTER);
		menuBtn3.setHorizontalTextPosition(JButton.CENTER);
		menuBtn4.setHorizontalTextPosition(JButton.CENTER);
		
		sidebgLabel.add(menuBtn1);
		sidebgLabel.add(menuBtn2);
		sidebgLabel.add(menuBtn3);
		sidebgLabel.add(menuBtn4);
		sidePanel.add(sidebgLabel);
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

	class ChangeList implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton selfBtn = (JButton) e.getSource();
			String text = selfBtn.getText();
			if (text.equals("커피")) {
				listPanel.removeAll();
				addlistButton(11);
				listPanel.revalidate();
				listPanel.repaint();
			} else if (text.equals("음료")) {
				listPanel.removeAll();
				addlistButton(22);
				listPanel.revalidate();
				listPanel.repaint();
			} else if (text.equals("디저트")) {
				// listScroll.removeAll();
				// listScroll.repaint();
				listPanel.removeAll();
				addlistButton(33);
				listPanel.repaint();
				listPanel.revalidate();
			}

		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		JButton btn = (JButton)e.getSource();
		Point point = btn.getLocation();
		//btn.setBorderPainted(true);
		btn.setFont(new Font("배달의민족 주아", Font.PLAIN, 25));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		JButton btn = (JButton)e.getSource();
		//btn.setBorderPainted(false);
		btn.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
	}
}
