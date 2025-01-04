package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    // 양방향 연관관계 -> 연관관계 주인을 정해줘야 함
    @OneToMany(mappedBy = "member") // 주인이 아니라 거울임
    private List<Order> orders = new ArrayList<>();

}
