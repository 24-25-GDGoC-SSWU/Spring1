package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else{
            em.merge(item);

            /*Item merge = em.merge(item);
            merge가 영속성 컨텍스트에서 관리되는 객체
            기존에 파라미터로 넘어온 item은 영속성 상태로 변하진 않음
            -> 더 쓸 일이 있으면 그 다음부터는 merge를 써야 함*/
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
