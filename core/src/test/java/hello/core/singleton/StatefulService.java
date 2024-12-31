package hello.core.singleton;

public class StatefulService {
    private int price; //상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price; //여기가 문제!

        //의도 -> 주문을 하면 값을 price에 저장하는 그런 코드를 짜겠다

        return price; // 무상태로 설계하기 위해선 void -> int로 변경후 반환 값으로 넘기면 됨
    }
    public int getPrice() {
        //price 값을 꺼내옴
        return price;
    }
}
