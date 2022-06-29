public interface OrderServer {
	
	void selectProducts(); // 상품 테이블 조회 C
	
	void selectProduct(int category); //상품 테이블에서 카테고리별로 조회 C
	
	void insertCart(); //장바구니 테이블에 데이터 넣기
	
	void selectCart(); //장바구니 테이블 조회
	
	void insertOrder(); //주문 테이블에 데이터 넣기
	
	void selectOrder(); //주문테이블 조회
	
	void insertProduct(); //상품 테이블에 상품 추가하기
	
	void deleteProduct(); //상품 테이블에 상품 삭제하기
	
	void insertMember(); // 신규 회원가입
	
	void deleteMember(); //회원탈퇴
	
	void updateProduct(); //메뉴의 가격을 변경
	
	void selectOrder1(); //주문테이블에서 주문번호별로 조회
	
	void updateCart(); // 기존에 담은 장바구니에서 옵션을 변경
	
	void deleteCart(); // 장바구니에서 선택했던 메뉴를 삭제
	
	void updateProduct1(); //판매중이던 메뉴 중 하나를 솔드아웃으로 바꾸기
	
	void insertCart1(); //장바구니 테이블에서 주문을 추가하면 초기화 될 메소드

	void insertOrder1(); //주문 테이블에 데이터가 들어오면 장바구니를 초기화 시키기
	/*
	insert into ORDERS values (
	주문번호, 회원ID, 상품번호, 주문상세번호, 주문시간, 주문상태, 상품수량, 상품가격
	, 옵션번호1, 옵션번호2, 옵션번호3, 옵션번호4, 옵션번호5
	);
	
	insert into ORDERS values (
	ONO, MID, PNO, ODNO, ODATE, OSTATE, PCOUNT, PSAL
	, COP1, COP2, COP3, COP4, COP5
	);
	*/
}
