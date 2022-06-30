import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.io.*;

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
		//insertProduct(127,"자바칩프라프치노", 6600, 1, 11);
		//selectProduct(1, 11);
		//deleteProduct(127);
		//insertCart(1,113, 11, 21, 32, 42, 53);
		//selectCart();
		//insertOrder();
		//selectOrder();
		//returnFileInfo(111);
		//System.out.println(returnFileInfo(111)[0] + "." + returnFileInfo(111)[1]);
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
	
	// 상품번호, 상품명, 상품 가격, 판매 상태, 카테고리번호
	public void insertProduct(int PNO, String PNAME, int PSAL, int PSTATE, int CNO) {
		sql = "insert into PRODUCT values(?, ?, ?, ?, ?)";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1,PNO);
			pstmt2.setString(2, PNAME);
			pstmt2.setInt(3, PSAL);
			pstmt2.setInt(4, PSTATE);
			pstmt2.setInt(5, CNO);
			int i = pstmt2.executeUpdate();
			if(i>0) {
				System.out.println("상품 추가 완료");
				con.commit();
			}else {
				System.out.println("상품 추가 실패");
				con.rollback();
			}
		}catch(SQLException se) {
			System.out.println("insert 실패: "+ se);
		}
	}
	
	@Override
	//mode == 1 이면 카테고리별 상품을 조회, mode != 1 단일 상품 조회
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
				System.out.println(rs.getInt(1)+ " "+ rs.getString(2) + " " + rs.getInt(3) +"원 " + rs.getInt(4) +" "+ rs.getInt(5));
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
		}
	}
	
	public void deleteProduct(int PNO) { //추가시킨 자바칩프라프치노에 문제가있어 삭제
		sql = "delete from PRODUCT where PNO=?";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, PNO);
			int i = pstmt2.executeUpdate();
			if(i>0) {
				System.out.println("삭제 성공");
				con.commit();
			}else {
				System.out.println("삭제 실패");
				con.rollback();
			}
		}catch(SQLException se) {}
	}

	@Override 
	// 장바구니번호, 기본은 kiosk, 상품번호, 옵션1(매장, 포장), 옵션2(아이스, 핫), 옵션3(미디움 / 라지), 옵션4(샷추가 / 추가안함), 옵션5(얼음 많이 / 얼음 조금/ 추가안함)
	public void insertCart(int CDNO , int PNO, int COP1, int COP2, int COP3, int COP4, int COP5) {
		sql = "insert into CART values(?,kiosk,?,?,?,?,?,?)";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, CDNO);
			pstmt2.setInt(2, PNO);
			pstmt2.setInt(3, COP1);
			pstmt2.setInt(4, COP2);
			pstmt2.setInt(5, COP3);
			pstmt2.setInt(6, COP4);
			pstmt2.setInt(7, COP5);
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
	//장바구니번호, 기본은 kiosk, 상품번호, 옵션1(매장, 포장), 옵션2(아이스, 핫), 옵션3(미디움 / 라지), 옵션4(샷추가 / 추가안함), 옵션5(얼음 많이 / 얼음 조금/ 추가안함)
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
	// 주문번호(자동생성), 회원ID(자동입력), 장바구니번호(상품번호, 옵션번호 1~5), 주문시간, 주문상태
	public void insertOrder(String cdno) { //주문 테이블에 데이터 넣기
		sql = "select to_char(SYSDATE, 'YYMMDD'), ORDERS_SEQ.nextval from dual";
		ResultSet rs = null;
		String date;
		String orderNoFinal;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				date = rs.getString(1);
				String orderNo = rs.getString(2);
				System.out.println(date + ", " + orderNo + "order길이: " + orderNo.length());
				if(orderNo.length() == 1) {
					orderNo = "00" + orderNo;
				} else if (orderNo.length() == 2) {
					orderNo = "0" + orderNo;
				}else if (orderNo.length() == 3) {
					orderNo = "" + orderNo;
				} else {
					System.out.println("범위 초과");
				}
				System.out.println(date + "-" + orderNo);
				if (cdno.length() == 1) {
					orderNoFinal = date + "-" + orderNo + "-0" + cdno;
					System.out.println(orderNoFinal);
				}else if (cdno.length() == 2){
					orderNoFinal = date + "-" + orderNo + "-" + cdno;
				}else {
					System.out.println("범위 초과");
				}
				// 장바구니번호(주문상세번호) 입력받아서 이걸 기준으로 장바구니 테이블에서 상품번호, 옵션번호1~5를 받아오기
				String sql = "select ";
				// 장바구니 번호에 있는 상품번호를 기준으로 상품테이블에서 가격데이터(PSAL) 가져오기
				
				// orderNoFinal + 주문상세번호,  제대로 완성 됐을때 insert문 실행
				String sql2 = "insert into ORDERS values(?,);";
			}
		}catch(SQLException se) {
			System.out.println("상품을 찾을 수 없습니다." + se);
		}
		
		
	}

	@Override
	public void selectOrder() { //주문테이블 조회
		sql = "select * from ORDERS order by ONO";
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
	
	//이미지를 불러와서 배열에 담아서 이미지를 집어넣기 위한 메서드
	String[] returnFileInfo(int pno) {
		sql = "select FPATH, FEXT from FILES where pno=?";
		ResultSet rs = null;
		String [] fileInfo = new String[2];
		try {
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, pno);
			rs = pstmt1.executeQuery();

			while(rs.next()) {			
				fileInfo[0] = rs.getString(1);
				fileInfo[1] = rs.getString(2);
				//System.out.println(fileInfo[0] + ", " + fileInfo[1]);
			}
			return fileInfo; 
		}catch(SQLException se) {
			System.out.println(se);
		}
		
		return fileInfo;
	}
	

	public static void main(String[] args) {
		OrderServerImpl os = new OrderServerImpl();
		os.init();
	}

}
