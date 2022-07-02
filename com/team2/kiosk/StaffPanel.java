package com.team2.kiosk;

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
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.border.*;

class StaffPanel extends JPanel implements ActionListener, MouseListener, Runnable {
	OrderServerImpl os;
	MainPanel mp;
	OrderClient oc;
	LinkedHashSet<Vector> listSet = new LinkedHashSet<Vector>(); //주문리스트
	
	JPanel listPanel, detailPanel;
	JTable table1, table2;
	String orderNumber, totalPrice;
	JLabel detailToplabel2, listbottomlabel2, listbottomlabel4, listbottomlabel6
	, detailbottomlabel2, detailbottomlabel4;
	
	JPanel listtablePanel1, listtablePanel;
	DefaultTableModel model1, model2;
	
	Vector<String> columnNames1;
	Vector<Vector<Object>> rowData1;

	StaffPanel(MainPanel mp) {
		this.mp = mp;
		init();
		setListPanel();
		setDetailPanel();
		onListPanel();
		//onDetailPanel();
		Thread th = new Thread(this);
		th.start();
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
		
		JButton backToListBtn = new JButton(">>");
		backToListBtn.setBounds(590, 15, 50, 50);
		backToListBtn.setBackground(Color.BLACK);
		backToListBtn.setForeground(Color.WHITE);
		backToListBtn.setFont(new Font("HYPOST", Font.BOLD, 13));
		backToListBtn.addActionListener(e -> mp.offStaffPanel());
		
		listPanel.add(listToplabelline1);
		listPanel.add(listToplabelline2);
		listPanel.add(backToListBtn);
		//테이블 
		listtablePanel1 = new JPanel();
		listtablePanel1.setBackground(new Color(30, 30, 100));
		listtablePanel1.setBounds(0, 80, 684, 400);
		
		table1 = new JTable(model1);
		setListTable();
		
		table1.setPreferredScrollableViewportSize(new Dimension(680, 300));
		table1.setFillsViewportHeight(true);
		table1.setBackground(Color.WHITE);
		table1.addMouseListener(this);
		
		JScrollPane sp = new JScrollPane(table1);
		listtablePanel1.add(sp);
		
		listPanel.add(listtablePanel1, BorderLayout.CENTER);

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
		listbottomlabel2 = new JLabel("");
		listbottomlabel2.setBounds(170, 500, 130, 120);
		listbottomlabel2.setForeground(Color.WHITE);
		listbottomlabel2.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabel2);
		JLabel listbottomlabel3 = new JLabel("| 상태:");
		listbottomlabel3.setBounds(230, 500, 130, 120);
		listbottomlabel3.setForeground(Color.WHITE);
		listbottomlabel3.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabel3);
		listbottomlabel4 = new JLabel("");
		listbottomlabel4.setBounds(320, 500, 130, 120);
		listbottomlabel4.setForeground(Color.WHITE);
		listbottomlabel4.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabel4);
		JLabel listbottomlabel5 = new JLabel("| 가격:");
		listbottomlabel5.setBounds(450, 500, 130, 120);
		listbottomlabel5.setForeground(Color.WHITE);
		listbottomlabel5.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabel5);
		listbottomlabel6 = new JLabel("");
		listbottomlabel6.setBounds(540, 500, 130, 120);
		listbottomlabel6.setForeground(Color.WHITE);
		listbottomlabel6.setFont(new Font("HYPOST", Font.BOLD, 28));
		listPanel.add(listbottomlabel6);
		JButton listbottombutton1 = new JButton("상세화면");
		listbottombutton1.setBounds(190, 630, 280, 70);
		listbottombutton1.setBackground(Color.BLACK);
		listbottombutton1.setForeground(Color.WHITE);
		listbottombutton1.setFont(new Font("HYPOST", Font.BOLD, 28));
		listbottombutton1.addActionListener(this);

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
		detailToplabel.setBounds(225, 0, 130, 80);
		detailToplabel.setForeground(Color.WHITE);
		detailToplabel.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailToplabel);

		detailToplabel2 = new JLabel("153");
		detailToplabel2.setBounds(385, 0, 120, 80);
		detailToplabel2.setForeground(Color.WHITE);
		detailToplabel2.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailToplabel2);
		
		JButton backToListBtn = new JButton(">>");
		backToListBtn.setBounds(590, 15, 50, 50);
		backToListBtn.setBackground(Color.BLACK);
		backToListBtn.setForeground(Color.WHITE);
		backToListBtn.setFont(new Font("HYPOST", Font.BOLD, 13));
		backToListBtn.addActionListener(this);
		detailPanel.add(backToListBtn);
		
		listtablePanel = new JPanel();
		listtablePanel.setBackground(new Color(30, 30, 100));
		listtablePanel.setBounds(0, 80, 684, 400);
		
		table2 = new JTable();
		table2.setPreferredScrollableViewportSize(new Dimension(680, 300));
		table2.setFillsViewportHeight(true);
		table2.setBackground(Color.WHITE);
		JScrollPane sp = new JScrollPane(table2);
		listtablePanel.add(sp);
		
		setdetailTable();
		
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

		detailbottomlabel2 = new JLabel("5개");
		detailbottomlabel2.setBounds(200, 500, 150, 120);
		detailbottomlabel2.setForeground(Color.WHITE);
		detailbottomlabel2.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailbottomlabel2);

		JLabel detailbottomlabel3 = new JLabel("총결제금액:");
		detailbottomlabel3.setBounds(350, 500, 200, 120);
		detailbottomlabel3.setForeground(Color.WHITE);
		detailbottomlabel3.setFont(new Font("HYPOST", Font.BOLD, 28));
		detailPanel.add(detailbottomlabel3);

		detailbottomlabel4 = new JLabel("");
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
	
	void setListTable() {
		listSet.clear();
		columnNames1 = new Vector<String>();
		rowData1 = new Vector<Vector<Object>>();
		columnNames1.add("주문번호");
		columnNames1.add("상태");
		columnNames1.add("주문메뉴");
		columnNames1.add("가격");
		columnNames1.add("주문시간");
		
		os.selectOrder();
		Vector orderV = null;
		String pname = "";
		for (Vector<Object> vector: os.ordersSet) {
			orderV = new Vector();
			String orderNo = (String)vector.get(0);
			if(orderNo.endsWith("1")){
				int pno = (Integer)vector.get(2);
				os.selectProduct(2, pno);
				// 상품 이름
				pname = os.productVector.get(1).toString();
				orderNo = orderNo.substring(0, 10);
				// 같은 주문번호로 주문된게 몇개인지 확인
				int count = os.returnSameOrderNumCount(orderNo);
				String countS = Integer.toString(count);
				// 같은 주문번호로 기준 합계금액 확인
				int priceSum = os.returnPriceSum(orderNo);
				String priceSumS = Integer.toString(priceSum);
				String orderState = null;
				// 주문번호, 주문상태
				orderV.add(orderNo);
				if(vector.get(5).equals(1)) {
					orderState = "주문대기";
				} else if(vector.get(5).equals(2)) {
					orderState = "결제완료";
				} else if(vector.get(5).equals(3)) {
					orderState = "결제취소";
				}
				orderV.add(orderState);
				// 대표메뉴
				if (count == 1){
					orderV.add(pname);
				} else {
					String num = Integer.toString(count-1);
					orderV.add(pname + " 외 " + num + "개");
				}
				// 가격
				orderV.add(priceSum + "원");
				// 주문시간
				orderV.add(vector.get(4));
				//System.out.println(orderV);
				listSet.add(orderV);
			} else {}
		}
		for(Vector v:listSet) {
			//System.out.println(v);
			rowData1.add(v);
		}
		model1 = new DefaultTableModel(rowData1, columnNames1);
		table1.setModel(model1);
		table1.getColumnModel().getColumn(1).setPreferredWidth(20);
		table1.getColumnModel().getColumn(3).setPreferredWidth(30);
		table1.getColumnModel().getColumn(4).setPreferredWidth(100);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table1.getColumnModel() ; 
		
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
	}
	
	void setdetailTable() {
		Vector<String> columnNames = new Vector<String>();
		Vector<Vector<Object>> rowData = new Vector<Vector<Object>>();
		columnNames.add("주문번호");
		columnNames.add("상품명");
		columnNames.add("가격");
		columnNames.add("옵션1");
		columnNames.add("옵션2");
		columnNames.add("옵션3");
		columnNames.add("옵션4");
		columnNames.add("옵션5");
		
		Vector<Object> orderV = new Vector<Object>();	
		for (Vector<Object> vector: os.ordersSet) {
			orderV = new Vector();

			int pno = (Integer)vector.get(2);
			os.selectProduct(2, pno);
			// 상품 이름
			String pname = os.productVector.get(1).toString();
			String price = os.productVector.get(2).toString();
			int cop1 = (Integer)vector.get(7);
			int cop2 = (Integer)vector.get(8);
			int cop3 = (Integer)vector.get(9);
			int cop4 = (Integer)vector.get(10);
			int cop5 = (Integer)vector.get(11);
			String option1="", option2="", option3="", option4="", option5="";
			if(cop1==11) option1 = "매장";
			else if(cop1==12) option1 = "포장";
			if(cop2==21) option2 = "아이스";
			else if(cop2==22) option2 = "핫";
			else option2 = "";
			if(cop3==31) option3 = "미디움";
			else if(cop3==32) option3 = "라지";
			else option3 = "";
			if(cop4==41) option4 = "샷추가";
			else if(cop4==42) option4 = "추가안함";
			else option4 = "";
			if(cop5==51) option5 = "얼음많이";
			else if(cop5==52) option5 = "얼음조금";
			else if(cop5==53) option5 = "선택안함";
			else option5 = "";
			
			orderV.add((String)vector.get(0));
			orderV.add(pname);
			orderV.add(price);
			orderV.add(option1);
			orderV.add(option2);
			orderV.add(option3);
			orderV.add(option4);
			orderV.add(option5);
			rowData.add(orderV);
		}

		model2 = new DefaultTableModel(rowData, columnNames);
		table2.setModel(model2);
		table2.getColumnModel().getColumn(0).setPreferredWidth(150);
		table2.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table2.getColumnModel() ; 
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(2).setCellRenderer(dtcr);
		tcm.getColumn(3).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		tcm.getColumn(5).setCellRenderer(dtcr);
		tcm.getColumn(6).setCellRenderer(dtcr);
		tcm.getColumn(7).setCellRenderer(dtcr);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if (btn.getText().equals("상세화면")) {
			try {
				if(orderNumber.equals(null)) {
				} else {
					onDetailPanel();
					detailToplabel2.setText(orderNumber.substring(7,10));
				}
			}catch(NullPointerException npe){}
		} else if (btn.getText().equals(">>")) {
			onListPanel();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table1.getSelectedRow();
		try {
			orderNumber = table1.getValueAt(row, 0).toString();
			totalPrice = table1.getValueAt(row, 3).toString();
			String orderCount = table1.getValueAt(row, 2).toString();
			if (orderCount.endsWith("개")){
				int startIdx = orderCount.indexOf("외") + 2;
				int endIdx = startIdx + 1;
				orderCount = orderCount.substring(startIdx, endIdx);
			}else {
				orderCount = "1";
			}
			String orderState = table1.getValueAt(row, 1).toString();
			listbottomlabel2.setText(orderNumber.substring(7,10));
			listbottomlabel4.setText(orderState);
			listbottomlabel6.setText(totalPrice);
			detailbottomlabel2.setText(orderCount);
			detailbottomlabel4.setText(totalPrice);
			os.selectOrder(orderNumber);
			setdetailTable();
		} catch (ArrayIndexOutOfBoundsException io){}
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void run() {
		try {
			while(true) {
				Thread.sleep(5000);
				if(isVisible()) { // 관리자 페이지가 On 일 경우에만
					setListTable(); //페이지 새로 고침
				}
			}
		} catch(Exception e) {}
		
	}
}
