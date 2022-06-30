import java.awt.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class MainPanel extends JPanel implements Runnable {
	OrderClient oc;
	JPanel listPanel, sidePanel, cartPanel, optionPanel;
	JPanel cartSubPanel1, cartSubPanel2;
<<<<<<< HEAD
	JButton cartBtn, listBtn, optionBtn1, optionBtn2, optionBtn3, optionBtn4, optionBtn5, optionBtn6, optionBtn7,
			optionBtn8, optionBtn9;
	JLabel cartLabel1, cartLabel2;
	Font font;
	Image coffee1 = returnImg("src/coffee1.png", 50, 60);

	MainPanel(OrderClient oc) {
=======
	JScrollPane listScroll;
	JButton cartBtn, listBtn;
	JLabel cartLabel1, cartLabel2, orderLabel2, orderLabel4;
	Font font;
	Image coffee1 = returnImg("src/coffee1.png", 50, 60);
	HashMap<Integer, JLabel> labelMap = new HashMap<Integer, JLabel>();
	int cartCount = 0;
	
	MainPanel(OrderClient oc){
>>>>>>> bfbdfebdddfb49f6cd87aeab1ae2278e71a63aec
		this.oc = oc;
		init();
		setCartPanel();
		setListPanel();
		setSidePanel();
		setOptionPanel();
		//testMode();
	}

	void testMode() {
		listPanel.setVisible(false);
		sidePanel.setVisible(false);
		cartPanel.setVisible(false);
		cartSubPanel1.setVisible(false);
		cartSubPanel2.setVisible(false);
		optionPanel.setVisible(true);
	}

	void init() {
		setBounds(0, 100, 684, 860);
		setVisible(true);
		setLayout(null);
		setBackground(new Color(250, 250, 250));
		Font font = new Font("HYPOST", Font.BOLD, 20);
	}

	void addSubLabel(String name, String op1, String op2, String op3, String op4) {
		cartCount += 1;
		String labelNo = Integer.toString(cartCount);
		JLabel subLabel = new JLabel(labelNo);
		subLabel.setFont(font);
		subLabel.setVisible(true);
		subLabel.setLayout(null);
		subLabel.setBackground(new Color(70, 250, 200));

		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon(coffee1));
		imageLabel.setBounds(30, 10, 50, 70);
		JLabel nameLabel = new JLabel(name);
		nameLabel.setBounds(90, 10, 230, 70);
		//nameLabel.setFont(font);
		nameLabel.setBorder(new EtchedBorder());
		nameLabel.setOpaque(true);
		nameLabel.setBackground(new Color(250, 250, 250));

		JLabel optionLabel1 = new JLabel(op1);
		JLabel optionLabel2 = new JLabel(op2);
		JLabel optionLabel3 = new JLabel(op3);
		JLabel optionLabel4 = new JLabel(op4);

		optionLabel1.setBounds(330, 10, 80, 30);
		optionLabel2.setBounds(420, 10, 80, 30);
		optionLabel3.setBounds(330, 50, 80, 30);
		optionLabel4.setBounds(420, 50, 80, 30);

		optionLabel1.setHorizontalAlignment(JLabel.CENTER);
		optionLabel2.setHorizontalAlignment(JLabel.CENTER);
		optionLabel3.setHorizontalAlignment(JLabel.CENTER);
		optionLabel4.setHorizontalAlignment(JLabel.CENTER);
<<<<<<< HEAD

		optionLabel1.setFont(font);
		optionLabel2.setFont(font);
		optionLabel3.setFont(font);
		optionLabel4.setFont(font);

		optionLabel1.setOpaque(true);
		optionLabel2.setOpaque(true);
		optionLabel3.setOpaque(true);
		optionLabel4.setOpaque(true);

=======
		
		
		
		
>>>>>>> bfbdfebdddfb49f6cd87aeab1ae2278e71a63aec
		optionLabel1.setBackground(new Color(250, 250, 250));
		optionLabel2.setBackground(new Color(250, 250, 250));
		optionLabel3.setBackground(new Color(250, 250, 250));
		optionLabel4.setBackground(new Color(250, 250, 250));

		subLabel.add(imageLabel);
		subLabel.add(nameLabel);
		subLabel.add(optionLabel1);
		subLabel.add(optionLabel2);
		subLabel.add(optionLabel3);
		subLabel.add(optionLabel4);
<<<<<<< HEAD

		cartSubPanel1.add(subLabel);
=======
		
		labelMap.put(cartCount, subLabel);
		
		setLabel();
	}
	
	void setLabel() {
		for(int i=1; i<=labelMap.size(); i++){
			cartSubPanel1.add(labelMap.get(i));
			cartSubPanel1.repaint();
			cartSubPanel2.repaint();
			//cartSubPanel1.repaint();
			cartPanel.repaint();
			oc.repaint();
		}
>>>>>>> bfbdfebdddfb49f6cd87aeab1ae2278e71a63aec
	}

	void setCartPanel() {
		Image imgDown = returnImg("src/down.png", 20, 20);
		Image imgUp = returnImg("src/up.png", 20, 20);
<<<<<<< HEAD

=======
		
>>>>>>> bfbdfebdddfb49f6cd87aeab1ae2278e71a63aec
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
			if (cartPanel.getSize().equals(new Dimension(684, 160))) {
				Thread th = new Thread(this);
				th.start();
<<<<<<< HEAD
				cartBtn.setIcon(new ImageIcon(imgDown));
=======
			    cartBtn.setIcon(new ImageIcon(imgDown));
			    listScroll.setVisible(false);
>>>>>>> bfbdfebdddfb49f6cd87aeab1ae2278e71a63aec
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
		cartPanel.add(cartLabel1);
		cartPanel.add(cartLabel2);
		cartPanel.add(cartBtn);

		cartSubPanel1 = new JPanel();
		cartSubPanel1.setBounds(60, 35, 564, 681);
		cartSubPanel1.setVisible(true);
		cartSubPanel1.setLayout(new GridLayout(7, 1, 10, 10));
		cartSubPanel1.setBackground(new Color(70, 70, 200));

		// 상품이 선택된 갯수만큼 추가
		//addSubLabel("에그 베이컨 과카몰리 샌드위치", "HOT", "MEDIUM", "추가안함", "얼음많이");
		//addSubLabel("아메리카노", "HOT", "MEDIUM", "추가안함", "얼음많이");
		//addSubLabel("그린티", "HOT", "MEDIUM", "추가안함", "얼음많이");
		//addSubLabel("에이드", "HOT", "MEDIUM", "추가안함", "얼음많이");
		//addSubLabel("에이드", "HOT", "MEDIUM", "추가안함", "얼음많이");
		// 추가 END
		cartPanel.add(cartSubPanel1);

		cartSubPanel2 = new JPanel();
		cartSubPanel2.setBounds(0, 750, 684, 35);
		cartSubPanel2.setVisible(true);
		cartSubPanel2.setLayout(null);
		cartSubPanel2.setBackground(new Color(250, 250, 250));
		JLabel orderLabel1 = new JLabel("주문수량:");
		orderLabel2 = new JLabel("0");
		JLabel orderLabel3 = new JLabel("주문금액:");
		orderLabel4 = new JLabel("0");
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
		//listPanel.setBounds(120, 0, 564, 621);
		listPanel.setBounds(120, 0, 464, 521);
		listPanel.setVisible(true);
<<<<<<< HEAD
		listPanel.setLayout(new GridLayout(3, 3, 20, 20));
		listPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		for (int i = 1; i <= 9; i++) {
			ImageIcon listimage = new ImageIcon(returnImg("./src/coffee1_americano.png", 120, 125));

=======
		listPanel.setLayout(new GridLayout(0, 3, 20, 20));
		listPanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 , 20));
		for (int i=1; i<=19; i++){
	        ImageIcon listimage = new ImageIcon(returnImg("./src/coffee1_americano.png", 120, 125));
		
>>>>>>> bfbdfebdddfb49f6cd87aeab1ae2278e71a63aec
			String text = "아메리카노";
			listBtn = new JButton(text, listimage);
			listBtn.setBackground(Color.WHITE);
			listBtn.setFont(new Font("HYPOST", Font.BOLD, 15));
			listBtn.setVerticalTextPosition(listBtn.BOTTOM); // 텍스트 아래로
			listBtn.setHorizontalTextPosition(listBtn.CENTER);
			listBtn.addActionListener(e ->{
				if (cartCount == 10) {
					JOptionPane.showMessageDialog(null, 
					    "장바구니에는 최대 10개의 상품만 담을 수 있습니다.",
					    "안내메시지",
					    JOptionPane.WARNING_MESSAGE);
				} else {
					addSubLabel("에이드", "HOT", "MEDIUM", "추가안함", "얼음많이");
					int curNum = Integer.parseInt(orderLabel2.getText());
					curNum+=1;
					orderLabel2.setText(Integer.toString(curNum));
				}
			});
			listPanel.add(listBtn);
		}
		listScroll = new JScrollPane(listPanel);
		listScroll.setBounds(120, 0, 564, 621);
		listScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		listScroll.getVerticalScrollBar().setUnitIncrement(18);
		listScroll.getVerticalScrollBar().setPreferredSize(new Dimension(15, 0));
		add(listScroll);
	}

	void setOptionPanel() {

		optionPanel = new JPanel();
		optionPanel.setBounds(0, 0, 684, 860);
		optionPanel.setVisible(false);
		optionPanel.setLayout(null);
		optionPanel.setBackground(new Color(50, 50, 50));
		// 옵션선택글자
		JLabel LetterLabe1 = new JLabel("|아이스/핫");
		LetterLabe1.setVisible(true);
		LetterLabe1.setFont(new Font("굴림", Font.BOLD, 30));
		LetterLabe1.setBounds(100, 28, 400, 100);
		LetterLabe1.setForeground(Color.BLACK);
		optionPanel.add(LetterLabe1);
		JLabel LetterLabel2 = new JLabel("|사이즈");
		LetterLabel2.setVisible(true);
		LetterLabel2.setFont(new Font("굴림", Font.BOLD, 30));
		LetterLabel2.setBounds(100, 158, 400, 100);
		LetterLabel2.setForeground(Color.BLACK);
		optionPanel.add(LetterLabel2);
		JLabel LetterLabe13 = new JLabel("|에스프레소샷");
		LetterLabe13.setVisible(true);
		LetterLabe13.setFont(new Font("굴림", Font.BOLD, 30));
		LetterLabe13.setBounds(100, 288, 400, 100);
		LetterLabe13.setForeground(Color.BLACK);
		optionPanel.add(LetterLabe13);
		JLabel LetterLabe14 = new JLabel("|얼음");
		LetterLabe14.setVisible(true);
		LetterLabe14.setFont(new Font("굴림", Font.BOLD, 30));
		LetterLabe14.setBounds(100, 422, 400, 100);
		LetterLabe14.setForeground(Color.BLACK);
		optionPanel.add(LetterLabe14);
		// 구분선
		JLabel optionLabel1 = new JLabel();
		optionLabel1.setBounds(65, 125, 550, 35);
		optionLabel1.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		optionPanel.add(optionLabel1);
		JLabel optionLabel2 = new JLabel();
		optionLabel2.setBounds(65, 255, 550, 35);
		optionLabel2.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		optionPanel.add(optionLabel2);
		JLabel optionLabel3 = new JLabel();
		optionLabel3.setBounds(65, 385, 550, 35);
		optionLabel3.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		optionPanel.add(optionLabel3);
		JLabel optionLabel4 = new JLabel();
		optionLabel4.setBounds(65, 515, 550, 35);
		optionLabel4.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		optionPanel.add(optionLabel4);

		// 옵션추가현황확인'옵션'
		JLabel optioncheckLabel = new JLabel("옵션");
		optioncheckLabel.setBounds(80, 600, 110, 50);
		optioncheckLabel.setVisible(true);
		optioncheckLabel.setForeground(Color.WHITE);
		optioncheckLabel.setFont(new Font("굴림", Font.BOLD, 30));
		optionPanel.add(optioncheckLabel);

		// 옵션추가현황확인체크박스 이미지+text
		String optionimagePath = "./src/optcheckboximage.png";
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(optionimagePath));
		} catch (Exception e) {
			System.out.println(e);
		}
		Image img = bufferedImage.getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon optionimage1 = new ImageIcon(img);

		JLabel optionimageLabel1 = new JLabel(optionimage1);
		optionimageLabel1.setBounds(80, 650, 110, 50);
		optionimageLabel1.setText("ICE");
		optionimageLabel1.setVisible(true);
		optionimageLabel1.setForeground(Color.WHITE);
		optionimageLabel1.setFont(new Font("굴림", Font.BOLD, 30));
		// optionimageLabel1.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel1.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel1.setHorizontalTextPosition(AbstractButton.RIGHT);
		optionPanel.add(optionimageLabel1);

		JLabel optionimageLabel2 = new JLabel(optionimage1);
		optionPanel.add(optionimageLabel2);
		optionimageLabel2.setBounds(185, 650, 180, 50);
		optionimageLabel2.setText("MEDIUM");
		optionimageLabel2.setVisible(true);
		optionimageLabel2.setForeground(Color.WHITE);
		optionimageLabel2.setFont(new Font("굴림", Font.BOLD, 30));
		// optionimageLabel2.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel2.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel2.setHorizontalTextPosition(AbstractButton.RIGHT);
		optionPanel.add(optionimageLabel2);

		JLabel optionimageLabel3 = new JLabel(optionimage1);
		optionimageLabel3.setBounds(360, 650, 145, 50);
		optionimageLabel3.setText("샷추가");
		optionimageLabel3.setVisible(true);
		optionimageLabel3.setForeground(Color.WHITE);
		optionimageLabel3.setFont(new Font("굴림", Font.BOLD, 25));
		// optionimageLabel3.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel3.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel3.setHorizontalTextPosition(AbstractButton.RIGHT);
		optionPanel.add(optionimageLabel3);

		JLabel optionimageLabel4 = new JLabel(optionimage1);
		optionimageLabel4.setBounds(480, 650, 165, 50);
		optionimageLabel4.setText("얼음많이");
		optionimageLabel4.setVisible(true);
		optionimageLabel4.setForeground(Color.WHITE);
		optionimageLabel4.setFont(new Font("굴림", Font.BOLD, 25));
		// optionimageLabel4.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel4.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel4.setHorizontalTextPosition(AbstractButton.RIGHT);
		optionPanel.add(optionimageLabel4);

		optionBtn1 = new JButton("ICE");
		optionBtn1.setForeground(Color.WHITE);
		optionBtn1.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn1.setFocusPainted(false);
		// BorderFactory.createLineBorder
		// inBtn.setBorder(BorderFactory.createEmptyBorder(3 , 3 , 3 , 3));
		optionBtn1.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn1.setBackground(new Color(51, 25, 0));
		optionBtn1.setBounds(100, 100, 200, 50);
		optionBtn2 = new JButton("HOT");
		optionBtn2.setForeground(Color.WHITE);
		optionBtn2.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn2.setFocusPainted(false);
		optionBtn2.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn2.setBackground(new Color(51, 25, 0));
		optionBtn2.setBounds(350, 100, 200, 50);

		optionBtn3 = new JButton("MEDIUM");
		optionBtn3.setForeground(Color.WHITE);
		optionBtn3.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn3.setFocusPainted(false);
		optionBtn3.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn3.setBackground(new Color(51, 25, 0));
		optionBtn3.setBounds(100, 230, 200, 50);
		optionBtn4 = new JButton("LARGE");
		optionBtn4.setForeground(Color.WHITE);
		optionBtn4.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn4.setFocusPainted(false);
		optionBtn4.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn4.setBackground(new Color(51, 25, 0));
		optionBtn4.setBounds(350, 230, 200, 50);
		optionBtn5 = new JButton("샷추가");
		optionBtn5.setForeground(Color.WHITE);
		optionBtn5.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn5.setFocusPainted(false);
		optionBtn5.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn5.setBackground(new Color(51, 25, 0));
		optionBtn5.setBounds(100, 360, 200, 50);
		optionBtn6 = new JButton("추가안함");
		optionBtn6.setForeground(Color.WHITE);
		optionBtn6.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn6.setFocusPainted(false);
		optionBtn6.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn6.setBackground(new Color(51, 25, 0));
		optionBtn6.setBounds(350, 360, 200, 50);
		optionBtn7 = new JButton("얼음조금");
		optionBtn7.setForeground(Color.WHITE);
		optionBtn7.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn7.setFocusPainted(false);
		optionBtn7.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn7.setBackground(new Color(51, 25, 0));
		optionBtn7.setBounds(100, 490, 150, 50);
		optionBtn8 = new JButton("얼음많이");
		optionBtn8.setForeground(Color.WHITE);
		optionBtn8.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn8.setFocusPainted(false);
		optionBtn8.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn8.setBackground(new Color(51, 25, 0));
		optionBtn8.setBounds(280, 490, 150, 50);
		optionBtn9 = new JButton("선택안함");
		optionBtn9.setForeground(Color.WHITE);
		optionBtn9.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn9.setFocusPainted(false);
		optionBtn9.setFont(new Font("HYPOST", Font.BOLD, 28));
		optionBtn9.setBackground(new Color(51, 25, 0));
		optionBtn9.setBounds(460, 490, 150, 50);

		JSeparator topSolidline = new JSeparator();
		topSolidline.setBounds(60, 12, 560, 560);
		topSolidline.setForeground(Color.black); // top line color
		topSolidline.setBackground(Color.black.brighter());
		optionPanel.add(topSolidline);
		JSeparator bottomSolidline = new JSeparator();
		bottomSolidline.setBounds(60, 766, 560, 560);
		bottomSolidline.setForeground(Color.black); // top line color
		bottomSolidline.setBackground(Color.black.brighter());
		optionPanel.add(bottomSolidline);

		optionPanel.add(optionBtn1);
		optionPanel.add(optionBtn2);
		optionPanel.add(optionBtn3);
		optionPanel.add(optionBtn4);
		optionPanel.add(optionBtn5);
		optionPanel.add(optionBtn6);
		optionPanel.add(optionBtn7);
		optionPanel.add(optionBtn8);
		optionPanel.add(optionBtn9);

		add(optionPanel);

	}

	void setSidePanel() {
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

}
