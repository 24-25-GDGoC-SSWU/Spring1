package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired
    EntityManager em;

    @Test
    public void updateTest() throws Exception {
        Book book = em.find(Book.class, 1L);

        //TX
        /*변경본에 대해서 JPA가 찾아서 업테이트 쿼리를 자동으로 생성해서
        데이터베이스에 반영함 -> 변경 감지*/
        book.setName("asdasg");

        //변경감지 == dirty checking
        //TX commit
    }
}