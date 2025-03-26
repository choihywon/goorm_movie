package com.shakkib.netflixclone.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testAdminEntity() {
        // Given: 테스트할 Admin 엔티티 생성
        Admin admin = new Admin("admin@example.com","관리자");

        // When: Admin 엔티티를 영속화하고 flush로 DB에 반영
        em.persist(admin);
        em.flush();
        em.clear(); // 영속성 컨텍스트 초기화

        // Then: 다시 조회하여 데이터 검증
        Admin foundAdmin = em.find(Admin.class, admin.getId());
        assertThat(foundAdmin).isNotNull();
        assertThat(foundAdmin.getEmail()).isEqualTo("admin@example.com");
        assertThat(foundAdmin.getName()).isEqualTo("관리자");
    }
}
