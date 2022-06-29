import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OrderServerImpl implements OrderServer {
	static final String URL="jdbc:oracle:thin:@localhost:1521:JAVA";
	static final String USR="team2";
	static final String PWD="java123";
	Connection con;
	PreparedStatement pstmt1, pstmt2;
	Statement stmt;
	String sql = "";
	
	void init() {
		connectDB();
		//selectProducts();
		//selectProduct(33);
		//insertCart();
		//selectCart();
		selectOrder();
	}
	//DB와의 연결
	void connectDB() {
		String info1 = URL;
		String info2 = USR;
		String info3 = PWD;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(info1, info2, info3);
			stmt = con.createStatement();
			System.out.println("연결 성공");
		}catch(ClassNotFoundException cnfe) {
			System.out.println("드라이버 로딩 실패:" + cnfe);
		}catch(SQLException se) {
			System.out.println("연결 실패: "+ se);
		}
	}
	
	public void selectProducts() { // 상품 테이블 조회
			sql = "select * from PRODUCT";
			ResultSet rs = null;
			try {
				pstmt1 = con.prepareStatement(sql);
				rs = pstmt1.executeQuery();
				while(rs.next()) {
					System.out.println(rs.getInt(1)+ " "+ rs.getString("PNAME") + " " + rs.getInt(3) +"원 " + rs.getInt(4) +" "+ rs.getInt(5));
				}
				//con.commit();
			}catch(SQLException se) {
				System.out.println("상품을 찾을 수 없습니다." + se);
			}
		}
	
	@Override
	//String SQL1= "select * from PRODUCT where CNO="+category+"";
	public void selectProduct(int category) {
		sql = "select * from PRODUCT where CNO="+category+"";
		ResultSet rs = null;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+ " "+ rs.getString("PNAME") + " " + rs.getInt(3) +"원 " + rs.getInt(4) +" "+ rs.getInt(5));
			}
			//con.commit();
		}catch(SQLException se) {
			System.out.println("상품을 찾을 수 없습니다." + se);
		}
	}


	@Override 
	public void insertCart() {
		sql = "insert into CART values(1,?,?,12,21,32,42,53)";
		try {
			pstmt2 = con.prepareStatement(sql);
			pstmt2.setString(1,"heart1");
			pstmt2.setInt(2, 116);
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
		sql = "select * from CART";
		ResultSet rs = null;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getInt(1)+" "+ rs.getString(2)+" "+ rs.getInt(3)
				+" "+ rs.getInt(4)+" "+ rs.getInt(5)+" "+ rs.getInt(6)+" "+ rs.getInt(7)
				+" "+ rs.getInt(8));
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
		sql = "select * from Order";
		ResultSet rs = null;
		try {
			pstmt1 = con.prepareStatement(sql);
			rs = pstmt1.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+ rs.getString(2)+" "+ rs.getInt(3)
				+" "+ rs.getInt(4)+" "+ rs.getString(5)+" "+ rs.getInt(6)+" "+ rs.getInt(7)
				+" "+ rs.getInt(8)+" "+ rs.getInt(9)+" "+ rs.getInt(10)+" "+ rs.getInt(11)
				+" "+ rs.getInt(12));
			}
			
		}catch(SQLException se) {
			System.out.println("상품을 찾을 수 없습니다." + se);
		}
	}

	public void selectOrder1() { //주문테이블에서 주문번호별로 조회
		
	}
	
	public void insertProduct() {//상품 테이블에 상품 추가하기
	}
	
	public void deleteProduct() { //상품 테이블에 상품 삭제하기
		
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
