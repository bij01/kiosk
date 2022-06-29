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
	JScrollPane listScroll;
	JButton cartBtn, listBtn;
	JLabel cartLabel1, cartLabel2, orderLabel2, orderLabel4;
	Font font;
	Image coffee1 = returnImg("src/coffee1.png", 50, 60);
	HashMap<Integer, JLabel> labelMap = new HashMap<Integer, JLabel>();
	int cartCount = 0;
	
	MainPanel(OrderClient oc){
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
		Font font = new Font("맑은 고딕", Font.BOLD, 15);
	}
	
	void addSubLabel(String name, String op1, String op2, String op3, String op4) {
		cartCount += 1;
		String labelNo = Integer.toString(cartCount);
		JLabel subLabel = new JLabel(labelNo);
		//subLabel.setBounds(0, 0, 684, 180);
		//subLabel.setPreferredSize(new Dimension(684, 180));
		subLabel.setVisible(true);
		subLabel.setLayout(null);
		subLabel.setBackground(new Color(70, 250, 200));
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setIcon(new ImageIcon(coffee1));
		imageLabel.setBounds(30, 10, 50, 70);
		JLabel nameLabel = new JLabel(name);
		nameLabel.setBounds(90, 10, 230, 70);
		nameLabel.setFont(font);
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
		/*
		JLabel countLabel = new JLabel("1");
		countLabel.setBounds(560, 25, 100, 30);
		countLabel.setOpaque(true);
		countLabel.setHorizontalAlignment(JLabel.CENTER);
		JButton countMinusBtn = new JButton("X");
		countMinusBtn.addActionListener(e ->{
			int count = Integer.parseInt(countLabel.getText());
			count -= 1;
			String newCount = Integer.toString(count);
			if (newCount.equals("0")) {
				JButton selfBtn = (JButton) e.getSource();
				JLabel selfLabel = (JLabel) selfBtn.getParent().getParent();
				System.out.println(selfLabel.getText());
				//System.out.println(selfBtn.getParent());
				int thisIndex = Integer.parseInt(selfLabel.getText());
				System.out.println("Label: " + labelMap.get(thisIndex));
				
				// 삭제된 라벨의 index 번호가 마지막 번호가 아닐 경우 해당 index 번호 재정리
				// 1, 2, 3, 4 중 - 2번 삭제 -> 3, 4 -> 2, 3 으로 자리를 옮김
				
				if (thisIndex < labelMap.size()) {
					Vector<JLabel> tempV = new Vector<JLabel>();
					for (int i=thisIndex + 1; i <= labelMap.size(); i++) {
						JLabel label = new JLabel();
						label = labelMap.get(i);
						label.setText(Integer.toString(i));
						tempV.add(label);
						System.out.println(labelMap.get(i));
					}
					System.out.println("temp 사이즈: " + tempV.size());
					int labelMapSize = labelMap.size();
					for (int i=thisIndex; i <= labelMap.size(); i++) {
						labelMap.remove(i);
					}
					cartSubPanel1.remove(selfLabel);
					cartSubPanel1.repaint();
					//i = 2 -> 0, i = 3 -> 1
					
					for(int i=thisIndex; i<labelMapSize; i++){
						System.out.println("thisindex: " + (i-thisIndex) + "i:" + i + ", 라벨맵 사이즈: " + labelMapSize);
						labelMap.put(i, tempV.get(i - thisIndex));
						setLabel();
					}
					
					cartSubPanel1.repaint();
					setLabel();
					cartSubPanel1.repaint();
				} else {
					labelMap.remove(thisIndex);
					cartSubPanel1.remove(selfLabel);
					cartSubPanel1.repaint();
					setLabel();
					cartSubPanel1.repaint();
				}
			} else {
				countLabel.setText(newCount);
			}
		});
		
		JButton countPlusBtn = new JButton("+");
		countPlusBtn.addActionListener(e ->{
			addSubLabel("티라미수", "HOT", "MEDIUM", "추가안함", "얼음많이");
		});
		
		countMinusBtn.setBounds(0, 0, 30, 30);
		countPlusBtn.setBounds(70, 0, 30, 30);
		countLabel.add(countMinusBtn);
		countLabel.add(countPlusBtn);
		subLabel.add(countLabel);
		
		JButton deleteBtn = new JButton("X");
		deleteBtn.addActionListener(e ->{
			labelMap.clear();
			cartCount = 0;
			cartSubPanel1.removeAll();
			cartSubPanel1.repaint();
		});
		deleteBtn.setBounds(520, 25, 45, 45);
		subLabel.add(deleteBtn);
		*/
		subLabel.add(imageLabel);
		subLabel.add(nameLabel);
		subLabel.add(optionLabel1);
		subLabel.add(optionLabel2);
		subLabel.add(optionLabel3);
		subLabel.add(optionLabel4);
		
		labelMap.put(cartCount, subLabel);
		
		setLabel();
	}
	
	void setLabel() {
		System.out.println("라벨맵 사이즈: "+labelMap.size());
		for(int i=1; i<=labelMap.size(); i++){
			cartSubPanel1.add(labelMap.get(i));
			cartSubPanel1.repaint();
			cartSubPanel2.repaint();
			//cartSubPanel1.repaint();
			cartPanel.repaint();
			oc.repaint();
		}
	}
	
	void setCartPanel(){
		Image imgDown = returnImg("src/down.png", 20, 20);
		Image imgUp = returnImg("src/up.png", 20, 20);
		
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
		cartPanel.add(cartLabel1);
		cartPanel.add(cartLabel2);
		cartPanel.add(cartBtn);
		
		cartSubPanel1 = new JPanel();
		cartSubPanel1.setBounds(0, 35, 684, 661);
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
		//listPanel.setBounds(120, 0, 564, 621);
		listPanel.setBounds(120, 0, 464, 521);
		listPanel.setVisible(true);
		listPanel.setLayout(new GridLayout(0, 3, 20, 20));
		listPanel.setBorder(BorderFactory.createEmptyBorder(20 , 20 , 20 , 20));
		for (int i=1; i<=19; i++){
	        ImageIcon listimage = new ImageIcon(returnImg("./src/coffee1_americano.png", 120, 125));
		
			String text = "아메리카노";
			listBtn = new JButton(text, listimage);	
			listBtn.setBackground(Color.WHITE);
			listBtn.setFont(new Font("HYPOST",Font.BOLD,15));
			listBtn.setVerticalTextPosition(listBtn.BOTTOM);  // 텍스트 아래로
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
		listScroll.getVerticalScrollBar().setBackground(new Color(80, 80, 80));
		add(listScroll);
	}
	void setOptionPanel() {
		optionPanel = new JPanel();
		optionPanel.setBounds(0, 0, 684, 860);
		optionPanel.setVisible(false);
		optionPanel.setLayout(null);
		optionPanel.setBackground(new Color(50, 50, 50));
		//옵션 패널 
		
		//
		add(optionPanel);
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
