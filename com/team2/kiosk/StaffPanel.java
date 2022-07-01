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

class StaffPanel extends JPanel {
	OrderServerImpl os;
	MainPanel mp;
	OrderClient oc;
	
	JPanel listPanel, detailPanel;

	StaffPanel(MainPanel mp) {
		this.mp = mp;
		init();
		setListPanel();
		setDetailPanel();
		//onListPanel();
		onDetailPanel();
	}

	void init() {
		this.os = mp.os;
		this.oc = mp.oc;
		setBounds(0, 0, 684, 785);
		setVisible(false);
		setLayout(null);
		setBackground(new Color(80, 80, 80));
	}

	void onListPanel() {
		listPanel.setVisible(true);
		detailPanel.setVisible(false);
	}

	void onDetailPanel() {
		listPanel.setVisible(false);
		detailPanel.setVisible(true);
	}

	void setListPanel() {
		listPanel = new JPanel();
		listPanel.setBounds(0, 0, 684, 785);
		listPanel.setVisible(false);
		listPanel.setLayout(null);
		listPanel.setBackground(new Color(100, 150, 100));
		// 리스트 패널

		JLabel listToplabel = new JLabel("주문번호:");
		listToplabel.setBounds(230, -20, 130, 120);
		listToplabel.setForeground(Color.WHITE);
		listToplabel.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listToplabel);

		JLabel listToplabe2 = new JLabel("153");
		listToplabe2.setBounds(370, -20, 120, 120);
		listToplabe2.setForeground(Color.WHITE);
		listToplabe2.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listToplabe2);

		JSeparator listToplabelline1 = new JSeparator();
		listToplabelline1.setBounds(40, 10, 600, 600);
		listToplabelline1.setForeground(Color.WHITE); // top line color
		listToplabelline1.setBackground(Color.black.brighter());
		JSeparator listToplabelline2 = new JSeparator();
		listToplabelline2.setBounds(40, 70, 600, 600);
		listToplabelline2.setForeground(Color.WHITE); // top line color
		listToplabelline2.setBackground(Color.black.brighter());

		listPanel.add(listToplabelline1);
		listPanel.add(listToplabelline2);

		JPanel listtablePanel = new JPanel();
		listtablePanel.setBackground(new Color(30, 30, 100));
		listtablePanel.setBounds(0, 80, 684, 400);
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
		columnNames.add("주문번호");
		columnNames.add("상태");
		columnNames.add("주문메뉴");
		columnNames.add("가격");
		columnNames.add("주문시간");
		Vector<Object> v1 = new Vector<Object>();
		v1.add(1);
		v1.add("대기중");
		v1.add("아메리카노 외 3잔");
		v1.add("15000원");
		v1.add("SYSDATE");
		
		os.selectOrder();

		for (Vector<Object> vector:os.ordersSet) {
			String orderNo = (String)vector.get(0);
			orderNo = orderNo.substring(0, 10);
			int pno = (Integer)vector.get(2);
			os.selectProduct(2, pno);
			String pname = os.productVector.get(1).toString();
			int count = os.returnSameOrderNumCount(orderNo);
			System.out.println(count);
		}

		
		//System.out.println(os.ordersSet);
		
		JTable table = new JTable(rowData, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(680, 300));
		table.setFillsViewportHeight(true);
		table.setBackground(Color.WHITE);
		JScrollPane sp = new JScrollPane(table);
		listtablePanel.add(sp);
		listPanel.add(listtablePanel, BorderLayout.CENTER);

		JSeparator listbottomlabelline1 = new JSeparator();
		listbottomlabelline1.setBounds(40, 490, 600, 600);
		listbottomlabelline1.setForeground(Color.WHITE); // top line color
		listbottomlabelline1.setBackground(Color.black.brighter());
		JSeparator listbottomlabelline2 = new JSeparator();
		listbottomlabelline2.setBounds(40, 770, 600, 600);
		listbottomlabelline2.setForeground(Color.WHITE); // top line color
		listbottomlabelline2.setBackground(Color.black.brighter());
		listPanel.add(listbottomlabelline1);
		listPanel.add(listbottomlabelline2);

		JLabel listbottomlabel = new JLabel("주문번호:");
		listbottomlabel.setBounds(40, 500, 130, 120);
		listbottomlabel.setForeground(Color.WHITE);
		listbottomlabel.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabel);

		JLabel listbottomlabel2 = new JLabel("153");
		listbottomlabel2.setBounds(170, 500, 130, 120);
		listbottomlabel2.setForeground(Color.WHITE);
		listbottomlabel2.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabel2);

		JLabel listbottomlabel3 = new JLabel("| 상태:");
		listbottomlabel3.setBounds(230, 500, 130, 120);
		listbottomlabel3.setForeground(Color.WHITE);
		listbottomlabel3.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabel3);

		JLabel listbottomlabe4 = new JLabel("결제완료");
		listbottomlabe4.setBounds(320, 500, 130, 120);
		listbottomlabe4.setForeground(Color.WHITE);
		listbottomlabe4.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabe4);

		JLabel listbottomlabe5 = new JLabel("| 가격:");
		listbottomlabe5.setBounds(450, 500, 130, 120);
		listbottomlabe5.setForeground(Color.WHITE);
		listbottomlabe5.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabe5);

		JLabel listbottomlabe6 = new JLabel("35.000원");
		listbottomlabe6.setBounds(540, 500, 130, 120);
		listbottomlabe6.setForeground(Color.WHITE);
		listbottomlabe6.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabe6);

		JButton listbottombutton1 = new JButton("선택하기");
		listbottombutton1.setBounds(190, 630, 280, 70);
		listbottombutton1.setBackground(Color.BLACK);
		listbottombutton1.setForeground(Color.WHITE);
		listbottombutton1.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottombutton1);

		add(listPanel);

	}

	void setDetailPanel(){
		detailPanel = new JPanel();
		detailPanel.setBounds(0, 0, 684, 785);
		detailPanel.setVisible(false);
		detailPanel.setLayout(null);
		detailPanel.setBackground(new Color(100, 100, 150));
		// 디테일 패널

		//
		add(detailPanel);
	}
}
