package com.team2.kiosk;

public interface OrderServer {
	
	void selectProduct(int mode, int no); //상품 데이터를 조회
	
	void insertProduct(int PNO, String PNAME, int PSAL, int PSTATE, int CNO); //새로운 상품 자바칩 프라프치노를 추가하기
	
	void deleteProduct(int PNO); //추가시킨 자바칩프라프치노에 문제가있어 삭제
	
	void insertCart(int CDNO, int PNO, int COP1, int COP2, int COP3, int COP4, int COP5); 
	// 장바구니번호, 유저ID, 상품번호, 옵션1(매장, 포장), 옵션2(아이스, 핫), 옵션3(미디움 / 라지), 옵션4(샷추가 / 추가안함), 옵션5(얼음 많이 / 얼음 조금/ 추가안함)
	
	void selectCart(); //현재 장바구니 테이블 조회
	
	void insertOrder(int cdno); //주문 테이블에 데이터 넣기
	
	void selectOrder(); //주문테이블 조회
	
	//void insertMember(); // 신규 회원가입
	
	//void deleteMember(); //회원탈퇴
	
	//void updateProduct(); //메뉴의 가격을 변경
	
	//void selectOrder1(); //주문테이블에서 주문번호별로 조회
	
	//void updateCart(); // 기존에 담은 장바구니에서 옵션을 변경
	
	//void deleteCart(); // 장바구니에서 선택했던 메뉴를 삭제

	//void insertCart1(); //장바구니 테이블에서 주문을 추가하면 초기화 될 메소드

	//void insertOrder1(); //주문 테이블에 데이터가 들어오면 장바구니를 초기화 시키기
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
