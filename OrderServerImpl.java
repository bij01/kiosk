import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.sql.RowSetInternal;

public class OrderServerImpl implements OrderServer {
	static final String URL="jdbc:oracle:thin:@localhost:1521:JAVA";
	static final String USR="team2";
	static final String PWD="java123";
	Connection con;
	PreparedStatement pstmt1, pstmt2, pstmt3, pstmt4; //select=1 insert=2 update=3 delete=4
	Statement stmt;
	String sql = "";
	
	Vector<Vector> cartVector1 = new Vector<Vector>();
	Vector<Vector> productsVector = new Vector<Vector>(); //카테고리별 상품 조회
	Vector<Object> productVector = new Vector<Object>(); // 단일 상품 조회
	
	void init() {
		connectDB();
		//selectProducts();
		//selectProduct(2, 111);
		//System.out.println(productsVector);
		//System.out.println(productVector);
		//insertCart("heart1", 113);
		//selectCart();
		//System.out.println(cartVector1);
		//insertOrder();
		//selectOrder();
		//insertProduct(127,"자바칩프라프치노");
		//deleteProduct(127,"자바칩프라프치노");
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
	
	@Override
	//String SQL1= "select * from PRODUCT where CNO="+category+"";
	public void selectProduct(int mode, int no) {
		if(mode == 1) {
			sql = "select * from PRODUCT where CNO="+no+" order by PNO";
		} else {
			sql = "select * from PRODUCT where PNO="+no+"";
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
			//con.commit();
		}catch(SQLException se) {
			System.out.println("상품을 찾을 수 없습니다." + se);
		}
	}


	@Override 
	public void insertCart(String MID, int PNO) {
		// 장바구니번호, 유저ID, 상품번호, 옵션1(매장, 포장), 옵션2(아이스, 핫), 옵션3(), 옵션4, 옵션5
		sql = "insert into CART values(CART_SEQ.nextval,?,?,12,21,32,42,53)";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1,MID);
			pstmt2.setInt(2, PNO);
			int i = pstmt2.executeUpdate();
			if(i>0) {
				System.out.println("추가 성공");
				con.commit();
			}else {
				System.out.println("실험 실패");
				con.rollback();
			}
		}catch(SQLException se) {
			System.out.println("insert실패:"+se);
		}
	}
	
	@Override
	public void selectCart() { //장바구니 테이블 조회
		sql = "select * from CART order by CDNO";
		ResultSet rs = null;
		Vector<Object> cartVector2 = null;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			
			while(rs.next()) {
				cartVector2 = new Vector<Object>();
				System.out.println(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getInt(3)
				+" "+ rs.getInt(4)+" "+ rs.getInt(5)+" "+ rs.getInt(6)+" "+ rs.getInt(7)
				+" "+ rs.getInt(8));
				cartVector2.add(rs.getInt(1));
				cartVector2.add(rs.getString(2));
				cartVector2.add(rs.getInt(3));
				cartVector2.add(rs.getInt(4));
				cartVector2.add(rs.getInt(5));
				cartVector2.add(rs.getInt(6));
				cartVector2.add(rs.getInt(7));
				cartVector2.add(rs.getInt(8));
				cartVector1.add(cartVector2);
				
			}
			
		}catch(SQLException se) {
			System.out.println("상품을 찾을 수 없습니다." + se);
		}
	}
	
	@Override
	public void insertOrder() { //주문 테이블에 데이터 넣기
		sql = "insert into Order valuse(to_cahr(SYSDATE, 'YYMMDD'))";
	}

	@Override
	public void selectOrder() { //주문테이블 조회
		sql = "select * from ORDERS";
		ResultSet rs = null;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+ rs.getString(2)+" "+ rs.getInt(3)
				+" "+ rs.getInt(4)+" "+ rs.getString(5)+" "+ rs.getInt(6)+" "+ rs.getInt(7)
				+"원 "+ rs.getInt(8)+" "+ rs.getInt(9)+" "+ rs.getInt(10)+" "+ rs.getInt(11)
				+" "+ rs.getInt(12));
			}
			
		}catch(SQLException se) {
			System.out.println("상품을 찾을 수 없습니다." + se);
		}
	}

	public void selectOrder1() { //주문테이블에서 주문번호별로 조회
		
	}
	
	public void insertProduct(int PNO, String PNAME) {//상품 테이블에 상품 추가하기
		sql = "insert into PRODUCT values(?, ?, 6600, 1, 11)";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1,PNO);
			pstmt2.setString(2, PNAME);
			int i = pstmt2.executeUpdate();
			if(i>0) {
				System.out.println("상품 추가 완료");	
			}else {
				System.out.println("상품 추가 실패");
			}
		}catch(SQLException se) {
			System.out.println("insert 실패: "+ se);
		}
	}
	
	public void deleteProduct(int PNO, String PNAME) { //추가시킨 자바칩프라프치노에 문제가있어 삭제
		sql = "delete ";
		
	}
	
	public void insertMember() { // 신규 회원가입
		
	}
	
	public void deleteMember() { //회원탈퇴
		
	}
	
	public void updateProduct() { //메뉴의 가격을 변경
		
	}
	
	public void updateCart() { // 기존에 담은 장바구니에서 옵션을 변경
		
	}
	
	public void deleteCart() { // 장바구니에서 선택했던 메뉴를 삭제
		
	}
	
	public void updateProduct1() { //판매중이던 메뉴 중 하나를 솔드아웃으로 바꾸기
		
	}
	
	public void insertCart1() { //장바구니 테이블에서 주문을 추가하면 초기화 될 메소드
		
	}

	public void insertOrder1() { //주문 테이블에 데이터가 들어오면 장바구니를 초기화 시키기
		
	}
	

	public static void main(String[] args) {
		OrderServerImpl os = new OrderServerImpl();
		os.init();
	}

}
