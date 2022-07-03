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
import java.util.Random;

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
	
	String basePath = new File("").getAbsolutePath();
	String topimagePath = "/src/topsideimg.png";
	JLabel pageNameLabel;

	void testMode() { // 바로 두번째 화면으로 넘어가기(개발 끝나면 삭제)
		firstPanel.setVisible(false);
		mainPanel.setVisible(true);
		bottomPanel.setVisible(true);
	}

	void init() {
		setTitle("아싸커피");
		setSize(WIDTH, HEIGHT);
		setResizable(false);
		setLocationRelativeTo(null);
		cp = getContentPane();
		cp.setLayout(null);
		setFirstPanel();
		addOptionMember();
		
		setTopPanel();
		setBottomPanel();
		setMainPanel();
		
		testMode();
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

		String bgimagePath = "/src/background3.png";
		Image img = returnImg(bgimagePath, 680, 960);
		
		ImageIcon bgimg = new ImageIcon(img);
		JLabel bgimageLabel = new JLabel(bgimg);
		bgimageLabel.setBounds(0, 0, 680, 960);
		/* OS 색상 바꾸기 -> 이미지 수정 필요
		bgimageLabel.setOpaque(true);
		new Thread(() -> {
			while(true) {
				try {
					Thread.sleep(800);
					int min, max;
					min = 10;
					max = 255;
					Random random = new Random();
					int num = random.nextInt(max - min + 1) + min;
					int num2 = random.nextInt(max - min + 1) + min;
					int num3 = random.nextInt(max - min + 1) + min;
					bgimageLabel.setBackground(new Color(num,num2,num3));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		*/
		// 버튼이미지입히기
		String btnimagePath = "/src/butbackground.png";
		Image img2 = returnImg(btnimagePath, 330, 80);
		ImageIcon butimage = new ImageIcon(img2);

		inBtn = new JButton("먹고가기>", butimage);
		inBtn.setVerticalTextPosition(inBtn.CENTER); // 텍스트 아래로
		inBtn.setHorizontalTextPosition(inBtn.CENTER);
		inBtn.setForeground(Color.WHITE);
		inBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		inBtn.setFocusPainted(false);
		// BorderFactory.createLineBorder
		// inBtn.setBorder(BorderFactory.createEmptyBorder(3 , 3 , 3 , 3));
		inBtn.setFont(new Font("휴먼고딕체", Font.BOLD, 40));
		inBtn.setBackground(new Color(0, 0, 0, 0));
		inBtn.setBounds(5, 860, 330, 80);
		inBtn.addActionListener(this);

		outBtn = new JButton("포장하기>", butimage);
		outBtn.setHorizontalTextPosition(outBtn.CENTER);
		outBtn.setBounds(345, 860, 330, 80);
		outBtn.addActionListener(this);
		outBtn.setForeground(Color.WHITE);
		outBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		outBtn.setFocusPainted(false);
		outBtn.setFont(new Font("휴먼고딕체", Font.BOLD, 40));
		outBtn.setBackground(new Color(0, 0, 0, 0));

		bgimageLabel.add(inBtn);
		bgimageLabel.add(outBtn);
		firstPanel.add(bgimageLabel);
		firstPanel.setBorder(new BevelBorder(BevelBorder.RAISED));
		cp.add(firstPanel);

	}

	void setTopPanel() {
		topPanel = new JPanel();
		topPanel.setBounds(0, 0, 684, 100);
		topPanel.setBackground(new Color(30, 30, 30));
		ImageIcon maintitleImage = new ImageIcon(returnImg(topimagePath, 684, 100));
		JLabel mainTitleLabel = new JLabel(maintitleImage);
		pageNameLabel = new JLabel("주문화면");
		pageNameLabel.setBounds(0, 0, 684, 100);
		pageNameLabel.setForeground(Color.WHITE);
		pageNameLabel.setHorizontalAlignment(JLabel.CENTER);
		pageNameLabel.setFont(new Font("휴먼매직체", Font.BOLD, 50));
		mainTitleLabel.add(pageNameLabel);
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
		bottomPanel.setBackground(new Color(30, 30, 30));

		ImageIcon bottomImage = new ImageIcon(returnImg(topimagePath, 684, 80));
		JLabel bottomLabel = new JLabel(bottomImage);

		mainBtn1 = new JButton("처음으로");
		mainBtn2 = new JButton("주문하기");
		mainBtn1.setForeground(Color.WHITE);
		mainBtn2.setForeground(Color.WHITE);
		mainBtn1.setBackground(new Color(10, 10, 10, 230));
		mainBtn2.setBackground(new Color(10, 10, 10, 230));
		mainBtn1.setFont(new Font("휴먼고딕체", Font.BOLD, 20));
		mainBtn2.setFont(new Font("휴먼고딕체", Font.BOLD, 20));
		mainBtn1.setBounds(172, 15, 120, 50);
		mainBtn2.setBounds(372, 15, 120, 50);
		mainBtn1.addActionListener(this);
		mainBtn2.addActionListener(this);
		bottomLabel.add(mainBtn1);
		bottomLabel.add(mainBtn2);
		bottomPanel.add(bottomLabel);

		cp.add(bottomPanel);
	}
	
	Image returnImg(String path, int size1, int size2) {
		path = basePath + path;
		Image img = null;
		try {
			BufferedImage bufferedImage = null;
			try {
				bufferedImage = ImageIO.read(new File(path));
			} catch (Exception e) {
			}
			img = bufferedImage.getScaledInstance(size1, size2, Image.SCALE_DEFAULT);
		} catch (Exception ex) {
			//System.out.println(ex);
		}
		return img;
	}
	
	void moveToFirstView() {
		firstPanel.setSize(0, 961);// 684, 961
		new Thread(() -> {
			for(int i=0;i<684;i+=6) {
				try {
					if(i==678) {
						i+=8;
					}
					Thread.sleep(1);
					firstPanel.setSize(i, 961);
					firstPanel.repaint();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		topPanel.setVisible(false);
		firstPanel.setVisible(true);
		mainPanel.setVisible(false);
		mainBtn1.setVisible(false);
		mainBtn2.setVisible(false);
		bottomPanel.setVisible(false);
		
		mainPanel.offOptionPanel();
		mainPanel.offStaffPanel();
		mainPanel.initCart();
		repaint();
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
			topPanel.setVisible(true);
			mainPanel.setVisible(true);
			bottomPanel.setVisible(true);
		} else if (btnText.equals("확인")) {
			mainPanel.cop2 = op.optionimageLabel1.getText();
			mainPanel.cop3 = op.optionimageLabel2.getText();
			mainPanel.cop4 = op.optionimageLabel3.getText();
			mainPanel.cop5 = op.optionimageLabel4.getText();
			System.out.print(mainPanel.cop1 + "\t");
			System.out.print(mainPanel.cop2 + "\t");
			System.out.print(mainPanel.cop3 + "\t");
			System.out.print(mainPanel.cop4 + "\t");
			System.out.print(mainPanel.cop5 + "\t");
			mainPanel.offOptionPanel();
			mainPanel.addProductOnCart(mainPanel.pname, mainPanel.cop2, mainPanel.cop3, mainPanel.cop4, mainPanel.cop5);
		} else if (btnText.equals("처음으로")) {
			moveToFirstView();
		} else if (btnText.equals("주문하기")) {
			if (mainPanel.cartCount == 0) {
				JOptionPane.showMessageDialog(null, "장바구니에 담긴 상품이 없습니다.", "안내메시지", JOptionPane.WARNING_MESSAGE);
			} else {
				for (int i = 1; i <= mainPanel.cartCount; i++) {
					mainPanel.os.insertOrder(i);
				}
				mainPanel.initCart();
				mainPanel.os.deleteCart();
				JOptionPane.showMessageDialog(null, "주문이 완료 되었습니다.", "안내메시지", JOptionPane.WARNING_MESSAGE);
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
