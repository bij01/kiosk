package com.team2.kiosk;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;

public class OptionPanel extends JPanel implements ActionListener {
	JLabel optionimageLabel1, optionimageLabel2, optionimageLabel3, optionimageLabel4;
	JButton optionBtn1, optionBtn2, optionBtn3, optionBtn4, optionBtn5, optionBtn6, optionBtn7, optionBtn8, optionBtn9;

	String basePath = new File("").getAbsolutePath();

	OptionPanel() {
		setBounds(0, 0, 684, 785);
		setVisible(false);
		setLayout(null);
		setBackground(new Color(255, 255, 255));
		// 옵션선택글자
		JLabel LetterLabe1 = new JLabel("HOT/ICE");// 128
		LetterLabe1.setVisible(true);
		LetterLabe1.setFont(new Font("배달의민족 주아", Font.BOLD, 30));
		LetterLabe1.setBounds(280, 10, 400, 100);
		LetterLabe1.setForeground(new Color(145, 110, 74));
		add(LetterLabe1);
		JLabel LetterLabel2 = new JLabel("사이즈");
		LetterLabel2.setVisible(true);
		LetterLabel2.setFont(new Font("배달의민족 주아", Font.BOLD, 30));
		LetterLabel2.setBounds(298, 138, 400, 100);
		LetterLabel2.setForeground(new Color(145, 110, 74));
		add(LetterLabel2);
		JLabel LetterLabe13 = new JLabel("에스프레소");
		LetterLabe13.setVisible(true);
		LetterLabe13.setFont(new Font("배달의민족 주아", Font.BOLD, 30));
		LetterLabe13.setBounds(275, 266, 400, 100);
		LetterLabe13.setForeground(new Color(145, 110, 74));
		add(LetterLabe13);
		JLabel LetterLabe14 = new JLabel("얼음");
		LetterLabe14.setVisible(true);
		LetterLabe14.setFont(new Font("배달의민족 주아", Font.BOLD, 30));
		LetterLabe14.setBounds(310, 394, 400, 100);
		LetterLabe14.setForeground(new Color(145, 110, 74));
		add(LetterLabe14);
		// 구분선

		JLabel optionLabel1 = new JLabel("-----------");// 11,세로길이128
		optionLabel1.setBounds(244, 85, 240, 10);
		optionLabel1.setFont(new Font("굴림", Font.BOLD, 25));
		optionLabel1.setForeground(new Color(237, 229, 214));
		// optionLabel1.setBorder(new MatteBorder(0, 0, 3, 0, new Color(145,110,74)));
		add(optionLabel1);
		JLabel optionLabel2 = new JLabel("-----------");
		optionLabel2.setBounds(244, 213, 240, 10);
		optionLabel2.setFont(new Font("굴림", Font.BOLD, 25));
		optionLabel2.setForeground(new Color(237, 229, 214));
		// optionLabel2.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		add(optionLabel2);
		JLabel optionLabel3 = new JLabel("-----------");
		optionLabel3.setBounds(244, 341, 240, 10);
		optionLabel3.setFont(new Font("굴림", Font.BOLD, 25));
		optionLabel3.setForeground(new Color(237, 229, 214));
		// optionLabel3.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		add(optionLabel3);
		JLabel optionLabel4 = new JLabel("-----------");
		optionLabel4.setBounds(244, 469, 240, 10);
		optionLabel4.setFont(new Font("굴림", Font.BOLD, 25));
		optionLabel4.setForeground(new Color(237, 229, 214));
		// optionLabel4.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		add(optionLabel4);

		// 옵션추가현황확인'옵션'

		// 옵션추가현황확인체크박스 이미지+text
		String optionimagePath = "/src/optcheckboximage.png";
		Image img = returnImg(optionimagePath, 30, 30);
		ImageIcon optionimage1 = new ImageIcon(img);

		optionimageLabel1 = new JLabel("#ICE");
		optionimageLabel1.setBounds(80, 650, 110, 50);
		// optionimageLabel1.setText("#ICE");
		optionimageLabel1.setVisible(true);
		optionimageLabel1.setForeground(new Color(84, 84, 84));
		optionimageLabel1.setFont(new Font("배달의민족 주아", Font.PLAIN, 35));
		// optionimageLabel1.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel1.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel1.setHorizontalTextPosition(AbstractButton.RIGHT);
		
		optionimageLabel2 = new JLabel("#MEDIUM");
		//add(optionimageLabel2);
		optionimageLabel2.setBounds(180, 650, 180, 50);
		//optionimageLabel2.setText("MEDIUM");
		optionimageLabel2.setVisible(true);
		optionimageLabel2.setForeground(new Color(84, 84, 84));
		optionimageLabel2.setFont(new Font("배달의민족 주아", Font.PLAIN, 35));
		// optionimageLabel2.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel2.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel2.setHorizontalTextPosition(AbstractButton.RIGHT);
		optionimageLabel3 = new JLabel("#샷추가");
		optionimageLabel3.setBounds(335, 650, 145, 50);
		//optionimageLabel3.setText("샷추가");
		optionimageLabel3.setVisible(true);
		optionimageLabel3.setForeground(new Color(84, 84, 84));
		optionimageLabel3.setFont(new Font("배달의민족 주아", Font.PLAIN, 35));
		// optionimageLabel3.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel3.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel3.setHorizontalTextPosition(AbstractButton.RIGHT);
		optionimageLabel4 = new JLabel("#얼음많이");
		optionimageLabel4.setBounds(480, 650, 165, 50);
		//optionimageLabel4.setText("얼음많이");
		optionimageLabel4.setVisible(true);
		optionimageLabel4.setForeground(new Color(84, 84, 84));
		optionimageLabel4.setFont(new Font("배달의민족 주아", Font.PLAIN, 35));
		// optionimageLabel4.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel4.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel4.setHorizontalTextPosition(AbstractButton.RIGHT);

		add(optionimageLabel1);
		add(optionimageLabel2);
		add(optionimageLabel3);
		add(optionimageLabel4);

		String btnimagePath = "/src/optionbut.png";
		Image img2 = returnImg(btnimagePath, 200, 70);
		ImageIcon optbutImage = new ImageIcon(img2);

		optionBtn1 = new JButton("HOT", optbutImage);
		optionBtn1.setForeground(new Color(84, 84, 84));
		optionBtn1.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn1.setFocusPainted(false);
		// BorderFactory.createLineBorder
		// inBtn.setBorder(BorderFactory.createEmptyBorder(3 , 3 , 3 , 3));
		optionBtn1.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn1.setBackground(new Color(0, 0, 0, 0));
		optionBtn1.setBounds(150, 85, 200, 70);
		optionBtn1.setVerticalTextPosition(optionBtn1.CENTER);
		optionBtn1.setHorizontalTextPosition(optionBtn1.CENTER);
		optionBtn2 = new JButton("ICE", optbutImage);
		optionBtn2.setForeground(new Color(84, 84, 84));
		optionBtn2.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn2.setFocusPainted(false);
		optionBtn2.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn2.setBackground(new Color(0, 0, 0, 0));
		optionBtn2.setBounds(325, 85, 200, 70);
		optionBtn2.setVerticalTextPosition(optionBtn2.CENTER);
		optionBtn2.setHorizontalTextPosition(optionBtn2.CENTER);

		optionBtn3 = new JButton("MEDIUM", optbutImage);
		optionBtn3.setForeground(new Color(84, 84, 84));
		optionBtn3.setBorder(BorderFactory.createRaisedBevelBorder());
		// optionBtn3.setFocusPainted(false);
		optionBtn3.setBackground(new Color(0, 0, 0, 0));
		optionBtn3.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn3.setBounds(150, 213, 200, 70);
		optionBtn3.setVerticalTextPosition(optionBtn3.CENTER);
		optionBtn3.setHorizontalTextPosition(optionBtn3.CENTER);
		optionBtn4 = new JButton("LARGE", optbutImage);
		optionBtn4.setForeground(new Color(84, 84, 84));
		optionBtn4.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn4.setBackground(new Color(0, 0, 0, 0));
		optionBtn4.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn4.setBounds(325, 213, 200, 70);
		optionBtn4.setVerticalTextPosition(optionBtn4.CENTER);
		optionBtn4.setHorizontalTextPosition(optionBtn4.CENTER);

		optionBtn5 = new JButton("샷추가", optbutImage);
		optionBtn5.setForeground(new Color(84, 84, 84));
		optionBtn5.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn5.setBackground(new Color(0, 0, 0, 0));
		optionBtn5.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn5.setBounds(150, 341, 200, 70);
		optionBtn5.setVerticalTextPosition(optionBtn5.CENTER);
		optionBtn5.setHorizontalTextPosition(optionBtn5.CENTER);
		optionBtn6 = new JButton("추가안함", optbutImage);
		optionBtn6.setForeground(new Color(84, 84, 84));
		optionBtn6.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn6.setBackground(new Color(0, 0, 0, 0));
		optionBtn6.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn6.setBounds(325, 341, 200, 70);
		optionBtn6.setVerticalTextPosition(optionBtn6.CENTER);
		optionBtn6.setHorizontalTextPosition(optionBtn6.CENTER);

		// 간격175

		optionBtn7 = new JButton("추가안함", optbutImage);
		optionBtn7.setForeground(new Color(84, 84, 84));
		optionBtn7.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn7.setBackground(new Color(0, 0, 0, 0));
		optionBtn7.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn7.setBounds(60, 469, 200, 70);
		optionBtn7.setVerticalTextPosition(optionBtn7.CENTER);
		optionBtn7.setHorizontalTextPosition(optionBtn7.CENTER);

		optionBtn8 = new JButton("얼음조금", optbutImage);
		optionBtn8.setForeground(new Color(84, 84, 84));
		optionBtn8.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn8.setBackground(new Color(0, 0, 0, 0));
		optionBtn8.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn8.setBounds(235, 469, 200, 70);
		optionBtn8.setVerticalTextPosition(optionBtn8.CENTER);
		optionBtn8.setHorizontalTextPosition(optionBtn8.CENTER);

		optionBtn9 = new JButton("얼음많이", optbutImage);
		optionBtn9.setForeground(new Color(84, 84, 84));
		optionBtn9.setBorder(BorderFactory.createRaisedBevelBorder());
		optionBtn9.setBackground(new Color(0, 0, 0, 0));
		optionBtn9.setFont(new Font("배달의민족 주아", Font.PLAIN, 20));
		optionBtn9.setBounds(410, 469, 200, 70);
		optionBtn9.setVerticalTextPosition(optionBtn9.CENTER);
		optionBtn9.setHorizontalTextPosition(optionBtn9.CENTER);

		optionBtn1.addActionListener(this);
		optionBtn2.addActionListener(this);
		optionBtn3.addActionListener(this);
		optionBtn4.addActionListener(this);
		optionBtn5.addActionListener(this);
		optionBtn6.addActionListener(this);
		optionBtn7.addActionListener(this);
		optionBtn8.addActionListener(this);
		optionBtn9.addActionListener(this);
//위,아래줍바꿈
		/*
		 * JSeparator topSolidline = new JSeparator(); topSolidline.setBounds(60, 12,
		 * 560, 560); topSolidline.setForeground(Color.black); // top line color
		 * topSolidline.setBackground(Color.black.brighter()); add(topSolidline);
		 * JSeparator bottomSolidline = new JSeparator(); bottomSolidline.setBounds(60,
		 * 766, 560, 560); bottomSolidline.setForeground(Color.black); // top line color
		 * bottomSolidline.setBackground(Color.black.brighter()); add(bottomSolidline);
		 */

		add(optionBtn1);
		add(optionBtn2);
		add(optionBtn3);
		add(optionBtn4);
		add(optionBtn5);
		add(optionBtn6);
		add(optionBtn7);
		add(optionBtn8);
		add(optionBtn9);
	}

	/*
	 * 컴포넌트 점선참고 public void paintComponent(Graphics g) { Graphics2D
	 * g2=(Graphics2D)g; float dash1[] = {1,1f}; g.setFont(new Font("(1,1f",
	 * Font.BOLD, 20)); g.drawString("{1,1f}", 220, 85); }
	 */

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
			System.out.println(ex);
		}
		return img;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton selfBtn = (JButton) e.getSource();
		String text = selfBtn.getText();
		System.out.println(text);

		if (text.equals("ICE"))
			optionimageLabel1.setText("ICE");
		else if (text.equals("HOT")) {
			optionimageLabel1.setText("HOT");
			optionimageLabel4.setText("선택안함");
		} else if (text.equals("MEDIUM"))
			optionimageLabel2.setText("MEDIUM");
		else if (text.equals("LARGE"))
			optionimageLabel2.setText("LARGE");
		else if (text.equals("샷추가"))
			optionimageLabel3.setText("샷추가");
		else if (text.equals("추가안함"))
			optionimageLabel3.setText("추가안함");
		if (optionimageLabel1.getText().equals("HOT") && text.equals("얼음조금")) {
			JOptionPane.showMessageDialog(null, "뜨거운 음료에는 얼음을 추가할 수 없습니다.", "안내메시지", JOptionPane.WARNING_MESSAGE);
		} else if (optionimageLabel1.getText().equals("HOT") && text.equals("얼음많이")) {
			JOptionPane.showMessageDialog(null, "뜨거운 음료에는 얼음을 추가할 수 없습니다.", "안내메시지", JOptionPane.WARNING_MESSAGE);
		} else {
			if (text.equals("얼음조금"))
				optionimageLabel4.setText("얼음조금");
			else if (text.equals("얼음많이"))
				optionimageLabel4.setText("얼음많이");
			else if (text.equals("선택안함"))
				optionimageLabel4.setText("선택안함");
		}
	}
}
