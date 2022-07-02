package com.team2.kiosk;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.security.auth.login.Configuration;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.BevelBorder;

import com.formdev.flatlaf.*;

class OrderClient extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7610854644979608019L;
	final int WIDTH = 700;
	final int HEIGHT = 1000;
	
	MainPanel mainPanel;
	OptionPanel op;

	Container cp;
	JPanel firstPanel, topPanel, bottomPanel;
	JButton inBtn, outBtn, mainBtn1, mainBtn2;
	
	void testMode(){ // 바로 두번째 화면으로 넘어가기(개발 끝나면 삭제)
		firstPanel.setVisible(false);
		mainPanel.setVisible(true);
		bottomPanel.setVisible(true);
	}

	void init() {
		setTitle("아싸커피");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		cp = getContentPane();
		cp.setLayout(null);
		setFirstPanel();
		addOptionMember();
		setTopPanel();
		setBottomPanel();
		setMainPanel();
	//	testMode();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	void addOptionMember() {
		op = new OptionPanel();
	}
	
	void setFirstPanel() {
		firstPanel = new JPanel();
		firstPanel.setBounds(0, 0, 684, 961);
		firstPanel.setLayout(null);
		firstPanel.setVisible(true);
		firstPanel.setBackground(new Color(150, 70, 70));
		
		String bgimagePath = "./src/originbackground.png";
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(bgimagePath));
		} catch (Exception e) {
			System.out.println(e);
		}
		Image img = bufferedImage.getScaledInstance(680, 960, Image.SCALE_DEFAULT);
		ImageIcon bgimg = new ImageIcon(img);
		JLabel bgimageLabel = new JLabel(bgimg);
		bgimageLabel.setBounds(0, 0, 680, 960);
		
		
		//버튼이미지입히기
		String butimagePath ="./src/butbackground.png";
		bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(butimagePath));
		} catch (Exception e) {
			System.out.println(e);
		}
		Image img2 = bufferedImage.getScaledInstance(330,80, Image.SCALE_DEFAULT);
		ImageIcon butimage = new ImageIcon(img2);
		
	
		
		inBtn = new JButton("먹고가기>",butimage);
		inBtn.setVerticalTextPosition(inBtn.CENTER); // 텍스트 아래로
		inBtn.setHorizontalTextPosition(inBtn.CENTER);
		inBtn.setForeground(Color.WHITE);
		inBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		inBtn.setFocusPainted(false);
		// BorderFactory.createLineBorder
		// inBtn.setBorder(BorderFactory.createEmptyBorder(3 , 3 , 3 , 3));
		inBtn.setFont(new Font("휴먼고딕체", Font.BOLD, 40));
		inBtn.setBackground(new Color(0,0,0,0));
		inBtn.setBounds(5, 860, 330, 80);
		inBtn.addActionListener(this);
		outBtn = new JButton("포장하기>",butimage);
		outBtn.setVerticalTextPosition(outBtn.CENTER); // 텍스트 아래로
		outBtn.setHorizontalTextPosition(outBtn.CENTER);	
		outBtn.setBounds(345, 860, 330, 80);
		outBtn.addActionListener(this);
		outBtn.setForeground(Color.WHITE);
		outBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		outBtn.setFocusPainted(false);
		outBtn.setFont(new Font("휴먼고딕체", Font.BOLD, 40));
		outBtn.setBackground(new Color(0,0,0,0));
		

		bgimageLabel.add(inBtn);
		bgimageLabel.add(outBtn);
		firstPanel.add(bgimageLabel);
		firstPanel.setBorder(new BevelBorder(BevelBorder.RAISED));	
		cp.add(firstPanel);
		
		
		/*JPanel butbtopPanel = new JPanel();
		butbtopPanel.setBounds(0, 0, 682, 150);
		butbtopPanel.setVisible(true);
		butbtopPanel.setBackground(new Color(0, 0, 0));
	    
	
		
		JPanel butbottomPanel = new JPanel();
		
		butbottomPanel.setBounds(0, 820, 682, 150);
		butbottomPanel.setVisible(true);
		butbottomPanel.setBackground(new Color(0, 0, 0));
		cp.add(butbtopPanel);
		cp.add(butbottomPanel);*/
		//cp.add(firstPanel);
		
		
		//butbottomPanel = new JPanel();
		
		
	
	
	}

	void setTopPanel() {
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, 684, 100);
		topPanel.setBackground(new Color(30, 30, 30));
		String topimagePath = "./src/menutitle1.png";
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(topimagePath));
		} catch (Exception e) {
			System.out.println(e);
		}
		Image img = bufferedImage.getScaledInstance(400, 100, Image.SCALE_DEFAULT);
		ImageIcon maintitleImage = new ImageIcon(img);
		JLabel mainTitleLabel = new JLabel(maintitleImage);
		mainTitleLabel.setBounds(0, -15, 500, 100);
		mainTitleLabel.setBounds(-50, 0, 500, 100);
		topPanel.add(mainTitleLabel);
		
		cp.add(topPanel);
	}

	void setMainPanel() {
		mainPanel = new MainPanel(this);
		mainPanel.setVisible(false);
		cp.setBackground(new Color(150, 150, 150));
		cp.add(mainPanel);
	}

	void setBottomPanel() {
		bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 881, 684, 80);
		bottomPanel.setVisible(false);
		bottomPanel.setBackground(new Color(30, 30, 30));
		bottomPanel.setLayout(null);
		mainBtn1 = new JButton("처음으로");
		mainBtn2 = new JButton("주문하기");
		mainBtn1.setForeground(Color.WHITE);
		mainBtn2.setForeground(Color.WHITE);
		mainBtn1.setBackground(new Color(10, 10, 10));
		mainBtn2.setBackground(new Color(10, 10, 10));
		mainBtn1.setFont(new Font("HYPOST", Font.BOLD, 20));
		mainBtn2.setFont(new Font("HYPOST", Font.BOLD, 20));
		mainBtn1.setBounds(172, 15, 120, 50);
		mainBtn2.setBounds(372, 15, 120, 50);
		mainBtn1.addActionListener(this);
		mainBtn2.addActionListener(this);
		bottomPanel.add(mainBtn1);
		bottomPanel.add(mainBtn2);
		cp.add(bottomPanel);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
		}
		new OrderClient().init();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton Btn = (JButton) e.getSource();
		String btnText = Btn.getText();
		if (btnText.equals("먹고가기>") || btnText.equals("포장하기>")) {
			mainPanel.cop1 = Btn.getText().trim();
			firstPanel.setVisible(false);
			mainPanel.setVisible(true);
			bottomPanel.setVisible(true);
		} else if (btnText.equals("확인")){
			mainPanel.cop2 = op.optionimageLabel1.getText();
			mainPanel.cop3 = op.optionimageLabel2.getText();
			mainPanel.cop4 = op.optionimageLabel3.getText();
			mainPanel.cop5 = op.optionimageLabel4.getText();
			System.out.print(mainPanel.cop1+"\t");
			System.out.print(mainPanel.cop2+"\t");
			System.out.print(mainPanel.cop3+"\t");
			System.out.print(mainPanel.cop4+"\t");
			System.out.print(mainPanel.cop5+"\t");
			mainPanel.offOptionPanel();
			mainPanel.addProductOnCart(mainPanel.pname, mainPanel.cop2, mainPanel.cop3, mainPanel.cop4, mainPanel.cop5);
		} else if (btnText.equals("처음으로")) {
			firstPanel.setVisible(true);
			mainPanel.setVisible(false);
			bottomPanel.setVisible(false);
			mainPanel.offOptionPanel();
			mainPanel.initCart();
			repaint();
		} else if (btnText.equals("주문하기")) {
			if(mainPanel.cartCount == 0) {
				JOptionPane.showMessageDialog(null, 
					    "장바구니에 담긴 상품이 없습니다.",
					    "안내메시지",
					    JOptionPane.WARNING_MESSAGE);
			} else {
				for(int i=1; i<=mainPanel.cartCount;i++) {
					mainPanel.os.insertOrder(i);
				}
				mainPanel.initCart();
				mainPanel.os.deleteCart();
				JOptionPane.showMessageDialog(null,"주문이 완료 되었습니다.",
					    "안내메시지",JOptionPane.WARNING_MESSAGE);
				if (mainPanel.cartPanel.getSize().equals(new Dimension(684, 785))) {
					mainPanel.cartBtn.setIcon(new ImageIcon(mainPanel.imgUp));
					mainPanel.cartPanel.setBounds(0, 621, 684, 160);
					mainPanel.listScroll.setVisible(true);
					mainPanel.listPanel.setVisible(true);
					mainPanel.sidePanel.setVisible(true);
					mainPanel.repaint();
					repaint();
				}
			}
		}
	}

}
