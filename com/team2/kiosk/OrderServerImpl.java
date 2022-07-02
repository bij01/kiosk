package com.team2.kiosk;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Vector;
import java.io.*;

public class OrderServerImpl implements OrderServer {
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
	
	LinkedHashSet<Vector> ordersSet = new LinkedHashSet<Vector>();
	
	
	void init() {
		connectDB();
		//returnSameOrderNumCount("220701-024");
		//insertProduct(128,"자바칩프라프치노1", 6600, 1, 11);
		//selectProduct(1, 11);
		//deleteProduct(127);
		//insertCart(1, 113, 11, 21, 32, 42, 53);
		//selectCart();
		//insertOrder(1);
		//selectOrder();
		//System.out.println(ordersSet);
		//returnFileInfo(111);
		//insertMember("strong1", "power123", "김근육", "010-3333-6666", 0);
		//selectMember(2,"010-4252-3906");
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
	int returnPriceSum(String ono) {
		sql = "select sum(psal) from product where pno in "
				+ "(select pno from orders where ono like ?)";
		ResultSet rs = null;
		int sum = 0;
		try {
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, "%"+ono+"%");
			rs = pstmt1.executeQuery();
			
			while(rs.next()) {
				sum = rs.getInt(1);
				//System.out.println(sum);
			}
		}catch(SQLException se) {
			System.out.println("select 실패: "+ se);
		}finally {
			try {
				if (pstmt1!=null) pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sum;
	}
	
	int returnSameOrderNumCount(String ono) {
		sql = "select count(ONO) from orders where ONO like ?";
		ResultSet rs = null;
		int count = 0;
		try {
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, "%"+ono+"%");
			rs = pstmt1.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
				//System.out.println(count);
			}
		}catch(SQLException se) {
			System.out.println("select 실패: "+ se);
		}finally {
			try {
				if (pstmt1!=null) pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}
	
	
	// 상품번호, 상품명, 상품 가격, 판매 상태, 카테고리번호
	public void insertProduct(int PNO, String PNAME, int PPRICE, int PSTATE, int CNO) {
		sql = "insert into PRODUCT values(?, ?, ?, ?, ?)";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1,PNO);
			pstmt2.setString(2, PNAME);
			pstmt2.setInt(3, PPRICE);
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
		}finally {
			try {
				if (pstmt2!=null) pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	//mode == 1 이면 카테고리별 상품을 조회, mode != 1 단일 상품 조회
	public void selectProduct(int mode, int cno) {
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
					productVector.clear();
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
				//System.out.println("삭제 실패");
				con.rollback();
			}
		}catch(SQLException se) {
		}finally {
			try {
				if (pstmt2!=null) pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override 
	// 장바구니번호, 기본은 kiosk, 상품번호, 옵션1(매장, 포장), 옵션2(아이스, 핫), 옵션3(미디움 / 라지), 옵션4(샷추가 / 추가안함), 옵션5(얼음 많이 / 얼음 조금/ 추가안함)
	public void insertCart(int CDNO , int PNO, int COP1, int COP2, int COP3, int COP4, int COP5) {
		sql = "insert into CART values(?, 'kiosk', ?, ?, ?, ?, ?, ?)";
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
		}finally {
			try {
				if (pstmt2!=null) pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	void deleteCart() {
		sql = "delete from CART";
		try {
			pstmt2 = con.prepareStatement(sql);
			int i = pstmt2.executeUpdate();
			if(i>0) {
				System.out.println("삭제 성공");
				con.commit();
			}else {
				//System.out.println("삭제 실패");
				con.rollback();
			}
		}catch(SQLException se) {
		}finally {
			try {
				if (pstmt2!=null) pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
		}finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt1!=null) pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	// 주문번호(자동생성), 회원ID(자동입력), 장바구니번호(상품번호, 옵션번호 1~5), 주문시간, 주문상태
	public void insertOrder(int cdno) { //주문 테이블에 데이터 넣기
		String orderNo = returnOrderNo(cdno);
		// 장바구니번호(주문상세번호) 입력받아서 이걸 기준으로 장바구니 테이블에서 상품번호, 옵션번호1~5를 받아오기
		//PNO, COP1, COP2, COP3, COP4, COP5 
		int options[] = selectCartOptions(cdno);
	
		// 장바구니 번호에 있는 상품번호를 기준으로 상품테이블에서 가격데이터(PPRICE) 가져오기
		int pprice = returnPrice(options[0]);
		//System.out.println(orderNo + ", " + pprice);
		
		// orderNo + 주문상세번호,  제대로 완성 됐을때 insert문 실행
		// 1.ONO 2.MID 3.PNO 4.ODNO 5.ODATE 6.OSTATE 7.PPRICE 8.COP1 9.COP2 10.COP3 11.COP4 12.COP5
		//    ?  kiosk   ?      ?     sys       ?        ?       ?      ?       ?       ?       ?
		String sql = "insert into ORDERS values(?,'kiosk',?,?,SYSDATE,?,?,?,?,?,?,?)";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, orderNo);
			pstmt2.setInt(2,options[0]);
			pstmt2.setInt(3,cdno);
			pstmt2.setInt(4,1);
			pstmt2.setInt(5,pprice);
			pstmt2.setInt(6,options[1]);
			pstmt2.setInt(7,options[2]);
			pstmt2.setInt(8,options[3]);
			pstmt2.setInt(9,options[4]);
			pstmt2.setInt(10,options[5]);
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
		}finally {
			try {
				if (pstmt2!=null) pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	String returnOrderNo(int cdno) {
		if (cdno == 1) {
			sql = "select to_char(SYSDATE, 'YYMMDD'), ORDERS_SEQ.nextval from dual";
		} else {
			sql = "select to_char(SYSDATE, 'YYMMDD'), ORDERS_SEQ.currval from dual";
		}
		ResultSet rs = null;
		String date;
		String date2;
		String finalOrderNo = null;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				date = rs.getString(1);
				String orderNo = rs.getString(2);
				//System.out.println(date + ", " + orderNo + "order길이: " + orderNo.length());
				if(orderNo.length() == 1) {
					orderNo = "00" + orderNo;
				} else if (orderNo.length() == 2) {
					orderNo = "0" + orderNo;
				}else if (orderNo.length() == 3) {
					orderNo = "" + orderNo;
				} else {
					System.out.println("범위 초과");
				}
				//System.out.println(date + "-" + orderNo);
				date2 = date + "-" + orderNo;
				String cdnoS = Integer.toString(cdno);
				if (cdnoS.length() == 1) {
					finalOrderNo = date + "-" + orderNo + "-0" + cdnoS;
					//System.out.println(finalOrderNo);
					return finalOrderNo;
				}else if (cdnoS.length() == 2){
					finalOrderNo = date + "-" + orderNo + "-" + cdnoS;
					return finalOrderNo;
				}else {
					System.out.println("범위 초과");
				}
				return date2;
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
		return finalOrderNo;
		
	}
	
	int returnPrice(int pno) {
		String sql = "select PPRICE from PRODUCT where PNO=?"; 
		ResultSet rs = null;
		int price = 0;
		try {
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, pno);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				price = rs.getInt(1);
				return price;
			}
		}catch(SQLException se) {
		}finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt1!=null) pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return price;
	}
	
	int[] selectCartOptions(int cdno) {
		String sql = "select PNO, COP1, COP2, COP3, COP4, COP5 from CART where CDNO=?";
		ResultSet rs = null;
		int options[] = new int[6];
		try {
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setInt(1, cdno);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				options[0] = rs.getInt(1); options[1] = rs.getInt(2); options[2] = rs.getInt(3);
				options[3] = rs.getInt(4); options[4] = rs.getInt(5); options[5] = rs.getInt(6);
				return options;
			}
		}catch(SQLException se) {
		}finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt1!=null) pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if (rs!=null) rs.close();
					if (pstmt1!=null) pstmt1.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return options;
	}

	@Override
	public void selectOrder() { //주문테이블 조회
		ordersSet.clear();
		sql = "select * from ORDERS order by ONO desc";
		ResultSet rs = null;
		Vector<Object> orderVector;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				orderVector = new Vector<Object>();
				orderVector.add(rs.getString(1));
				orderVector.add(rs.getString(2));
				orderVector.add(rs.getInt(3));
				orderVector.add(rs.getInt(4));
				orderVector.add(rs.getString(5));
				orderVector.add(rs.getInt(6));
				orderVector.add(rs.getInt(7));
				orderVector.add(rs.getInt(8));
				orderVector.add(rs.getInt(9));
				orderVector.add(rs.getInt(10));
				orderVector.add(rs.getInt(11));
				orderVector.add(rs.getInt(12));
				ordersSet.add(orderVector);
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
	
	public void selectOrder(String ono) { //주문번호로 주문테이블 조회
		ordersSet.clear();
		sql = "select * from ORDERS where ONO like ? order by ONO";
		ResultSet rs = null;
		Vector<Object> orderVector;
		try {
			pstmt1 = con.prepareStatement(sql);
			pstmt1.setString(1, "%"+ono+"%");
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				orderVector = new Vector<Object>();
				orderVector.add(rs.getString(1));
				orderVector.add(rs.getString(2));
				orderVector.add(rs.getInt(3));
				orderVector.add(rs.getInt(4));
				orderVector.add(rs.getString(5));
				orderVector.add(rs.getInt(6));
				orderVector.add(rs.getInt(7));
				orderVector.add(rs.getInt(8));
				orderVector.add(rs.getInt(9));
				orderVector.add(rs.getInt(10));
				orderVector.add(rs.getInt(11));
				orderVector.add(rs.getInt(12));
				ordersSet.add(orderVector);
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
		}finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt1!=null) pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return fileInfo;
	}
	
	void insertMember(String MID, String MPW, String MNAME, String MPHNO, int MPOINT) {
		sql = "insert into MEMBER values (?, ? , MEMBER_SEQ.nextval , ?, ? , ?)";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1, MID);
			pstmt2.setString(2, MPW);
			pstmt2.setString(3, MNAME);
			pstmt2.setString(4, MPHNO);
			pstmt2.setInt(5, MPOINT);
			int i = pstmt2.executeUpdate();
			if(i>0) {
				System.out.println("추가 성공");
				con.commit();
			}else {
				System.out.println("추가 실패");
				con.rollback();
			}
		}catch(SQLException se) {
			System.out.println("insert실패:"+se);
		}finally {
			try {
				if (pstmt2!=null) pstmt2.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//Vector<Vector> memberVector1 = new Vector<Vector>(); //회원 ID로 조회
	//Vector<Object> memberVector2 = new Vector<Object>(); // 회원 핸드폰 번호로 조회
	void selectMember(int mode, String keyword) { //완성 후 시간이 남으면 stetment로 해보기
		if (mode==1) {
			sql = "select MID, MPW, MNO, MNAME, MPOINT from MEMBER where MID="+keyword+"";
		} else {
			sql = "select MID, MPW, MNO, MNAME, MPOINT from MEMBER where MPHNO='"+keyword+"'";
		}
		ResultSet rs = null;
		Vector<Object> MemberV = null;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			//System.out.println("test");
			while(rs.next()) {
				if(mode == 1) {
					MemberV = new Vector<Object>();
					MemberV.add(rs.getString(1));
					MemberV.add(rs.getString(2));
					MemberV.add(rs.getInt(3));
					MemberV.add(rs.getString(4));
					MemberV.add(rs.getInt(5));
					memberVector1.add(MemberV);
				} else {
					memberVector2.add(rs.getString(1));
					memberVector2.add(rs.getString(2));
					memberVector2.add(rs.getInt(3));
					memberVector2.add(rs.getString(4));
					memberVector2.add(rs.getInt(5));
				}
			}
		}catch(SQLException se) {
			System.out.println("오류 : " + se);
		}finally {
			try {
				if (rs!=null) rs.close();
				if (pstmt1!=null) pstmt1.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	// 1 = 주문대기 2=결제완료 3= 결제 취소
	void updateOSTATE(int ostate, String ono) {
		sql = "update ORDERS set OSTATE =? where ONO LIKE ? ";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setInt(1, ostate);
			pstmt2.setString(2, ""+ono+"%");
			int i =pstmt2.executeUpdate();
			System.out.println("test");
			if(i>0) {
				System.out.println("변경 성공");
				con.commit();
			}else {
				System.out.println("변경 실패");
				con.rollback();
			}
		}catch(SQLException se) {
			System.out.println("update실패:"+se);
		}finally {
			try {
				if (pstmt2!=null) pstmt2.close();
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
