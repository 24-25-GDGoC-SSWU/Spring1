package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // ORDINAL이 디폴트임, Ordinal은 숫자 1,2,3,4로 들어감
    private DeliveryStatus status; //READY, COMP //ex) READY면 0이 들어가고, COMP면 1이 들어감
    // ex) READY, XXX, COMP 중간에 다른 상태가 들어가면 망함
    // -> 꼭 STRING으로 써야 함!! -> 중간에 뭐가 들어가도 순서에 의해서 밀리는 게 없음
}
