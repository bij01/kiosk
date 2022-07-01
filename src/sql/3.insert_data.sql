--회원 데이터 추가

insert into MEMBER values('coffe25', 'coffe234', MEMBER_SEQ.nextval,'카페인','010-4252-3906', 80000);
insert into MEMBER values('kiosk', 'kiosk1', MEMBER_SEQ.nextval, '키오스크', '010-5252-5252', 0);
insert into MEMBER values('abc123', 'a1234', MEMBER_SEQ.nextval,'김하나','010-2225-2225', 3000);
insert into MEMBER values('rorex11', 'popo14', MEMBER_SEQ.nextval,'강루리','010-2341-2525', 11000);
insert into MEMBER values('apsl776', 'rod4545', MEMBER_SEQ.nextval,'머머리','010-2512-9989', 8700);
insert into MEMBER values('polo1', 'roll1029', MEMBER_SEQ.nextval,'상하이','010-2626-1111', 6200);
insert into MEMBER values('nomoney1', 'tnrgml11', MEMBER_SEQ.nextval,'김숙희','010-4848-2325', 1500);
insert into MEMBER values('lock0098', 'dudgml098', MEMBER_SEQ.nextval,'하영희','010-6060-1925', 0);
insert into MEMBER values('poweregg1', 'egg1234', MEMBER_SEQ.nextval,'강아린','010-8741-1981', 7680);
insert into MEMBER values('robit133', 'beewnfl1', MEMBER_SEQ.nextval,'배주리','010-6666-1182', 8000);
insert into MEMBER values('dnjsals98', 'dh9988', MEMBER_SEQ.nextval,'오원민','010-1812-4789', 1000);
insert into MEMBER values('tellbi123', 'power0', MEMBER_SEQ.nextval,'이윤성','010-2324-8785', 64000);
insert into MEMBER values('simse233', 'gml85754', MEMBER_SEQ.nextval,'심희서','010-1984-6035', 80000);
insert into MEMBER values('8897fnfl', 'fnfl99', MEMBER_SEQ.nextval,'김루리','010-0707-1666', 6500);
insert into MEMBER values('heart1', 'loki85', MEMBER_SEQ.nextval,'심득서','010-8542-9998', 76500);
insert into MEMBER values('eden2513', 'eden22', MEMBER_SEQ.nextval,'박인서','010-5552-1111', 11500);
insert into MEMBER values('mimo12', 'emo41', MEMBER_SEQ.nextval,'한혜성','010-2002-9631', 22000);
insert into MEMBER values('heart20', 'loki885', MEMBER_SEQ.nextval,'심영득','010-7861-0635', 34800);
insert into MEMBER values('Ion88', 'molri1', MEMBER_SEQ.nextval,'강호철','010-0728-6035', 2000);
insert into MEMBER values('gold102', 'asc888w', MEMBER_SEQ.nextval,'금성아','010-3452-9696', 7700);
insert into MEMBER values('dydtjr123', 'dragon09', MEMBER_SEQ.nextval,'김용석','010-8741-2547', 6500);
insert into MEMBER values('stone34', 'mouse12', MEMBER_SEQ.nextval,'석구안','010-6666-3397', 8000);
insert into MEMBER values('sigur87', 'gur88976', MEMBER_SEQ.nextval,'조시혁','010-7837-6347', 64000);
insert into MEMBER values('anna123', 'loiur33', MEMBER_SEQ.nextval,'서무시','010-1104-1207', 3000);


-- 카테고리 데이터 추가
insert into CATEGORY values(11 , '커피');
insert into CATEGORY values(22 , '음료');
insert into CATEGORY values(33 , '디저트');

-- 상품 데이터 추가
insert into PRODUCT values(111, '아메리카노', 3200, 1, 11);
insert into PRODUCT values(112, '카페라떼', 3700, 1, 11);
insert into PRODUCT values(113, '바닐라라떼', 3900, 1, 11);
insert into PRODUCT values(114, '카푸치노', 3700, 1, 11);
insert into PRODUCT values(115, '카페모카', 3900, 1, 11);
insert into PRODUCT values(116, '카라멜마끼야또', 3900, 1, 11);
insert into PRODUCT values(117, '화이트초콜릿모카', 3900, 1, 11);
insert into PRODUCT values(118, '민트모카', 4200, 1, 11);
insert into PRODUCT values(119, '에스프레소', 2900, 1, 11);
insert into PRODUCT values(120, '에스프레소 콘파냐', 3200, 1, 11);
insert into PRODUCT values(121, '에스프레소 마끼야또', 3200, 1, 11);
insert into PRODUCT values(122, '연유 카페라떼', 3800, 1, 11);
insert into PRODUCT values(123, '콜드브루 라떼', 4200, 1, 11);
insert into PRODUCT values(124, '콜드브루 화이트비엔나', 4500, 1, 11);
insert into PRODUCT values(125, '콜드브루 니트로', 3900, 1, 11);
insert into PRODUCT values(126, '콜드브루 아메리카노', 3700, 1, 11);

insert into PRODUCT values(221, '자몽 에이드', 3800, 1, 22);
insert into PRODUCT values(222, '레몬 에이드', 3800, 1, 22);
insert into PRODUCT values(223, '라임 에이드', 3800, 1, 22);
insert into PRODUCT values(224, '청포도 에이드', 3800, 1, 22);
insert into PRODUCT values(225, '썬라이즈 펀치', 3900, 1, 22);
insert into PRODUCT values(226, '오션 펀치', 3900, 1, 22);
insert into PRODUCT values(227, '선셋 펀치', 3900, 1, 22);
insert into PRODUCT values(228, '오리진 쉐이크', 4300, 1, 22);
insert into PRODUCT values(229, '초코쿠키 쉐이크', 4500, 1, 22);
insert into PRODUCT values(230, '딸기 쉐이크', 4800, 1, 22);
insert into PRODUCT values(231, '아포가토 오리지널', 4500, 1, 22);
insert into PRODUCT values(232, '아포가토 콜드브루 바닐라모카', 4800, 1, 22);
insert into PRODUCT values(233, '자몽네이블오렌지', 4200, 1, 22);
insert into PRODUCT values(234, '석류 애플라임', 4200, 1, 22);
insert into PRODUCT values(235, '레몬차', 3800, 1, 22);
insert into PRODUCT values(236, '밀크티', 3800, 1, 22);

insert into PRODUCT values(331, '(와플)플레인', 2300, 1, 33);
insert into PRODUCT values(332, '(브레드)허니카라멜', 4600, 1, 33);
insert into PRODUCT values(333, '프레즐', 2300, 1, 33);
insert into PRODUCT values(334, '크림치즈(포션)', 800, 1, 33);
insert into PRODUCT values(335, '(베이글)플레인', 1900, 1, 33);
insert into PRODUCT values(336, '에그 베이컨 과카몰리 샌드위치', 4500, 1, 33);
insert into PRODUCT values(337, '마카롱', 2200, 1, 33);
insert into PRODUCT values(338, '초코티라미수', 3900, 1, 33);
insert into PRODUCT values(339, '수플레', 3900, 1, 33);
insert into PRODUCT values(340, '초콜릿브라우니',  4200, 1, 33);

--11 매장 12 포장 
--21 아이스 22 핫
--31 미디움 32 라지(1000)
--41 샷추가(500원) 42 추가안함
--51얼음조금 52 얼음많이 53 선택안함
insert into OPTIONS values(11, 0);
insert into OPTIONS values(12, 0);
insert into OPTIONS values(21, 0);
insert into OPTIONS values(22, 0);
insert into OPTIONS values(31, 0);
insert into OPTIONS values(32, 1000);
insert into OPTIONS values(41, 500);
insert into OPTIONS values(42, 0);
insert into OPTIONS values(51, 0);
insert into OPTIONS values(52, 0);
insert into OPTIONS values(53, 0);

commit;