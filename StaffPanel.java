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
	
	StaffPanel(){
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
		
		
		//
		add(listPanel);
	}
	
	void setDetailPanel() {
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
