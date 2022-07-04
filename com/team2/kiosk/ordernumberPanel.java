package com.team2.kiosk;

import java.awt.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

class ordernumberPanel extends JPanel implements ActionListener {

	String basePath = new File("").getAbsolutePath();

	ordernumberPanel() {

		setBounds(0, 0, 684, 785);
		setVisible(true);
		setLayout(null);
		//setBackground(new Color(0, 229, 214))/(new Color(237, 229, 214));
		JPanel orderNumberPanel = new JPanel();
		orderNumberPanel.setBounds(0, 0, 684, 780);
		orderNumberPanel.setLayout(null);
		orderNumberPanel.setBackground(new Color(237, 229, 214));
		orderNumberPanel.setVisible(true);
		// panel.setBackground(new Color(30,30,30));

		JLabel ordernumberLabel = new JLabel("주문이 완료되었습니다!");
		ordernumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ordernumberLabel.setFont(new Font("배달의민족 주아", Font.PLAIN, 34));

		ordernumberLabel.setForeground(Color.BLACK);
		ordernumberLabel.setBounds(113, 53, 471, 67);
		orderNumberPanel.add(ordernumberLabel);

		JLabel ordernumberLabel1 = new JLabel("주문번호");
		ordernumberLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		ordernumberLabel1.setFont(new Font("굴림", Font.BOLD, 18));
		ordernumberLabel1.setBounds(287, 154, 92, 28);
		orderNumberPanel.add(ordernumberLabel1);

		JLabel ordernumberLabel2 = new JLabel("353");
		ordernumberLabel2.setBackground(Color.ORANGE);
		ordernumberLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		ordernumberLabel2.setFont(new Font("굴림", Font.BOLD, 80));
		ordernumberLabel2.setBounds(222, 200, 222, 110);
		orderNumberPanel.add(ordernumberLabel2);

		JLabel ordernumberLabel3 = new JLabel("메뉴가 준비되면 주문번호를 호출해 드립니다.");
		ordernumberLabel3.setFont(new Font("배달의민족 주아", Font.PLAIN, 23));
		ordernumberLabel3.setBounds(153, 347, 471, 35);
		orderNumberPanel.add(ordernumberLabel3);

		String ordernumberPath = "./src/ordernumber.png";
		Image img = returnImg(ordernumberPath, 280, 280);
		ImageIcon optionimage1 = new ImageIcon(img);
		JLabel orderNumberLabel = new JLabel(optionimage1);
		orderNumberLabel.setBounds(195, 410, 280, 280);

		orderNumberPanel.add(orderNumberLabel);
		add(orderNumberPanel);
		
		
		


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
			// System.out.println(ex);
		}
		return img;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}