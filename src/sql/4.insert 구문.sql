--option
--11 ���� 12 ���� 
--21 ���̽� 22 ��
--31 �̵�� 32 ����(1000)
--41 ���߰�(500��) 42 �߰�����
--51�������� 52 �������� 53 ���þ���

--ORDERS 
--OSTATE 1 = �ֹ���� 2 = �ֹ������Ϸ�  3 = ���� ���
--PRODUCT
--PSTATE 1 = �Ǹ��� 2 = �ֵ�ƿ�

---------------------------------
-- �ֹ���ȣ , ȸ��ID, ��ǰ��ȣ, �ֹ��󼼹�ȣ
--���ϳ����� �������� �Ƹ޸�ī�� ����, ī��� ������ ��ٱ��Ͽ� ��Ҵ�.
insert into CART values(CART_SEQ.nextval,111, 12, 21, 31, 42, 53);
insert into CART values(CART_SEQ.nextval,112, 12, 21, 31, 42, 53);
-- ���ϳ����� �ֹ��� ������ ī���Ϳ� ���޵Ǿ���.(�ֹ��Ϸ����)
insert into ORDERS values(220629-001-01, 'abc123', 111, ORDERS_SEQ.nextval, sysdate, 2, 3200, 12, 21, 31, 42, 53);
insert into ORDERS values(220629-001-02, 'abc123', 112, ORDERS_SEQ.nextval, sysdate, 2, 3200, 12, 21, 31, 42, 53);
-- �ֹ��� ������ CART(��ٱ���)�� ����ش�.������ �ʱ�ȭ
delete cart;
drop sequence CART_SEQ;
create sequence CART_SEQ increment by 1 start with 1 nocache;



-- �ɿ��澾�� �ݼ��ƾ��� ī�信 ���� �ɿ��澾�� ���������� �����߶ǿ� ������� ���� ������ ��ī���� ������ġ��,
-- �ݼ��ƾ��� ȭ��Ʈ���ݸ���ī�� ��ī�� ���ī����� ����Ļ�� ���� �ֹ��Ͽ���.

insert into CART values(CART_SEQ.nextval, 121, 11, 21, 31, 42, 53);
insert into CART values(CART_SEQ.nextval, 117, 11, 21, 31, 42, 53);

commit;