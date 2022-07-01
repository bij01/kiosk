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

class StaffPanel extends JPanel implements ActionListener, MouseListener {
	OrderServerImpl os;
	MainPanel mp;
	OrderClient oc;
	LinkedHashSet<Vector> listSet = new LinkedHashSet<Vector>(); //주문리스트
	
	JPanel listPanel, detailPanel;
	JTable table1, table2;
	String orderNumber, totalPrice;

	StaffPanel(MainPanel mp) {
		this.mp = mp;
		init();
		setListPanel();
		setDetailPanel();
		onListPanel();
		//onDetailPanel();
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
			}
			orderNo = orderNo.substring(0, 10);
			// 같은 주문번호로 주문된게 몇개인지 확인
			int count = os.returnSameOrderNumCount(orderNo);
			String countS = Integer.toString(count);
			// 같은 주문번호로 기준 합계금액 확인
			int priceSum = os.returnPriceSum(orderNo);
			String priceSumS = Integer.toString(priceSum);
			//System.out.println("주문번호: "+ orderNo +", 주문합계: " + count + ", 금액: " + priceSum);
			
			//orderV.add(countS);
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
			if (countS.equals("1")){
				orderV.add(pname);
			} else {
				String num = Integer.toString(count-1);
				orderV.add(pname + " 외 " + num + "개");
			}
			// 가격
			orderV.add(priceSum + "원");
			// 주문시간
			orderV.add(vector.get(4));
			
			listSet.add(orderV);
		}

		for(Vector v:listSet) {
			//System.out.println(v);
			rowData.add(v);
		}
			
		//System.out.println(os.ordersSet);
		
		table1 = new JTable(rowData, columnNames);
		table1.setPreferredScrollableViewportSize(new Dimension(680, 300));
		table1.setFillsViewportHeight(true);
		table1.setBackground(Color.WHITE);
		table1.getColumnModel().getColumn(1).setPreferredWidth(20);
		table1.getColumnModel().getColumn(3).setPreferredWidth(30);
		table1.getColumnModel().getColumn(4).setPreferredWidth(100);
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.CENTER);
		TableColumnModel tcm = table1.getColumnModel() ; 
		table1.addMouseListener(this);
		tcm.getColumn(0).setCellRenderer(dtcr);
		tcm.getColumn(1).setCellRenderer(dtcr);
		tcm.getColumn(4).setCellRenderer(dtcr);
		
		JScrollPane sp = new JScrollPane(table1);
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
		table2 = new JTable(rowData, columnNames);
		table2.setPreferredScrollableViewportSize(new Dimension(680, 300));
		table2.setFillsViewportHeight(true);
		table2.setBackground(Color.WHITE);
		

		JScrollPane sp = new JScrollPane(table2);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton)e.getSource();
		if (btn.getText().equals("선택하기")) {
			
			//onDetailPanel();
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table1.getSelectedRow();
		System.out.println(row);
		orderNumber = table1.getValueAt(row, 0).toString();
		totalPrice = table1.getValueAt(row, 3).toString();
		System.out.println(orderNumber +", "+totalPrice);
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
