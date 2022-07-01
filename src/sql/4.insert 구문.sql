--option
--11 매장 12 포장 
--21 아이스 22 핫
--31 미디움 32 라지(1000)
--41 샷추가(500원) 42 추가안함
--51얼음조금 52 얼음많이 53 선택안함

--ORDERS 
--OSTATE 1 = 주문대기 2 = 주문결제완료  3 = 결제 취소
--PRODUCT
--PSTATE 1 = 판매중 2 = 솔드아웃

---------------------------------
-- 주문번호 , 회원ID, 상품번호, 주문상세번호
--김하나씨는 포장으로 아메리카노 한잔, 카페라떼 한잔을 장바구니에 담았다.
insert into CART values(CART_SEQ.nextval,111, 12, 21, 31, 42, 53);
insert into CART values(CART_SEQ.nextval,112, 12, 21, 31, 42, 53);
-- 김하나씨가 주문한 내역이 카운터에 전달되었다.(주문완료상태)
insert into ORDERS values(220629-001-01, 'abc123', 111, ORDERS_SEQ.nextval, sysdate, 2, 3200, 12, 21, 31, 42, 53);
insert into ORDERS values(220629-001-02, 'abc123', 112, ORDERS_SEQ.nextval, sysdate, 2, 3200, 12, 21, 31, 42, 53);
-- 주문이 들어갔으니 CART(장바구니)를 비워준다.시퀀스 초기화
delete cart;
drop sequence CART_SEQ;
create sequence CART_SEQ increment by 1 start with 1 nocache;



-- 심영득씨와 금성아씨는 카페에 들어와 심영득씨는 에스프레소 마끼야또와 프레즐과 에그 베이컨 과카몰리 샌드위치를,
-- 금성아씨는 화이트초콜릿모카와 마카롱 허니카라멜을 매장식사로 같이 주문하였다.

insert into CART values(CART_SEQ.nextval, 121, 11, 21, 31, 42, 53);
insert into CART values(CART_SEQ.nextval, 117, 11, 21, 31, 42, 53);

commit;