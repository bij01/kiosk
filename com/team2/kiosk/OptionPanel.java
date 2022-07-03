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
		setBackground(new Color(50, 50, 50));
		// 옵션선택글자
		JLabel LetterLabe1 = new JLabel("|아이스/핫");
		LetterLabe1.setVisible(true);
		LetterLabe1.setFont(new Font("굴림", Font.BOLD, 30));
		LetterLabe1.setBounds(100, 28, 400, 100);
		LetterLabe1.setForeground(Color.BLACK);
		add(LetterLabe1);
		JLabel LetterLabel2 = new JLabel("|사이즈");
		LetterLabel2.setVisible(true);
		LetterLabel2.setFont(new Font("굴림", Font.BOLD, 30));
		LetterLabel2.setBounds(100, 158, 400, 100);
		LetterLabel2.setForeground(Color.BLACK);
		add(LetterLabel2);
		JLabel LetterLabe13 = new JLabel("|에스프레소샷");
		LetterLabe13.setVisible(true);
		LetterLabe13.setFont(new Font("굴림", Font.BOLD, 30));
		LetterLabe13.setBounds(100, 288, 400, 100);
		LetterLabe13.setForeground(Color.BLACK);
		add(LetterLabe13);
		JLabel LetterLabe14 = new JLabel("|얼음");
		LetterLabe14.setVisible(true);
		LetterLabe14.setFont(new Font("굴림", Font.BOLD, 30));
		LetterLabe14.setBounds(100, 422, 400, 100);
		LetterLabe14.setForeground(Color.BLACK);
		add(LetterLabe14);
		// 구분선
		JLabel optionLabel1 = new JLabel();
		optionLabel1.setBounds(65, 125, 550, 35);
		optionLabel1.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		add(optionLabel1);
		JLabel optionLabel2 = new JLabel();
		optionLabel2.setBounds(65, 255, 550, 35);
		optionLabel2.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		add(optionLabel2);
		JLabel optionLabel3 = new JLabel();
		optionLabel3.setBounds(65, 385, 550, 35);
		optionLabel3.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		add(optionLabel3);
		JLabel optionLabel4 = new JLabel();
		optionLabel4.setBounds(65, 515, 550, 35);
		optionLabel4.setBorder(new MatteBorder(0, 0, 5, 0, Color.BLACK));
		add(optionLabel4);

		// 옵션추가현황확인'옵션'
		JLabel optioncheckLabel = new JLabel("옵션");
		optioncheckLabel.setBounds(80, 600, 110, 50);
		optioncheckLabel.setVisible(true);
		optioncheckLabel.setForeground(Color.WHITE);
		optioncheckLabel.setFont(new Font("굴림", Font.BOLD, 30));
		add(optioncheckLabel);

		// 옵션추가현황확인체크박스 이미지+text
		String optionimagePath = "/src/optcheckboximage.png";
		Image img = returnImg(optionimagePath, 30, 30);
		ImageIcon optionimage1 = new ImageIcon(img);

		optionimageLabel1 = new JLabel(optionimage1);
		optionimageLabel1.setBounds(80, 650, 110, 50);
		optionimageLabel1.setText("ICE");
		optionimageLabel1.setVisible(true);
		optionimageLabel1.setForeground(Color.WHITE);
		optionimageLabel1.setFont(new Font("굴림", Font.BOLD, 25));
		// optionimageLabel1.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel1.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel1.setHorizontalTextPosition(AbstractButton.RIGHT);

		optionimageLabel2 = new JLabel(optionimage1);
		add(optionimageLabel2);
		optionimageLabel2.setBounds(180, 650, 180, 50);
		optionimageLabel2.setText("MEDIUM");
		optionimageLabel2.setVisible(true);
		optionimageLabel2.setForeground(Color.WHITE);
		optionimageLabel2.setFont(new Font("굴림", Font.BOLD, 25));
		// optionimageLabel2.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel2.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel2.setHorizontalTextPosition(AbstractButton.RIGHT);

		optionimageLabel3 = new JLabel(optionimage1);
		optionimageLabel3.setBounds(335, 650, 145, 50);
		optionimageLabel3.setText("샷추가");
		optionimageLabel3.setVisible(true);
		optionimageLabel3.setForeground(Color.WHITE);
		optionimageLabel3.setFont(new Font("굴림", Font.BOLD, 25));
		// optionimageLabel3.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel3.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel3.setHorizontalTextPosition(AbstractButton.RIGHT);

		optionimageLabel4 = new JLabel(optionimage1);
		optionimageLabel4.setBounds(480, 650, 165, 50);
		optionimageLabel4.setText("얼음많이");
		optionimageLabel4.setVisible(true);
		optionimageLabel4.setForeground(Color.WHITE);
		optionimageLabel4.setFont(new Font("굴림", Font.BOLD, 25));
		// optionimageLabel4.setBorder(new MatteBorder(5,5,5,5, Color.WHITE));
		optionimageLabel4.setHorizontalAlignment(AbstractButton.LEFT);
		optionimageLabel4.setHorizontalTextPosition(AbstractButton.RIGHT);

		add(optionimageLabel1);
		add(optionimageLabel2);
		add(optionimageLabel3);
		add(optionimageLabel4);

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

		optionBtn1.addActionListener(this);
		optionBtn2.addActionListener(this);
		optionBtn3.addActionListener(this);
		optionBtn4.addActionListener(this);
		optionBtn5.addActionListener(this);
		optionBtn6.addActionListener(this);
		optionBtn7.addActionListener(this);
		optionBtn8.addActionListener(this);
		optionBtn9.addActionListener(this);

		JSeparator topSolidline = new JSeparator();
		topSolidline.setBounds(60, 12, 560, 560);
		topSolidline.setForeground(Color.black); // top line color
		topSolidline.setBackground(Color.black.brighter());
		add(topSolidline);
		JSeparator bottomSolidline = new JSeparator();
		bottomSolidline.setBounds(60, 766, 560, 560);
		bottomSolidline.setForeground(Color.black); // top line color
		bottomSolidline.setBackground(Color.black.brighter());
		add(bottomSolidline);

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
