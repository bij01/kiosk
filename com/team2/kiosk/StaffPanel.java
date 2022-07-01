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
	JPanel listPanel, detailPanel;

	StaffPanel() {
		init();
		setListPanel();
		setDetailPanel();
		onListPanel();
		//onDetailPanel();
	}

	void init() {
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
		JLabel listToplabel = new JLabel("주문목록리스트");
		listToplabel.setBounds(230, -20, 230, 120);
		listToplabel.setForeground(Color.WHITE);
		listToplabel.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listToplabel);
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
		//테이블 
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
		Vector<Object> v2 = new Vector<Object>();
		v2.add(2);
		v2.add("결제완료");
		v2.add("카페라떼 외 2잔");
		v2.add("7200원");
		v2.add("SYSDATE");
		Vector<Object> v3 = new Vector<Object>();
		v3.add(1);
		v3.add("대기중");
		v3.add("아메리카노 외 3잔");
		v3.add("15000원");
		v3.add("SYSDATE");
		Vector<Object> v4 = new Vector<Object>();
		v4.add(2);
		v4.add("결제완료");
		v4.add("카페라떼 외 2잔");
		v4.add("7200원");
		v4.add("SYSDATE");
		Vector<Object> v5 = new Vector<Object>();
		v5.add(1);
		v5.add("대기중");
		v5.add("아메리카노 외 3잔");
		v5.add("15000원");
		v5.add("SYSDATE");
		Vector<Object> v6 = new Vector<Object>();
		v6.add(2);
		v6.add("결제완료");
		v6.add("카페라떼 외 2잔");
		v6.add("7200원");
		v6.add("SYSDATE");
		Vector<Object> v7 = new Vector<Object>();
		v7.add(1);
		v7.add("대기중");
		v7.add("아메리카노 외 3잔");
		v7.add("15000원");
		v7.add("SYSDATE");
		Vector<Object> v8 = new Vector<Object>();
		v8.add(2);
		v8.add("결제완료");
		v8.add("카페라떼 외 2잔");
		v8.add("7200원");
		v8.add("SYSDATE");
		rowData.add(v1);
		rowData.add(v2);
		rowData.add(v3);
		rowData.add(v4);
		rowData.add(v5);
		rowData.add(v6);
		rowData.add(v7);
		rowData.add(v8);
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
	
	void setDetailPanel() {
		detailPanel = new JPanel();
		detailPanel.setBounds(0, 0, 684, 785);
		detailPanel.setVisible(false);
		detailPanel.setLayout(null);
		detailPanel.setBackground(new Color(100, 100, 150));

		JSeparator detailToplabelline1 = new JSeparator();
		detailToplabelline1.setBounds(40, 10, 600, 600);
		detailToplabelline1.setForeground(Color.WHITE); // top line color
		detailToplabelline1.setBackground(Color.black.brighter());
		JSeparator detaiToplabelline2 = new JSeparator();
		detaiToplabelline2.setBounds(40, 70, 600, 600);
		detaiToplabelline2.setForeground(Color.WHITE); // top line color
		detaiToplabelline2.setBackground(Color.black.brighter());
		listPanel.add(detailToplabelline1);
		listPanel.add(detaiToplabelline2);

		JLabel detailToplabel = new JLabel("주문번호:");
		detailToplabel.setBounds(225, -20, 130, 120);
		detailToplabel.setForeground(Color.WHITE);
		detailToplabel.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailToplabel);

		JLabel detailToplabel2 = new JLabel("153");
		detailToplabel2.setBounds(385, -20, 120, 120);
		detailToplabel2.setForeground(Color.WHITE);
		detailToplabel2.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailToplabel2);

		JPanel listtablePanel = new JPanel();
		listtablePanel.setBackground(new Color(30, 30, 100));
		listtablePanel.setBounds(0, 80, 684, 400);
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
		columnNames.add("상품명");
		columnNames.add("옵션");
		columnNames.add("수량");
		columnNames.add("가격");
		Vector<Object> v1 = new Vector<Object>();
		v1.add("아메리카노");
		v1.add("샷추가");
		v1.add("5");
		v1.add("15.000");
		Vector<Object> v2 = new Vector<Object>();
		v2.add("아메리카노");
		v2.add("샷추가");
		v2.add("5");
		v2.add("15.000");
		rowData.add(v1);
		rowData.add(v2);
		JTable table = new JTable(rowData, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(680, 300));
		table.setFillsViewportHeight(true);
		table.setBackground(Color.WHITE);
		JScrollPane sp = new JScrollPane(table);
		listtablePanel.add(sp);
		detailPanel.add(listtablePanel, BorderLayout.CENTER);

		// 구분선
		JSeparator detailbottomlabelline1 = new JSeparator();
		detailbottomlabelline1.setBounds(40, 490, 600, 600);
		detailbottomlabelline1.setForeground(Color.WHITE); // top line color
		detailbottomlabelline1.setBackground(Color.black.brighter());
		JSeparator detailbottomlabelline2 = new JSeparator();
		detailbottomlabelline2.setBounds(40, 770, 600, 600);
		detailbottomlabelline2.setForeground(Color.WHITE); // top line color
		detailbottomlabelline2.setBackground(Color.black.brighter());
		detailPanel.add(detailbottomlabelline1);
		detailPanel.add(detailbottomlabelline2);
		// 수량,결제금액
		JLabel detailbottomlabel = new JLabel("총수량:");
		detailbottomlabel.setBounds(50, 500, 150, 120);
		detailbottomlabel.setForeground(Color.WHITE);
		detailbottomlabel.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailbottomlabel);

		JLabel detailbottomlabel2 = new JLabel("5개");
		detailbottomlabel2.setBounds(200, 500, 150, 120);
		detailbottomlabel2.setForeground(Color.WHITE);
		detailbottomlabel2.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailbottomlabel2);

		JLabel detailbottomlabel3 = new JLabel("총결제금액:");
		detailbottomlabel3.setBounds(350, 500, 200, 120);
		detailbottomlabel3.setForeground(Color.WHITE);
		detailbottomlabel3.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailbottomlabel3);

		JLabel detailbottomlabel4 = new JLabel("800원");
		detailbottomlabel4.setBounds(550, 500, 150, 120);
		detailbottomlabel4.setForeground(Color.WHITE);
		detailbottomlabel4.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailbottomlabel4);

		JButton detailbottombutton1 = new JButton("결제취소");
		detailbottombutton1.setBounds(50, 630, 280, 70);
		detailbottombutton1.setBackground(Color.BLACK);
		detailbottombutton1.setForeground(Color.WHITE);
		detailbottombutton1.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailbottombutton1);
		add(detailPanel);

		JButton detailbottombutton2 = new JButton("결제완료");
		detailbottombutton2.setBounds(360, 630, 280, 70);
		detailbottombutton2.setBackground(Color.BLACK);
		detailbottombutton2.setForeground(Color.WHITE);
		detailbottombutton2.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailbottombutton2);
		add(detailPanel);

	}
}
