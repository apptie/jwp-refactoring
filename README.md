# 키친포스

## 용어 사전

| 한글명 | 영문명 | 설명 |
| --- | --- | --- |
| 상품 | product | 메뉴를 관리하는 기준이 되는 데이터 |
| 메뉴 그룹 | menu group | 메뉴 묶음, 분류 |
| 메뉴 | menu | 메뉴 그룹에 속하는 실제 주문 가능 단위 |
| 메뉴 상품 | menu product | 메뉴에 속하는 수량이 있는 상품 |
| 금액 | amount | 가격 * 수량 |
| 주문 테이블 | order table | 매장에서 주문이 발생하는 영역 |
| 빈 테이블 | empty table | 주문을 등록할 수 없는 주문 테이블 |
| 주문 | order | 매장에서 발생하는 주문 |
| 주문 상태 | order status | 주문은 조리 ➜ 식사 ➜ 계산 완료 순서로 진행된다. |
| 방문한 손님 수 | number of guests | 필수 사항은 아니며 주문은 0명으로 등록할 수 있다. |
| 단체 지정 | table group | 통합 계산을 위해 개별 주문 테이블을 그룹화하는 기능 |
| 주문 항목 | order line item | 주문에 속하는 수량이 있는 메뉴 |
| 매장 식사 | eat in | 포장하지 않고 매장에서 식사하는 것 |

## 요구 사항

- 상품 (Product)
  - 기능
    - 상품을 등록할 수 있다.
    - 등록한 상품을 조회할 수 있다.
  - 제약조건
    - 상품의 이름이 존재해야 한다.
    - 상품의 가격이 존재해야 한다.
    - 상품의 가격은 0원 이하일 수 없다.
    - 상품의 이름은 255글자를 넘을 수 없다.
- 메뉴 상품 (MenuProduct)
  - 제약조건
    - 메뉴 상품을 등록한 메뉴가 존재해야 한다.
    - 메뉴 상품에 등록할 상품이 존재해야 한다.
    - 메뉴 상품에 등록한 상품 수량이 존재해야 한다.
    - 메뉴 상품에 등록한 상품 총 금액의 합은 양수여야 한다.
- 메뉴 (Menu)
  - 기능
    - 메뉴를 등록할 수 있다.
      - 등록할 때 메뉴에 포함되는 메뉴 상품이 같이 등록된다.
    - 등록한 메뉴를 조회할 수 있다.
      - 조회할 때 메뉴에 등록한 메뉴 상품이 같이 조회된다.
  - 제약조건
    - 메뉴의 가격이 모든 메뉴 상품의 가격 합보다 클 수 없다.
    - 메뉴를 등록할 메뉴 그룹이 존재해야 한다.
    - 메뉴의 가격은 0원 이하일 수 없다.
    - 메뉴의 이름을 255글자를 넘을 수 없다.
- 메뉴 그룹 (MenuGroup)
  - 기능
    - 상품 그룹을 등록할 수 있다.
    - 등록한 상품 그룹을 조회할 수 있다.
  - 제약조건
    - 메뉴 그룹의 이름이 존재해야 한다.
    - 메뉴 그룹의 이름은 255글자를 넘을 수 없다.
- 주문 상태 (OrderStatus)
  - 제약조건
    - 조리 ➜ 식사 ➜ 계산 완료 순서로 진행해야 한다.
- 주문 항목 (OrderLineItem)
  - 제약조건
    - 주문 항목을 등록할 주문이 존재해야 한다.
- 주문 (Order)
  - 기능
    - 주문을 등록할 수 있다.
      - 주문 등록 시 주문 항목이 같이 등록된다.
    - 등록한 주문을 조회할 수 있다.
      - 주문 조회 시 주문에 등록한 주문 항목이 같이 조회된다.
    - 주문의 상태를 변경할 수 있다.
  - 제약조건
    - 주문이 등록한 주문 항목의 개수는 1 이상이어야 한다.
    - 주문이 등록한 주문 항목에 지정된 상품은 존재해야 한다.
    - 주문을 등록할 주문 테이블은 존재해야 한다.
    - 주문을 등록할 주문 테이블은 비어 있어서는 안 된다.
    - 주문의 상태가 계산 완료인 경우 상태를 변경할 수 없다.
- 주문 테이블 (OrderTable)
  - 기능
    - 주문 테이블을 등록할 수 있다.
    - 등록한 주문 테이블 목록을 조회할 수 있다.
    - 주문 테이블이 비었는지 여부를 변경할 수 있다.
    - 주문 테이블의 방문한 손님의 수를 변경할 수 있다.
  - 제약조건
    - 비었는지 여부를 변경할 때 변경하고자 하는 주문 테이블이 존재해야 한다.
    - 비었는지 여부를 변경할 때 변경하고자 하는 주문 테이블의 상태가 계산 완료여야 한다.
    - 방문한 손님의 수를 변경하고자 할 때 변경하고자 하는 주문 테이블이 존재해야 한다.
    - 방문한 손님의 수를 변경하고자 할 때 손님의 수는 0 미만이 될 수 없다.
    - 방문한 손님의 수를 변경하고자 할 때 변경하고자 하는 주문 테이블이 비어 있어서는 안 된다.
