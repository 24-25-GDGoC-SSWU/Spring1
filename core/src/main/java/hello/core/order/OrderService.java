package hello.core.order;

public interface OrderService {
    // 클라이언트는 주문을 할 때 회원아이디, 상품명, 상품 가격을 파라미터로 넘겨야 함
    // -> 반환값으로 주문 결과를 받음
    Order createOrder(Long memberId, String itemName, int itemPrice); //return 값이 oreder다
}
