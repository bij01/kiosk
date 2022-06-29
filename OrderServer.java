public interface OrderServer {
	
	void selectProduct(); //상품 테이블 조회
	
	void selectCart(); //장바구니 테이블 조회
	
	void selectOrder(); //주문테이블 조회
	
	void insertCart(); //장바구니 테이블에 데이터 넣기

	void insertOrder(); //주문 테이블에 데이터 넣기
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
