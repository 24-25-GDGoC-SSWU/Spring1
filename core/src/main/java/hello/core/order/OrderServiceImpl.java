package hello.core.order;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//주문이 들어오면 주문을 만들어서 반환해주기만 하면 됨
// -> 주문 생성 요청이 오면 회원 정보를 먼저 조회하고 그 다음에 할인 정책에다가 회원을 그냥 넘김
//-> 최종적으로 할인된 가격을 받음 -> 최근 생성된 주문을 반환
@Component
public class OrderServiceImpl implements OrderService{
    //두 개를 다 받음
    //인터페이스에만 의존하고 구체적인 클래스에 대해서는 모름 -> DIP를 매우 잘 지키고 있음
    // 변경사항이 있을 때 AppConfig만 변경하면 되고 해당 코드는 바꿀 필요 x -> OCP 만족
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired // 생성자 위에 Autowired 해줌 -> 스프링이 생성하 ㄹ때 자동으로 애플리케이션 컨텍스트에서 얘를 주입하고 디스카운트 폴리쉬도 주입해줌
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    // 설계 잘 됨 -> orderService 입장에서는 할인에 대해서 잘 모르니 너 알아서 해라 결과만 나한테 던져줘라고 설계한 것
    //-> 단일 책임 원칙을 잘 지킨 것 (변경이 필요하면 할인 쪽만 고치면 되고 주문 쪽은 건드릴 필요 없으니까)
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    //private final MemberRepository memberRepository; 얘랑 달라야 하는 게 아닌가?
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
