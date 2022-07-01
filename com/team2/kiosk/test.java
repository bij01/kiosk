package com.team2.kiosk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

public class test {
	
	static final String URL="jdbc:oracle:thin:@localhost:1521:JAVA";
	static final String USR="team2";
	static final String PWD="java123";
	Connection con;
	PreparedStatement pstmt1, pstmt2, pstmt3, pstmt4; //select=1 insert=2 update=3 delete=4
	Statement stmt, stmt1;
	String sql = "";
	
	Vector<Vector> cartVector1 = new Vector<Vector>();
	Vector<Vector> productsVector = new Vector<Vector>(); //카테고리별 상품 조회
	Vector<Object> productVector = new Vector<Object>(); // 단일 상품 조회
	Vector<Vector> memberVector1 = new Vector<Vector>(); //회원 이름으로 조회
	Vector<Object> memberVector2 = new Vector<Object>(); // 회원 핸드폰 번호로 조회
	
	void init() {
		connectDB();
		//insertProduct(128,"자바칩프라프치노1", 6600, 1, 11);
		selectProduct(1, 11);
		System.out.println(productsVector);
		//deleteProduct(127);
		//insertCart(1, 113, 11, 21, 32, 42, 53);
		//selectCart();
		//insertOrder(1);
		//selectOrder();
		//returnFileInfo(111);
		//insertMember("strong1", "power123", "김근육", "010-3333-6666", 0);
		//selectMember(2,"010-4252-3906");
		//updateOSTATE(3, "220701-011");
	}
	//DB와의 연결
	void connectDB() {
		String info1 = URL;
		String info2 = USR;
		String info3 = PWD;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(info1, info2, info3);
			con.setAutoCommit(false);
			stmt = con.createStatement();
			System.out.println("연결 성공");
		}catch(ClassNotFoundException cnfe) {
			System.out.println("드라이버 로딩 실패:" + cnfe);
		}catch(SQLException se) {
			System.out.println("연결 실패: "+ se);
		}
	}
	
	void selectProduct(int mode, int cno) {
		System.out.println("test");
		if(mode == 1) {
			sql = "select * from PRODUCT where CNO="+cno+" order by PNO";
		} else {
			sql = "select * from PRODUCT where PNO="+cno+"";
		}
		ResultSet rs = null;
		Vector<Object> productV = null;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				//System.out.println(rs.getInt(1)+ " "+ rs.getString(2) + " " + rs.getInt(3) +"원 " + rs.getInt(4) +" "+ rs.getInt(5));
				if(mode == 1) {
					productV = new Vector<Object>();
					productV.add(rs.getInt(1));
					productV.add(rs.getString(2));
					productV.add(rs.getInt(3));
					productV.add(rs.getInt(4));
					productV.add(rs.getInt(5));
					productsVector.add(productV);
				} else {
					productVector.add(rs.getInt(1));
					productVector.add(rs.getString(2));
					productVector.add(rs.getInt(3));
					productVector.add(rs.getInt(4));
					productVector.add(rs.getInt(5));
				}
			}
		}catch(SQLException se) {
			System.out.println("상품을 찾을 수 없습니다." + se);
		}finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt1!=null) pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
public static void main(String[] args) {
	OrderServerImpl os = new OrderServerImpl();
	os.init();
}

}
