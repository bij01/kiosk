import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import javax.swing.border.*;
import com.formdev.flatlaf.*;

class OrderClient extends JFrame implements ActionListener {
	private static final long serialVersionUID = 7610854644979608019L;
	final int WIDTH = 700;
	final int HEIGHT = 1000;

	Container cp;
	JPanel firstPanel, mainPanel, bottomPanel;
	JButton inBtn, outBtn;
	public String inout;
	
	void testMode(){ // 바로 두번째 화면으로 넘어가기(개발 끝나면 삭제)
		firstPanel.setVisible(false);
		mainPanel.setVisible(true);
		bottomPanel.setVisible(true);
	}
	void init(){
		setTitle("아싸커피");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);
		cp = getContentPane();
		cp.setLayout(null);
		setFirstPanel();
		setBottomPanel();
		setMainPanel();
		testMode();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	void setFirstPanel(){
		firstPanel = new JPanel();
		firstPanel.setBounds(0, 0, 684, 961);
		firstPanel.setLayout(null);
		firstPanel.setVisible(true);
		firstPanel.setBackground(new Color(50, 70, 70));	
		String bgimagePath = "./src/background1.png";
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(new File(bgimagePath));
		} catch (Exception e){
			System.out.println(e);
		}
        Image img = bufferedImage.getScaledInstance(680, 960, Image.SCALE_DEFAULT);
		ImageIcon bgimg = new ImageIcon(img);
		JLabel bgimageLabel = new JLabel(bgimg);
		bgimageLabel.setBounds(0,0,680,960);		
		inBtn = new JButton("매장");
		inBtn.setForeground(Color.WHITE);
		inBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		inBtn.setFocusPainted(false);
		//BorderFactory.createLineBorder
		//inBtn.setBorder(BorderFactory.createEmptyBorder(3 , 3 , 3 , 3));
		inBtn.setFont(new Font("HYPOST",Font.BOLD,28));
		inBtn.setBackground(new Color(51,25,0));	
		inBtn.setBounds(80, 800, 200, 80);
		inBtn.addActionListener(this);
		outBtn = new JButton("포장");
		outBtn.setBounds(400, 800, 200, 80);
		outBtn.addActionListener(this);	
		outBtn.setForeground(Color.WHITE);
		outBtn.setBorder(BorderFactory.createRaisedBevelBorder());
		outBtn.setFocusPainted(false);
		outBtn.setFont(new Font("HYPOST",Font.BOLD,28));
		outBtn.setBackground(new Color(51,25,0));	
		bgimageLabel.add(inBtn);
		bgimageLabel.add(outBtn);	
		firstPanel.add(bgimageLabel);
		cp.add(firstPanel);	
	
		
	}
	void setMainPanel(){
		mainPanel = new MainPanel(this);
		mainPanel.setVisible(false);	
		cp.setBackground(new Color(150, 150, 150));
		cp.add(mainPanel);
	}
	void setBottomPanel(){
		bottomPanel = new JPanel();
		bottomPanel.setBounds(0, 881, 684, 80);
		bottomPanel.setVisible(false);
		bottomPanel.setBackground(new Color(50, 70, 70));
		cp.add(bottomPanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton Btn = (JButton) e.getSource();
		inout = Btn.getText().trim();
		System.out.println(inout);
		firstPanel.setVisible(false);
		mainPanel.setVisible(true);
		bottomPanel.setVisible(true);
	}
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(
				new FlatLightLaf());
		} catch (UnsupportedLookAndFeelException e) {
		}
		new OrderClient().init();
	}

	
}
